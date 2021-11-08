package dongjoo.second.weightdiary.ui.main.presenter;

import dongjoo.second.weightdiary.ui.base.presenter.BasePresenter;
import dongjoo.second.weightdiary.ui.main.view.MainView;

public interface MainPresenter<V extends MainView> extends BasePresenter<V> {
    void onResume();

    void onBackPressed();

    void onActivityResultForAppDestroyOk();
    // void onRequestPermissionsResultForSMSReceive(int[] grantResults);

  //  void onClickLogout();

}
