package dongjoo.second.weightdiary.common.dialog.confirmcancel.presenter;

import java.util.List;

import dongjoo.second.weightdiary.common.dialog.confirmcancel.view.ConfirmCancelView;
import dongjoo.second.weightdiary.common.dto.ConfirmCancelDialogDto;
import dongjoo.second.weightdiary.common.entity.History;
import dongjoo.second.weightdiary.common.flag.DialogFlag;
import dongjoo.second.weightdiary.ui.base.presenter.BasePresenterImpl;


public class ConfirmCancelPresenterImpl<V extends ConfirmCancelView> extends BasePresenterImpl<V> implements ConfirmCancelPresenter<V> {

    private int flag;

    private ConfirmCancelDialogDto confirmCancelDialogDto;


    @Override
    public void init(ConfirmCancelDialogDto confirmCancelDialogDto, int flag) {
        getBaseView().showProgressDialog();

        this.confirmCancelDialogDto = confirmCancelDialogDto;
        this.flag = flag;

        if (this.flag == DialogFlag.HISTORY_DELETE_CONFIRM) {
            getBaseView().setTitleContent(getBaseView().getHistoryDeleteConfirm());
        }

        if (this.flag == DialogFlag.APP_DESTROY_CONFIRM) {
            getBaseView().setTitleContent(getBaseView().getAppDestroyConfirm());
        }

        getBaseView().goneProgressDialog();
    }

    @Override
    public void onClickConfirm() {
        getBaseView().showProgressDialog();


        if (flag == DialogFlag.HISTORY_DELETE_CONFIRM) {
            List<History> histories = this.confirmCancelDialogDto.getHistories();
            getBaseView().removeAllSharedPreferHistory(histories);
            getBaseView().goneProgressDialog();

            getBaseView().showAdFront();
        }

        if (flag == DialogFlag.APP_DESTROY_CONFIRM) {
            getBaseView().goneProgressDialog();
            getBaseView().showAdFront();
        }



        getBaseView().goneProgressDialog();
    }

    @Override
    public void onClickCancel() {
        getBaseView().setActivityFinish();
    }


    @Override
    public void onAdDismissedFullScreenContent(){
        getBaseView().navigateToBackWithResultOk();
    }

}
