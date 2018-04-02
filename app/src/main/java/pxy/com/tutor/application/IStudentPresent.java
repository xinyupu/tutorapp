package pxy.com.tutor.application;

import com.pxy.pangjiao.mvp.presenter.IPresenter;

/**
 * Created by pxy on 2018/4/1.
 */

public interface IStudentPresent extends IPresenter {

    void login(String no,String pwd);

    void register(String no,String pwd);
}
