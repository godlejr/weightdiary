package dongjoo.second.weightdiary.ui.intro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import dongjoo.second.weightdiary.R;
import dongjoo.second.weightdiary.ui.base.activity.BaseActivity;
import dongjoo.second.weightdiary.ui.intro.presenter.IntroPresenter;
import dongjoo.second.weightdiary.ui.intro.presenter.IntroPresenterImpl;
import dongjoo.second.weightdiary.ui.intro.view.IntroView;
import dongjoo.second.weightdiary.ui.main.activity.MainActivity;

public class IntroActivity extends BaseActivity implements IntroView, Animation.AnimationListener {

    @BindView(R.id.fl_intro)
    FrameLayout mFrameLayout;

    private IntroPresenter mPresenter;

    private Handler mSplashHandler;
    private Animation mFadeOutAnimation;
    private Animation mFadeInAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);

        mPresenter = new IntroPresenterImpl<IntroView>();
        mPresenter.onAttach(this);
        init();
    }


    protected void init() {
        this.mSplashHandler = new Handler();
        this.mFadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        this.mFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        this.mPresenter.init();
    }

    @Override
    public void setToolbarLayout() {

    }

    @Override
    public void showToolbarTitle(String message) {

    }



    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void showSplash() {
        this.mSplashHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.onDestroySplash();
            }
        }, 2000);
    }

    @Override
    public void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void setSplashFadeOutAnimation() {
        mFrameLayout.startAnimation(this.mFadeOutAnimation);
    }

    @Override
    public void setSplashFadeInAnimation() {
        mFrameLayout.startAnimation(this.mFadeInAnimation);

    }

    @Override
    public void setFadeInAnimationListener() {
        this.mFadeInAnimation.setAnimationListener(this);

    }



}
