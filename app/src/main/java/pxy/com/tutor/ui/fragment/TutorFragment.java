package pxy.com.tutor.ui.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pxy.pangjiao.compiler.injectview.annotation.InitView;
import com.pxy.pangjiao.compiler.mpv.annotation.AutowireProxy;
import com.pxy.pangjiao.compiler.mpv.annotation.Views;
import com.pxy.pangjiao.mvp.viewmodel.views.PJFragment;

import pxy.com.tutor.ui.activity.MainActivity;
import pxy.com.tutor.R;
import pxy.com.tutor.adapter.protocol.gettutors.GetTutorsResponse;
import pxy.com.tutor.application.IPublicView;
import pxy.com.tutor.application.ITutorPresent;
import pxy.com.tutor.ui.adapter.TutorRecyclerViewAdapter;

/**
 * Created by Administrator on 2018/3/29.
 */

@Views
public class TutorFragment extends PJFragment implements IPublicView {

    @InitView(id = R.id.recycler_view)
    public RecyclerView recyclerView;

    @InitView(id = R.id.no_data)
    public TextView tvNoData;

    @AutowireProxy
    public ITutorPresent tutorPresent;

    private TutorRecyclerViewAdapter adapter;

    @Override
    protected int initView() {
        return R.layout.layout_fragment_tutor;
    }

    @Override
    protected void initData() {
        adapter = new TutorRecyclerViewAdapter(activity);
        LinearLayoutManager manager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        tutorPresent.getTutors(0, ((MainActivity) activity).currentCity);
    }

    @Override
    public void refresh(Object o) {
        if (o instanceof GetTutorsResponse) {
            tvNoData.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter.setDataBeans(((GetTutorsResponse) o).getData());
            adapter.notifyDataSetChanged();
        }
    }

    public void onSelectCity(String city) {
        tutorPresent.getTutors(0, city);
    }

    @Override
    public void clearData() {
        recyclerView.setVisibility(View.GONE);
        tvNoData.setVisibility(View.VISIBLE);
        adapter.setDataBeans(null);
        adapter.notifyDataSetChanged();
    }
}
