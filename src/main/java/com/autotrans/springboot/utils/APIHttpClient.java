package com.autotrans.springboot.utils;

import com.alibaba.druid.support.json.JSONUtils;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.common.HttpMethods;
import com.arronlong.httpclientutil.common.HttpResult;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;


public class APIHttpClient {
 
	// 接口地址
	private static String apiURL = "http://joemercy.xicp.net:25698/api/v1.0/orders?client=account:320";
	private Log logger = LogFactory.getLog(this.getClass());
	private static final String pattern = "yyyy-MM-dd HH:mm:ss:SSS";
	private HttpClient httpClient = null;
	private HttpPost method = null;
	private long startTime = 0L;
	private long endTime = 0L;
	private int status = 0;
 
	/**
	 * 接口地址
	 * 
	 * @param url
	 */
	public APIHttpClient(String url) {
 
		if (url != null) {
			this.apiURL = url;
		}
		if (apiURL != null) {
			httpClient = new DefaultHttpClient();
			method = new HttpPost(apiURL);
 
		}
	}

	/**
	 * 调用 API
	 * 
	 * @param parameters
	 * @return
	 */
	public String post(String parameters) {
		String body = null;
		logger.info("parameters:" + parameters);
 
		if (method != null & parameters != null
				&& !"".equals(parameters.trim())) {
			try {
 
				// 建立一个NameValuePair数组，用于存储欲传送的参数
				method.addHeader("Content-type","application/json; charset=utf-8");
				method.setHeader("Accept", "application/json");
				method.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));
				startTime = System.currentTimeMillis();
				HttpResponse response = httpClient.execute(method);
				endTime = System.currentTimeMillis();
				int statusCode = response.getStatusLine().getStatusCode();
				logger.info("statusCode:" + statusCode);
				logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed:" + response.getStatusLine());
					status = 1;
				}
 
