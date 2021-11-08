package dongjoo.second.weightdiary.common.dialog.confirmcancel.view;


import dongjoo.second.weightdiary.ui.base.view.BaseView;

public interface ConfirmCancelView extends BaseView {
    void setTitleContent(String content);

    void navigateToBackWithResultOk();


    void showAdFront();

    void navigateToBack();
}
