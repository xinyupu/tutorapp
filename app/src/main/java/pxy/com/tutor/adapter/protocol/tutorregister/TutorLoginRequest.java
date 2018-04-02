package pxy.com.tutor.adapter.protocol.tutorregister;

import com.pxy.pangjiao.net.NetModel;
import com.pxy.pangjiao.net.helper.PNet;

/**
 * Created by Administrator on 2018/4/2.
 */

@PNet(api = "tutorLogin")
public class TutorLoginRequest extends NetModel<TutorLoginResponse> {

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
