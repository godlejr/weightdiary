package dongjoo.second.weightdiary.ui.historywrite.view;


import dongjoo.second.weightdiary.ui.base.view.BaseView;

public interface HistoryWriteView extends BaseView {


    String getHistoryWriteTitle();

    void setmStmtTvContent(String content);

    void setmTextCountTvConten(String content);

    boolean ismSuccessRbtnChecked();

    boolean ismMediumRbtnChecked();

    String getmContentEt();

    void setmFinishBtnBackgroundColorPointColor();

    void setmFinishBtnBackgroundColorDarkGray();

    void setmFinishBtnClickable();

    void setmFinishBtnUnclickable();

    void onBackBtnClick();

    void setContentAddTextChangedListener();
}
