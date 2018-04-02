package pxy.com.tutor.adapter.protocol.gettutor;

import com.pxy.pangjiao.net.NetModel;
import com.pxy.pangjiao.net.helper.PNet;

/**
 * Created by Administrator on 2018/4/2.
 */

@PNet(api = "getTutor",method = "GET")
public class GetTutorRequest extends NetModel<GetTutorResponse>{
    private String no;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
