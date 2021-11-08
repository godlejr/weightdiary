package dongjoo.second.weightdiary.ui.setting.presenter;

import dongjoo.second.weightdiary.ui.base.presenter.BasePresenter;
import dongjoo.second.weightdiary.ui.setting.view.SettingFragmentView;


public interface SettingFragmentPresenter<V extends SettingFragmentView> extends BasePresenter<V> {

    void onPause();

    void onResume();

    void onClickUserWrite();

    void onClickMail();

    void onClickPromotion();

    void onClickComment();
}
