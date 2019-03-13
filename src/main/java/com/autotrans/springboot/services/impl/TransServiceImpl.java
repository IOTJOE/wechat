package com.autotrans.springboot.services.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpResult;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.autotrans.springboot.models.TraderByDay;
import com.autotrans.springboot.models.TraderUser;
import com.autotrans.springboot.services.TraderByDayService;
import com.autotrans.springboot.services.TraderUserService;
import com.autotrans.springboot.services.TransService;
import com.autotrans.springboot.utils.APIHttpClient;
import com.autotrans.springboot.utils.SafeUtils;
import com.csvreader.CsvReader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.autotrans.springboot.utils.DateFormat.getSqlDate;
import static com.autotrans.springboot.utils.DateFormat.getStringDateShort;

@Service
public class TransServiceImpl implements TransService {
    private static final Logger logger = LoggerFactory.getLogger(TransServiceImpl.class);
    static boolean caching = false;
    @Autowired
    private TraderByDayService traderByDayService;
    @Autowired
    private TraderUserService traderUserService;
    @Override
    public void autoTrans(int transType) {
        Thread t =Thread.currentThread();
//        if(!caching) {
//            caching = true;
            List<TraderUser> listResult = traderUserService.selectAllTraderUser();
            for (TraderUser user : listResult) {

                if (user.getStatus() == 1 && user.getAutoBuyStatus() == 1) {
                    logger.info(user.getFileName() + "--" + user.getUserAccount());
                    List<TraderByDay> listTraderByDays = readListTraderByDay(user,transType);
                    for (TraderByDay traderByDay : listTraderByDays) {
                        logger.info(traderByDay.toString());
                        //执行买入操作,调用买入方法
                        if (traderByDay.getStatus() == 1) {//1:初始化.2:下单成功3:交易结束
                            order(user, traderByDay);
                        }
                    }
                }
            }
//            caching = false;
//        }else{
//            logger.info("程序正在执行");
//        }
    }
    @Override
    public void autoSold(String type,int transType) {
        List<TraderUser> listResult = traderUserService.selectAllTraderUser();
        for (TraderUser user : listResult){
            logger.info(user.getFileName()+"--"+user.getUserAccount());
            if(user.getStatus()==1&&user.getAutoSoldStatus()==1) {
                List<TraderByDay> listTraderByDays = readListTraderByDay(user,transType);
                //1.撤单
                for (TraderByDay traderByDay : listTraderByDays) {
                    logger.info(traderByDay.toString());
                    if(traderByDay.getStatus()!=0 && traderByDay.getDirection()==2){//0:下单失败,1:初始化.2:下单成功3:已撤单4:未成交5:部分成交6已成交
                        try {
                            try {
                                deleteOrder(user,traderByDay);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        } catch (HttpProcessException e) {
                            e.printStackTrace();
                        }

                    }
                }

                //TODO:查询当前用户的持仓情况
                String url = user.getTransIp()+"/api/v1.0/orders?client=*:"+user.getUserAccount();
                JSONObject columsObject = null;
                try {
                    columsObject = new JSONObject(HttpClientUtil.get(HttpConfig.custom().url(url))).getJSONObject("dataTable");
                } catch (HttpProcessException e) {
                    e.printStackTrace();
                }
                JSONArray columsArr1 =columsObject.getJSONArray("columns");
                JSONArray columsArr2 =columsObject.getJSONArray("rows");
                for (TraderByDay traderByDay : listTraderByDays) {
                    logger.info("更新数据traderByDay的成交数量,撤单数量,成交状态"+traderByDay.toString());
                    if(traderByDay.getStatus()!=0 && traderByDay.getDirection()==2){//0:下单失败,1:初始化.2:下单成功3:已撤单4:未成交5:部分成交6已成交
                        //TODO:2.更新数据traderByDay的成交数量,撤单数量,成交状态

                        int orderIndex = 0;
                        int successAmountIndex = 0;
                        int revokeAmountIndex = 0;

                        for(int i = 0;i<columsArr1.length();i++){
                            if(SafeUtils.getString(columsArr1.get(i)).equals("合同编号")||SafeUtils.getString(columsArr1.get(i)).equals("委托编号")){
                                orderIndex=i;
                            }
                            if(SafeUtils.getString(columsArr1.get(i)).equals("成交数量")){
                                successAmountIndex=i;
                            }
                            if(SafeUtils.getString(columsArr1.get(i)).equals("委托数量")){
                                revokeAmountIndex=i;
                            }
                        }

                        for(int i = 0;i<columsArr2.length();i++){

                            String orderId = SafeUtils.getString(columsArr2.getJSONArray(i).get(orderIndex));
                            int successAmount = SafeUtils.getInt(columsArr2.getJSONArray(i).get(successAmountIndex));
                            int revokeAmount = SafeUtils.getInt(columsArr2.getJSONArray(i).get(revokeAmountIndex))-SafeUtils.getInt(columsArr2.getJSONArray(i).get(successAmountIndex));
                            if(traderByDay.getTransId().equals(orderId)){
                                traderByDay.setSuccessAmount(successAmount);
                                traderByDay.setRevokeAmount(revokeAmount);
                                traderByDayService.save(traderByDay);
                                //3.执卖出操作
                                if(type.equals("MARKET")) {
                                    sold(user, traderByDay);
                                }
                                else if(type.equals("LIMIT")) {
                                    soldLimit(user, traderByDay);
                                }
                            }
                        }
                    }
                }

            }
        }
    }
    public void deleteOrder(TraderUser user,TraderByDay traderByDay) throws HttpProcessException, FileNotFoundException {
        String userAccount = traderByDay.getUserAccount();
        String orderId = traderByDay.getTransId();
        String url = user.getTransIp()+"/api/v1.0/orders/"+orderId+"?client=account:"+userAccount;
        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("client", "*:" + userAccount);
        HttpResult respResult = APIHttpClient.delete(url, map);
        logger.info("返回结果：\n" + respResult.getResult());
        int status;
        String desc;
        if(respResult.getStatusCode() == 200){
            desc="撤单成功";
            status=traderByDay.getStatus();

        }else{
            desc="撤单失败";
            status=traderByDay.getStatus();
        }
        int  successAmount=traderByDay.getSuccessAmount();
        int  revokeAmount=traderByDay.getRevokeAmount();
        String transId=traderByDay.getTransId();
        String source="";
        String message=SafeUtils.getString(respResult.getResult());
        long id = traderByDay.getId();
        traderByDayService.updateTransStatus(status,successAmount,revokeAmount,transId, source,message,desc,id);

    }

    /**
     * 市价卖出
     * @param user
     * @param traderByDay
     */
    public void sold(TraderUser user,TraderByDay traderByDay){

        String action="";
        if (traderByDay.getDirection() == 2) {
            action = "SELL";//direction方向：1买 2到期卖3:挂单预卖
        }
        String userAccount = traderByDay.getUserAccount();
        int marketType = traderByDay.getMarketType();
        String symbol = traderByDay.getStockId();

        int priceType = SafeUtils.getInt(traderByDay.getPriceType());
        String type = "MARKET";
        if(marketType==1){//沪市市价
            type="MARKET";
            priceType=6;
        }
        else if(marketType==2){//深市市价
            type="MARKET";
            priceType=4;
        }
        BigDecimal price= SafeUtils.getBigDecimal(traderByDay.getPrice());
        int amount = SafeUtils.getInt(traderByDay.getRevokeAmount());//撤单的数量
        String url = user.getTransIp()+"/api/v1.0/orders?client=*:" +userAccount;

        Map map =new HashMap();
        map.put("action", action);
        map.put("symbol",symbol);
        map.put("type", type);
        map.put("priceType", priceType);
        map.put("price", price);
        map.put("amount", amount);
        String msg=	JSONUtils.toJSONString(map);
        logger.info("request===="+msg);
        HttpResult result = null ;
        try{
            result = APIHttpClient.sendPost(url,msg);
            logger.info("result===="+result.getResult());
        }catch (Exception e){
            e.printStackTrace();
        }
        JSONObject json=new JSONObject(result.getResult());
        Map<String,Object> resultTequest=new HashMap<String, Object>();
        Iterator it = json.keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            Object value = json.get(key);
            resultTequest.put(key, value);
        }
        int status;
        String desc;
        if(SafeUtils.getString(resultTequest.get("id")).equals("")){
            desc="市价下单失败";
            status=0;

        }else{
            desc="市价下单成功";
            status=2;
        }
        int  successAmount=0;
        int  revokeAmount=0;
        String transId=SafeUtils.getString(resultTequest.get("id"));
        String source=SafeUtils.getString(resultTequest.get("source"));
        String message=SafeUtils.getString(resultTequest.get("message"));
        long id = traderByDay.getId();
        traderByDayService.updateTransStatus(status,successAmount,revokeAmount,transId, source,message,desc,id);

    }

