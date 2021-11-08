package dongjoo.second.weightdiary.common.dialog.confirmcancel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dongjoo.second.weightdiary.R;
import dongjoo.second.weightdiary.common.dialog.confirmcancel.presenter.ConfirmCancelPresenter;
import dongjoo.second.weightdiary.common.dialog.confirmcancel.presenter.ConfirmCancelPresenterImpl;
import dongjoo.second.weightdiary.common.dialog.confirmcancel.view.ConfirmCancelView;
import dongjoo.second.weightdiary.common.dto.ConfirmCancelDialogDto;
import dongjoo.second.weightdiary.common.flag.ActivityRequestResultFlag;
import dongjoo.second.weightdiary.ui.base.activity.BaseActivity;

public class ConfirmCancelActivity extends BaseActivity implements ConfirmCancelView {

    private ConfirmCancelPresenter mPresenter;

    @BindView(R.id.tv_confirmcanceldialog_title)
    TextView mTitleTv;

    @BindView(R.id.adView)
    AdView mAdView;

    @BindString(R.string.ad_front_prod_1)
    String mAdFrontId;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_confirmcancel_dialog);
        getWindow().setLayout(android.view.WindowManager.LayoutParams.WRAP_CONTENT, android.view.WindowManager.LayoutParams.WRAP_CONTENT);
        ButterKnife.bind(this);

        MobileAds.initialize(this.mContext, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mPresenter = new ConfirmCancelPresenterImpl();
        mPresenter.onAttach(this);

        //배너 광고
        AdRequest adRequest_banner = new AdRequest.Builder().build();
        this.mAdView.loadAd(adRequest_banner);

        this.showProgressDialog();
        this.mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                init();
                goneProgressDialog();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                init();
                goneProgressDialog();
            }
        });


        //전면 광고
        AdRequest adRequest_front = new AdRequest.Builder().build();

        mInterstitialAd.load(this, mAdFrontId, adRequest_front,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;

                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                mPresenter.onAdDismissedFullScreenContent();
                                Log.d("TAG", "The ad was dismissed.");
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                Log.d("TAG", "The ad failed to show.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                mInterstitialAd = null;
                                Log.d("TAG", "The ad was shown.");
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
                    }
                });




    }

    @Override
    protected void init() {
        ConfirmCancelDialogDto confirmCancelDialogDto = (ConfirmCancelDialogDto) getIntent().getExtras().getSerializable("confirmCancelDialogDto");
        int flag = getIntent().getIntExtra("flag", 0);

        this.mPresenter.init(confirmCancelDialogDto, flag);


    }

    @Override
    public void setTitleContent(String content) {
        this.mTitleTv.setText(content);
    }

    @OnClick(R.id.btn_confirmcanceldialog_confirm)
    public void onClickConfirm() {
        this.mPresenter.onClickConfirm();
    }

    @OnClick(R.id.btn_confirmcanceldialog_cancel)
    public void onClickCancel() {
        this.mPresenter.onClickCancel();
    }

    @Override
    public void navigateToBackWithResultOk() {
        Intent intent = getIntent();
        if (getParent() == null) {
            setResult(ActivityRequestResultFlag.RESULT_OK, intent);
        } else {
            getParent().setResult(ActivityRequestResultFlag.RESULT_OK, intent);
        }
        finish();
    }

    @Override
    public void showAdFront() {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(ConfirmCancelActivity.this);
        } else {
            this.mPresenter.onAdDismissedFullScreenContent();
        }
    }

    @Override
    public void navigateToBack() {
        finish();
    }
}
