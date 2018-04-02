package pxy.com.tutor.application.imp;

import com.pxy.pangjiao.compiler.mpv.annotation.Autowire;
import com.pxy.pangjiao.compiler.mpv.annotation.Presenter;

import pxy.com.tutor.application.IStudentPresent;
import pxy.com.tutor.application.IStudentView;
import pxy.com.tutor.service.IAppService;

/**
 * Created by pxy on 2018/4/1.
 */

@Presenter
public class StudentPresent implements IStudentPresent {

    @Autowire
    public IAppService appService;

    private IStudentView view;

    @Override
    public void login(String no, String pwd) {

    }

    @Override
    public void register(String no, String pwd) {

    }

    @Override
    public void build(Object o) {
        this.view= (IStudentView) o;
    }

    @Override
    public void onDestroy() {
        this.view=null;
    }
}
