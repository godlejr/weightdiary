package dongjoo.second.weightdiary.ui.main.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import dongjoo.second.weightdiary.R;
import dongjoo.second.weightdiary.common.dialog.confirmcancel.activity.ConfirmCancelActivity;
import dongjoo.second.weightdiary.common.dto.ConfirmCancelDialogDto;
import dongjoo.second.weightdiary.common.flag.ActivityRequestResultFlag;
import dongjoo.second.weightdiary.ui.base.activity.BaseActivity;
import dongjoo.second.weightdiary.ui.history.fragment.HistoryFragment;
import dongjoo.second.weightdiary.ui.home.fragment.HomeFragment;
import dongjoo.second.weightdiary.ui.main.adapter.MainTabAdapter;
import dongjoo.second.weightdiary.ui.main.presenter.MainPresenter;
import dongjoo.second.weightdiary.ui.main.presenter.MainPresenterImpl;
import dongjoo.second.weightdiary.ui.main.view.MainView;
import dongjoo.second.weightdiary.ui.setting.fragment.SettingFragment;

public class MainActivity extends BaseActivity implements MainView, TabLayout.OnTabSelectedListener {

    private MainPresenter mPresenter;

    private IncludedToolbarLayout mIncludedToolbarLayout;

    //layout
    private InflatedTabLayout_1 mInflatedTabLayout_1;
    private InflatedTabLayout_2 mInflatedTabLayout_2;
    private InflatedTabLayout_3 mInflatedTabLayout_3;

    private MainTabAdapter mTabAdapter;


    @BindView(R.id.tl_mainactivity)
    TabLayout mTabLayout;

    @BindView(R.id.vp_mainactivity)
    ViewPager mViewPager;

    @BindView(R.id.in_mainactivity_toolbar)
    View mToolbar;


    @BindString(R.string.tab_title1)
    String tabTitle1;

    @BindString(R.string.tab_title2)
    String tabTitle2;

    @BindString(R.string.tab_title3)
    String tabTitle3;

    @BindString(R.string.title_main)
    String mainTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new MainPresenterImpl<MainView>();
        this.mPresenter.onAttach(this);

        init();

    }

    protected void init() {
        this.mPresenter.init();
    }

//
//    @Override
//    public void navigateToConfirmCancelDialogActivity(ConfirmCancelDialogDto confirmCancelDialogDto, int flag, int requestCode) {
//        Intent intent = new Intent(this.mContext, ConfirmCancelActivity.class);
//        intent.putExtra("flag", flag);
//        intent.putExtra("confirmCancelDialogDto", confirmCancelDialogDto);
//        startActivityForResult(intent, requestCode);
//    }


    @Override
    public void setToolbarLayout() {
        mIncludedToolbarLayout = new IncludedToolbarLayout();
        ButterKnife.bind(mIncludedToolbarLayout, mToolbar);
    }

    @Override
    public void showToolbarTitle(String message) {
        mIncludedToolbarLayout.mToolbarTitle.setText(message);
    }

//    @OnClick(R.id.ib_toolbarmain_logout)
//    public void onClickLogoutBtn(){
//        this.mPresenter.onClickLogout();
//    }

    @Override
    public void setTabLayout() {
        View tab_1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_tab_text, null, false);
        View tab_2 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_tab_text, null, false);
        View tab_3 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_tab_text, null, false);

        mInflatedTabLayout_1 = new InflatedTabLayout_1();
        mInflatedTabLayout_2 = new InflatedTabLayout_2();
        mInflatedTabLayout_3 = new InflatedTabLayout_3();


        ButterKnife.bind(mInflatedTabLayout_1, tab_1);
        ButterKnife.bind(mInflatedTabLayout_2, tab_2);
        ButterKnife.bind(mInflatedTabLayout_3, tab_3);


        mInflatedTabLayout_1.mTabTitle.setText(tabTitle1);
        mInflatedTabLayout_2.mTabTitle.setText(tabTitle2);
        mInflatedTabLayout_3.mTabTitle.setText(tabTitle3);


        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());

        mTabLayout.getTabAt(0).setCustomView(tab_1);
        mTabLayout.getTabAt(1).setCustomView(tab_2);
        mTabLayout.getTabAt(2).setCustomView(tab_3);


        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(this);

    }

    @Override
    public void setTabAdapter() {
        mTabAdapter = new MainTabAdapter(getSupportFragmentManager(), this);

        mTabAdapter.addFragment(new HomeFragment());
        mTabAdapter.addFragment(new HistoryFragment());
        mTabAdapter.addFragment(new SettingFragment());

        mViewPager.setAdapter(mTabAdapter);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

//        switch (requestCode) {
//            case PermissionFlag.PERMISSION_REQUEST_SMS_RECEIVE:
//                this.mPresenter.onRequestPermissionsResultForSMSReceive(grantResults);
//                break;
//        }

    }

    @Override
    public String getMainTitle() {
        return mainTitle;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    static class IncludedToolbarLayout {

        @BindView(R.id.tv_toolbarmain_logo)
        TextView mToolbarTitle;


    }

    static class InflatedTabLayout_1 {
        @BindView(R.id.tv_all_tab)
        TextView mTabTitle;
    }

    static class InflatedTabLayout_2 {
        @BindView(R.id.tv_all_tab)
        TextView mTabTitle;
    }

    static class InflatedTabLayout_3 {
        @BindView(R.id.tv_all_tab)
        TextView mTabTitle;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ActivityRequestResultFlag.CONFIRM_CANCEL_DIALOG_APP_DESTROY_REQUEST:
                switch (resultCode) {
                    case ActivityRequestResultFlag.RESULT_OK:
                        this.mPresenter.onActivityResultForAppDestroyOk();
                        break;
                }
                break;
        }
    }


    @Override
    public void destroyApp() {
        if (Build.VERSION.SDK_INT >= 21) {
            finishAndRemoveTask();
        } else {
            finish();
        }

        System.exit(0);
    }

    @Override
    protected void onResume() {

        if (!this.isDestroyed()) {
            super.onResume();
            mPresenter.onResume();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        this.mPresenter.onBackPressed();
    }

    @Override
    public void navigateToConfirmCancelDialogActivity(ConfirmCancelDialogDto confirmCancelDialogDto, int flag, int requestCode) {
        Intent intent = new Intent(this.mContext, ConfirmCancelActivity.class);
        intent.putExtra("flag", flag);
        intent.putExtra("confirmCancelDialogDto", confirmCancelDialogDto);
        startActivityForResult(intent, requestCode);
    }
}
