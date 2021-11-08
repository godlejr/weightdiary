package dongjoo.second.weightdiary.ui.history.view;

import java.util.List;

import dongjoo.second.weightdiary.common.dto.ConfirmCancelDialogDto;
import dongjoo.second.weightdiary.common.entity.History;
import dongjoo.second.weightdiary.ui.base.view.BaseView;


public interface HistoryFragmentView extends BaseView {
    public void setHistoriesViewByItem(List<History> histories);

    public void clearHistoriesAdapter();

    public void setScrollViewOnScrollChangeListener();

    void showSelectionBtn();

    void goneSelectionBtn();

    void showDeleteBtn();

    void goneDeleteBtn();


    void historiesAdapterNotifyItemRangeInserted(int startPosition, int itemCount);

    void historiesAdapterNotifyItemChanged(int position);

    void showWriteBtn();

    void hideWriteBtn();

    void onClickAllSelection();

    void onClickWrite();

    void showHistoryEmptyContent();

    void goneHistoryEmptyContent();

    void historiesAdapterNotifyItemRangeChanged(int startPosition, int size);

    void navigateToHistoryWriteActivity();

    void navigateToConfirmCancelDialogActivity(ConfirmCancelDialogDto confirmCancelDialogDto, int flag, int requestCode);

    void navigateToShare(History history);
}
