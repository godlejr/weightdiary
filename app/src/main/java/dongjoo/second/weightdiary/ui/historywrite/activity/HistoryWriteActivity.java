package dongjoo.second.weightdiary.ui.historywrite.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

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
import dongjoo.second.weightdiary.ui.base.activity.BaseActivity;
import dongjoo.second.weightdiary.ui.historywrite.presenter.HistoryWritePresenter;
import dongjoo.second.weightdiary.ui.historywrite.presenter.HistoryWritePresenterImpl;
import dongjoo.second.weightdiary.ui.historywrite.view.HistoryWriteView;


public class HistoryWriteActivity extends BaseActivity implements HistoryWriteView, TextWatcher {

    private HistoryWritePresenter mPresenter;

    @BindView(R.id.rbtn_historywrite_success)
    RadioButton mSuccessRbtn;

    @BindView(R.id.rbtn_historywrite_medium)
    RadioButton mMediunRbtn;


    @BindView(R.id.btn_historywrite_finish)
    Button mFinishBtn;

    @BindView(R.id.tv_historywrite_stmt)
    TextView mStmtTv;

    @BindView(R.id.tv_historywrite_textcount)
    TextView mTextCountTv;

    @BindView(R.id.et_historywrite_content)
    EditText mContentEt;

    @BindColor(R.color.pointColor)
    int pointColor;

    @BindColor(R.color.darkGray)
    int darkGray;

    @BindView(R.id.in_historywrite_toolbar)
    View mToolbar;

    @BindView(R.id.adView)
    AdView mAdView;



    @BindString(R.string.title_history_write)
    String HistoryWriteTitle;


    private IncludedToolbarLayout mIncludedToolbarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_write);

        ButterKnife.bind(this);



        MobileAds.initialize(this.mContext, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        this.mAdView.loadAd(adRequest);

        this.mPresenter = new HistoryWritePresenterImpl();
        this.mPresenter.onAttach(this);
        init();

    }

    @Override
    public String getHistoryWriteTitle() {
        return HistoryWriteTitle;
    }

    @Override
    protected void init() {
        this.mPresenter.init();
    }


    @Override
    public void setToolbarLayout() {
        this.mIncludedToolbarLayout = new IncludedToolbarLayout();
        ButterKnife.bind(mIncludedToolbarLayout, mToolbar);
    }

    @Override
    public void showToolbarTitle(String message) {
        this.mIncludedToolbarLayout.mToolbarTitle.setText(message);
    }

    @Override
    public void setmStmtTvContent(String content){
        this.mStmtTv.setText(content);
    }

    @Override
    public void setmTextCountTvConten(String content){
        this.mTextCountTv.setText(content);
    }

    @Override
    public boolean ismSuccessRbtnChecked() {
        return mSuccessRbtn.isChecked();
    }

    @Override
    public boolean ismMediumRbtnChecked() {
        return mMediunRbtn.isChecked();
    }

    @OnClick({R.id.rbtn_historywrite_success, R.id.rbtn_historywrite_medium})
    public void onClickHistoryStatus() {
        this.mPresenter.onClickHistoryStatus();
    }

    @Override
    public String getmContentEt() {
        return this.mContentEt.getText().toString();
    }

    @Override
    public void setmFinishBtnBackgroundColorPointColor() {
        this.mFinishBtn.setBackgroundColor(pointColor);
    }

    @Override
    public void setmFinishBtnBackgroundColorDarkGray() {
        this.mFinishBtn.setBackgroundColor(darkGray);
    }

    @Override
    public void setmFinishBtnClickable() {
        this.mFinishBtn.setClickable(true);
    }

    @Override
    public void setmFinishBtnUnclickable() {
        this.mFinishBtn.setClickable(false);
    }


    @OnClick(R.id.ib_toolbartextback_back)
    @Override
    public void onBackBtnClick() {
        this.mPresenter.onBackPressed();
    }


    @OnClick(R.id.btn_historywrite_finish)
    public void onFinishBtnClick() {
        this.mPresenter.onFinishBtnClick();
    }

    @Override
    public void onBackPressed() {
        this.mPresenter.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setContentAddTextChangedListener() {
        this.mContentEt.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String input = this.mContentEt.getText().toString();
        this.mTextCountTv.setText(input.length()+"");
    }

    @Override
    public void afterTextChanged(Editable s) {
        String content = this.mContentEt.getText().toString();
        mPresenter.afterTextChanged(content);
    }


    static class IncludedToolbarLayout {

        @BindView(R.id.ib_toolbartextback_back)
        ImageButton mToolbarBackBtn;

        @BindView(R.id.tv_toolbartextback_title)
        TextView mToolbarTitle;
    }


}
