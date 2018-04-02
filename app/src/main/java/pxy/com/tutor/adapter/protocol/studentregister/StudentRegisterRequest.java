package pxy.com.tutor.adapter.protocol.studentregister;

import com.pxy.pangjiao.net.NetModel;
import com.pxy.pangjiao.net.helper.PNet;

/**
 * Created by pxy on 2018/4/1.
 */

@PNet(api = "http://192.168.102:8080/registerStudent")
public class StudentRegisterRequest extends NetModel<StudentRegisterResponse> {


    private String areaTeach;
    private String headUrl;
    private String name;
    private String pwd;
    private String requireTeach;
    private String studentCondition;
    private String studentNO;
    private String studentRequireDesc;
    private String subjectTeach;
    private String teachingTime;

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

    public String getRequireTeach() {
        return requireTeach;
    }

    public void setRequireTeach(String requireTeach) {
        this.requireTeach = requireTeach;
    }

    public String getStudentCondition() {
        return studentCondition;
    }

    public void setStudentCondition(String studentCondition) {
        this.studentCondition = studentCondition;
    }

    public String getStudentNO() {
        return studentNO;
    }

    public void setStudentNO(String studentNO) {
        this.studentNO = studentNO;
    }

    public String getStudentRequireDesc() {
        return studentRequireDesc;
    }

    public void setStudentRequireDesc(String studentRequireDesc) {
        this.studentRequireDesc = studentRequireDesc;
    }

    public String getSubjectTeach() {
        return subjectTeach;
    }

    public void setSubjectTeach(String subjectTeach) {
        this.subjectTeach = subjectTeach;
    }

    public String getTeachingTime() {
        return teachingTime;
    }

    public void setTeachingTime(String teachingTime) {
        this.teachingTime = teachingTime;
    }
}
