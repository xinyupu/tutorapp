package pxy.com.tutor.ui.fragment;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pxy.pangjiao.compiler.injectview.annotation.InitView;
import com.pxy.pangjiao.compiler.injectview.annotation.OnClick;
import com.pxy.pangjiao.compiler.mpv.annotation.AutowireProxy;
import com.pxy.pangjiao.compiler.mpv.annotation.Views;
import com.pxy.pangjiao.databus.DataBus;
import com.pxy.pangjiao.databus.DataEvent;
import com.pxy.pangjiao.mvp.viewmodel.views.PJFragment;

import pxy.com.tutor.R;
import pxy.com.tutor.adapter.protocol.studentlogin.StudentLoginResponse;
import pxy.com.tutor.adapter.protocol.studentregister.StudentRegisterResponse;
import pxy.com.tutor.adapter.protocol.tutorlogin.TutorRegisterResponse;
import pxy.com.tutor.adapter.protocol.tutorregister.TutorLoginResponse;
import pxy.com.tutor.application.IUserPresent;
import pxy.com.tutor.application.IUserView;
import pxy.com.tutor.ui.activity.RegisterActivity;

/**
 * Created by Administrator on 2018/3/29.
 */

@Views
public class SettingFragment extends PJFragment implements IUserView {


    @InitView(id = R.id.tv_register)
    public TextView tvRegister;

    @InitView(id = R.id.is_check_tutor)
    public CheckBox checkBox;

    @InitView(id = R.id.login)
    public Button btnLogin;


    @InitView(id = R.id.no)
    public EditText edNO;

    @InitView(id = R.id.pwd)
    public EditText edPwd;

    @InitView(id = R.id.rl_login_view)
    public RelativeLayout rlLoginView;

    @InitView(id = R.id.rl_info)
    public RelativeLayout rlInfo;

    @InitView(id = R.id.no_user)
    public TextView tvNO;

    @InitView(id = R.id.name_user)
    public TextView tvName;

    @AutowireProxy
    public IUserPresent userPresent;


    @Override
    protected int initView() {
        return R.layout.layout_fragment_setting;
    }


    @Override
    protected void initData() {
        DataBus.getDefault().register(this);
    }

    @OnClick(R.id.tv_register)
    public View.OnClickListener register_Click = view -> {
        activity.startActivity(new Intent(activity, RegisterActivity.class));
    };


    @OnClick(R.id.login)
    public View.OnClickListener login_Click = view -> {

        String no = edNO.getText().toString();
        String pwd = edPwd.getText().toString();
        if (TextUtils.isEmpty(no)) {
            showToast("编号不能为空");
            return;
        }

        if (TextUtils.isEmpty(pwd)) {
            showToast("密码不能为空");
            return;
        }
        if (checkBox.isChecked()) {
            userPresent.loginTutor(no, pwd);
        } else {
            userPresent.loginStudent(no, pwd);
        }
    };

    @DataEvent
    public void onEvent(Object o) {
        if (o instanceof StudentRegisterResponse.DataBean) {
            StudentRegisterResponse.DataBean bean = (StudentRegisterResponse.DataBean) o;
            rlLoginView.setVisibility(View.GONE);
            showInfo(bean.getName(), bean.getStudentNO());
        } else if (o instanceof TutorRegisterResponse.DataBean) {
            TutorRegisterResponse.DataBean bean = (TutorRegisterResponse.DataBean) o;
            rlLoginView.setVisibility(View.GONE);
            showInfo(bean.getName(), bean.getTutorNO());
        }
    }

    private void showInfo(String name, String no) {
        rlInfo.setVisibility(View.VISIBLE);
        tvName.setText(name);
        tvNO.setText(no);
    }


    @Override
    public void registerSuccess(Object o) {

    }

    @Override
    public void loginSuccess(Object o) {
        if (o instanceof StudentLoginResponse.DataBean) {
            rlLoginView.setVisibility(View.GONE);
            StudentLoginResponse.DataBean dataBean = (StudentLoginResponse.DataBean) o;
            showInfo(dataBean.getName(), dataBean.getStudentNO());
        } else if (o instanceof TutorLoginResponse.DataBean) {
            TutorLoginResponse.DataBean bean = (TutorLoginResponse.DataBean) o;
            rlLoginView.setVisibility(View.GONE);
            showInfo(bean.getName(), bean.getTutorNO());
        }
    }

    @Override
    public void refresh(Object o) {

    }
}
