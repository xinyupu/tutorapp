package pxy.com.tutor.globle;

import android.app.Application;

import com.pxy.pangjiao.PangJiao;
import com.pxy.pangjiao.net.NetDefaultConfig;

import pxy.com.tutor.socketservice.SocketService;

/**
 * Created by Administrator on 2018/3/29.
 */

public class TutorApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PangJiao.init(this, new NetDefaultConfig().setHost("http://192.168.100.10:8080/"));
       // PangJiao.init(this, new NetDefaultConfig().setHost("http://10.0.0.2:8080/"));
        Environment.create();
        SocketService.createInstance("192.168.100.10");
    }
}
