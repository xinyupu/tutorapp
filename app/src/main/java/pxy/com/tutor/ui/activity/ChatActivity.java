package pxy.com.tutor.ui.activity;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.pxy.pangjiao.compiler.injectview.annotation.InitView;
import com.pxy.pangjiao.compiler.injectview.annotation.OnClick;
import com.pxy.pangjiao.databus.DataBus;
import com.pxy.pangjiao.databus.DataEvent;
import com.pxy.pangjiao.mvp.viewmodel.views.PJAppCompatActivity;

import java.util.List;

import pxy.com.tutor.R;
import pxy.com.tutor.globle.Env;
import pxy.com.tutor.socketservice.ChatContainer;
import pxy.com.tutor.socketservice.SocketService;
import pxy.com.tutor.ui.adapter.ChatRecyclerViewAdapter;
import pxy.com.tutor.ui.viewmodel.ChatInfoData;

/**
 * Created by pxy on 2018/4/1.
 */

public class ChatActivity extends PJAppCompatActivity implements SocketService.IReceivedMsg{

    @InitView(id = R.id.toolbar)
    public Toolbar toolbar;

    @InitView(id = R.id.tutor_name)
    public TextView tvName;

    @InitView(id = R.id.recycler_view)
    public RecyclerView recyclerView;

    @InitView(id = R.id.ed_chat_inpput)
    public EditText edInput;

    private String targerNO;
    private List<ChatContainer.Msg> msgs;

    private ChatRecyclerViewAdapter adapter;

    @Override
    public int initView() {
        return R.layout.activity_chat;
    }

    @Override
    public void initData() {
        SocketService.getInstance().login(Env.getAppContext().isTutor(), Env.getAppContext().getNo());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(v -> ChatActivity.this.finish());
        adapter = new ChatRecyclerViewAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        SocketService.getInstance().setReceivedListener(this);
        DataBus.getDefault().register(this);
    }

    @DataEvent
    public void onEvent(Object o) {
        if (o instanceof ChatInfoData) {
            tvName.setText(((ChatInfoData) o).getName());
            targerNO = ((ChatInfoData) o).getNo();
            msgs = ((ChatInfoData) o).getMsgs();
            adapter.setMsgs(msgs);
            adapter.notifyDataSetChanged();
            recyclerView.scrollToPosition(adapter.getItemCount() - 1);
        }
    }

    @OnClick(R.id.send)
    public View.OnClickListener send_Click = view -> {
        String content = edInput.getText().toString();

        if (!Env.getAppContext().isLogin()){
            showToast("请先登录");
            return;
        }
        if (!TextUtils.isEmpty(content)) {
            SocketService.getInstance().send(content, Env.getAppContext().isTutor(), Env.getAppContext().getNo(), targerNO);
            closeKeyboard();
            ChatContainer.Msg msgMy = new ChatContainer.Msg();
            msgMy.msg = content;
            msgMy.no = Env.getAppContext().getNo();
            adapter.getMsgs().add(msgMy);
            adapter.notifyDataSetChanged();
            recyclerView.scrollToPosition(adapter.getItemCount() - 1);
            edInput.setText("");
        }
    };

    private void closeKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SocketService.getInstance().clearListener(this);
    }

    @Override
    public void notifyMsg() {
        runOnUiThread(()->adapter.notifyDataSetChanged());
    }
}
