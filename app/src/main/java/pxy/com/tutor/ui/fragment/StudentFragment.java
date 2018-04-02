package pxy.com.tutor.ui.fragment;



import com.pxy.pangjiao.mvp.viewmodel.views.PJFragment;

import pxy.com.tutor.R;

/**
 * Created by Administrator on 2018/3/29.
 */

public class StudentFragment extends PJFragment {


    @Override
    protected int initView() {
        return R.layout.layout_fragment_student;
    }

    @Override
    protected void initData() {

    }

    public void onSelectCity(String city) {
       // tutorPresent.getTutors(0, city);
    }
}
