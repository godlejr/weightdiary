package dongjoo.second.weightdiary.ui.intro.view;


import dongjoo.second.weightdiary.ui.base.view.BaseView;

/**
 * IntroView
 *
 * @author 김동주
 * @since 2021.10.07
 */
public interface IntroView extends BaseView {

    void showSplash();

    void navigateToMainActivity();

    void setSplashFadeOutAnimation();

    void setSplashFadeInAnimation();

    void setFadeInAnimationListener();
}
