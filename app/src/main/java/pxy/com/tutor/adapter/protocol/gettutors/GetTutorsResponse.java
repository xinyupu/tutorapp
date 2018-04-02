package pxy.com.tutor.adapter.protocol.gettutors;

import java.util.List;

/**
 * Created by pxy on 2018/3/31.
 */

public class GetTutorsResponse {

    private String msg;
    private int resultCode;
    private boolean success;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private String tutorNO;
        private String name;
        private String pwd;
        private String birthday;
        private String education;
        private String canTeach;
        private String majorTeach;
        private String descTeach;
        private Object headUrl;
        private String address;
        private int id;

        public String getTutorNO() {
            return tutorNO;
        }

        public void setTutorNO(String tutorNO) {
            this.tutorNO = tutorNO;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getCanTeach() {
            return canTeach;
        }

        public void setCanTeach(String canTeach) {
            this.canTeach = canTeach;
        }

        public String getMajorTeach() {
            return majorTeach;
        }

        public void setMajorTeach(String majorTeach) {
            this.majorTeach = majorTeach;
        }

        public String getDescTeach() {
            return descTeach;
        }

        public void setDescTeach(String descTeach) {
            this.descTeach = descTeach;
        }

        public Object getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(Object headUrl) {
            this.headUrl = headUrl;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
