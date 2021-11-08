package dongjoo.second.weightdiary.ui.historywrite.presenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dongjoo.second.weightdiary.common.entity.History;
import dongjoo.second.weightdiary.common.entity.User;
import dongjoo.second.weightdiary.common.flag.HistoryFlag;
import dongjoo.second.weightdiary.ui.base.presenter.BasePresenterImpl;
import dongjoo.second.weightdiary.ui.historywrite.view.HistoryWriteView;


public class HistoryWritePresenterImpl<V extends HistoryWriteView> extends BasePresenterImpl<V> implements HistoryWritePresenter<V> {

    private int status;
    private boolean isFinishClickable;

    public HistoryWritePresenterImpl() {
    }


    @Override
    public void init() {

        this.status = 1;
        this.isFinishClickable = false;

        getBaseView().setToolbarLayout();

        getBaseView().showToolbarTitle(getBaseView().getHistoryWriteTitle());
        getBaseView().showProgressDialog();


        User user = getBaseView().getSharedPreferUser();

        String stmt = getBaseView().getQuitSmokingStatement();

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

            stmt = getBaseView().getWordFixStopsmoking() + " " + (diffDays + 1) + getBaseView().getWordFixDateCount();

        }

        getBaseView().setmStmtTvContent(stmt);


        //listener 등록
        getBaseView().setContentAddTextChangedListener();

        getBaseView().goneProgressDialog();

    }


    @Override
    public void onBackPressed() {
        getBaseView().showProgressDialog();
        getBaseView().setActivityFinish();
        getBaseView().goneProgressDialog();
    }


    @Override
    public void onFinishBtnClick() {

        String text = getBaseView().getmContentEt();

        if (text.length() > 0) {

            SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            History history = new History();
            history.setStatus(this.status);
            history.setText(text);


            long now = System.currentTimeMillis();
            Date today = new Date(now);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String todayStr = sdf.format(today);
            String dateTitle = todayStr;

            User user = getBaseView().getSharedPreferUser();

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

                dateTitle = getBaseView().getWordFixStopsmoking() + " " + (diffDays + 1) +getBaseView().getWordFixDateCount() +" (" + todayStr + ")";
            }

            history.setDateTitle(dateTitle);

            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String todayTimeStr = sdf2.format(today);

            history.setCdate(todayTimeStr);

            getBaseView().setSharedPreferHistory(history);

            getBaseView().showMessage(getBaseView().getActionWriteComplete());
            getBaseView().setActivityFinish();

        }
    }

    @Override
    public void onClickHistoryStatus() {

        this.status = Integer.parseInt(getHistoryStatusByRbtnChecked());

    }

    @Override
    public String getHistoryStatusByRbtnChecked() {
        String status = HistoryFlag.HISTORY_1_STATUS; //init

        if (getBaseView().ismSuccessRbtnChecked()) {
            status = HistoryFlag.HISTORY_1_STATUS;

        } else if (getBaseView().ismMediumRbtnChecked()) {
            status = HistoryFlag.HISTORY_2_STATUS;

        }
        return status;
    }

    @Override
    public void afterTextChanged(String content) {
        if (content.length() > 0) {
            if (!isFinishClickable) {
                getBaseView().setmFinishBtnBackgroundColorPointColor();
                getBaseView().setmFinishBtnClickable();
                isFinishClickable = true;
            }
        } else {
            if (isFinishClickable) {
                getBaseView().setmFinishBtnBackgroundColorDarkGray();
                getBaseView().setmFinishBtnUnclickable();
                isFinishClickable = false;
            }
        }
    }

}
