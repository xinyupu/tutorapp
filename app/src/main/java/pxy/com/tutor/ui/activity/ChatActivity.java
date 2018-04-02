package pxy.com.tutor.ui.activity;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pxy.pangjiao.compiler.injectview.annotation.InitView;
import com.pxy.pangjiao.databus.DataBus;
import com.pxy.pangjiao.databus.DataEvent;
import com.pxy.pangjiao.mvp.viewmodel.views.PJAppCompatActivity;

import pxy.com.tutor.R;
import pxy.com.tutor.ui.viewmodel.ChatInfoData;

/**
 * Created by pxy on 2018/4/1.
 */

public class ChatActivity extends PJAppCompatActivity {

    @InitView(id = R.id.toolbar)
    public Toolbar toolbar;

    @InitView(id = R.id.tutor_name)
    public TextView tvName;

    @InitView(id = R.id.recycler_view)
    public RecyclerView recyclerView;

    @InitView(id = R.id.ed_chat_inpput)
    public EditText edInput;

    @Override
    public int initView() {
        return R.layout.activity_chat;
    }

    @Override
    public void initData() {
        setSupportActionBar(toolbar);
//设置toolbar后调用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(v -> ChatActivity.this.finish());
        DataBus.getDefault().register(this);
    }

    @DataEvent
    public void onEvent(Object o) {
        if (o instanceof ChatInfoData) {
            tvName.setText(((ChatInfoData) o).getName());
        }
    }
}
