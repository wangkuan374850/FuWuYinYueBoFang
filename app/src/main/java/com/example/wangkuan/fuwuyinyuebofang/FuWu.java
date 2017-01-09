package com.example.wangkuan.fuwuyinyuebofang;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class FuWu extends Service{

	private MediaPlayer mp;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return new ZhongJian();
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//ʵ����������
		
		mp = new MediaPlayer();
	}
	public class ZhongJian extends Binder{
		//�����ķ���
		public void qidong(String path){
			try {
				mp.setDataSource(path);
				//׼��
				mp.prepare();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			//����
			mp.start();
		}
		public void TingZhi(){
			if (mp.isPlaying()) {
				mp.stop();//ֹͣ
				mp.reset();
			}
		}
		public void ChongZhi(){
			//if (mp.isPlaying()) {
				//����
				mp.reset();
			//}
		}
	}

}
