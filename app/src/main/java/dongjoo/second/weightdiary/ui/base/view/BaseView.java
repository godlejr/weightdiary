package dongjoo.second.weightdiary.ui.base.view;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dongjoo.second.weightdiary.common.entity.History;
import dongjoo.second.weightdiary.common.entity.User;

/**
 * BaseView
 *
 * @author 김동주
 * @since 2021.10.07
 */
public interface BaseView {

    void showMessage(String message);

    String getAppDestroyConfirm();

    String getHistoryDeleteConfirm();

    void setToolbarLayout();

    void setActivityFinish();

    void showToolbarTitle(String message);

    void removeSharedPreferHistory(int index);

    void showProgressDialog();

    void goneProgressDialog();

    void setSharedPreferUser(User user);

    User getSharedPreferUser();

    void removeSharedPreferUser();

    void setSharedPreferHistory(History history);

    void removeAllSharedPreferHistory(List<History> removeHistories);

    ArrayList<History> getSharedPreferHistories();


    String getWordFixDays();

    String getWordFixHours();

    String getWordFixMinutes();

    String getWordFixPassed();

    String getWordFixCurrency();

    String getWordFixExtension();

    String getActionWrite();

    String getActionWriteComplete();

    String getWordFixStopsmoking();

    String getWordFixDateCount();

    String getQuitSmokingStatement();

    Context getContext();


}
