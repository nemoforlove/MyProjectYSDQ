package com.gpf.myprojectysdq.utils;

import android.net.Uri;

public class TimeUtils {

		public static String formatTime(long time){
			String min=time/(1000*60)+"";
			String sec=time%(1000*60)+"";
			if(min.length()<2){
				min="0"+time/(1000*60)+"";
			}else{
				min=time/(1000*60)+"";
			}
			if(sec.length()==4){
				sec="0"+(time%(1000*60))+"";
			}else if(sec.length()==3){
				sec="00"+(time%(1000*60))+"";
			}else if(sec.length()==2){
				sec="000"+(time%(1000*60))+"";
			}else if(sec.length()==1){
				sec="0000"+(time%(1000*60))+"";
			}
			return min+":"+sec.trim().substring(0,2);
		}


	// 判断是否为网络视频的方法
	public static boolean isNetUri(String path){
		boolean result = false;
		if(path != null){
           if(path.toLowerCase().startsWith("http") || path.toLowerCase().startsWith("https")
				   || path.toLowerCase().startsWith("rtsp")
				   || path.toLowerCase().startsWith("mms")){
               result = true;// 为网络视频
		   }
		}
		return result;
	}
}
