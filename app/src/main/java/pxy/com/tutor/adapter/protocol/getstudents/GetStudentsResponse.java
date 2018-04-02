package pxy.com.tutor.adapter.protocol.getstudents;

import com.pxy.pangjiao.compiler.mpv.annotation.DataSource;

import java.util.List;

/**
 * Created by Administrator on 2018/3/29.
 */

@DataSource
public class GetStudentsResponse {


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


        private String studentNO;
        private String name;
        private String pwd;
        private Object subjectTeach;
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

        public Object getSubjectTeach() {
            return subjectTeach;
        }

        public void setSubjectTeach(Object subjectTeach) {
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
