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
import com.pxy.pangjiao.mvp.viewmodel.views.PJAppCompatActivity;

import pxy.com.tutor.R;

/**
 * Created by pxy on 2018/4/1.
 */

public class RegisterActivity extends PJAppCompatActivity {

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
        String pwd = edPwd.getText().toString();
        String pwdC = edPwdConfirm.getText().toString();
        if (TextUtils.isEmpty(no)) {
            showToast("编号不能为空");
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
    };
}
