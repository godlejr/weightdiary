package dongjoo.second.weightdiary.ui.home.presenter;

import dongjoo.second.weightdiary.ui.base.presenter.BasePresenter;
import dongjoo.second.weightdiary.ui.home.view.HomeFragmentView;


public interface HomeFragmentPresenter<V extends HomeFragmentView> extends BasePresenter<V> {

    void onPause();

    void onResume();

    void onClickUserWrite();
}
