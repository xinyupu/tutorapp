package pxy.com.tutor.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pxy.pangjiao.compiler.injectview.annotation.InitView;
import com.pxy.pangjiao.compiler.injectview.annotation.OnClick;
import com.pxy.pangjiao.compiler.mpv.annotation.AutowireProxy;
import com.pxy.pangjiao.compiler.mpv.annotation.Views;
import com.pxy.pangjiao.mvp.viewmodel.views.PJAppCompatActivity;
import com.roughike.bottombar.BottomBar;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.HotCity;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;

import java.util.ArrayList;
import java.util.List;

import pxy.com.tutor.R;
import pxy.com.tutor.application.IPublicView;
import pxy.com.tutor.application.ITutorPresent;
import pxy.com.tutor.application.imp.TouristService;
import pxy.com.tutor.globle.Env;
import pxy.com.tutor.socketservice.SocketService;
import pxy.com.tutor.ui.fragment.ChatFragment;
import pxy.com.tutor.ui.fragment.SettingFragment;
import pxy.com.tutor.ui.fragment.StudentFragment;
import pxy.com.tutor.ui.fragment.TutorFragment;
import pxy.com.tutor.ui.utils.IpGetUtil;

@Views
public class MainActivity extends PJAppCompatActivity {


    @InitView(id = R.id.toolbar)
    public Toolbar toolbar;

    @InitView(id = R.id.tv_current_city)
    public TextView tvCurrentCity;

    @InitView(id = R.id.bottomBar)
    public BottomBar bottomBar;

    @InitView(id = R.id.person_info)
    public TextView tvPersonInfo;

    @InitView(id = R.id.btn_show)
    public TextView tvCityBtn;


    FragmentManager fm;
    Fragment mCurrentFragment;


    SettingFragment settingFragment;
    StudentFragment studentFragment;
    TutorFragment tutorFragment;
    ChatFragment chatFragment;

    String currentTag = "tutorFragment";
    public String currentCity = "广州";
    private final String TutorFragment = "tutorFragment";
    private final String StudentFragment = "studentFragment";
    private final String ChatFragment = "chatFragment";

    @Override
    public int initView() {
        return R.layout.activity_main;
    }


    @Override
    public void initData() {
        String ipAddress = IpGetUtil.getIPAddress(this);
        Env.getAppContext().setHostIp(ipAddress);
    //    Env.getAppContext().setHostIp("10.0.2.2");
        settingFragment = new SettingFragment();
        studentFragment = new StudentFragment();
        tutorFragment = new TutorFragment();
        chatFragment=new ChatFragment();

        fm = getFragmentManager();
        fm.beginTransaction().add(R.id.replace, tutorFragment, "tutorFragment").add(R.id.replace, studentFragment, "studentFragment").hide(studentFragment)
                .add(R.id.replace, chatFragment, "chatFragment").hide(chatFragment) .add(R.id.replace, settingFragment, "settingFragment").hide(settingFragment).commit();
        setSupportActionBar(toolbar);
        bottomBar.setOnTabSelectListener(tabId -> {
            if (tabId == R.id.tab_tutor) {
                switchFragment(currentTag, "tutorFragment");
                mCurrentFragment = tutorFragment;
                tvPersonInfo.setVisibility(View.GONE);
                tvCurrentCity.setVisibility(View.VISIBLE);
                tvCityBtn.setVisibility(View.VISIBLE);
            }
           /* else if (tabId == R.id.tab_student) {
                switchFragment(currentTag, "studentFragment");
                mCurrentFragment = studentFragment;
                tvPersonInfo.setVisibility(View.GONE);
                tvCurrentCity.setVisibility(View.VISIBLE);
                tvCityBtn.setVisibility(View.VISIBLE);
            }*/else if (tabId==R.id.tab_chat){
                switchFragment(currentTag, "chatFragment");
                mCurrentFragment = chatFragment;
                tvPersonInfo.setText("聊天");
                tvPersonInfo.setVisibility(View.VISIBLE);
                tvCurrentCity.setVisibility(View.GONE);
                tvCityBtn.setVisibility(View.GONE);
            }
            else if (tabId == R.id.tab_setting) {
                switchFragment(currentTag, "settingFragment");
                mCurrentFragment = settingFragment;
                tvPersonInfo.setText("个人信息");
                tvPersonInfo.setVisibility(View.VISIBLE);
                tvCurrentCity.setVisibility(View.GONE);
                tvCityBtn.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @OnClick(R.id.btn_show)
    public View.OnClickListener btn_Click = v -> {
        city();
    };


    private void city() {
        List<HotCity> hotCities = new ArrayList<>();
        hotCities.add(new HotCity("北京", "北京", "101010100"));
        hotCities.add(new HotCity("上海", "上海", "101020100"));
        hotCities.add(new HotCity("广州", "广东", "101280101"));
        hotCities.add(new HotCity("深圳", "广东", "101280601"));
        hotCities.add(new HotCity("杭州", "浙江", "101210101"));
        CityPicker.getInstance()
                .setFragmentManager(getSupportFragmentManager())    //此方法必须调用
                .enableAnimation(true)    //启用动画效果
                .setLocatedCity(new LocatedCity(currentCity, "浙江", "101210101"))  //APP自身已定位的城市，默认为null（定位失败）
                .setHotCities(hotCities)    //指定热门城市
                .setOnPickListener(new OnPickListener() {
                    @Override
                    public void onPick(int position, City data) {
                        if (data != null) {
                            tvCurrentCity.setText(data.getName());
                            currentCity = data.getName();
                            //Toast.makeText(getApplicationContext(), data.getName(), Toast.LENGTH_SHORT).show();
                            if (mCurrentFragment instanceof TutorFragment) {
                                ((TutorFragment) mCurrentFragment).onSelectCity(data.getName());
                            }
                        }
                    }

                    @Override
                    public void onLocate() {
                        new Handler().postDelayed(() -> CityPicker.getInstance()
                                .locateComplete(new LocatedCity("深圳", "广东", "101280601"), LocateState.SUCCESS), 2000);
                    }
                })
                .show();
    }

    public void switchFragment(String fromTag, String toTag) {
        Fragment from = fm.findFragmentByTag(fromTag);
        Fragment to = fm.findFragmentByTag(toTag);
        if (mCurrentFragment != to) {
            mCurrentFragment = to;
            currentTag = toTag;
            FragmentTransaction transaction = fm.beginTransaction();
            if (!to.isAdded()) {//判断是否被添加到了Activity里面去了
                transaction.hide(from).add(R.id.replace, to).commit();
            }
            else {
                transaction.hide(from).show(to).commit();
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SocketService.getInstance().stop();
        System.exit(0);
    }
}
