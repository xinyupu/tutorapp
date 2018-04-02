package pxy.com.tutor.adapter.protocol.studentlogin;

import com.pxy.pangjiao.net.NetModel;
import com.pxy.pangjiao.net.helper.PNet;

/**
 * Created by pxy on 2018/4/1.
 */

@PNet(api = "studentLogin")
public class StudentLoginRequest extends NetModel<StudentLoginResponse> {

    private String no;
    private String pwd;


    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
