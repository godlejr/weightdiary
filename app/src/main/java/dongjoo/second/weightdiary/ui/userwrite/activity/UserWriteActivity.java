package dongjoo.second.weightdiary.ui.userwrite.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dongjoo.second.weightdiary.R;
import dongjoo.second.weightdiary.ui.base.activity.BaseActivity;
import dongjoo.second.weightdiary.ui.userwrite.presenter.UserWritePresenter;
import dongjoo.second.weightdiary.ui.userwrite.presenter.UserWritePresenterImpl;
import dongjoo.second.weightdiary.ui.userwrite.view.UserWriteView;


public class UserWriteActivity extends BaseActivity implements UserWriteView {

    private UserWritePresenter mPresenter;
    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mPresenter.onDateSet(year, monthOfYear + 1, dayOfMonth);
        }
    };

    private TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mPresenter.onTimeSet(hourOfDay, minute);
        }
    };

    private TimePickerDialog timePickerDialog;
    private DatePickerDialog datePickerDialog;

    @BindView(R.id.btn_writeactivity_finish)
    Button mFinishBtn;

    @BindView(R.id.tv_userwrite_date)
    TextView mDateTv;

    @BindView(R.id.tv_userwrite_time)
    TextView mTimeTv;


    @BindView(R.id.et_userwrite_count)
    EditText mCountEt;

    @BindView(R.id.et_userwrite_price)
    EditText mPriceEt;

    @BindView(R.id.in_userwrite_toolbar)
    View mToolbar;

    @BindView(R.id.adView)
    AdView mAdView;

    @BindString(R.string.title_user_write)
    String userWriteTitle;

    private IncludedToolbarLayout mIncludedToolbarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_write);

        ButterKnife.bind(this);



        MobileAds.initialize(this.mContext, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        this.mAdView.loadAd(adRequest);

        this.mPresenter = new UserWritePresenterImpl();
        this.mPresenter.onAttach(this);
        init();

    }


    @Override
    protected void init() {
        this.mPresenter.init();
    }

    @Override
    public void setDatePickerDialog(int year, int month, int day) {
        datePickerDialog = new DatePickerDialog(this, R.style.MySpinnerDateTimePickerStyle, onDateSetListener, year, month, day);
    }

    @Override
    public void setTimePickerDialog(int hour, int minute, boolean is24) {
        timePickerDialog = new TimePickerDialog(this, R.style.MySpinnerDateTimePickerStyle, onTimeSetListener, hour, minute, is24);
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
    public String getUserWriteTitle() {
        return userWriteTitle;
    }

    @OnClick(R.id.tv_userwrite_date)
    public void onClickDate() {
        this.mPresenter.onClickDate();
    }

    @OnClick(R.id.tv_userwrite_time)
    public void onClickTime() {
        this.mPresenter.onClickTime();
    }

    @Override
    public void setmDateTvContent(String content) {
        this.mDateTv.setText(content);
    }

    @Override
    public void setmTimeTvContent(String content) {
        this.mTimeTv.setText(content);
    }

    @Override
    public void setmCountEtContent(String content) {
        this.mCountEt.setText(content);
    }

    @Override
    public void setmPriceEtContent(String content) {
        this.mPriceEt.setText(content);
    }

    @Override
    public void showTimePickDialog() {
        timePickerDialog.show();
    }

    @Override
    public void showDatePickDialog() {
        datePickerDialog.show();
    }

    @Override
    public String getmDateTvContent() {
        return mDateTv.getText().toString();
    }

    @Override
    public String getmTimeTvContent() {
        return mTimeTv.getText().toString();
    }

    @Override
    public String getmCountEtContent() {
        return mCountEt.getText().toString();
    }

    @Override
    public String getmPriceEtContent() {
        return mPriceEt.getText().toString();
    }

    @OnClick(R.id.ib_toolbartextback_back)
    @Override
    public void onBackBtnClick() {
        this.mPresenter.onBackPressed();
    }


    @OnClick(R.id.btn_writeactivity_finish)
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


    static class IncludedToolbarLayout {

        @BindView(R.id.ib_toolbartextback_back)
        ImageButton mToolbarBackBtn;

        @BindView(R.id.tv_toolbartextback_title)
        TextView mToolbarTitle;
    }


}
