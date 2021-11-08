package dongjoo.second.weightdiary.ui.main.presenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dongjoo.second.weightdiary.common.dto.ConfirmCancelDialogDto;
import dongjoo.second.weightdiary.common.entity.User;
import dongjoo.second.weightdiary.common.flag.ActivityRequestResultFlag;
import dongjoo.second.weightdiary.common.flag.DialogFlag;
import dongjoo.second.weightdiary.ui.base.presenter.BasePresenterImpl;
import dongjoo.second.weightdiary.ui.main.view.MainView;

public class MainPresenterImpl<V extends MainView> extends BasePresenterImpl<V> implements MainPresenter<V> {

    public MainPresenterImpl() {

    }

    @Override
    public void init() {

        getBaseView().setToolbarLayout();
        getBaseView().setTabLayout();
        getBaseView().setTabAdapter();
        showTitle();

    }

    private void showTitle() {
        User user = getBaseView().getSharedPreferUser();

        String title = getBaseView().getMainTitle();

        if (user != null) {
            String startDays = user.getStartDays();

            Calendar getToday = Calendar.getInstance();
            getToday.setTime(new Date()); //금일 날짜

            String s_date = startDays;//"yyyy-MM-dd"
            Date date = null;

            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(s_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar cmpDate = Calendar.getInstance();
            cmpDate.setTime(date); //특정 일자

            long diffSec = (getToday.getTimeInMillis() - cmpDate.getTimeInMillis()) / 1000;
            long diffDays = diffSec / (24 * 60 * 60); //일자수 차이
            long diffHours = diffSec / (60 * 60); //시간 차이

            title = getBaseView().getWordFixStopsmoking() + " " + (diffDays + 1) + getBaseView().getWordFixDateCount();
        }

        getBaseView().showToolbarTitle(title);
    }

    @Override
    public void onResume() {
        showTitle();
    }

    @Override
    public void onBackPressed() {
        ConfirmCancelDialogDto confirmCancelDialogDto = new ConfirmCancelDialogDto();

        int flag = DialogFlag.APP_DESTROY_CONFIRM;
        int requestCode = ActivityRequestResultFlag.CONFIRM_CANCEL_DIALOG_APP_DESTROY_REQUEST;

        getBaseView().navigateToConfirmCancelDialogActivity(confirmCancelDialogDto, flag, requestCode);
    }

    @Override
    public void onActivityResultForAppDestroyOk() {
        getBaseView().destroyApp();
    }

//    @Override
//    public void onRequestPermissionsResultForSMSReceive(int[] grantResults) {
//        if (grantResults.length > 0 && grantResults[0] == PermissionFlag.PERMISSION_DENIED) {
//            //권한 등록 실패
//        }
//    }

//    @Override
//    public void onClickLogout() {
//
//        User user = getBaseView().getSharedPrersUser();
//
//        ConfirmCancelDialogDto confirmCancelDialogDto = new ConfirmCancelDialogDto();
//        confirmCancelDialogDto.setUser(user);
//
//        int flag = DialogFlag.LOGOUT_CONFIRM;
//        int requestCode = ActivityRequestResultFlag.CONFIRM_CANCEL_DIALOG_LOGOUT_REQUEST;
//        getBaseView().navigateToConfirmCancelDialogActivity(confirmCancelDialogDto, flag, requestCode);
//    }

//    @Override
//    public void onActivityResultForLogoutResultOk() {
//        getBaseView().navigateToLoginActivity();
//    }
}
