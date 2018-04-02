package pxy.com.tutor.application;

import com.pxy.pangjiao.mvp.presenter.IPresenter;

/**
 * Created by pxy on 2018/4/1.
 */

public interface IUserPresent extends IPresenter {

    void loginStudent(String no, String pwd);

    void registerStudent(String no, String pwd, String name,String area);

    void loginTutor(String no, String pwd);

    void registerTutor(String no, String pwd, String name,String area);
}