    /**
     * 获取当前价格跌1%的价格
     * @param urls
     * @param symbol
     * @return
     */
    public String getSoldPrice(String urls,String symbol){
        String url = urls+"/api/v1.0/quotes/"+symbol;
        JSONObject columsObject = null;
                    try {
            columsObject = new JSONObject(HttpClientUtil.get(HttpConfig.custom().url(url)));
        } catch (HttpProcessException e) {
            e.printStackTrace();
        }
        DecimalFormat df = new DecimalFormat("#.00");
        Double preClose =columsObject.getDouble("preClose");
        Double lowLimit =columsObject.getDouble("lowLimit");

        Double sold=preClose*0.99;
        if(sold<lowLimit)
            sold=lowLimit;
        String soldPrice=df.format(sold);
        System.out.println("股票代码:"+symbol+"卖出价"+soldPrice+"当前价"+preClose+"跌停价"+lowLimit);
        return soldPrice;
    }
    /**
     * 限价卖出
     * @param user
     * @param traderByDay
     */
    public void soldLimit(TraderUser user,TraderByDay traderByDay){

        String action="";
        if (traderByDay.getDirection() == 2) {
            action = "SELL";//direction方向：1买 2到期卖3:挂单预卖
        }
        String userAccount = traderByDay.getUserAccount();
//        int marketType = traderByDay.getMarketType();
        String symbol = traderByDay.getStockId();

        int priceType = 0;
        String type = "LIMIT";
        //TODO:获取价格

        String soldPrice=getSoldPrice(user.getTransIp(),symbol);


        int amount = SafeUtils.getInt(traderByDay.getRevokeAmount());//撤单的数量
        String url = user.getTransIp()+"/api/v1.0/orders?client=*:" +userAccount;

        Map map =new HashMap();
        map.put("action", action);
        map.put("symbol",symbol);
        map.put("type", type);
        map.put("priceType", priceType);
        map.put("price", SafeUtils.getBigDecimal(soldPrice));
        map.put("amount", amount);
        String msg=	JSONUtils.toJSONString(map);
        logger.info("request===="+msg);
        HttpResult result = null ;
        try{
            result = APIHttpClient.sendPost(url,msg);
            logger.info("result===="+result.getResult());
        }catch (Exception e){
            e.printStackTrace();
        }
        JSONObject json=null;
        try{
            json=new JSONObject(result.getResult());
        }catch (Exception e){
            e.printStackTrace();
        }
        Map<String,Object> resultTequest=new HashMap<String, Object>();
        Iterator it = json.keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            Object value = json.get(key);
            resultTequest.put(key, value);
        }
        int status;
        String desc;
        if(SafeUtils.getString(resultTequest.get("id")).equals("")){
            desc="市价下单失败";
            status=0;

        }else{
            desc="市价下单成功";
            status=2;
        }
        int  successAmount=0;
        int  revokeAmount=0;
        String transId=SafeUtils.getString(resultTequest.get("id"));
        String source=SafeUtils.getString(resultTequest.get("source"));
        String message=SafeUtils.getString(resultTequest.get("message"));
        long id = traderByDay.getId();
        traderByDayService.updateTransStatus(status,successAmount,revokeAmount,transId, source,message,desc,id);

    }
    /**
     * 下单
     * @param user
     * @param traderByDay
     */
    public void order(TraderUser user,TraderByDay traderByDay){
        String action=null;
        if (traderByDay.getDirection() == 1) {
            action = "BUY";//direction方向：1买 2到期卖3:挂单预卖;4:融资买入;5:卖券还款;6:融券卖出;7:买券还款;8:直接还款;9:直接还券
        } else if(traderByDay.getDirection() == 2 || traderByDay.getDirection() == 3){
            action = "SELL";
        }else if(traderByDay.getDirection() == 4){
            action = "BUY_ON_MARGIN";
        }else if(traderByDay.getDirection() == 5){
            action = "SELL_THEN_REPAY";
        }else if(traderByDay.getDirection() == 6){
            action = "SELL_ON_MARGIN";
        }else if(traderByDay.getDirection() == 7){
            action = "BUY_THEN_REPAY";
        }else if(traderByDay.getDirection() == 8){
            action = "REPAY_CASH";
        }else if(traderByDay.getDirection() == 9){
            action = "REPAY_SEC";
        }
        String userAccount = traderByDay.getUserAccount();
        int marketType = traderByDay.getMarketType();
        String symbol = traderByDay.getStockId();

        int priceType = SafeUtils.getInt(traderByDay.getPriceType());
        String type = "";
//        if(priceType==0){
        type="LIMIT";
        priceType=0;
//        }
//        else if((priceType==1 || priceType==6 ) && marketType==1){//沪市市价
//            type="MARKET";
//            priceType=6;
//        }
//        else if((priceType==1 || priceType==4)&& marketType==2){//深市市价
//            type="MARKET";
//            priceType=4;
//        }
        BigDecimal price= SafeUtils.getBigDecimal(traderByDay.getPrice());
        int amount = SafeUtils.getInt(traderByDay.getAmount());
        String url = user.getTransIp()+"/api/v1.0/orders?client=*:" +userAccount;

        Map map =new HashMap();
        map.put("action", action);
        map.put("symbol",symbol);
        map.put("type", type);
        map.put("priceType", priceType);
        map.put("price", price);
        map.put("amount", amount);
        String msg=	JSONUtils.toJSONString(map);
        logger.info("request===="+msg);
        HttpResult result = null ;
        try{
            result = APIHttpClient.sendPost(url,msg);
            logger.info("result===="+result.getResult());
        }catch (Exception e){
            e.printStackTrace();
        }
        JSONObject json=new JSONObject(result.getResult());
        Map<String,Object> resultTequest=new HashMap<String, Object>();
        Iterator it = json.keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            Object value = json.get(key);
            resultTequest.put(key, value);
        }
        int status;
        String desc;
        if(SafeUtils.getString(resultTequest.get("id")).equals("")){
            desc="下单失败";
            status=0;

        }else{
            desc="下单成功";
            status=2;
        }
        int  successAmount=0;
        int  revokeAmount=0;
        String transId=SafeUtils.getString(resultTequest.get("id"));
        String source=SafeUtils.getString(resultTequest.get("source"));
        String message=SafeUtils.getString(resultTequest.get("message"));
        long id = traderByDay.getId();
        traderByDayService.updateTransStatus(status,successAmount,revokeAmount,transId, source,message,desc,id);

    }
    public  List<TraderByDay> readListTraderByDay(TraderUser user,int transType ) {
        String accountName = user.getName();
        String userAccount =user.getUserAccount();
        String fileName = user.getFileName();
        List<TraderByDay> listResult= null;
        if (!userAccount.equals("")) {
            //放入需要返回的值
            //先从数据库读,读不到就读excel
            listResult = traderByDayService.selectByUserAccountAndTransDate(userAccount, getStringDateShort());
//            net.sf.json.JSONArray json = net.sf.json.JSONArray.fromObject(listResult);
//            String jsonMap = json.toString();
//            logger.info(jsonMap);
            if (listResult.size() == 0 && transType==1) {
                logger.info("第一次读取" + getSqlDate());
                //转为json
//            jsonMap = getJSONFromFile("/Users/joe/Downloads/" + fileName + ".csv", "\\,");
                saveTraderByDay(userAccount, "/Users/joe/Nustore Files/量化团队共享/利曦资产/" + fileName);
//                saveTraderByDayForETF(accountName,userAccount, "/Users/joe/Nustore Files/量化团队共享/利曦资产/" + "TradePlan_ETF.csv");
                listResult = traderByDayService.selectByUserAccountAndTransDate(userAccount, getStringDateShort());

            }
            else if(transType==2) {
                saveTraderByDayForETF(accountName, userAccount, "/Users/joe/Nustore Files/量化团队共享/利曦资产/" + "TradePlan_ETF.csv");
                listResult = traderByDayService.selectByUserAccountAndTransDate(userAccount, getStringDateShort());
            }

        }
        return listResult;
    }
    public void saveTraderByDay(String userAccount,String csvFilePath){

        TraderByDay traderByDay = null;

        //生成CsvReader对象，以，为分隔符，GBK编码方式
        CsvReader r;
        try {
            r = new CsvReader(csvFilePath,',', Charset.forName("GBK"));
            //读取表头
            r.readHeaders();
            //逐条读取记录，直至读完
            while (r.readRecord()) {
                //TODO: 判断表格日期>=系统当前日期,就写入SQL
                if(conpareTwoDate(r.get("OrderDate"),getStringDateShort())>=0){
                traderByDay=new TraderByDay();
                traderByDay.setUserAccount(userAccount);
//                traderByDay.setTransDate(getStringDateShort());
                traderByDay.setTransDate(r.get("OrderDate"));
                traderByDay.setStockId(r.get("StockId"));
                traderByDay.setPrice(r.get("Price"));
                traderByDay.setAmount(SafeUtils.getInteger(r.get("Amount")));
                traderByDay.setDirection(SafeUtils.getInteger(r.get("Direction")));
                traderByDay.setPriceType(SafeUtils.getInteger(r.get("PriceType")));
                traderByDay.setMarketType(SafeUtils.getInteger(r.get("MarketType")));
                traderByDay.setBuyDate(r.get("BuyDate"));
                traderByDay.setSellDate(r.get("SellDate"));
                traderByDay.setStatus(1);
                traderByDayService.save(traderByDay);

                }
            }
            r.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void saveTraderByDayForETF(String accountName,String userAccount,String csvFilePath){

        TraderByDay traderByDay = null;

        //生成CsvReader对象，以，为分隔符，GBK编码方式
        CsvReader r;
        try {
            r = new CsvReader(csvFilePath,',', Charset.forName("GBK"));
            //读取表头
            r.readHeaders();
            //逐条读取记录，直至读完
            while (r.readRecord()) {
                traderByDay=new TraderByDay();
                if(r.get("AccountName").equals(accountName)&&conpareTwoDate(r.get("OrderDate"),getStringDateShort())>=0) {
                    //TODO: 判断表格日期>=系统当前日期,就写入SQL
                    traderByDay.setUserAccount(userAccount);
                    traderByDay.setTransDate(r.get("OrderDate"));
                    traderByDay.setStockId(r.get("StockId"));
                    traderByDay.setPrice(r.get("Price"));
                    traderByDay.setAmount(SafeUtils.getInteger(r.get("Amount")));
                    traderByDay.setDirection(SafeUtils.getInteger(r.get("Direction")));
                    traderByDay.setPriceType(SafeUtils.getInteger(r.get("PriceType")));
                    traderByDay.setMarketType(SafeUtils.getInteger(r.get("MarketType")));
                    traderByDay.setBuyDate(r.get("BuyDate"));
                    traderByDay.setSellDate(r.get("SellDate"));
                    traderByDay.setStatus(1);
                    traderByDayService.save(traderByDay);
                }
            }
            r.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * time1>time2 数据日期>当前日期 返回 1
     * @param time1 //数据日期
     * @param time2 //系统当前日期
     * @return
     */
    private int conpareTwoDate(String time1,String time2){
        System.out.println(time1);
        System.out.println(time2);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = sdf1.parse(time1);
            Date date2 = sdf2.parse(time2);
            System.out.println(date1.compareTo(date2));
            return  date1.compareTo(date2);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }
}
