package pxy.com.tutor.globle;

/**
 * Created by Administrator on 2018/4/2.
 */

public class Env {
    public static AppContext getAppContext() {
        return Environment.getInstance().appContext;
    }
}

