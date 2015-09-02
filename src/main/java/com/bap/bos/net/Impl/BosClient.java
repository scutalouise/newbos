package com.bap.bos.net.Impl;
import java.util.concurrent.FutureTask;

public class BosClient {
	
	public String accountCheckDays(){
		try {
//			FutureTask<String> threadResult=new FutureTask<String>(new BosClientThread("20131120"));
//			new Thread(threadResult).start();
//			return threadResult.get();
			FutureTask<String> threadResult=new FutureTask<String>(new UnloadingOilThread("0000011404170000","01","01"));
			new Thread(threadResult).start();
			return threadResult.get();
		}catch (Exception e) {
			/*网络异常*/
			e.printStackTrace();
			return "NetError";
		}
	}
	
	/**
	 * @param args*/
	 
	public static void main(String[] args) throws Exception{
		BosClient BosClient=new BosClient();
		String jieguo=BosClient.accountCheckDays();
		System.out.print("TankNo:"+jieguo.substring(0,2));
		System.out.print("订单号:"+jieguo.substring(2,18));
		System.out.print("卸油状态:"+jieguo.substring(18,20));
		System.out.print("结果:"+jieguo.substring(20,22));
		System.out.print(Integer.valueOf("00")+"  "+Integer.valueOf("01"));
	}
	 
}
