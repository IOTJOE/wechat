package com.baolishang.springboot.controllers;

import com.alibaba.druid.support.json.JSONUtils;
import com.arronlong.httpclientutil.common.HttpResult;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.baolishang.springboot.models.TraderByDay;
import com.baolishang.springboot.models.TraderLog;
import com.baolishang.springboot.models.TraderUser;
import com.baolishang.springboot.services.TraderByDayService;
import com.baolishang.springboot.services.TraderLogService;
import com.baolishang.springboot.services.TraderUserService;
import com.baolishang.springboot.utils.APIHttpClient;
import com.baolishang.springboot.utils.SafeUtils;
import com.csvreader.CsvReader;
import org.json.CDL;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.baolishang.springboot.utils.APIHttpClient.delete;
import static com.baolishang.springboot.utils.DateFormat.getSqlDate;
import static com.baolishang.springboot.utils.DateFormat.getStringDateShort;


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
        System.out.println("request===="+msg);
        HttpResult result = null ;
        try{

            result = APIHttpClient.sendPost(url,msg);
            System.out.println("result===="+result.getResult());
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
            System.out.println(jsonMap);

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(jsonMap);//返回json数值
            out.flush();
            out.close();
    }
    @RequestMapping(value = "/deleteOrder")

    public void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, HttpProcessException {
        String userAccount = request.getParameter("userAccount");
        String orderId = request.getParameter("orderId");
        String url = request.getParameter("url")+orderId;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("client", "*:"+userAccount);
        HttpResult respResult  = delete(url,map);
        System.out.println("返回结果：\n"+respResult.getResult());
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
        System.out.println(jsonMap);

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
            Map<String, Object> returnMap = new HashMap<String, Object>();
            //先从数据库读,读不到就读excel
            List<TraderByDay> listResult = traderByDayService.selectByUserAccountAndTransDate(userAccount, getStringDateShort());
            net.sf.json.JSONArray json = net.sf.json.JSONArray.fromObject(listResult);
            String jsonMap = json.toString();
            System.out.println(jsonMap);
            if (listResult.size() == 0) {
                log.info("第一次读取" + getSqlDate());
                //转为json
//            jsonMap = getJSONFromFile("/Users/joe/Downloads/" + fileName + ".csv", "\\,");
                saveTraderByDay(userAccount, "/Users/joe/Downloads/" + fileName + ".csv");
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



}
