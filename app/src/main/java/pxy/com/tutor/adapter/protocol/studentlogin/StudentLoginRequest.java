package pxy.com.tutor.adapter.protocol.studentlogin;

import com.pxy.pangjiao.net.NetModel;
import com.pxy.pangjiao.net.helper.PNet;

/**
 * Created by pxy on 2018/4/1.
 */

@PNet(api = "http://192.168.102:8080/studentLogin")
public class StudentLoginRequest extends NetModel<StudentLoginResponse> {

    private String NO;
    private String pwd;

    public String getNO() {
        return NO;
    }

    public void setNO(String NO) {
        this.NO = NO;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
