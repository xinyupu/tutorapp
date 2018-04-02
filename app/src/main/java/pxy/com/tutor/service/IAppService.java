package pxy.com.tutor.service;

import pxy.com.tutor.adapter.protocol.getstudents.GetStudentsResponse;
import pxy.com.tutor.adapter.protocol.gettutors.GetTutorsResponse;
import pxy.com.tutor.adapter.protocol.studentlogin.StudentLoginResponse;
import pxy.com.tutor.adapter.protocol.studentregister.StudentRegisterResponse;

/**
 * Created by Administrator on 2018/3/15.
 */

public interface IAppService {

    GetTutorsResponse getTutors(String city, int page);

    GetStudentsResponse getStudents(String city, int page);

    StudentRegisterResponse studentRegister(String no, String pwd);

    StudentLoginResponse studentLogin(String no,String pwd);
}
