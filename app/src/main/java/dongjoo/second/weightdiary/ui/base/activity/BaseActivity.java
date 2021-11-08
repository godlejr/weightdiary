package dongjoo.second.weightdiary.ui.base.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindString;
import dongjoo.second.weightdiary.R;
import dongjoo.second.weightdiary.common.entity.History;
import dongjoo.second.weightdiary.common.entity.User;
import dongjoo.second.weightdiary.common.utils.ToastUtil;
import dongjoo.second.weightdiary.repository.local.SharedPreferenceManager;
import dongjoo.second.weightdiary.ui.base.view.BaseView;


/**
 * BaseActivity
 *
 * @author 김동주
 * @since 2021.10.06
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected Context mContext;

    protected ToastUtil toastUtil;

    protected SharedPreferenceManager mSharedPreferenceManager;

    protected ProgressDialog mProgressDialog;

    protected Handler mProgressDialogHandler;


    @BindString(R.string.app_destroy_confirm)
    String appDestroyConfirm;

    @BindString(R.string.history_delete_confirm)
    String historyDeleteConfirm;

    @BindString(R.string.quitSmokingStatements1)
    String quitSmokingStatements1;

    @BindString(R.string.quitSmokingStatements2)
    String quitSmokingStatements2;

    @BindString(R.string.quitSmokingStatements3)
    String quitSmokingStatements3;

    @BindString(R.string.quitSmokingStatements4)
    String quitSmokingStatements4;

    @BindString(R.string.quitSmokingStatements5)
    String quitSmokingStatements5;

    @BindString(R.string.quitSmokingStatements6)
    String quitSmokingStatements6;

    @BindString(R.string.quitSmokingStatements7)
    String quitSmokingStatements7;


    @BindString(R.string.quitSmokingStatements8)
    String quitSmokingStatements8;

    @BindString(R.string.quitSmokingStatements9)
    String quitSmokingStatements9;

    @BindString(R.string.quitSmokingStatements10)
    String quitSmokingStatements10;

    @BindString(R.string.quitSmokingStatements11)
    String quitSmokingStatements11;


    @BindString(R.string.action_write)
    String actionWrite;

    @BindString(R.string.action_write_complete)
    String actionWriteComplete;

    @BindString(R.string.word_fix_stopsmoking)
    String wordFixStopsmoking;

    @BindString(R.string.word_fix_date_count)
    String wordFixDateCount;

    @BindString(R.string.word_fix_days)
    String wordFixDays;

    @BindString(R.string.word_fix_hours)
    String wordFixHours;

    @BindString(R.string.word_fix_minutes)
    String wordFixMinutes;

    @BindString(R.string.word_fix_passed)
    String wordFixPassed;

    @BindString(R.string.word_fix_currency)
    String wordFixCurrency;

    @BindString(R.string.word_fix_extension)
    String wordFixExtension;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = BaseActivity.this;

        //toast
        this.toastUtil = new ToastUtil(this);

        //sharedPreference
        this.mSharedPreferenceManager = new SharedPreferenceManager(this);

        //ProgressDialog
        this.mProgressDialog = new ProgressDialog(this);

        this.mProgressDialogHandler = new Handler();


    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public void showMessage(String message) {
        toastUtil.showMessage(message);
    }


    @Override
    public String getAppDestroyConfirm() {
        return appDestroyConfirm;
    }

    @Override
    public String getHistoryDeleteConfirm() {
        return historyDeleteConfirm;
    }

    @Override
    public void setToolbarLayout() {

    }

    @Override
    public void setActivityFinish() {
        finish();
    }

    @Override
    public void showToolbarTitle(String message) {

    }

    @Override
    public void setSharedPreferUser(User user) {
        this.mSharedPreferenceManager.setUser(user);
    }

    @Override
    public User getSharedPreferUser() {
        return this.mSharedPreferenceManager.getUser();
    }

    @Override
    public void removeSharedPreferUser() {
        this.mSharedPreferenceManager.removeUser();
    }

    @Override
    public void setSharedPreferHistory(History history) {
        this.mSharedPreferenceManager.setHistory(history);
    }

    @Override
    public ArrayList<History> getSharedPreferHistories() {
        return this.mSharedPreferenceManager.getHistories();
    }

    @Override
    public void removeSharedPreferHistory(int index) {
        this.mSharedPreferenceManager.removeHistory(index);
    }

    @Override
    public void removeAllSharedPreferHistory(List<History> removeHistories) {
        this.mSharedPreferenceManager.removeAllHistory(removeHistories);
    }

    @Override
    public void showProgressDialog() {
        this.mProgressDialog.show();
        if (this.mProgressDialog.getWindow() != null) {
            this.mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        this.mProgressDialog.getWindow().getDecorView().setBackgroundResource(R.color.transparent);
        this.mProgressDialog.setCanceledOnTouchOutside(false);
        this.mProgressDialog.setContentView(R.layout.progress_dialog);

    }

    @Override
    public String getQuitSmokingStatement() {

        String[] quitSmokingStatements = {
                quitSmokingStatements1, quitSmokingStatements2, quitSmokingStatements3,
                quitSmokingStatements4, quitSmokingStatements5, quitSmokingStatements6,
                quitSmokingStatements7, quitSmokingStatements8, quitSmokingStatements9,
                quitSmokingStatements10, quitSmokingStatements11
        };
        Random rd = new Random();
        String stmt = quitSmokingStatements[rd.nextInt(11)];

        return stmt;

    }

    @Override
    public String getWordFixDays() {
        return wordFixDays;
    }

    @Override
    public String getWordFixHours() {
        return wordFixHours;
    }

    @Override
    public String getWordFixMinutes() {
        return wordFixMinutes;
    }

    @Override
    public String getWordFixPassed() {
        return wordFixPassed;
    }

    @Override
    public String getWordFixCurrency() {
        return wordFixCurrency;
    }


    @Override
    public String getWordFixExtension() {
        return wordFixExtension;
    }

    @Override
    public String getActionWrite() {
        return actionWrite;
    }
    @Override
    public String getActionWriteComplete() {
        return actionWriteComplete;
    }

    @Override
    public String getWordFixStopsmoking() {
        return wordFixStopsmoking;
    }
    @Override
    public String getWordFixDateCount() {
        return wordFixDateCount;
    }

    @Override
    public void goneProgressDialog() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }


    protected abstract void init();

    @Override
    public Context getContext() {
        return this.mContext;
    }
}
