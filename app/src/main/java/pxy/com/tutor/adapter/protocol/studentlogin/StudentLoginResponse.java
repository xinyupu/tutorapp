package pxy.com.tutor.adapter.protocol.studentlogin;

/**
 * Created by pxy on 2018/4/1.
 */

public class StudentLoginResponse {



    private String msg;
    private int resultCode;
    private DataBean data;
    private boolean success;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {

        private String studentNO;
        private String name;
        private String pwd;
        private String subjectTeach;
        private String requireTeach;
        private String teachingTime;
        private String areaTeach;
        private String headUrl;
        private String studentCondition;
        private String studentRequireDesc;
        private int id;

        public String getStudentNO() {
            return studentNO;
        }

        public void setStudentNO(String studentNO) {
            this.studentNO = studentNO;
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

        public String getSubjectTeach() {
            return subjectTeach;
        }

        public void setSubjectTeach(String subjectTeach) {
            this.subjectTeach = subjectTeach;
        }

        public String getRequireTeach() {
            return requireTeach;
        }

        public void setRequireTeach(String requireTeach) {
            this.requireTeach = requireTeach;
        }

        public String getTeachingTime() {
            return teachingTime;
        }

        public void setTeachingTime(String teachingTime) {
            this.teachingTime = teachingTime;
        }

        public String getAreaTeach() {
            return areaTeach;
        }

        public void setAreaTeach(String areaTeach) {
            this.areaTeach = areaTeach;
        }

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public String getStudentCondition() {
            return studentCondition;
        }

        public void setStudentCondition(String studentCondition) {
            this.studentCondition = studentCondition;
        }

        public String getStudentRequireDesc() {
            return studentRequireDesc;
        }

        public void setStudentRequireDesc(String studentRequireDesc) {
            this.studentRequireDesc = studentRequireDesc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
