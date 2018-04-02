package pxy.com.tutor.application.imp;

import com.pxy.pangjiao.compiler.mpv.annotation.Autowire;
import com.pxy.pangjiao.compiler.mpv.annotation.Presenter;

import pxy.com.tutor.adapter.protocol.studentlogin.StudentLoginResponse;
import pxy.com.tutor.adapter.protocol.studentregister.StudentRegisterResponse;
import pxy.com.tutor.adapter.protocol.tutorlogin.TutorRegisterResponse;
import pxy.com.tutor.adapter.protocol.tutorregister.TutorLoginResponse;
import pxy.com.tutor.application.IUserPresent;
import pxy.com.tutor.application.IUserView;
import pxy.com.tutor.globle.Env;
import pxy.com.tutor.service.IAppService;
import pxy.com.tutor.socketservice.SocketService;

/**
 * Created by pxy on 2018/4/1.
 */

@Presenter(singleton = false)
public class UserPresent implements IUserPresent {

    @Autowire
    public IAppService appService;

    private IUserView view;

    @Override
    public void loginStudent(String no, String pwd) {
        StudentLoginResponse response = appService.studentLogin(no, pwd);
        if (response.isSuccess()) {
            view.loginSuccess(response.getData());
            Env.getAppContext().setNo(response.getData().getStudentNO());
            Env.getAppContext().setTutor(false);
            Env.getAppContext().setLogin(true);
            Env.getAppContext().setName(response.getData().getName());
            SocketService.getInstance().login(false, response.getData().getStudentNO());
        } else {
            view.showToast(response.getMsg());
        }

    }

    @Override
    public void registerStudent(String no, String pwd, String name, String area) {
        StudentRegisterResponse response = appService.studentRegister(no, pwd, name, area);
        if (response.isSuccess()) {
            view.showToast("注册成功");
            view.registerSuccess(response.getData());
            Env.getAppContext().setNo(response.getData().getStudentNO());
            Env.getAppContext().setTutor(false);
            Env.getAppContext().setLogin(true);
            Env.getAppContext().setName(response.getData().getName());
            SocketService.getInstance().login(false, response.getData().getStudentNO());
        } else {
            view.showToast(response.getMsg());
        }
    }

    @Override
    public void loginTutor(String no, String pwd) {
        TutorLoginResponse response = appService.tutorLogin(no, pwd);
        if (response.isSuccess()) {
            view.loginSuccess(response.getData());
            Env.getAppContext().setNo(response.getData().getTutorNO());
            Env.getAppContext().setTutor(true);
            Env.getAppContext().setLogin(true);
            Env.getAppContext().setName(response.getData().getName());
            SocketService.getInstance().login(true, response.getData().getTutorNO());
        } else {
            view.showToast(response.getMsg());
        }

    }

    @Override
    public void registerTutor(String no, String pwd, String name, String area) {
        TutorRegisterResponse response = appService.tutorRegister(no, pwd, name, area);
        if (response.isSuccess()) {
            view.showToast("注册成功");
            view.registerSuccess(response.getData());
            Env.getAppContext().setNo(response.getData().getTutorNO());
            Env.getAppContext().setTutor(true);
            Env.getAppContext().setLogin(true);
            Env.getAppContext().setName(response.getData().getName());
            SocketService.getInstance().login(true, response.getData().getTutorNO());
        } else {
            view.showToast(response.getMsg());
        }
    }

    @Override
    public void build(Object o) {
        this.view = (IUserView) o;
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
