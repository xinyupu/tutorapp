package pxy.com.tutor.adapter.protocol.getstudent;

import com.pxy.pangjiao.net.NetModel;
import com.pxy.pangjiao.net.helper.PNet;

/**
 * Created by Administrator on 2018/4/2.
 */

@PNet(api = "getStudent",method = "GET")
public class GetStudentRequest extends NetModel<GetStudentResponse> {
    private String no;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
