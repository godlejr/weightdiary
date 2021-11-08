package dongjoo.second.weightdiary.ui.setting.presenter;

import dongjoo.second.weightdiary.ui.base.presenter.BasePresenterImpl;
import dongjoo.second.weightdiary.ui.setting.view.SettingFragmentView;


public class SettingFragmentPresenterImpl<V extends SettingFragmentView> extends BasePresenterImpl<V> implements SettingFragmentPresenter<V> {

//    private HomeFragmentInteractor mInteractor;

    public SettingFragmentPresenterImpl() {
    }


    @Override
    public void init() {
//        this.mInteractor = new HomeFragmentInteractorImpl<HomeFragmentPresenter>();
//        this.mInteractor.onAttach(this);
    }

    @Override
    public void initView() {

    }


    @Override
    public void onPause() {
        //fragment pause flag setting
//        boolean isHomeFragmentPause = this.mInteractor.isHomeFragmentPause();
//
//        if (!isHomeFragmentPause) {
//            this.mInteractor.setHomeFragmentPause(true);
//        }
    }

    @Override
    public void onResume() {
        initView();
    }

    @Override
    public void onClickUserWrite() {
        getBaseView().navigateToUserWriteActivity();
    }

    @Override
    public void onClickMail() {
        getBaseView().navigateToDevEmail();
    }

    @Override
    public void onClickPromotion() {
        getBaseView().navigateToShareApp();
    }

    @Override
    public void onClickComment() {
        getBaseView().navigateToGoogleAppStore();
    }
}
