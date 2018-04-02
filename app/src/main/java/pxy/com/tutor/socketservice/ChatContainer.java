package pxy.com.tutor.socketservice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/2.
 */

public class ChatContainer {
    private String name;
    private String headUrl;
    private boolean isTutor;
    private String no;
    private List<Msg> receivedMsgs=new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public boolean isTutor() {
        return isTutor;
    }

    public void setTutor(boolean tutor) {
        isTutor = tutor;
    }

    public List<Msg> getReceivedMsgs() {
        return receivedMsgs;
    }


    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
    public static class Msg{
        public String no;
        public String headUrl;
        public String msg;
    }
}
