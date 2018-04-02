package pxy.com.tutor.service.imp;


import com.pxy.pangjiao.compiler.mpv.annotation.Service;

import pxy.com.tutor.adapter.protocol.getstudent.GetStudentRequest;
import pxy.com.tutor.adapter.protocol.getstudent.GetStudentResponse;
import pxy.com.tutor.adapter.protocol.getstudents.GetStudentsRequest;
import pxy.com.tutor.adapter.protocol.getstudents.GetStudentsResponse;
import pxy.com.tutor.adapter.protocol.gettutor.GetTutorRequest;
import pxy.com.tutor.adapter.protocol.gettutor.GetTutorResponse;
import pxy.com.tutor.adapter.protocol.gettutors.GetTutorsRequest;
import pxy.com.tutor.adapter.protocol.gettutors.GetTutorsResponse;
import pxy.com.tutor.adapter.protocol.studentlogin.StudentLoginRequest;
import pxy.com.tutor.adapter.protocol.studentlogin.StudentLoginResponse;
import pxy.com.tutor.adapter.protocol.studentregister.StudentRegisterRequest;
import pxy.com.tutor.adapter.protocol.studentregister.StudentRegisterResponse;
import pxy.com.tutor.adapter.protocol.tutorlogin.TutorRegisterRequest;
import pxy.com.tutor.adapter.protocol.tutorlogin.TutorRegisterResponse;
import pxy.com.tutor.adapter.protocol.tutorregister.TutorLoginRequest;
import pxy.com.tutor.adapter.protocol.tutorregister.TutorLoginResponse;
import pxy.com.tutor.service.IAppService;


/**
 * Created by Administrator on 2018/3/15.
 */

@Service
public class AppService implements IAppService {

    @Override
    public GetTutorsResponse getTutors(String city, int page) {
        GetTutorsRequest request = new GetTutorsRequest();
        request.setCity(city);
        request.setPage(page);
        return request.execute();
    }

    @Override
    public GetStudentsResponse getStudents(String city, int page) {
        GetStudentsRequest request = new GetStudentsRequest();
        request.setCity(city);
        request.setPage(page);
        return request.execute();
    }

    @Override
    public StudentRegisterResponse studentRegister(String no, String pwd, String name,String area) {
        StudentRegisterRequest request = new StudentRegisterRequest();
        request.setStudentNO(no);
        request.setPwd(pwd);
        request.setName(name);
        request.setAreaTeach(area);
        return request.execute();
    }

    @Override
    public StudentLoginResponse studentLogin(String no, String pwd) {
        StudentLoginRequest request = new StudentLoginRequest();
        request.setNo(no);
        request.setPwd(pwd);
        return request.execute();
    }

    @Override
    public TutorLoginResponse tutorLogin(String no, String pwd) {
        TutorLoginRequest request = new TutorLoginRequest();
        request.setNo(no);
        request.setPwd(pwd);
        return request.execute();
    }

    @Override
    public TutorRegisterResponse tutorRegister(String no, String pwd, String name,String area) {
        TutorRegisterRequest request = new TutorRegisterRequest();
        request.setTutorNO(no);
        request.setPwd(pwd);
        request.setName(name);
        request.setAddress(area);
        return request.execute();
    }

    @Override
    public GetStudentResponse getStudent(String no) {
        GetStudentRequest request=new GetStudentRequest();
        request.setNo(no);
        return request.execute();
    }

    @Override
    public GetTutorResponse getTutor(String no) {
        GetTutorRequest request=new GetTutorRequest();
        request.setNo(no);
        return request.execute();
    }
}
