package dongjoo.second.weightdiary.ui.home.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dongjoo.second.weightdiary.R;
import dongjoo.second.weightdiary.ui.base.fragment.BaseFragment;
import dongjoo.second.weightdiary.ui.home.presenter.HomeFragmentPresenter;
import dongjoo.second.weightdiary.ui.home.presenter.HomeFragmentPresenterImpl;
import dongjoo.second.weightdiary.ui.home.view.HomeFragmentView;
import dongjoo.second.weightdiary.ui.userwrite.activity.UserWriteActivity;

public class HomeFragment extends BaseFragment implements HomeFragmentView {

    private HomeFragmentPresenter mPresenter;

    @BindView(R.id.tv_homefragment_notice)
    TextView mNoticeTv;


    @BindView(R.id.tv_homefragment_startdays_val)
    TextView mStartdaysVal;

    @BindView(R.id.tv_homefragment_quitperiod_val)
    TextView mQuitperiodVal;

    @BindView(R.id.tv_homefragment_life_val)
    TextView mLifeVal;

    @BindView(R.id.tv_homefragment_money_val)
    TextView mMoneyVal;


    @BindView(R.id.tv_homefragment_val1)
    TextView mTv1_20mu;

    @BindView(R.id.tv_homefragment_val2)
    TextView mTv2_8h;

    @BindView(R.id.tv_homefragment_val3)
    TextView mTv3_24h;

    @BindView(R.id.tv_homefragment_val4)
    TextView mTv4_48h;

    @BindView(R.id.tv_homefragment_val5)
    TextView mTv5_72h;

    @BindView(R.id.tv_homefragment_val6)
    TextView mTv6_2w;

    @BindView(R.id.tv_homefragment_val7)
    TextView mTv7_3mo;

    @BindView(R.id.tv_homefragment_val8)
    TextView mTv8_9mo;

    @BindView(R.id.tv_homefragment_val9)
    TextView mTv9_1y;

    @BindView(R.id.tv_homefragment_val10)
    TextView mTv10_5y;

    @BindView(R.id.tv_homefragment_val11)
    TextView mTv11_10y;

    @BindView(R.id.tv_homefragment_val5_9)
    TextView mTv12_1w;

    @BindView(R.id.tv_homefragment_val6_1)
    TextView mTv13_3w;

    @BindView(R.id.tv_homefragment_val6_2)
    TextView mTv14_4w;

    @BindView(R.id.tv_homefragment_val6_25)
    TextView mTv15_6w;

    @BindView(R.id.tv_homefragment_val6_3)
    TextView mTv16_2m;

    @BindView(R.id.tv_homefragment_val6_4)
    TextView mTv17_2_5m;

    @BindView(R.id.tv_homefragment_val7_1)
    TextView mTv18_4_5m;

    @BindView(R.id.tv_homefragment_val7_2)
    TextView mTv19_6m;


    @BindView(R.id.pg_homefragment_1)
    ProgressBar mPg1_20mu;

    @BindView(R.id.pg_homefragment_2)
    ProgressBar mPg2_8h;

    @BindView(R.id.pg_homefragment_3)
    ProgressBar mPg3_24h;

    @BindView(R.id.pg_homefragment_4)
    ProgressBar mPg4_48h;

    @BindView(R.id.pg_homefragment_5)
    ProgressBar mPg5_72h;

    @BindView(R.id.pg_homefragment_6)
    ProgressBar mPg6_2w;

    @BindView(R.id.pg_homefragment_7)
    ProgressBar mPg7_3mo;

    @BindView(R.id.pg_homefragment_8)
    ProgressBar mPg8_9mo;

    @BindView(R.id.pg_homefragment_9)
    ProgressBar mPg9_1y;

    @BindView(R.id.pg_homefragment_10)
    ProgressBar mPg10_5y;

    @BindView(R.id.pg_homefragment_11)
    ProgressBar mPg11_10y;

    @BindView(R.id.pg_homefragment_5_9)
    ProgressBar mPg12_1w;

    @BindView(R.id.pg_homefragment_6_1)
    ProgressBar mPg13_3w;

    @BindView(R.id.pg_homefragment_6_2)
    ProgressBar mPg14_4w;

    @BindView(R.id.pg_homefragment_6_25)
    ProgressBar mPg15_6w;

    @BindView(R.id.pg_homefragment_6_3)
    ProgressBar mPg16_2m;

