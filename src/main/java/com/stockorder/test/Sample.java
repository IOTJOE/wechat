package com.stockorder.test;
import com.stockorder.api.*;

public class Sample {

	/**
     ��Ʊ�Զ��������� Python �Զ��µ�ʹ�� ����
     �˽ű��� StockOrderApi.py Order.dll �ŵ����Լ���д�Ľű�ͬһĿ¼
	 */
	public static void main(String[] args) {
		//�������
		//StockOrderApi.Buy("600000", 100, 0, 1, 0);
		//��������,�ǳֲֹɲŻ��ж���
		//StockOrderApi.Sell("600000", 100, 0, 2, 0);

		//�˻���Ϣ
		System.out.println("--------------------------------");
		System.out.println("��Ʊ�Զ����׽ӿڲ���");
		System.out.println("�˻���Ϣ");
		System.out.println("--------------------------------");

		String[] arrAccountInfo = {"���ʲ�", "�����ʽ�", "�ֲ�����ֵ", "��ӯ�����", "�ֲ�����"};
		for (int i = 0; i < arrAccountInfo.length; i++)
		{
			float value = StockOrderApi.GetAccountInfo( ""  , i, 0);
			System.out.printf("%s %f ",  arrAccountInfo[i], value);
			System.out.println();
		}
		System.out.println("--------------------------------");
		System.out.println(" ");

		System.out.println("��Ʊ�ֲ�");
		System.out.println("--------------------------------");
		//ȡ�����еĳֲֹ�Ʊ����,����� ','������
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
			System.out.println("�ɳ���");
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
			System.out.println("�ɳ�����");
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
		
		//����
		//StockOrderApi.CancelOrder1("600006", 1, 0);
		//����
		//StockOrderApi.CancelOrder1("600026", 2, 0);
	}

}
