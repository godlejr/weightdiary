package dongjoo.second.weightdiary.common.dialog.confirmcancel.presenter;


import dongjoo.second.weightdiary.common.dialog.confirmcancel.view.ConfirmCancelView;
import dongjoo.second.weightdiary.common.dto.ConfirmCancelDialogDto;
import dongjoo.second.weightdiary.ui.base.presenter.BasePresenter;

public interface ConfirmCancelPresenter<V extends ConfirmCancelView> extends BasePresenter<V> {
    void init(ConfirmCancelDialogDto confirmCancelDialogDto, int flag);

    void onClickConfirm();

    void onClickCancel();

    void onAdDismissedFullScreenContent();
}