				// Read the response body
				body = EntityUtils.toString(response.getEntity());
 
			} catch (IOException e) {
				// 网络错误
				status = 3;
			} finally {
				logger.info("调用接口状态：" + status);
			}
 
		}
		return body;
	}
	public static HttpResult sendPost(String url,String json) throws HttpProcessException, FileNotFoundException {
		Header[] headers = HttpHeader.custom()
				.contentType("application/json")
				.accept("application/json")
				.acceptCharset("utf-8")
				.build();
		HCB hcb = HCB.custom()
				.timeout(5000) //超时
				.retry(0)		//重试0次
				;
		HttpClient client = hcb.build();
		HttpConfig config = HttpConfig.custom()
				.method(HttpMethods.POST)
				.headers(headers)	//设置headers，不需要时则无需设置
				.url(url)	          //设置请求的url
				.json(json)          //设置请求参数，没有则无需设置
				.encoding("utf-8") //设置请求和返回编码，默认就是Charset.defaultCharset()

				.client(client);    //如果只是简单使用，无需设置，会自动获取默认的一个client对象
//		String result = HttpClientUtil.post(config);
		HttpResult result = HttpClientUtil.sendAndGetResp(config);
		return result;
	}
	public static HttpResult delete(String url,Map map) throws HttpProcessException, FileNotFoundException {
		Header[] headers = HttpHeader.custom()
				.accept("application/json")
				.acceptCharset("utf-8")
				.build();
		HCB hcb = HCB.custom()
				.timeout(5000) //超时
				;
		HttpClient client = hcb.build();
		HttpConfig config = HttpConfig.custom()
				.method(HttpMethods.DELETE)
				.headers(headers)	//设置headers，不需要时则无需设置
				.url(url)	          //设置请求的url
				.map(map)
				.encoding("utf-8") //设置请求和返回编码，默认就是Charset.defaultCharset()
				.client(client);    //如果只是简单使用，无需设置，会自动获取默认的一个client对象
//		String result =  HttpClientUtil.delete(config);
		HttpResult respResult = HttpClientUtil.sendAndGetResp(config);
//		System.out.println("返回结果：\n"+respResult.getResult());
//		System.out.println("返回resp-header："+respResult.getRespHeaders());//可以遍历
//		System.out.println("返回具体resp-header："+respResult.getHeaders("Date"));
//		System.out.println("返回StatusLine对象："+respResult.getStatusLine());
//		System.out.println("返回StatusCode："+respResult.getStatusCode());
//		System.out.println("返回HttpResponse对象）（可自行处理）："+respResult.getResp());
		return respResult;
	}
	public static void main(String[] args) throws HttpProcessException, FileNotFoundException {
		String url = "http://192.168.130.139:8888/api/v1.0/orders/3232";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("client", "*:43429990");
		System.out.println(delete(url,map));

	}
	public static void main2(String[] args) throws HttpProcessException, FileNotFoundException {
		String url = "http://192.168.130.139:8888/api/v1.0/delete/976955318?client=*:43429990";

		//最简单的使用：
		String html = HttpClientUtil.get(HttpConfig.custom().url(url));
		System.out.println("---------------"+html);

		//---------------------------------
		//			【详细说明】
		//--------------------------------

		//插件式配置Header（各种header信息、自定义header）
		Header[] headers = HttpHeader.custom()
				.contentType("application/json")
				.accept("application/json")
				.acceptCharset("utf-8")
//				.userAgent("javacl")
//				.other("customer", "自定义")
				.build();

		//插件式配置生成HttpClient时所需参数（超时、连接池、ssl、重试）
		HCB hcb = HCB.custom()
//				.timeout(1000) //超时
//				.pool(100, 10) //启用连接池，每个路由最大创建10个链接，总连接数限制为100个
//				.sslpv(SSLs.SSLProtocolVersion.TLSv1_2) 	//设置ssl版本号，默认SSLv3，也可以调用sslpv("TLSv1.2")
//				.ssl()  	  	//https，支持自定义ssl证书路径和密码，ssl(String keyStorePath, String keyStorepass)
//				.retry(5)		//重试5次
				;

		HttpClient client = hcb.build();

//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("key1", "value1");
//		map.put("key2", "value2");
		Map map =new HashMap();
		map.put("action", "BUY");
		map.put("symbol", "000001");
		map.put("type", "LIMIT");
		map.put("priceType", 0);
		map.put("price", 90.00);
		map.put("amount", 1000);
		map.put("client","account:320");
		String msg=	JSONUtils.toJSONString(map);
		//插件式配置请求参数（网址、请求参数、编码、client）
		HttpConfig config = HttpConfig.custom()
				.method(HttpMethods.DELETE)
				.headers(headers)	//设置headers，不需要时则无需设置
				.url(url)	          //设置请求的url
				.json(msg)	          //设置请求参数，没有则无需设置
				.encoding("utf-8") //设置请求和返回编码，默认就是Charset.defaultCharset()
				.client(client)    //如果只是简单使用，无需设置，会自动获取默认的一个client对象
				//.inenc("utf-8")  //设置请求编码，如果请求返回一直，不需要再单独设置
				//.inenc("utf-8")	//设置返回编码，如果请求返回一直，不需要再单独设置
				//.json("json字符串")                          //json方式请求的话，就不用设置map方法，当然二者可以共用。
				//.context(HttpCookies.custom().getContext()) //设置cookie，用于完成携带cookie的操作
				//.out(new FileOutputStream("保存地址"))       //下载的话，设置这个方法,否则不要设置
				//.files(new String[]{"d:/1.txt","d:/2.txt"}) //上传的话，传递文件路径，一般还需map配置，设置服务器保存路径
				;


		//使用方式：
//		String result1 = HttpClientUtil.get(config);     //get请求
		String result2 = HttpClientUtil.post(config);    //post请求
//		System.out.println("get========"+result1);
		System.out.println("post======="+result2);

		//HttpClientUtil.down(config);                   //下载，需要调用config.out(fileOutputStream对象)
		//HttpClientUtil.upload(config);                 //上传，需要调用config.files(文件路径数组)

		//如果指向看是否访问正常
		//String result3 = HttpClientUtil.head(config); // 返回Http协议号+状态码
		//int statusCode = HttpClientUtil.status(config);//返回状态码

		//[新增方法]sendAndGetResp，可以返回原生的HttpResponse对象，
		//同时返回常用的几类对象：result、header、StatusLine、StatusCode
		HttpResult respResult = HttpClientUtil.sendAndGetResp(config);
		System.out.println("返回结果：\n"+respResult.getResult());
		System.out.println("返回resp-header："+respResult.getRespHeaders());//可以遍历
		System.out.println("返回具体resp-header："+respResult.getHeaders("Date"));
		System.out.println("返回StatusLine对象："+respResult.getStatusLine());
		System.out.println("返回StatusCode："+respResult.getStatusCode());
		System.out.println("返回HttpResponse对象）（可自行处理）："+respResult.getResp());
	}
//	public static void main(String[] args) {
//		Map map =new HashMap();
//		map.put("action", "BUY");
//		map.put("symbol", "000001");
//		map.put("type", "LIMIT");
//		map.put("priceType", 0);
//		map.put("price", 90.00);
//		map.put("amount", 1000);
//		map.put("client","account:320");
//		String msg=	JSONUtils.toJSONString(map);
//		System.out.println(msg);
//		APIHttpClient ac = new APIHttpClient(apiURL);
//
//		System.out.println(ac.post(msg));
//	}
 
	/**
	 * 0.成功 1.执行方法失败 2.协议错误 3.网络错误
	 * 
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
 
	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
 
	/**
	 * @return the startTime
	 */
	public long getStartTime() {
		return startTime;
	}
 
	/**
	 * @return the endTime
	 */
	public long getEndTime() {
		return endTime;
	}
}
