package pxy.com.tutor.service;

import pxy.com.tutor.adapter.protocol.getstudent.GetStudentResponse;
import pxy.com.tutor.adapter.protocol.getstudents.GetStudentsResponse;
import pxy.com.tutor.adapter.protocol.gettutor.GetTutorResponse;
import pxy.com.tutor.adapter.protocol.gettutors.GetTutorsResponse;
import pxy.com.tutor.adapter.protocol.studentlogin.StudentLoginResponse;
import pxy.com.tutor.adapter.protocol.studentregister.StudentRegisterResponse;
import pxy.com.tutor.adapter.protocol.tutorlogin.TutorRegisterResponse;
import pxy.com.tutor.adapter.protocol.tutorregister.TutorLoginResponse;

/**
 * Created by Administrator on 2018/3/15.
 */

public interface IAppService {

    GetTutorsResponse getTutors(String city, int page);

    GetStudentsResponse getStudents(String city, int page);

    StudentRegisterResponse studentRegister(String no, String pwd, String name,String area);

    StudentLoginResponse studentLogin(String no, String pwd);

    TutorLoginResponse tutorLogin(String no, String pwd);

    TutorRegisterResponse tutorRegister(String no, String pwd, String name,String area);
    GetStudentResponse getStudent(String no);

    GetTutorResponse getTutor(String no);
}
