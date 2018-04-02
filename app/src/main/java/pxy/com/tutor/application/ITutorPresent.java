package pxy.com.tutor.application;

import com.pxy.pangjiao.mvp.presenter.IPresenter;

import pxy.com.tutor.ui.viewmodel.User;

/**
 * Created by Administrator on 2018/3/17.
 */

public interface ITutorPresent extends IPresenter {
    void getTutors(int page,String city);

}
