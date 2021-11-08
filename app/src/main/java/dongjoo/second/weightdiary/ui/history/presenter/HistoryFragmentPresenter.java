package dongjoo.second.weightdiary.ui.history.presenter;

import dongjoo.second.weightdiary.ui.base.presenter.BasePresenter;
import dongjoo.second.weightdiary.ui.history.view.HistoryFragmentView;

public interface HistoryFragmentPresenter<V extends HistoryFragmentView> extends BasePresenter<V> {

    void onScrollChange(int difference);

    void onClickHistoryContent(int position);

    void onClickAllSelection();

    void onClickWrite();


    void onPause();

    void onResume();

    void onClickHistoryDelete();

    void onActivityResultForHistoryDeleteDialogResultOk();

    void onClickHistorySharing(int position);
}
