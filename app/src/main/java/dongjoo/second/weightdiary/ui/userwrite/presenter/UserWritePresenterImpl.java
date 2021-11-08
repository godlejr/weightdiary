package dongjoo.second.weightdiary.ui.userwrite.presenter;

import java.util.Calendar;

import dongjoo.second.weightdiary.common.entity.User;
import dongjoo.second.weightdiary.ui.base.presenter.BasePresenterImpl;
import dongjoo.second.weightdiary.ui.userwrite.view.UserWriteView;


public class UserWritePresenterImpl<V extends UserWriteView> extends BasePresenterImpl<V> implements UserWritePresenter<V> {


    public UserWritePresenterImpl() {
    }


    @Override
    public void init() {

        getBaseView().setToolbarLayout();

        getBaseView().showToolbarTitle(getBaseView().getUserWriteTitle());
        getBaseView().showProgressDialog();


        User user = getBaseView().getSharedPreferUser();

        int year = 0;
        int month = 0;
        int day = 0;
        int hour = 0;
        int minute = 0;
        int meanOfSmoking = 20;
        int costOfSmoking = 0;

        if (user != null) {
            year = user.getYear();
            month = user.getMonth();
            day = user.getDay();
            hour = user.getHour();
            minute = user.getMinute();

            meanOfSmoking = user.getMeanOfSmoking();
            costOfSmoking = user.getCostOfSmoking();

        } else {
            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
            day = calendar.get(Calendar.DATE);
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);
        }

        //setting dialog (date, time)
        //24시간 여부
        boolean is24 = true;
        getBaseView().setDatePickerDialog(year, month -1 , day);
        getBaseView().setTimePickerDialog(hour, minute, is24);

        //초기 셋팅
        getBaseView().setmDateTvContent(year + "-" + month + "-" + day);
        getBaseView().setmTimeTvContent(hour + ":" + minute);
        getBaseView().setmCountEtContent(meanOfSmoking + "");
        getBaseView().setmPriceEtContent(costOfSmoking + "");

        getBaseView().goneProgressDialog();

    }


    @Override
    public void onBackPressed() {
        getBaseView().showProgressDialog();
        getBaseView().setActivityFinish();
        getBaseView().goneProgressDialog();
    }

    @Override
    public void onClickDate() {
        getBaseView().showDatePickDialog();
    }

    @Override
    public void onClickTime() {
        getBaseView().showTimePickDialog();
    }

    @Override
    public void onDateSet(int year, int monthOfYear, int dayOfMonth) {

        getBaseView().setmDateTvContent(year + "-" + monthOfYear + "-" + dayOfMonth);

    }

    @Override
    public void onTimeSet(int hourOfDay, int minute) {
        getBaseView().setmTimeTvContent(hourOfDay + ":" + minute);

    }

    @Override
    public void onFinishBtnClick() {

        //getter usage
        String dateStr = getBaseView().getmDateTvContent();
        String timeStr = getBaseView().getmTimeTvContent();

        String[] dateStrArr = dateStr.trim().split("-");
        String[] timeStrArr = timeStr.trim().split(":");

        int year = Integer.parseInt(dateStrArr[0]);
        int month = Integer.parseInt(dateStrArr[1]);
        int day = Integer.parseInt(dateStrArr[2]);

        int hour = Integer.parseInt(timeStrArr[0]);
        int minute = Integer.parseInt(timeStrArr[1]);

        String countStr = getBaseView().getmCountEtContent();
        String priceStr = getBaseView().getmPriceEtContent();

        if(countStr.equals("")){
            countStr = "0";
            getBaseView().setmCountEtContent("0");
        }

        if(priceStr.equals("")){
            priceStr = "0";
            getBaseView().setmPriceEtContent("0");

        }

        int meanOfSmoking = Integer.parseInt(countStr);
        int costOfSmoking = Integer.parseInt(priceStr);

        //User checker
        User user = getBaseView().getSharedPreferUser();

        if (user != null) {
            user.setYear(year);
            user.setMonth(month);
            user.setDay(day);
            user.setStartDays(year + "-" + month + "-" + day);

            user.setHour(hour);
            user.setMinute(minute);
            user.setStartHours(hour + ":" + minute);

            user.setMeanOfSmoking(meanOfSmoking);
            user.setCostOfSmoking(costOfSmoking);

            getBaseView().setSharedPreferUser(user);

        } else {
            User newUser = new User();
            newUser.setYear(year);
            newUser.setMonth(month);
            newUser.setDay(day);
            newUser.setStartDays(year + "-" + month + "-" + day);

            newUser.setHour(hour);
            newUser.setMinute(minute);
            newUser.setStartHours(hour + ":" + minute);

            newUser.setMeanOfSmoking(meanOfSmoking);
            newUser.setCostOfSmoking(costOfSmoking);

            getBaseView().setSharedPreferUser(newUser);
        }

        getBaseView().showMessage(getBaseView().getActionWriteComplete());
        getBaseView().onBackBtnClick();
    }


}
