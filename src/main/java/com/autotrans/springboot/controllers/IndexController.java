package com.autotrans.springboot.controllers;

import com.alibaba.druid.support.json.JSONUtils;
import com.arronlong.httpclientutil.common.HttpResult;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.autotrans.springboot.models.TraderByDay;
import com.autotrans.springboot.models.TraderLog;
import com.autotrans.springboot.models.TraderUser;
import com.autotrans.springboot.services.TraderByDayService;
import com.autotrans.springboot.services.TraderLogService;
import com.autotrans.springboot.services.TraderUserService;
import com.autotrans.springboot.services.TransService;
import com.autotrans.springboot.utils.APIHttpClient;
import com.autotrans.springboot.utils.SafeUtils;
import com.csvreader.CsvReader;
import org.json.CDL;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.*;

import static com.autotrans.springboot.utils.APIHttpClient.delete;
import static com.autotrans.springboot.utils.DateFormat.getSqlDate;
import static com.autotrans.springboot.utils.DateFormat.getStringDateShort;


/**
 * Created by dongzd on 2017/12/1.
 * 首页
 */

@Controller
public class IndexController {
    private static final Logger log = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private TraderByDayService traderByDayService;
    @Autowired
    private TraderLogService traderLogService;
    @Autowired
    private TraderUserService traderUserService;
    @Value("${filePath}")
    private String filePath;
    @Autowired
    private TransService transService;
    @RequestMapping(value = "transPost")
    public void transPost(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String action = request.getParameter("action");
        String userAccount = request.getParameter("userAccount");
        int marketType = SafeUtils.getInt(request.getParameter("marketType"));
        int direction = SafeUtils.getInt(request.getParameter("direction"));
         String symbol = request.getParameter("symbol");
         String type = request.getParameter("type");
         int priceType = SafeUtils.getInt(request.getParameter("priceType"));
         BigDecimal price= SafeUtils.getBigDecimal(request.getParameter("price"));
         int amount = SafeUtils.getInt(request.getParameter("amount"));
         String url = request.getParameter("url");
        log.info("下单请求"+url);

        Map map =new HashMap();

        map.put("action", action);
        map.put("symbol",symbol);
        if(action.equals("IPO")){
            map.put("amountProportion","ALL");
        }else {
            map.put("type", type);
            map.put("priceType", priceType);
            map.put("price", price);
            map.put("amount", amount);
        }

        String msg=	JSONUtils.toJSONString(map);
//        APIHttpClient ac = new APIHttpClient(url);
//        String result =ac.post(msg);
        log.info("request===="+msg);
        HttpResult result = null ;
        try{

            result = APIHttpClient.sendPost(url,msg);
            log.info("result===="+result.getResult());
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

        TraderLog traderLog = new TraderLog();
        traderLog.setUserAccount(userAccount);
        traderLog.setTransDate(getStringDateShort());
        traderLog.setAction(action);
        traderLog.setMarketType(marketType);
        traderLog.setStockId(symbol);
        traderLog.setDirection(direction);
        traderLog.setPriceType(priceType);
        traderLog.setType(type);
        traderLog.setPrice(SafeUtils.getString(price));
        traderLog.setAmount(amount);
        traderLog.setTransId(SafeUtils.getString(resultTequest.get("id")));
        traderLog.setSource(SafeUtils.getString(resultTequest.get("source")));
        traderLog.setMessage(SafeUtils.getString(resultTequest.get("message")));
        if(SafeUtils.getString(resultTequest.get("id")).equals("")){
            traderLog.setStatus("下单失败");
        }else{
            traderLog.setStatus("下单成功");
        }
        traderLogService.save(traderLog);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(result.getResult());//返回json数值
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/getUser")

    public void getUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

            //放入需要返回的值
            Map<String, Object> returnMap = new HashMap<String, Object>();
            //先从数据库读,读不到就读excel
            List<TraderUser> listResult = traderUserService.selectAllTraderUser();
            net.sf.json.JSONArray json = net.sf.json.JSONArray.fromObject(listResult);
            String jsonMap = json.toString();
            log.info(jsonMap);

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(jsonMap);//返回json数值
            out.flush();
            out.close();
    }
    @RequestMapping(value = "/autoTrans")
    public void autoBuy(HttpServletResponse response) throws IOException {
        transService.autoTrans(1);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("-----autoTrans-----");//返回json数值
        out.flush();
        out.close();
    }
    @RequestMapping(value = "/autoTransETF")
    public void autoBuyETF(HttpServletResponse response) throws IOException {
        transService.autoTrans(2);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("-----autoTrans-----");//返回json数值
        out.flush();
        out.close();
    }
    //自动卖出,限价
    @RequestMapping(value = "/autoSold")
    public void autoSoldLimit(HttpServletResponse response) throws IOException, HttpProcessException {
        transService.autoSold("LIMIT",1);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("-----autoSold-----");//返回json数值
        out.flush();
        out.close();
    }
    @RequestMapping(value = "/autoSoldETF")
    public void autoSoldLimitETF(HttpServletResponse response) throws IOException, HttpProcessException {
        transService.autoSold("LIMIT",2);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("-----autoSold-----");//返回json数值
        out.flush();
        out.close();
    }
//    public void autoTrans(){
//        List<TraderUser> listResult = traderUserService.selectAllTraderUser();
//        for (TraderUser user : listResult){
//
//            if(user.getStatus()==1 && user.getAutoBuyStatus()==1) {
//                log.info(user.getFileName()+"--"+user.getUserAccount());
//                List<TraderByDay> listTraderByDays = readListTraderByDay(user.getUserAccount(),user.getFileName());
//                for (TraderByDay traderByDay : listTraderByDays) {
//                    log.info(traderByDay.toString());
//                    //执行买入操作,调用买入方法
//                    if(traderByDay.getStatus()==1){//1:初始化.2:下单成功3:交易结束
//                        order(user,traderByDay);
//                    }
//                }
//            }
//        }
//    }
//    /**
//     * 自动卖出
//     */
//    public void autoSold(){
//        List<TraderUser> listResult = traderUserService.selectAllTraderUser();
//        for (TraderUser user : listResult){
//            log.info(user.getFileName()+"--"+user.getUserAccount());
//            if(user.getStatus()==1&&user.getAutoSoldStatus()==1) {
//                List<TraderByDay> listTraderByDays = readListTraderByDay(user.getUserAccount(),user.getFileName());
//                //1.撤单
//                for (TraderByDay traderByDay : listTraderByDays) {
//                    log.info(traderByDay.toString());
//                    if(traderByDay.getStatus()!=0 && traderByDay.getDirection()==2){//0:下单失败,1:初始化.2:下单成功3:已撤单4:未成交5:部分成交6已成交
//                        try {
//                            try {
//                                deleteOrder(user,traderByDay);
//                            } catch (FileNotFoundException e) {
//                                e.printStackTrace();
//                            }
//                        } catch (HttpProcessException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }
//
//                //TODO:查询当前用户的持仓情况
//                String url = user.getTransIp()+"/api/v1.0/orders?client=*:"+user.getUserAccount();
//                JSONObject columsObject = null;
//                try {
//                    columsObject = new JSONObject(HttpClientUtil.get(HttpConfig.custom().url(url))).getJSONObject("dataTable");
//                } catch (HttpProcessException e) {
//                    e.printStackTrace();
//                }
//                JSONArray columsArr1 =columsObject.getJSONArray("columns");
//                JSONArray columsArr2 =columsObject.getJSONArray("rows");
//                for (TraderByDay traderByDay : listTraderByDays) {
//                    log.info("更新数据traderByDay的成交数量,撤单数量,成交状态"+traderByDay.toString());
//                    if(traderByDay.getStatus()!=0 && traderByDay.getDirection()==2){//0:下单失败,1:初始化.2:下单成功3:已撤单4:未成交5:部分成交6已成交
//                        //TODO:2.更新数据traderByDay的成交数量,撤单数量,成交状态
//
//                        int orderIndex = 0;
//                        int successAmountIndex = 0;
//                        int revokeAmountIndex = 0;
//
//                        for(int i = 0;i<columsArr1.length();i++){
//                            if(SafeUtils.getString(columsArr1.get(i)).equals("合同编号")||SafeUtils.getString(columsArr1.get(i)).equals("委托编号")){
//                                orderIndex=i;
//                            }
//                            if(SafeUtils.getString(columsArr1.get(i)).equals("成交数量")){
//                                successAmountIndex=i;
//                            }
//                            if(SafeUtils.getString(columsArr1.get(i)).equals("委托数量")){
//                                revokeAmountIndex=i;
//                            }
//                        }
//
//                        for(int i = 0;i<columsArr2.length();i++){
//
//                            String orderId = SafeUtils.getString(columsArr2.getJSONArray(i).get(orderIndex));
//                            int successAmount = SafeUtils.getInt(columsArr2.getJSONArray(i).get(successAmountIndex));
//                            int revokeAmount = SafeUtils.getInt(columsArr2.getJSONArray(i).get(revokeAmountIndex))-SafeUtils.getInt(columsArr2.getJSONArray(i).get(successAmountIndex));
//                            if(traderByDay.getTransId().equals(orderId)){
//                                traderByDay.setSuccessAmount(successAmount);
//                                traderByDay.setRevokeAmount(revokeAmount);
//                                traderByDayService.save(traderByDay);
//                                //3.执卖出操作
//                                sold(user,traderByDay);
//                            }
//                        }
//                    }
//                }
//
//            }
//        }
//    }
//    public void deleteOrder(TraderUser user,TraderByDay traderByDay) throws HttpProcessException, FileNotFoundException {
//        String userAccount = traderByDay.getUserAccount();
//        String orderId = traderByDay.getTransId();
//        String url = user.getTransIp()+"/api/v1.0/orders/"+orderId;
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("client", "*:" + userAccount);
//        HttpResult respResult = APIHttpClient.delete(url, map);
//        log.info("返回结果：\n" + respResult.getResult());
//        int status;
//        String desc;
//        if(respResult.getStatusCode() == 200){
//            desc="撤单成功";
//            status=traderByDay.getStatus();
//
//        }else{
//            desc="撤单失败";
//            status=traderByDay.getStatus();
//        }
//        int  successAmount=traderByDay.getSuccessAmount();
//        int  revokeAmount=traderByDay.getRevokeAmount();
//        String transId=traderByDay.getTransId();
//        String source="";
//        String message=SafeUtils.getString(respResult.getResult());
//        long id = traderByDay.getId();
//        traderByDayService.updateTransStatus(status,successAmount,revokeAmount,transId, source,message,desc,id);
//
//    }
//    /**
//     * 市价卖出
//     * @param user
//     * @param traderByDay
//     */
//    public void sold(TraderUser user,TraderByDay traderByDay){
//
//        String action="";
//        if (traderByDay.getDirection() == 2) {
//            action = "SELL";//direction方向：1买 2到期卖3:挂单预卖
//        }
//        String userAccount = traderByDay.getUserAccount();
//        int marketType = traderByDay.getMarketType();
//        String symbol = traderByDay.getStockId();
//
//        int priceType = SafeUtils.getInt(traderByDay.getPriceType());
//        String type = "MARKET";
//        if(marketType==1){//沪市市价
//            type="MARKET";
//            priceType=6;
//        }
//        else if(marketType==2){//深市市价
//            type="MARKET";
//            priceType=4;
//        }
//        BigDecimal price= SafeUtils.getBigDecimal(traderByDay.getPrice());
//        int amount = SafeUtils.getInt(traderByDay.getRevokeAmount());//撤单的数量
//        String url = user.getTransIp()+"/api/v1.0/orders?client=*:" +userAccount;
//
//        Map map =new HashMap();
//        map.put("action", action);
//        map.put("symbol",symbol);
//        map.put("type", type);
//        map.put("priceType", priceType);
//        map.put("price", price);
//        map.put("amount", amount);
//        String msg=	JSONUtils.toJSONString(map);
//        log.info("request===="+msg);
//        HttpResult result = null ;
//        try{
//            result = APIHttpClient.sendPost(url,msg);
//            log.info("result===="+result.getResult());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        JSONObject json=new JSONObject(result.getResult());
//        Map<String,Object> resultTequest=new HashMap<String, Object>();
//        Iterator it = json.keys();
//        while (it.hasNext()) {
//            String key = (String) it.next();
//            Object value = json.get(key);
//            resultTequest.put(key, value);
//        }
//        int status;
//        String desc;
//        if(SafeUtils.getString(resultTequest.get("id")).equals("")){
//            desc="市价下单失败";
//            status=0;
//
//        }else{
//            desc="市价下单成功";
//            status=2;
//        }
//        int  successAmount=0;
//        int  revokeAmount=0;
//        String transId=SafeUtils.getString(resultTequest.get("id"));
//        String source=SafeUtils.getString(resultTequest.get("source"));
//        String message=SafeUtils.getString(resultTequest.get("message"));
//        long id = traderByDay.getId();
//        traderByDayService.updateTransStatus(status,successAmount,revokeAmount,transId, source,message,desc,id);
//
//    }
//    /**
//     * 下单
//     * @param user
//     * @param traderByDay
//     */
//    public void order(TraderUser user,TraderByDay traderByDay){
//        String action;
//        if (traderByDay.getDirection() == 1) {
//            action = "BUY";//direction方向：1买 2到期卖3:挂单预卖
//        } else {
//            action = "SELL";
//        }
//        String userAccount = traderByDay.getUserAccount();
//        int marketType = traderByDay.getMarketType();
//        String symbol = traderByDay.getStockId();
//
//        int priceType = SafeUtils.getInt(traderByDay.getPriceType());
//        String type = "";
////        if(priceType==0){
//        type="LIMIT";
//        priceType=0;
////        }
////        else if((priceType==1 || priceType==6 ) && marketType==1){//沪市市价
////            type="MARKET";
////            priceType=6;
////        }
////        else if((priceType==1 || priceType==4)&& marketType==2){//深市市价
////            type="MARKET";
////            priceType=4;
////        }
//        BigDecimal price= SafeUtils.getBigDecimal(traderByDay.getPrice());
//        int amount = SafeUtils.getInt(traderByDay.getAmount());
//        String url = user.getTransIp()+"/api/v1.0/orders?client=*:" +userAccount;
//
//        Map map =new HashMap();
//        map.put("action", action);
//        map.put("symbol",symbol);
//        map.put("type", type);
//        map.put("priceType", priceType);
//        map.put("price", price);
//        map.put("amount", amount);
//        String msg=	JSONUtils.toJSONString(map);
//        log.info("request===="+msg);
//        HttpResult result = null ;
//        try{
//            result = APIHttpClient.sendPost(url,msg);
//            log.info("result===="+result.getResult());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        JSONObject json=new JSONObject(result.getResult());
//        Map<String,Object> resultTequest=new HashMap<String, Object>();
//        Iterator it = json.keys();
//        while (it.hasNext()) {
//            String key = (String) it.next();
//            Object value = json.get(key);
//            resultTequest.put(key, value);
//        }
//        int status;
//        String desc;
//        if(SafeUtils.getString(resultTequest.get("id")).equals("")){
//            desc="下单失败";
//            status=0;
//
//        }else{
//            desc="下单成功";
//            status=2;
//        }
//        int  successAmount=0;
//        int  revokeAmount=0;
//        String transId=SafeUtils.getString(resultTequest.get("id"));
//        String source=SafeUtils.getString(resultTequest.get("source"));
//        String message=SafeUtils.getString(resultTequest.get("message"));
//        long id = traderByDay.getId();
//        traderByDayService.updateTransStatus(status,successAmount,revokeAmount,transId, source,message,desc,id);
//
//    }
    @RequestMapping(value = "/deleteOrder")

    public void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, HttpProcessException {
        String userAccount = request.getParameter("userAccount");
        String orderId = request.getParameter("orderId");
        String url = request.getParameter("url")+orderId;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("client", "*:"+userAccount);
        HttpResult respResult  = delete(url,map);
        log.info("返回结果：\n"+respResult.getResult());
        if(respResult.getStatusCode()==200){
            traderLogService.updateStatus("撤单成功",orderId);
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(respResult.getResult());//返回json数值
        out.flush();
        out.close();

    }
    @RequestMapping(value = "/getTraderLog")

    public void getTraderLog(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userAccount = request.getParameter("userAccount");
        //放入需要返回的值
        Map<String, Object> returnMap = new HashMap<String, Object>();
        //先从数据库读,读不到就读excel
        List<TraderLog> listResult = traderLogService.selectByUserAccountAndTransDate(userAccount,getStringDateShort());
        net.sf.json.JSONArray json = net.sf.json.JSONArray.fromObject(listResult);
        String jsonMap = json.toString();
        log.info(jsonMap);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonMap);//返回json数值
        out.flush();
        out.close();

    }
    @RequestMapping(value = "/readCsv")

    public void readCsv(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = request.getParameter("fileName");
        String userAccount = request.getParameter("userAccount");
        if(!userAccount.equals("")) {
            //放入需要返回的值
            //先从数据库读,读不到就读excel
            List<TraderByDay> listResult = traderByDayService.selectByUserAccountAndTransDate(userAccount, getStringDateShort());
            net.sf.json.JSONArray json = net.sf.json.JSONArray.fromObject(listResult);
            String jsonMap = json.toString();
            log.info(jsonMap);
            if (listResult.size() == 0) {
                log.info("第一次读取" + getSqlDate());
                //转为json
//            jsonMap = getJSONFromFile("/Users/joe/Downloads/" + fileName + ".csv", "\\,");
                saveTraderByDay(userAccount, filePath + fileName);
                listResult = traderByDayService.selectByUserAccountAndTransDate(userAccount, getStringDateShort());
                json = net.sf.json.JSONArray.fromObject(listResult);
                jsonMap = json.toString();
            }
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(jsonMap);//返回json数值
            out.flush();
            out.close();
        }
    }
    public  List<TraderByDay> readListTraderByDay(String userAccount,String fileName) {
        List<TraderByDay> listResult= null;
        if (!userAccount.equals("")) {
            //放入需要返回的值
            //先从数据库读,读不到就读excel
            listResult = traderByDayService.selectByUserAccountAndTransDate(userAccount, getStringDateShort());
//            net.sf.json.JSONArray json = net.sf.json.JSONArray.fromObject(listResult);
//            String jsonMap = json.toString();
//            log.info(jsonMap);
            if (listResult.size() == 0) {
                log.info("第一次读取" + getSqlDate());
                //转为json
//            jsonMap = getJSONFromFile("/Users/joe/Downloads/" + fileName + ".csv", "\\,");
                saveTraderByDay(userAccount, "/Users/joe/Nustore Files/量化团队共享/利曦资产/" + fileName);
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
                traderByDay=new TraderByDay();
                traderByDay.setUserAccount(userAccount);
                traderByDay.setTransDate(getStringDateShort());
                traderByDay.setStockId(r.get("StockId"));
                traderByDay.setPrice(r.get("Price"));
                traderByDay.setAmount(SafeUtils.getInteger(r.get("Amount")));
                traderByDay.setDirection(SafeUtils.getInteger(r.get("Direction")));
                traderByDay.setPriceType(SafeUtils.getInteger(r.get("PriceType")));
                traderByDay.setMarketType(SafeUtils.getInteger(r.get("MarketType")));
                traderByDay.setStatus(1);
                traderByDayService.save(traderByDay);
            }
            r.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @RequestMapping(value = "/saveCsv",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)

    public void saveCsv(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {

        String fileName = request.getParameter("fileName");;
        String fileList = request.getParameter("fileList");
        //后台接收
        org.json.JSONArray jsonArray = new org.json.JSONArray(fileList);
            String csv = CDL.toString(jsonArray);
        // 文件输出流
        BufferedWriter fileOutputStream = null;
        // 生成json格式文件
        try {
            //
            File file = new File("/Users/joe/Downloads/receive_"+fileName+".csv");
            if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
                file.getParentFile().mkdirs();
            }
            if (file.exists()) { // 如果已存在,删除旧文件
                file.delete();
            }
            file.createNewFile();

            // 实例化文件输出流
            fileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "GB2312"));
            fileOutputStream.write(csv);
            fileOutputStream.flush();
        } catch (Exception e) {
            log.error("生成数据文件是报错！", e);
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        uploadPingFile();
        uploadFileServerList();
    }



    /**
     * 上传心跳文件
     */
    public static void uploadPingFile(){
        try {

//            writeFiles(SafeUtils.getCurrentUnixTime(),"/Users/joe/Downloads/"+"ping_"+SafeUtils.getCurrentTimeStr("yyyyMMdd")+".dat");
            int str=SafeUtils.getCurrentUnixTime();
            String path = "/Users/joe/Downloads/yinhe/"+"ping_"+SafeUtils.getCurrentTimeStr("yyyyMMdd")+".dat";

            OutputStream os = new FileOutputStream(path);
            PrintWriter pw=new PrintWriter(os);
            pw.print(str);
            pw.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 上传服务端列表文件
     */
    public static void uploadFileServerList(){
        //TODO:从数据库获取最新更新时间

        //TODO:写入文件
        try {
//            writeFiles(SafeUtils.getCurrentUnixTime(),"/Users/joe/Downloads/"+"file_server_list_"+SafeUtils.getCurrentTimeStr("yyyyMMdd")+".dat");

            String path = "/Users/joe/Downloads/yinhe/"+"file_server_list_"+SafeUtils.getCurrentTimeStr("yyyyMMdd")+".dat";

            OutputStream os = new FileOutputStream(path);
            PrintWriter pw=new PrintWriter(os);
            //TODO:日期都要去数据库取
            int unixTime1=SafeUtils.getCurrentUnixTime();
            int unixTime2=SafeUtils.getCurrentUnixTime();
            String date1=SafeUtils.getCurrentTimeStr("yyyyMMdd");
            String date2=SafeUtils.getCurrentTimeStr("yyyyMMdd");
            pw.println("parentorder_req_"+date1+".dat"+"|"+unixTime1);
            pw.println("query_"+date2+".dat"+"|"+unixTime2);
            pw.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeFiles1() throws IOException {
        int number=1;
        OutputStream os = new FileOutputStream("/Users/joe/Downloads/output.txt");
        PrintWriter pw=new PrintWriter(os);
        for(int i=0;i<10;i++) {
            String s=""+number;
//            pw.println(s);//每输入一个数据，自动换行，便于我们每一行每一行地进行读取
            pw.print(s+"|");//不会自动换行，必要时可以自己添加分隔符
            number++;
        }
        pw.close();
        os.close();
    }
    public static void readFiles1() throws IOException {
        int num=0;
        char[] buf=new char[1024];
        //依次读取一个字符，读到最后没有了就返回-1。有分隔符号
        ArrayList<String>stringlist=new ArrayList();//储存待读取的字符串
        FileReader fr = new FileReader("/Users/joe/Downloads/output.txt");
        while((num=fr.read(buf))!=-1) {
            System.out.println(new String(buf,0,num));;
        }
        //对字符串进一步处理
        String temp="";
        for(int i=0;i<buf.length;i++) {
            if(buf[i]=='|') {//注意文件中的分隔符是中文还是英文字符
                stringlist.add(temp);
                temp="";
            }else {
                temp+=buf[i];
            }
        }
    }

    /**
     * 写入单行
     * @param str
     * @param path
     * @throws IOException
     */
    public static void writeFiles(Object str,String path) throws IOException {
        int number=1;
        OutputStream os = new FileOutputStream(path);
        PrintWriter pw=new PrintWriter(os);
        pw.print(str);
//        for(int i=0;i<10;i++) {
//            String s=""+number;
//            pw.println(s);//每输入一个数据，自动换行，便于我们每一行每一行地进行读取
////            pw.print(s+"|");//不会自动换行，必要时可以自己添加分隔符
//            number++;
//        }
        pw.close();
        os.close();
    }
    public static void readFiles() throws IOException {
        int position=0;
        String[] bufstring=new String[1024];
        //打开带读取的文件
        BufferedReader br = new BufferedReader(new FileReader("/Users/joe/Downloads/output.txt"));
        String line=null;
        while((line=br.readLine())!=null) {
            bufstring[position]=line;
            position++;
        }
        br.close();//关闭文件
        for(int i=0;i<position;i++) {
            System.out.println(bufstring[i]);
        }
    }
}
