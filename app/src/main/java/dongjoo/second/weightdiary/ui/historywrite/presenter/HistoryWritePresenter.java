package dongjoo.second.weightdiary.ui.historywrite.presenter;

import dongjoo.second.weightdiary.ui.base.presenter.BasePresenter;
import dongjoo.second.weightdiary.ui.historywrite.view.HistoryWriteView;


public interface HistoryWritePresenter<V extends HistoryWriteView> extends BasePresenter<V> {
    void init();

    void onBackPressed();


    void onFinishBtnClick();

    void onClickHistoryStatus();

    String getHistoryStatusByRbtnChecked();

    void afterTextChanged(String content);
}