    @BindView(R.id.pg_homefragment_6_4)
    ProgressBar mPg17_2_5m;

    @BindView(R.id.pg_homefragment_7_1)
    ProgressBar mPg18_4_5m;

    @BindView(R.id.pg_homefragment_7_2)
    ProgressBar mPg19_6m;


    @BindView(R.id.ll_homefragment_user)
    LinearLayout mUser;

    @BindView(R.id.ll_homefragment_userempty)
    LinearLayout mUserEmpty;


    @BindView(R.id.tv_homefragment_mylevel)
    TextView mMyLevelTv;


    @BindColor(R.color.darkGray)
    int mDarkGrayColor;

    @BindColor(R.color.pointColor)
    int mPointColor;

    @BindView(R.id.adView)
    AdView mAdView;

    @BindString(R.string.custom_level1)
    String customLevel1;

    @BindString(R.string.custom_level2)
    String customLevel2;

    @BindString(R.string.custom_level3)
    String customLevel3;

    @BindString(R.string.custom_level4)
    String customLevel4;

    @BindString(R.string.custom_level5)
    String customLevel5;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();

        this.mPresenter = new HomeFragmentPresenterImpl<HomeFragmentView>();
        this.mPresenter.onAttach(this);

        init();

        MobileAds.initialize(this.mContext, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });




    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initView();

        AdRequest adRequest = new AdRequest.Builder().build();
        this.mAdView.loadAd(adRequest);



        return view;
    }

    @Override
    protected void init() {
        this.mPresenter.init();
    }

    @Override
    protected void initView() {
        this.mPresenter.initView();
    }


    @Override
    public void showUserContent() {
        if (this.mUser.getVisibility() == View.GONE) {
            this.mUser.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void goneUserContent() {
        if (this.mUser.getVisibility() == View.VISIBLE) {
            this.mUser.setVisibility(View.GONE);
        }
    }

    @Override
    public void showUserEmptyContent() {
        if (this.mUserEmpty.getVisibility() == View.GONE) {
            this.mUserEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void goneUserEmptyContent() {
        if (this.mUserEmpty.getVisibility() == View.VISIBLE) {
            this.mUserEmpty.setVisibility(View.GONE);
        }
    }


    @Override
    public void setmTv12_1wContent(String content) {
        this.mTv12_1w.setText(content);
    }

    @Override
    public void setmTv13_3wContent(String content) {
        this.mTv13_3w.setText(content);
    }

    @Override
    public void setmTv14_4wContent(String content) {
        this.mTv14_4w.setText(content);
    }

    @Override
    public void setmTv15_6wContent(String content) {
        this.mTv15_6w.setText(content);
    }

    @Override
    public void setmTv16_2mContent(String content) {
        this.mTv16_2m.setText(content);
    }

    @Override
    public void setmTv17_2_5mContent(String content) {
        this.mTv17_2_5m.setText(content);
    }

    @Override
    public void setmTv18_4_5mContent(String content) {
        this.mTv18_4_5m.setText(content);
    }

    @Override
    public void setmTv19_6mContent(String content) {
        this.mTv19_6m.setText(content);
    }


    @Override
    public String getmTv12_1wContent() {
        return mTv12_1w.getText().toString();
    }

    @Override
    public String getmTv13_3wContent() {
        return mTv13_3w.getText().toString();
    }

    @Override
    public String getmTv14_4wContent() {
        return mTv14_4w.getText().toString();
    }

    @Override
    public String getmTv15_6wContent() {
        return mTv15_6w.getText().toString();
    }

    @Override
    public String getmTv16_2mContent() {
        return mTv16_2m.getText().toString();
    }

    @Override
    public String getmTv17_2_5mContent() {
        return mTv17_2_5m.getText().toString();
    }

    @Override
    public String getmTv18_4_5mContent() {
        return mTv18_4_5m.getText().toString();
    }

    @Override
    public String getmTv19_6mContent() {
        return mTv19_6m.getText().toString();
    }

    @Override
    public String getmMyLevelTvContent() {
        return this.mMyLevelTv.getText().toString();
    }

    @Override
    public void setmMyLevelTvContent(String content) {
        this.mMyLevelTv.setText(content);
    }

    @Override
    public String getmNoticeTvContent() {
        return this.mNoticeTv.getText().toString();
    }

    @Override
    public void setmNoticeTvContent(String content) {
        this.mNoticeTv.setText(content);
    }

    @Override
    public String getmStartdaysValContent() {
        return this.mStartdaysVal.getText().toString();
    }

    @Override
    public void setmStartdaysValContent(String content) {
        this.mStartdaysVal.setText(content);
    }

    @Override
    public String getmQuitperiodValContent() {
        return mQuitperiodVal.getText().toString();
    }

    @Override
    public void setmQuitperiodValContent(String content) {
        this.mQuitperiodVal.setText(content);
    }

    @Override
    public String getmLifeValContent() {
        return this.mLifeVal.getText().toString();
    }

    @Override
    public void setmLifeValContent(String content) {
        this.mLifeVal.setText(content);
    }

    @Override
    public String getmMoneyValContent() {
        return this.mMoneyVal.getText().toString();
    }

    @Override
    public void setmMoneyValContent(String content) {
        this.mMoneyVal.setText(content);
    }


    //graph
    @Override
    public String getmTv1_20muContent() {
        return this.mTv1_20mu.getText().toString();
    }

    @Override
    public void setmTv1_20muContent(String content) {
        this.mTv1_20mu.setText(content);
    }


    @Override
    public String getmTv2_8hContent() {
        return this.mTv2_8h.getText().toString();
    }

    @Override
    public void setmTv2_8hContent(String content) {
        this.mTv2_8h.setText(content);
    }

    @Override
    public String getmTv3_24hContent() {
        return this.mTv3_24h.getText().toString();
    }

    @Override
    public void setmTv3_24hContent(String content) {
        this.mTv3_24h.setText(content);
    }

    @Override
    public void setmTv4_48hContent(String content) {
        this.mTv4_48h.setText(content);
    }

    @Override
    public String getmTv5_72h() {
        return this.mTv5_72h.getText().toString();
    }

    @Override
    public void setmTv5_72hContent(String content) {
        this.mTv5_72h.setText(content);
    }

    @Override
    public String getmTv6_2w() {
        return this.mTv6_2w.getText().toString();
    }

    @Override
    public void setmTv6_2wContent(String content) {
        this.mTv6_2w.setText(content);
    }

    @Override
    public String getmTv7_3mo() {
        return this.mTv7_3mo.getText().toString();
    }

    @Override
    public void setmTv7_3moContent(String content) {
        this.mTv7_3mo.setText(content);
    }

    @Override
    public String getmTv8_9mo() {
        return this.mTv7_3mo.getText().toString();
    }

    @Override
    public void setmTv8_9moContent(String content) {
        this.mTv8_9mo.setText(content);
    }

    @Override
    public String getmTv9_1y() {
        return this.mTv9_1y.getText().toString();
    }

    @Override
    public void setmTv9_1yContent(String content) {
        this.mTv9_1y.setText(content);
    }

    @Override
    public String getmTv10_5y() {
        return this.mTv10_5y.getText().toString();
    }

    @Override
    public void setmTv10_5yContent(String content) {
        this.mTv10_5y.setText(content);
    }

    @Override
    public String getmTv11_10y() {
        return this.mTv11_10y.getText().toString();
    }

    @Override
    public void setmTv11_10yContent(String content) {
        this.mTv11_10y.setText(content);
    }

    @Override
    public int getmPg1_20muContent() {
        return this.mPg1_20mu.getProgress();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setmPg1_20muContent(int progress, boolean anim) {
        this.mPg1_20mu.setProgress(progress, anim);
    }

    @Override
    public int getmPg2_8hContent() {
        return this.mPg2_8h.getProgress();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setmPg2_8hContent(int progress, boolean anim) {
        this.mPg2_8h.setProgress(progress, anim);

    }

    @Override
    public int getmPg3_24hContent() {
        return this.mPg3_24h.getProgress();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setmPg3_24hContent(int progress, boolean anim) {
        this.mPg3_24h.setProgress(progress, anim);

    }

    @Override
    public int getmPg4_48hContent() {
        return this.mPg4_48h.getProgress();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setmPg4_48hContent(int progress, boolean anim) {
        this.mPg4_48h.setProgress(progress, anim);

    }

    @Override
    public int getmPg5_72hContent() {
        return this.mPg5_72h.getProgress();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setmPg5_72hContent(int progress, boolean anim) {
        this.mPg5_72h.setProgress(progress, anim);

    }

    @Override
    public int getmPg6_2wContent() {
        return this.mPg6_2w.getProgress();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setmPg6_2wContent(int progress, boolean anim) {
        this.mPg6_2w.setProgress(progress, anim);

    }

    @Override
    public int getmPg7_3moContent() {
        return this.mPg7_3mo.getProgress();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setmPg7_3moContent(int progress, boolean anim) {
        this.mPg7_3mo.setProgress(progress, anim);

    }

    @Override
    public int getmPg8_9moContent() {
        return this.mPg8_9mo.getProgress();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setmPg8_9moContent(int progress, boolean anim) {
        this.mPg8_9mo.setProgress(progress, anim);

    }

    @Override
    public int getmPg9_1yContent() {
        return this.mPg9_1y.getProgress();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setmPg9_1yContent(int progress, boolean anim) {
        this.mPg9_1y.setProgress(progress, anim);

    }

    @Override
    public int getmPg10_5yContent() {
        return this.mPg10_5y.getProgress();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setmPg10_5yContent(int progress, boolean anim) {
        this.mPg10_5y.setProgress(progress, anim);

    }

    @Override
    public int getmPg11_10yContent() {
        return this.mPg11_10y.getProgress();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setmPg11_10yContent(int progress, boolean anim) {
        this.mPg11_10y.setProgress(progress, anim);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setmPg12_1wContent(int progress, boolean anim) {
        this.mPg12_1w.setProgress(progress, anim);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setmPg13_3wContent(int progress, boolean anim) {
        this.mPg13_3w.setProgress(progress, anim);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setmPg14_4wContent(int progress, boolean anim) {
        this.mPg14_4w.setProgress(progress, anim);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setmPg15_6wContent(int progress, boolean anim) {
        this.mPg15_6w.setProgress(progress, anim);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setmPg16_2mContent(int progress, boolean anim) {
        this.mPg16_2m.setProgress(progress, anim);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setmPg17_2_5mContent(int progress, boolean anim) {
        this.mPg17_2_5m.setProgress(progress, anim);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setmPg18_4_5mContent(int progress, boolean anim) {
        this.mPg18_4_5m.setProgress(progress, anim);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setmPg19_6mContent(int progress, boolean anim) {
        this.mPg19_6m.setProgress(progress, anim);
    }

    @Override
    public void setmTv1_20muTextWithDarkGrayColor() {
        this.mTv1_20mu.setTextColor(mDarkGrayColor);
    }

    @Override
    public void setmTv2_8hTextWithDarkGrayColor() {
        this.mTv2_8h.setTextColor(mDarkGrayColor);
    }

    @Override
    public void setmTv3_24hTextWithDarkGrayColor() {
        this.mTv3_24h.setTextColor(mDarkGrayColor);
    }

    @Override
    public void setmTv4_48hTextWithDarkGrayColor() {
        this.mTv4_48h.setTextColor(mDarkGrayColor);
    }

    @Override
    public void setmTv5_72hTextWithDarkGrayColor() {
        this.mTv5_72h.setTextColor(mDarkGrayColor);
    }

    @Override
    public void setmTv6_2wTextWithDarkGrayColor() {
        this.mTv6_2w.setTextColor(mDarkGrayColor);
    }

    @Override
    public void setmTv7_3moTextWithDarkGrayColor() {
        this.mTv7_3mo.setTextColor(mDarkGrayColor);
    }

    @Override
    public void setmTv8_9moTextWithDarkGrayColor() {
        this.mTv8_9mo.setTextColor(mDarkGrayColor);
    }

    @Override
    public void setmTv9_1yTextWithDarkGrayColor() {
        this.mTv9_1y.setTextColor(mDarkGrayColor);
    }

    @Override
    public void setmTv10_5yTextWithDarkGrayColor() {
        this.mTv10_5y.setTextColor(mDarkGrayColor);
    }

    @Override
    public void setmTv11_10yTextWithDarkGrayColor() {
        this.mTv11_10y.setTextColor(mDarkGrayColor);
    }


    @Override
    public void setmTv1_20muTextWithPointColor() {
        this.mTv1_20mu.setTextColor(mPointColor);
    }

    @Override
    public void setmTv2_8hTextWithPointColor() {
        this.mTv2_8h.setTextColor(mPointColor);
    }

    @Override
    public void setmTv3_24hTextWithPointColor() {
        this.mTv3_24h.setTextColor(mPointColor);
    }

    @Override
    public void setmTv4_48hTextWithPointColor() {
        this.mTv4_48h.setTextColor(mPointColor);
    }

    @Override
    public void setmTv5_72hTextWithPointColor() {
        this.mTv5_72h.setTextColor(mPointColor);
    }

    @Override
    public void setmTv6_2wTextWithPointColor() {
        this.mTv6_2w.setTextColor(mPointColor);
    }

    @Override
    public void setmTv7_3moTextWithPointColor() {
        this.mTv7_3mo.setTextColor(mPointColor);
    }

    @Override
    public void setmTv8_9moTextWithPointColor() {
        this.mTv8_9mo.setTextColor(mPointColor);
    }

    @Override
    public void setmTv9_1yTextWithPointColor() {
        this.mTv9_1y.setTextColor(mPointColor);
    }

    @Override
    public void setmTv10_5yTextWithPointColor() {
        this.mTv10_5y.setTextColor(mPointColor);
    }

    @Override
    public void setmTv11_10yTextWithPointColor() {
        this.mTv11_10y.setTextColor(mPointColor);
    }


    @Override
    public void setmTv12_1wTextWithDarkGrayColor() {
        this.mTv12_1w.setTextColor(mDarkGrayColor);
    }

    @Override
    public void setmTv13_3wTextWithDarkGrayColor() {
        this.mTv13_3w.setTextColor(mDarkGrayColor);
    }

    @Override
    public void setmTv14_4wTextWithDarkGrayColor() {
        this.mTv14_4w.setTextColor(mDarkGrayColor);
    }

    @Override
    public void setmTv15_6wTextWithDarkGrayColor() {
        this.mTv15_6w.setTextColor(mDarkGrayColor);
    }

    @Override
    public void setmTv16_2mTextWithDarkGrayColor() {
        this.mTv16_2m.setTextColor(mDarkGrayColor);
    }

    @Override
    public void setmTv17_2_5mTextWithDarkGrayColor() {
        this.mTv17_2_5m.setTextColor(mDarkGrayColor);
    }

    @Override
    public void setmTv18_4_5mTextWithDarkGrayColor() {
        this.mTv18_4_5m.setTextColor(mDarkGrayColor);
    }

    @Override
    public void setmTv19_6mTextWithDarkGrayColor() {
        this.mTv19_6m.setTextColor(mDarkGrayColor);
    }


    @Override
    public void setmTv12_1wTextWithPointColor() {
        this.mTv12_1w.setTextColor(mPointColor);
    }

    @Override
    public void setmTv13_3wTextWithPointColor() {
        this.mTv13_3w.setTextColor(mPointColor);
    }

    @Override
    public void setmTv14_4wTextWithPointColor() {
        this.mTv14_4w.setTextColor(mPointColor);
    }

    @Override
    public void setmTv15_6wTextWithPointColor() {
        this.mTv15_6w.setTextColor(mPointColor);
    }

    @Override
    public void setmTv16_2mTextWithPointColor() {
        this.mTv16_2m.setTextColor(mPointColor);
    }

    @Override
    public void setmTv17_2_5mTextWithPointColor() {
        this.mTv17_2_5m.setTextColor(mPointColor);
    }

    @Override
    public void setmTv18_4_5mTextWithPointColor() {
        this.mTv18_4_5m.setTextColor(mPointColor);
    }

    @Override
    public void setmTv19_6mTextWithPointColor() {
        this.mTv19_6m.setTextColor(mPointColor);
    }

    @OnClick(R.id.tv_homefragment_userwrite)
    public void onClickUserWrite() {
        this.mPresenter.onClickUserWrite();
    }

    @Override
    public void navigateToUserWriteActivity() {
        Intent intent = new Intent(this.mContext, UserWriteActivity.class);
        startActivity(intent);
    }

    @Override
    public String getCustomLevel1() {
        return customLevel1;
    }

    @Override
    public String getCustomLevel2() {
        return customLevel2;
    }

    @Override
    public String getCustomLevel3() {
        return customLevel3;
    }

    @Override
    public String getCustomLevel4() {
        return customLevel4;
    }

    @Override
    public String getCustomLevel5() {
        return customLevel5;
    }

    @Override
    public void onResume() {
        super.onResume();

        this.mPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.mPresenter.onPause();
    }


}
