package com.stockorder.api;

public class StockOrderApi {
	
	static {
    	System.loadLibrary("StockOrderJni");
    }
	
	//1.交易API均是StockOrderJni.dll 桥接 Order.dll文件的导出函数，包括以下函数：
	/// 买入 Buy
	/// <param name="stkCode">股票代码 如:600000</param>
	/// <param name="vol">买入数量:股数</param>
	/// <param name="price">买入价格:如果是0按助手的设置价格下单</param>
	/// <param name="formulaNum">公式编号,和大智慧公式编号类似，只是一个标记,区分那个买入的</param>
	/// <param name="ZhuShouHao">助手编号，如果开了多个助手，可以指定编号</param>
	/// <returns>返回值无意义，可忽略</returns>
	static public  native int  Buy(String stkCode,  int vol,  float price, int formulaNum, int ZhuShouHao);

	/// 卖出 Sell
	/// <param name="stkCode">股票代码 如:600000</param>
	/// <param name="vol">卖出数量:股数</param>
	/// <param name="price">卖出价格:如果是0按助手的设置价格下单</param>
	/// <param name="formulaNum">公式编号,和大智慧公式编号类似，只是一个标记,区分那个买入的</param>
	/// <param name="ZhuShouHao">助手编号，如果开了多个助手，可以指定编号</param>
	/// <returns>返回值无意义，可忽略</returns>
	static public  native int  Sell(String stkCode,  int vol,  float price, int formulaNum, int ZhuShouHao);

	/// 获取个股持仓信息 GetPosInfo
	/// <param name="stkCode">股票代码 如:600000</param>
	/// <param name="nType">持仓信息类型:0持仓总量(股)//1可卖数量(股)//2成本价//3盈利金额//4盈利百分比 //5持仓市值//6下单天数//7持仓天数</param>
	/// <param name="ZhuShouHao">助手编号，如果开了多个助手，可以指定编号</param>
	/// <returns>返回相应的持仓信息</returns>	
	static public  native float  GetPosInfo(String stkCode, int nType, int nZhuShouHao);

	/// 获取账户信息 GetPosInfo
	/// <param name="stkCode">股票代码 如:600000</param>
	/// <param name="nType">持仓信息类型://0总资产//1可用资金//2持仓总市值//3总盈利金额//4持仓数量</param>
	/// <param name="ZhuShouHao">助手编号，如果开了多个助手，可以指定编号</param>
	/// <returns>返回相应的持仓信息</returns>
	static public  native float  GetAccountInfo(String stkCode, int nType, int nZhuShouHao);
	
	/// 初始化接收行情 InitReceiveQuote
	/// <param name="hQuoteWnd">负责接收行情的窗口 此窗口通过响应 WM_COPYDATA接收行情</param>
	/// <param name="ZhuShouHao">助手编号，如果开了多个助手，可以指定编号</param>
	/// <returns>返回值 忽略</returns>
	/// Java WM_COPYDATA 比较麻烦，不建议使用，自己想办法获取行情
	static public  native void  InitReceiveQuote(long hQuoteWnd, int ZhuShouHao);

	/// 注册某个股票的行情 RegisterQuote
	/// <param name="stkCode">股票代码 如:600000</param>
	/// <param name="ZhuShouHao">助手编号，如果开了多个助手，可以指定编号</param>
	/// /// Java WM_COPYDATA 比较麻烦，不建议使用，自己想办法获取行情
	static public  native void  RegisterQuote(String stkCode, int ZhuShouHao);
	
	/// GetAllPositionCode
	/// 返回所有持仓股票代码,以 ','号分开
	/// <param name="ZhuShouHao">助手编号，如果开了多个助手，可以指定编号</param>
	static public  native  String  GetAllPositionCode(int nZhuShouHao);
	
	/// 撤单由于提供给大智慧的函数名是 CancelOrder，为了不重名，因此是 CancelOrder1
	/// <param name="stkCode">股票代码 如:600000</param>
	/// <param name="orderType">撤单类型:1撤买单,2撤卖单</param>
	/// <param name="ZhuShouHao">助手编号，如果开了多个助手，可以指定编号</param>
	/// <returns>返回值无意义，可忽略</returns>
    static public  native int  CancelOrder1(String stkCode,  int orderType, int ZhuShouHao);

    /// 返回所有可撤股票代码,以 ','号分开
    /// <param name="ZhuShouHao">助手编号，如果开了多个助手，可以指定编号</param>
    // <param name="orderType">可撤单类型:1撤买单,2撤卖单</param>
    static public   native String GetAllOrderCode(int nZhuShouHao, int orderType);

	/// 获取个股可撤单信息 GetOrderInfo
	/// <param name="stkCode">股票代码 如:600000</param>
	/// <param name="orderType">可撤单类型:1撤买单,2撤卖单</param>
	/// <param name="infoType">查询类型:0数量,1时间秒</param>
	/// <param name="ZhuShouHao">助手编号，如果开了多个助手，可以指定编号</param>
	/// <returns>返回相应的可撤单信息</returns>
	static public   native float GetOrderInfo(String stkCode, int orderType, int infoType, int nZhuShouHao);
}
