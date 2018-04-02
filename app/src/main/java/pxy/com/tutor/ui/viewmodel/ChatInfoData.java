package pxy.com.tutor.ui.viewmodel;

import java.io.Serializable;

/**
 * Created by pxy on 2018/4/1.
 */

public class ChatInfoData implements Serializable {

    String name;
    String no;
    boolean isTutor;

    public ChatInfoData(String name, String no, boolean isTutor) {
        this.name = name;
        this.no = no;
        this.isTutor = isTutor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public boolean isTutor() {
        return isTutor;
    }

    public void setTutor(boolean tutor) {
        isTutor = tutor;
    }
}
