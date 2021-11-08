package dongjoo.second.weightdiary.ui.history.presenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dongjoo.second.weightdiary.common.dto.ConfirmCancelDialogDto;
import dongjoo.second.weightdiary.common.dto.HistoryDescendingDto;
import dongjoo.second.weightdiary.common.entity.History;
import dongjoo.second.weightdiary.common.flag.ActivityRequestResultFlag;
import dongjoo.second.weightdiary.common.flag.DialogFlag;
import dongjoo.second.weightdiary.ui.base.presenter.BasePresenterImpl;
import dongjoo.second.weightdiary.ui.history.view.HistoryFragmentView;


public class HistoryFragmentPresenterImpl<V extends HistoryFragmentView> extends BasePresenterImpl<V> implements HistoryFragmentPresenter<V> {

    private boolean isHistoriesChecked;
    private boolean isHistoryFragmentPause;


    private List<History> histories;

    public HistoryFragmentPresenterImpl() {
    }


    @Override
    public void init() {


    }

    @Override
    public void initView() {
        getBaseView().setScrollViewOnScrollChangeListener();
        getBaseView().showProgressDialog();


        showHistories();
    }


    public void showHistories() {
        isHistoriesChecked = false;
        isHistoryFragmentPause = false;

        histories = getBaseView().getSharedPreferHistories();

        if (histories != null) {
            int historiesSize = histories.size();

            //삭제 버튼 show or gone
            if (historiesSize > 0) {
                getBaseView().goneHistoryEmptyContent();
                getBaseView().showDeleteBtn();
                getBaseView().showSelectionBtn();
            } else {
                getBaseView().goneDeleteBtn();
                getBaseView().goneSelectionBtn();
                getBaseView().showHistoryEmptyContent();
            }

            //descending
            HistoryDescendingDto historyDescendingDto = new HistoryDescendingDto();
            Collections.sort(histories, historyDescendingDto);

            getBaseView().clearHistoriesAdapter(); //adapter clear
            getBaseView().setHistoriesViewByItem(histories);
        }else{
            getBaseView().showHistoryEmptyContent();
        }
        getBaseView().goneProgressDialog();

    }

    @Override
    public void onScrollChange(int difference) {
//        if (difference <= 50) {
//            getBaseView().showProgressDialog();
//        }
    }


    @Override
    public void onClickHistoryContent(int position) {

        int historiesSize = histories.size();

        History history = histories.get(position);
        boolean isChecked = history.isChecked();

        if (isChecked) {
            history.setChecked(false);
        } else {
            history.setChecked(true);
        }
        getBaseView().historiesAdapterNotifyItemChanged(position);

    }

    @Override
    public void onClickAllSelection() {
        getBaseView().showProgressDialog();


        if (histories != null) {
            int historiesSize = histories.size();
            boolean isChecked = isHistoriesChecked;


            for (History history : histories) {

                if (isChecked) {
                    history.setChecked(false);
                    isHistoriesChecked = false;
                } else {
                    history.setChecked(true);
                    isHistoriesChecked = true;
                }

            }

            getBaseView().historiesAdapterNotifyItemRangeChanged(0, historiesSize);
        }
        getBaseView().goneProgressDialog();
    }

    @Override
    public void onClickWrite() {
        getBaseView().showProgressDialog();
        getBaseView().goneProgressDialog();
        getBaseView().navigateToHistoryWriteActivity();

    }

    @Override
    public void onClickHistoryDelete() {
        getBaseView().showProgressDialog();

        if (histories != null) {
            List<History> selectedHistories = new ArrayList<>();

            for (History history : this.histories) {
                boolean isChecked = history.isChecked();

                if (isChecked) {
                    selectedHistories.add(history);
                }
            }

            int selectedHistoriesSize = selectedHistories.size();

            if (selectedHistoriesSize > 0) { //선택되면 다음으로 넘어간다.

                ConfirmCancelDialogDto confirmCancelDialogDto = new ConfirmCancelDialogDto();
                confirmCancelDialogDto.setHistories(selectedHistories);

                int flag = DialogFlag.HISTORY_DELETE_CONFIRM;
                int requestCode = ActivityRequestResultFlag.CONFIRM_CANCEL_DIALOG_HISTORY_DELETE_REQUEST;


                getBaseView().navigateToConfirmCancelDialogActivity(confirmCancelDialogDto, flag, requestCode);
            }

        }

        getBaseView().goneProgressDialog();

    }

    @Override
    public void onActivityResultForHistoryDeleteDialogResultOk() {
        //resume 자동 실행
    }

    @Override
    public void onClickHistorySharing(int position) {
        History history = this.histories.get(position);
        getBaseView().navigateToShare(history);

    }


    @Override
    public void onPause() {
        //fragment pause flag setting
        boolean isHistoryFragmentNewPause = this.isHistoryFragmentPause;

        if (!isHistoryFragmentNewPause) {
            this.isHistoryFragmentPause = true;
        }
    }

    @Override
    public void onResume() {
        boolean isHistoryFragmentNewPause = this.isHistoryFragmentPause;

        if (isHistoryFragmentNewPause) {
            showHistories();
        }
    }


}

