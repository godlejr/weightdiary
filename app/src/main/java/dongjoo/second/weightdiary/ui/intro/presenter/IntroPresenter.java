package dongjoo.second.weightdiary.ui.intro.presenter;


import dongjoo.second.weightdiary.ui.base.presenter.BasePresenter;
import dongjoo.second.weightdiary.ui.intro.view.IntroView;

/**
 * IntroView
 *
 * @author 김동주
 * @since 2021.10.07
 */
public interface IntroPresenter<V extends IntroView> extends BasePresenter<V> {
    void onDestroySplash();

    void onAnimationEnd();
}
