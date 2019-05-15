package com.stockorder.api;

public class StockOrderApi {
	
	static {
    	System.loadLibrary("StockOrderJni");
    }
	
	//1.����API����StockOrderJni.dll �Ž� Order.dll�ļ��ĵ����������������º�����
	/// ���� Buy
	/// <param name="stkCode">��Ʊ���� ��:600000</param>
	/// <param name="vol">��������:����</param>
	/// <param name="price">����۸�:�����0�����ֵ����ü۸��µ�</param>
	/// <param name="formulaNum">��ʽ���,�ʹ��ǻ۹�ʽ������ƣ�ֻ��һ�����,�����Ǹ������</param>
	/// <param name="ZhuShouHao">���ֱ�ţ�������˶�����֣�����ָ�����</param>
	/// <returns>����ֵ�����壬�ɺ���</returns>
	static public  native int  Buy(String stkCode,  int vol,  float price, int formulaNum, int ZhuShouHao);

	/// ���� Sell
	/// <param name="stkCode">��Ʊ���� ��:600000</param>
	/// <param name="vol">��������:����</param>
	/// <param name="price">�����۸�:�����0�����ֵ����ü۸��µ�</param>
	/// <param name="formulaNum">��ʽ���,�ʹ��ǻ۹�ʽ������ƣ�ֻ��һ�����,�����Ǹ������</param>
	/// <param name="ZhuShouHao">���ֱ�ţ�������˶�����֣�����ָ�����</param>
	/// <returns>����ֵ�����壬�ɺ���</returns>
	static public  native int  Sell(String stkCode,  int vol,  float price, int formulaNum, int ZhuShouHao);

	/// ��ȡ���ɳֲ���Ϣ GetPosInfo
	/// <param name="stkCode">��Ʊ���� ��:600000</param>
	/// <param name="nType">�ֲ���Ϣ����:0�ֲ�����(��)//1��������(��)//2�ɱ���//3ӯ�����//4ӯ���ٷֱ� //5�ֲ���ֵ//6�µ�����//7�ֲ�����</param>
	/// <param name="ZhuShouHao">���ֱ�ţ�������˶�����֣�����ָ�����</param>
	/// <returns>������Ӧ�ĳֲ���Ϣ</returns>	
	static public  native float  GetPosInfo(String stkCode, int nType, int nZhuShouHao);

	/// ��ȡ�˻���Ϣ GetPosInfo
	/// <param name="stkCode">��Ʊ���� ��:600000</param>
	/// <param name="nType">�ֲ���Ϣ����://0���ʲ�//1�����ʽ�//2�ֲ�����ֵ//3��ӯ�����//4�ֲ�����</param>
	/// <param name="ZhuShouHao">���ֱ�ţ�������˶�����֣�����ָ�����</param>
	/// <returns>������Ӧ�ĳֲ���Ϣ</returns>
	static public  native float  GetAccountInfo(String stkCode, int nType, int nZhuShouHao);
	
	/// ��ʼ���������� InitReceiveQuote
	/// <param name="hQuoteWnd">�����������Ĵ��� �˴���ͨ����Ӧ WM_COPYDATA��������</param>
	/// <param name="ZhuShouHao">���ֱ�ţ�������˶�����֣�����ָ�����</param>
	/// <returns>����ֵ ����</returns>
	/// Java WM_COPYDATA �Ƚ��鷳��������ʹ�ã��Լ���취��ȡ����
	static public  native void  InitReceiveQuote(long hQuoteWnd, int ZhuShouHao);

	/// ע��ĳ����Ʊ������ RegisterQuote
	/// <param name="stkCode">��Ʊ���� ��:600000</param>
	/// <param name="ZhuShouHao">���ֱ�ţ�������˶�����֣�����ָ�����</param>
	/// /// Java WM_COPYDATA �Ƚ��鷳��������ʹ�ã��Լ���취��ȡ����
	static public  native void  RegisterQuote(String stkCode, int ZhuShouHao);
	
	/// GetAllPositionCode
	/// �������гֲֹ�Ʊ����,�� ','�ŷֿ�
	/// <param name="ZhuShouHao">���ֱ�ţ�������˶�����֣�����ָ�����</param>
	static public  native  String  GetAllPositionCode(int nZhuShouHao);
	
	/// ���������ṩ�����ǻ۵ĺ������� CancelOrder��Ϊ�˲������������ CancelOrder1
	/// <param name="stkCode">��Ʊ���� ��:600000</param>
	/// <param name="orderType">��������:1����,2������</param>
	/// <param name="ZhuShouHao">���ֱ�ţ�������˶�����֣�����ָ�����</param>
	/// <returns>����ֵ�����壬�ɺ���</returns>
    static public  native int  CancelOrder1(String stkCode,  int orderType, int ZhuShouHao);

    /// �������пɳ���Ʊ����,�� ','�ŷֿ�
    /// <param name="ZhuShouHao">���ֱ�ţ�������˶�����֣�����ָ�����</param>
    // <param name="orderType">�ɳ�������:1����,2������</param>
    static public   native String GetAllOrderCode(int nZhuShouHao, int orderType);

	/// ��ȡ���ɿɳ�����Ϣ GetOrderInfo
	/// <param name="stkCode">��Ʊ���� ��:600000</param>
	/// <param name="orderType">�ɳ�������:1����,2������</param>
	/// <param name="infoType">��ѯ����:0����,1ʱ����</param>
	/// <param name="ZhuShouHao">���ֱ�ţ�������˶�����֣�����ָ�����</param>
	/// <returns>������Ӧ�Ŀɳ�����Ϣ</returns>
	static public   native float GetOrderInfo(String stkCode, int orderType, int infoType, int nZhuShouHao);
}
