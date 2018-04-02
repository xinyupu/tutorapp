package pxy.com.tutor.globle;

/**
 * Created by Administrator on 2018/4/2.
 */

public class Environment {

    private static Environment _instance;

    public AppContext appContext;

    public static Environment create() {
        if (_instance == null) {
            synchronized (Environment.class) {
                if (_instance == null) {
                    _instance = new Environment();
                }
            }
        }
        return _instance;
    }

    private Environment() {
        appContext = new AppContext();
    }

    public static Environment getInstance() {
        return _instance;
    }


}
