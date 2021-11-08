package dongjoo.second.weightdiary.ui.home.presenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dongjoo.second.weightdiary.common.entity.User;
import dongjoo.second.weightdiary.common.utils.StringUtil;
import dongjoo.second.weightdiary.ui.base.presenter.BasePresenterImpl;
import dongjoo.second.weightdiary.ui.home.view.HomeFragmentView;


public class HomeFragmentPresenterImpl<V extends HomeFragmentView> extends BasePresenterImpl<V> implements HomeFragmentPresenter<V> {


    public HomeFragmentPresenterImpl() {
    }


    @Override
    public void init() {
    }

    @Override
    public void initView() {

        //명언 투척
        getBaseView().setmNoticeTvContent(getBaseView().getQuitSmokingStatement());

        User user = getBaseView().getSharedPreferUser();

        int year = 0;
        int month = 0;
        int day = 0;
        int meanOfSmoking = 0;
        int costOfSmoking = 0;

        if (user != null) {

            getBaseView().goneUserEmptyContent();
            getBaseView().showUserContent();

            year = user.getYear();
            month = user.getMonth();
            day = user.getDay();
            meanOfSmoking = user.getMeanOfSmoking();
            costOfSmoking = user.getCostOfSmoking();

            String startdaysVal = year + "-" + month + "-" + day;

            //금연일자
            getBaseView().setmStartdaysValContent(startdaysVal);


            //날짜 시간 차이
            String startDays = user.getStartDays();
            int hour = user.getHour();
            int minute = user.getMinute();

            Calendar getToday = Calendar.getInstance();
            getToday.setTime(new Date()); //금일 날짜

            String s_date = startDays + " " + hour + ":" + minute;//"yyyy-MM-dd"
            Date date = null;

            try {
                date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(s_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar cmpDate = Calendar.getInstance();
            cmpDate.setTime(date); //특정 일자

            long diffSec = (getToday.getTimeInMillis() - cmpDate.getTimeInMillis()) / 1000;
            long diffDays = diffSec / (24 * 60 * 60); //일자수 차이
            long diffHours = (diffSec / (60 * 60)); //시간 차이
            long diffMinutes = (diffSec / (60)); //분 차이

            long diffHoursMod = (diffSec / (60 * 60)) % 24; //시간 차이
            long diffMinutesMod = (diffSec / (60)) % 60; //분 차이

            String daysStr = getBaseView().getWordFixDays();
            String minutesStr = getBaseView().getWordFixMinutes();
            String hoursStr = getBaseView().getWordFixHours();
            String passedStr = getBaseView().getWordFixPassed();
            String extensionStr = getBaseView().getWordFixExtension();


            String quitPeriodVal = diffHoursMod + hoursStr + " " + diffMinutesMod + minutesStr + " " + passedStr;

            if (diffDays > 0 || diffDays < 0) {
                quitPeriodVal = diffDays + daysStr + " " + diffHoursMod + hoursStr + " " + diffMinutesMod + minutesStr + " " + passedStr;
            }

            getBaseView().setmQuitperiodValContent(quitPeriodVal);


            //연장 수명 (나누기 8)
            long lifeSec = diffSec / 8;
            long lifeDays = lifeSec / (24 * 60 * 60); //일자수 차이
            long lifeHours = (lifeSec / (60 * 60)); //시간 차이
            long lifeMinutes = (lifeSec / (60));  //분 차이


            long lifeHoursMod = (lifeSec / (60 * 60)) % 24; //시간 차이
            long lifeMinutesMod = (lifeSec / (60)) % 60; //분 차이

            String lifeVal = lifeHoursMod + hoursStr + " " + lifeMinutesMod + minutesStr + " " + extensionStr;

            if (lifeDays > 0 || lifeDays < 0) {
                lifeVal = lifeDays + daysStr + " " + +lifeHoursMod + hoursStr + " " + lifeMinutesMod + minutesStr + " " + extensionStr;
            }

            getBaseView().setmLifeValContent(lifeVal);


            //아낀돈
            long money = costOfSmoking * meanOfSmoking / 20;

            if (diffDays > 0) {
                money *= diffDays;
            }

            String moneyVal = StringUtil.getWonMoneyFormatByPriceAndCurrCd(money, "KRW") + getBaseView().getWordFixCurrency();
            getBaseView().setmMoneyValContent(moneyVal);


            //그래프
            //애니 메이션
            boolean isAnim = true;

            double per1 = (((double) diffMinutes / 20) * 100);

            if (per1 < 100) {
                getBaseView().setmTv1_20muTextWithDarkGrayColor();
                getBaseView().setmTv1_20muContent((int) per1 + "%");
                getBaseView().setmPg1_20muContent((int) per1, isAnim);
            } else if (per1 >= 100) {
                getBaseView().setmTv1_20muTextWithPointColor();
                getBaseView().setmTv1_20muContent(100 + "%");
                getBaseView().setmPg1_20muContent(100, isAnim);
            }

            double per2 = (((double) diffMinutes / (8 * 60)) * 100);

            if (per2 < 100) {

                getBaseView().setmTv2_8hTextWithDarkGrayColor();
                getBaseView().setmTv2_8hContent((int) per2 + "%");
                getBaseView().setmPg2_8hContent((int) per2, isAnim);
            } else if (per2 >= 100) {
                getBaseView().setmTv2_8hTextWithPointColor();
                getBaseView().setmTv2_8hContent(100 + "%");
                getBaseView().setmPg2_8hContent(100, isAnim);
            }


            double per3 = (((double) diffMinutes / (24 * 60)) * 100);
            if (per3 < 100) {
                getBaseView().setmTv3_24hTextWithDarkGrayColor();
                getBaseView().setmTv3_24hContent((int) per3 + "%");
                getBaseView().setmPg3_24hContent((int) per3, isAnim);
            } else if (per3 >= 100) {
                getBaseView().setmTv3_24hTextWithPointColor();
                getBaseView().setmTv3_24hContent(100 + "%");
                getBaseView().setmPg3_24hContent(100, isAnim);
            }

            double per4 = (((double) diffMinutes / (2 * 24 * 60)) * 100);
            if (per4 < 100) {
                getBaseView().setmTv4_48hTextWithDarkGrayColor();
                getBaseView().setmTv4_48hContent((int) per4 + "%");
                getBaseView().setmPg4_48hContent((int) per4, isAnim);
            } else if (per4 >= 100) {
                getBaseView().setmTv4_48hTextWithPointColor();
                getBaseView().setmTv4_48hContent(100 + "%");
                getBaseView().setmPg4_48hContent(100, isAnim);
            }


            double per5 = (((double) diffMinutes / (3 * 24 * 60)) * 100);
            if (per5 < 100) {
                getBaseView().setmTv5_72hTextWithDarkGrayColor();
                getBaseView().setmTv5_72hContent((int) per5 + "%");
                getBaseView().setmPg5_72hContent((int) per5, isAnim);
            } else if (per5 >= 100) {
                getBaseView().setmTv5_72hTextWithPointColor();
                getBaseView().setmTv5_72hContent(100 + "%");
                getBaseView().setmPg5_72hContent(100, isAnim);
            }


            //1주
            double per5_1 = (((double) diffMinutes / (7 * 24 * 60)) * 100);
            if (per5_1 < 100) {
                getBaseView().setmTv12_1wTextWithDarkGrayColor();
                getBaseView().setmTv12_1wContent((int) per5_1 + "%");
                getBaseView().setmPg12_1wContent((int) per5_1, isAnim);
            } else if (per5_1 >= 100) {
                getBaseView().setmTv12_1wTextWithPointColor();
                getBaseView().setmTv12_1wContent(100 + "%");
                getBaseView().setmPg12_1wContent(100, isAnim);
            }


            double per6 = (((double) diffMinutes / (14 * 24 * 60)) * 100);
            if (per6 < 100) {
                getBaseView().setmTv6_2wTextWithDarkGrayColor();
                getBaseView().setmTv6_2wContent((int) per6 + "%");
                getBaseView().setmPg6_2wContent((int) per6, isAnim);
            } else if (per6 >= 100) {
                getBaseView().setmTv6_2wTextWithPointColor();
                getBaseView().setmTv6_2wContent(100 + "%");
                getBaseView().setmPg6_2wContent(100, isAnim);
            }

            //3주

            double per6_1 = (((double) diffMinutes / (21 * 24 * 60)) * 100);
            if (per6_1 < 100) {
                getBaseView().setmTv13_3wTextWithDarkGrayColor();
                getBaseView().setmTv13_3wContent((int) per6_1 + "%");
                getBaseView().setmPg13_3wContent((int) per6_1, isAnim);
            } else if (per6_1 >= 100) {
                getBaseView().setmTv13_3wTextWithPointColor();
                getBaseView().setmTv13_3wContent(100 + "%");
                getBaseView().setmPg13_3wContent(100, isAnim);
            }

            //4주
            double per6_2 = (((double) diffMinutes / (28 * 24 * 60)) * 100);
            if (per6_2 < 100) {
                getBaseView().setmTv14_4wTextWithDarkGrayColor();
                getBaseView().setmTv14_4wContent((int) per6_2 + "%");
                getBaseView().setmPg14_4wContent((int) per6_2, isAnim);
            } else if (per6_2 >= 100) {
                getBaseView().setmTv14_4wTextWithPointColor();
                getBaseView().setmTv14_4wContent(100 + "%");
                getBaseView().setmPg14_4wContent(100, isAnim);
            }


            //6주
            double per6_25 = (((double) diffMinutes / (42 * 24 * 60)) * 100);
            if (per6_25 < 100) {
                getBaseView().setmTv15_6wTextWithDarkGrayColor();
                getBaseView().setmTv15_6wContent((int) per6_25 + "%");
                getBaseView().setmPg15_6wContent((int) per6_25, isAnim);
            } else if (per6_25 >= 100) {
                getBaseView().setmTv15_6wTextWithPointColor();
                getBaseView().setmTv15_6wContent(100 + "%");
                getBaseView().setmPg15_6wContent(100, isAnim);
            }

            //2개월
            double per6_3 = (((double) diffMinutes / (60 * 24 * 60)) * 100);
            if (per6_3 < 100) {
                getBaseView().setmTv16_2mTextWithDarkGrayColor();
                getBaseView().setmTv16_2mContent((int) per6_3 + "%");
                getBaseView().setmPg16_2mContent((int) per6_3, isAnim);
            } else if (per6_3 >= 100) {
                getBaseView().setmTv16_2mTextWithPointColor();
                getBaseView().setmTv16_2mContent(100 + "%");
                getBaseView().setmPg16_2mContent(100, isAnim);
            }

            //2.5개월
            double per6_4 = (((double) diffMinutes / (75 * 24 * 60)) * 100);
            if (per6_4 < 100) {
                getBaseView().setmTv17_2_5mTextWithDarkGrayColor();
                getBaseView().setmTv17_2_5mContent((int) per6_4 + "%");
                getBaseView().setmPg17_2_5mContent((int) per6_4, isAnim);
            } else if (per6_4 >= 100) {
                getBaseView().setmTv17_2_5mTextWithPointColor();
                getBaseView().setmTv17_2_5mContent(100 + "%");
                getBaseView().setmPg17_2_5mContent(100, isAnim);
            }


            double per7 = (((double) diffMinutes / (90 * 24 * 60)) * 100);
            if (per7 < 100) {
                getBaseView().setmTv7_3moTextWithDarkGrayColor();
                getBaseView().setmTv7_3moContent((int) per7 + "%");
                getBaseView().setmPg7_3moContent((int) per7, isAnim);
            } else if (per7 >= 100) {
                getBaseView().setmTv7_3moTextWithPointColor();
                getBaseView().setmTv7_3moContent(100 + "%");
                getBaseView().setmPg7_3moContent(100, isAnim);
            }


            //4.5 m
            double per7_1 = (((double) diffMinutes / (135 * 24 * 60)) * 100);
            if (per7_1 < 100) {
                getBaseView().setmTv18_4_5mTextWithDarkGrayColor();
                getBaseView().setmTv18_4_5mContent((int) per7_1 + "%");
                getBaseView().setmPg18_4_5mContent((int) per7_1, isAnim);
            } else if (per7_1 >= 100) {
                getBaseView().setmTv18_4_5mTextWithPointColor();
                getBaseView().setmTv18_4_5mContent(100 + "%");
                getBaseView().setmPg18_4_5mContent(100, isAnim);
            }


            // 6m
            double per7_2 = (((double) diffMinutes / (180 * 24 * 60)) * 100);
            if (per7_2 < 100) {
                getBaseView().setmTv19_6mTextWithDarkGrayColor();
                getBaseView().setmTv19_6mContent((int) per7_2 + "%");
                getBaseView().setmPg19_6mContent((int) per7_2, isAnim);
            } else if (per7_2 >= 100) {
                getBaseView().setmTv19_6mTextWithPointColor();
                getBaseView().setmTv19_6mContent(100 + "%");
                getBaseView().setmPg19_6mContent(100, isAnim);
            }


            double per8 = (((double) diffMinutes / (270 * 24 * 60)) * 100);
            if (per8 < 100) {
                getBaseView().setmTv8_9moTextWithDarkGrayColor();
                getBaseView().setmTv8_9moContent((int) per8 + "%");
                getBaseView().setmPg8_9moContent((int) per8, isAnim);
            } else if (per8 >= 100) {
                getBaseView().setmTv8_9moTextWithPointColor();
                getBaseView().setmTv8_9moContent(100 + "%");
                getBaseView().setmPg8_9moContent(100, isAnim);
            }

            double per9 = (((double) diffMinutes / (365 * 24 * 60)) * 100);
            if (per9 < 100) {
                getBaseView().setmTv9_1yTextWithDarkGrayColor();
                getBaseView().setmTv9_1yContent((int) per9 + "%");
                getBaseView().setmPg9_1yContent((int) per9, isAnim);
            } else if (per9 >= 100) {
                getBaseView().setmTv9_1yTextWithPointColor();
                getBaseView().setmTv9_1yContent(100 + "%");
                getBaseView().setmPg9_1yContent(100, isAnim);
            }


            double per10 = (((double) diffMinutes / (5 * 365 * 24 * 60)) * 100);
            if (per10 < 100) {
                getBaseView().setmTv10_5yTextWithDarkGrayColor();
                getBaseView().setmTv10_5yContent((int) per10 + "%");
                getBaseView().setmPg10_5yContent((int) per10, isAnim);
            } else if (per10 >= 100) {
                getBaseView().setmTv10_5yTextWithPointColor();
                getBaseView().setmTv10_5yContent(100 + "%");
                getBaseView().setmPg10_5yContent(100, isAnim);
            }

            double per11 = (((double) diffMinutes / (10 * 365 * 24 * 60)) * 100);
            if (per11 < 100) {
                getBaseView().setmTv11_10yTextWithDarkGrayColor();
                getBaseView().setmTv11_10yContent((int) per11 + "%");
                getBaseView().setmPg11_10yContent((int) per11, isAnim);
            } else if (per11 >= 100) {
                getBaseView().setmTv11_10yTextWithPointColor();
                getBaseView().setmTv11_10yContent(100 + "%");
                getBaseView().setmPg11_10yContent(100, isAnim);
            }

            String myLevel = "Lv.1 지옥의 3일";
            if (per5 < 100) {
                myLevel = getBaseView().getCustomLevel1();
            } else if (per6_2 < 100) {
                myLevel = getBaseView().getCustomLevel2();
            } else if (per7_1 < 100) {
                myLevel = getBaseView().getCustomLevel3();
            } else if (per9 < 100) {
                myLevel = getBaseView().getCustomLevel4();
            } else {
                myLevel = getBaseView().getCustomLevel5();
            }

            getBaseView().setmMyLevelTvContent(myLevel);
        } else {

            getBaseView().goneUserContent();
            getBaseView().showUserEmptyContent();

        }


    }


    @Override
    public void onPause() {
        //fragment pause flag setting
//        boolean isHomeFragmentPause = this.mInteractor.isHomeFragmentPause();
//
//        if (!isHomeFragmentPause) {
//            this.mInteractor.setHomeFragmentPause(true);
//        }
    }

    @Override
    public void onResume() {
        initView();
    }

    @Override
    public void onClickUserWrite() {
        getBaseView().navigateToUserWriteActivity();
    }
}
