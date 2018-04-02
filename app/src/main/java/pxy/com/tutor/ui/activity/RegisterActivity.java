package pxy.com.tutor.ui.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.pxy.pangjiao.compiler.injectview.annotation.InitView;
import com.pxy.pangjiao.compiler.injectview.annotation.OnClick;
import com.pxy.pangjiao.compiler.mpv.annotation.AutowireProxy;
import com.pxy.pangjiao.compiler.mpv.annotation.Views;
import com.pxy.pangjiao.databus.DataBus;
import com.pxy.pangjiao.mvp.viewmodel.views.PJAppCompatActivity;

import pxy.com.tutor.R;
import pxy.com.tutor.application.IUserPresent;
import pxy.com.tutor.application.IUserView;

/**
 * Created by pxy on 2018/4/1.
 */

@Views
public class RegisterActivity extends PJAppCompatActivity implements IUserView {

    @InitView(id = R.id.toolbar)
    public Toolbar toolbar;


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

    @InitView(id = R.id.pwd_confirm)
    public EditText edPwdConfirm;

    @InitView(id = R.id.name)
    public EditText edName;

    @InitView(id = R.id.area)
    public EditText edAraea;

    @AutowireProxy
    public IUserPresent userPresent;

    @Override
    public int initView() {
        return R.layout.activity_register;
    }

    @Override
    public void initData() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(v -> RegisterActivity.this.finish());
    }

    @OnClick(R.id.login)
    public View.OnClickListener login_Click = view -> {

        String no = edNO.getText().toString();
        String name = edName.getText().toString();
        String pwd = edPwd.getText().toString();
        String pwdC = edPwdConfirm.getText().toString();
        String area=edAraea.getText().toString();
        if (TextUtils.isEmpty(no)) {
            showToast("编号不能为空");
            return;
        }
        if (TextUtils.isEmpty(area)) {
            showToast("区域不能为空");
            return;
        }

        if (TextUtils.isEmpty(name)) {
            showToast("姓名不能为空");
            return;
        }

        if (TextUtils.isEmpty(pwd)) {
            showToast("密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(pwdC)) {
            showToast("密码确认不能为空");
            return;
        }
        if (!pwd.equals(pwdC)) {
            showToast("两次密码输入不一致");
            return;
        }
        if (checkBox.isChecked()) {
            userPresent.registerTutor(no, pwd, name,area);
        } else {
            userPresent.registerStudent(no, pwd, name,area);
        }
    };

    @Override
    public void refresh(Object o) {

    }

    @Override
    public void registerSuccess(Object o) {
        DataBus.getDefault().post(o);
        this.finish();
    }

    @Override
    public void loginSuccess(Object o) {

    }
}
