package dongjoo.second.weightdiary.ui.intro.presenter;


import dongjoo.second.weightdiary.ui.base.presenter.BasePresenterImpl;
import dongjoo.second.weightdiary.ui.intro.view.IntroView;

public class IntroPresenterImpl<V extends IntroView> extends BasePresenterImpl<V> implements IntroPresenter<V> {
    public IntroPresenterImpl() {
    }

    @Override
    public void init() {
        getBaseView().setFadeInAnimationListener();
        getBaseView().setSplashFadeInAnimation();
        getBaseView().showSplash();
    }

    @Override
    public void onDestroySplash() {
        getBaseView().navigateToMainActivity();

    }

    @Override
    public void onAnimationEnd() {
        getBaseView().setSplashFadeOutAnimation();
    }
}
