package pxy.com.tutor.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pxy.pangjiao.compiler.injectview.annotation.InitView;
import com.pxy.pangjiao.mvp.viewmodel.views.PJFragment;

import pxy.com.tutor.R;
import pxy.com.tutor.socketservice.SocketService;
import pxy.com.tutor.ui.adapter.HomeChatRecyclerViewAdapter;

/**
 * Created by Administrator on 2018/4/2.
 */

public class ChatFragment extends PJFragment implements SocketService.IReceivedMsg {

    @InitView(id = R.id.recycler_view)
    public RecyclerView recyclerView;


    public HomeChatRecyclerViewAdapter adapter;

    @Override
    protected int initView() {
        return R.layout.laytou_fragment_chat;
    }

    @Override
    protected void initData() {
        adapter = new HomeChatRecyclerViewAdapter(activity);
        LinearLayoutManager manager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setContainer(SocketService.getInstance().getContainer());
        SocketService.getInstance().setReceivedListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void notifyMsg() {
        activity.runOnUiThread(() -> adapter.notifyDataSetChanged());
    }
}
