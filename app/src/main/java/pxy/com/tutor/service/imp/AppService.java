package pxy.com.tutor.service.imp;


import com.pxy.pangjiao.compiler.mpv.annotation.Service;

import pxy.com.tutor.adapter.protocol.getstudents.GetStudentsRequest;
import pxy.com.tutor.adapter.protocol.getstudents.GetStudentsResponse;
import pxy.com.tutor.adapter.protocol.gettutors.GetTutorRequest;
import pxy.com.tutor.adapter.protocol.gettutors.GetTutorsResponse;
import pxy.com.tutor.adapter.protocol.studentlogin.StudentLoginRequest;
import pxy.com.tutor.adapter.protocol.studentlogin.StudentLoginResponse;
import pxy.com.tutor.adapter.protocol.studentregister.StudentRegisterRequest;
import pxy.com.tutor.adapter.protocol.studentregister.StudentRegisterResponse;
import pxy.com.tutor.service.IAppService;


/**
 * Created by Administrator on 2018/3/15.
 */

@Service
public class AppService implements IAppService {

    @Override
    public GetTutorsResponse getTutors(String city, int page) {
        GetTutorRequest request = new GetTutorRequest();
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
    public StudentRegisterResponse studentRegister(String no, String pwd) {
        StudentRegisterRequest request = new StudentRegisterRequest();
        request.setStudentNO(no);
        request.setPwd(pwd);
        return request.execute();
    }

    @Override
    public StudentLoginResponse studentLogin(String no, String pwd) {
        StudentLoginRequest request = new StudentLoginRequest();
        request.setNO(no);
        request.setPwd(pwd);
        return request.execute();
    }
}
