package pxy.com.tutor.adapter.protocol.getstudents;

import com.pxy.pangjiao.net.NetModel;
import com.pxy.pangjiao.net.helper.PNet;

/**
 * Created by Administrator on 2018/3/29.
 */

@PNet(api = "getStudents",method = "GET")
public class GetStudentsRequest extends NetModel<GetStudentsResponse> {


    private int page;
    private String city;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
