package pxy.com.tutor.adapter.protocol.gettutors;

import com.pxy.pangjiao.net.NetModel;
import com.pxy.pangjiao.net.helper.PNet;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by pxy on 2018/3/31.
 */

@PNet(api = "http://192.168.1.102:8080/getTutors",method = "GET")
public class GetTutorRequest extends NetModel<GetTutorsResponse> {
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
        try {
            this.city = URLEncoder.encode(city,"utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
