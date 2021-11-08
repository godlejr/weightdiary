package dongjoo.second.weightdiary.ui.base.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.fragment.app.Fragment;

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
 * BaseFragment
 *
 * @author 김동주
 * @since 2021.10.07
 */
public abstract class BaseFragment extends Fragment implements BaseView {

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();
        //toast
        this.toastUtil = new ToastUtil(mContext);

        //sharedPreference
        this.mSharedPreferenceManager = new SharedPreferenceManager(mContext);

        //ProgressDialog
        this.mProgressDialogHandler = new Handler();
        this.mProgressDialog = new ProgressDialog(mContext);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void showMessage(String message) {
        toastUtil.showMessage(message);
    }

    @Override
    public void setToolbarLayout() {

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
    public void setActivityFinish() {
        //fragment는 적용 대상 아님.
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
    public void showProgressDialog() {
        this.mProgressDialog.show();
        if (this.mProgressDialog.getWindow() != null) {
            this.mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        this.mProgressDialog.getWindow().getDecorView().setBackgroundResource(R.color.transparent);
        this.mProgressDialog.setContentView(R.layout.progress_dialog);
    }

    @Override
    public void goneProgressDialog() {
        this.mProgressDialogHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }
        }, 400);

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
    public String getWordFixExtension() {
        return wordFixExtension;
    }

    protected abstract void init();

    protected abstract void initView();

    @Override
    public Context getContext() {
        return this.mContext;
    }
}
