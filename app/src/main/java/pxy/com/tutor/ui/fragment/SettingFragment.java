package pxy.com.tutor.ui.fragment;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.pxy.pangjiao.compiler.injectview.annotation.InitView;
import com.pxy.pangjiao.compiler.injectview.annotation.OnClick;
import com.pxy.pangjiao.mvp.viewmodel.views.PJFragment;

import pxy.com.tutor.R;
import pxy.com.tutor.ui.activity.RegisterActivity;

/**
 * Created by Administrator on 2018/3/29.
 */

public class SettingFragment extends PJFragment {


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


    @Override
    protected int initView() {
        return R.layout.layout_fragment_setting;
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
    };


    @Override
    protected void initData() {

    }
}
