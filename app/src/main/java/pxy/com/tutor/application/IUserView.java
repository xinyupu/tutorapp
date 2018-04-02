package pxy.com.tutor.application;

import com.pxy.pangjiao.mvp.viewmodel.views.IView;

/**
 * Created by pxy on 2018/4/1.
 */

public interface IUserView extends IView {

    void registerSuccess(Object o);

    void loginSuccess(Object o);
}
