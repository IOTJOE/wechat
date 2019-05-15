package com.stockorder.test;
import com.stockorder.api.*;

public class Sample {

	/**
     股票自动交易助手 Python 自动下单使用 例子
     此脚本和 StockOrderApi.py Order.dll 放到你自己编写的脚本同一目录
	 */
	public static void main(String[] args) {
		//买入测试
		//StockOrderApi.Buy("600000", 100, 0, 1, 0);
		//卖出测试,是持仓股才会有动作
		//StockOrderApi.Sell("600000", 100, 0, 2, 0);

		//账户信息
		System.out.println("--------------------------------");
		System.out.println("股票自动交易接口测试");
		System.out.println("账户信息");
		System.out.println("--------------------------------");

		String[] arrAccountInfo = {"总资产", "可用资金", "持仓总市值", "总盈利金额", "持仓数量"};
		for (int i = 0; i < arrAccountInfo.length; i++)
		{
			float value = StockOrderApi.GetAccountInfo( ""  , i, 0);
			System.out.printf("%s %f ",  arrAccountInfo[i], value);
			System.out.println();
		}
		System.out.println("--------------------------------");
		System.out.println(" ");

		System.out.println("股票持仓");
		System.out.println("--------------------------------");
		//取出所有的持仓股票代码,结果以 ','隔开的
		String allStockCode = StockOrderApi.GetAllPositionCode(0);
		String[] allStockCodeArray = allStockCode.split(",");
		for (int i = 0; i < allStockCodeArray.length; i++)
		{
			float vol = StockOrderApi.GetPosInfo( allStockCodeArray[i]  , 0 , 0);
			float changeP = StockOrderApi.GetPosInfo( allStockCodeArray[i]  , 4 , 0);
			System.out.printf ("%s %d %.2f%%", allStockCodeArray[i], (int)vol, changeP);
			System.out.println();
		}

		System.out.println ("--------------------------------");
		
		allStockCode = StockOrderApi.GetAllOrderCode(0, 1);
		if (!allStockCode.isEmpty())
		{
			System.out.println("可撤买单");
			System.out.println("--------------------------------");
			allStockCodeArray = allStockCode.split(",");
			for (int i = 0; i < allStockCodeArray.length; i++)
			{
				int vol = (int)StockOrderApi.GetOrderInfo(allStockCodeArray[i] , 0, 0, 0);
				int seconds = (int)StockOrderApi.GetOrderInfo(allStockCodeArray[i] , 0, 1, 0);
				System.out.printf ("%s %d %d", allStockCodeArray[i], vol, seconds);
				System.out.println();
			}
			System.out.println ("--------------------------------");
		}
		
		allStockCode = StockOrderApi.GetAllOrderCode(0, 2);
		if (!allStockCode.isEmpty())
		{
			System.out.println("可撤卖单");
			System.out.println("--------------------------------");
			allStockCodeArray = allStockCode.split(",");
			for (int i = 0; i < allStockCodeArray.length; i++)
			{
				int vol = (int)StockOrderApi.GetOrderInfo(allStockCodeArray[i] , 1, 0, 0);
				int seconds = (int)StockOrderApi.GetOrderInfo(allStockCodeArray[i] , 1, 1, 0);
				System.out.printf ("%s %d %d", allStockCodeArray[i], vol, seconds);
				System.out.println();
			}
			System.out.println ("--------------------------------");
		}
		
		//撤买
		//StockOrderApi.CancelOrder1("600006", 1, 0);
		//撤卖
		//StockOrderApi.CancelOrder1("600026", 2, 0);
	}

}
