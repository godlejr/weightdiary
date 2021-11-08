package dongjoo.second.weightdiary.ui.history.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dongjoo.second.weightdiary.R;
import dongjoo.second.weightdiary.common.dialog.confirmcancel.activity.ConfirmCancelActivity;
import dongjoo.second.weightdiary.common.dto.ConfirmCancelDialogDto;
import dongjoo.second.weightdiary.common.entity.History;
import dongjoo.second.weightdiary.common.flag.ActivityRequestResultFlag;
import dongjoo.second.weightdiary.ui.base.fragment.BaseFragment;
import dongjoo.second.weightdiary.ui.history.adapter.HistoriesAdapter;
import dongjoo.second.weightdiary.ui.history.presenter.HistoryFragmentPresenter;
import dongjoo.second.weightdiary.ui.history.presenter.HistoryFragmentPresenterImpl;
import dongjoo.second.weightdiary.ui.history.view.HistoryFragmentView;
import dongjoo.second.weightdiary.ui.historywrite.activity.HistoryWriteActivity;

public class HistoryFragment extends BaseFragment implements HistoryFragmentView, NestedScrollView.OnScrollChangeListener {

    private Context mContext;
    private HistoryFragmentPresenter mPresenter;
    private HistoriesAdapter mHistoriesAdapter;


    @BindView(R.id.btn_history_write)
    Button mWriteBtn;

    @BindView(R.id.rv_history)
    RecyclerView mHistoriesRv;

    @BindView(R.id.nsv_history)
    NestedScrollView mCardHistoryNsv;

    @BindView(R.id.tv_history_delete)
    TextView mDeleteBtn;


    @BindView(R.id.tv_history_historyempty)
    TextView mHistoryEmptyTv;


    @BindView(R.id.tv_history_all_selection)
    TextView mSelectionBtn;

    @BindView(R.id.adView)
    AdView mAdView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mContext = getActivity();

        this.mPresenter = new HistoryFragmentPresenterImpl<HistoryFragmentView>();
        this.mPresenter.onAttach(this);

        init();

        MobileAds.initialize(this.mContext, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
    }

    protected void init() {
        this.mPresenter.init();
    }


    @Override
    public void showSelectionBtn() {
        if (this.mSelectionBtn.getVisibility() == View.GONE) {
            this.mSelectionBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void goneSelectionBtn() {
        if (this.mSelectionBtn.getVisibility() == View.VISIBLE) {
            this.mSelectionBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void showDeleteBtn() {
        if (this.mDeleteBtn.getVisibility() == View.GONE) {
            this.mDeleteBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void goneDeleteBtn() {
        if (this.mDeleteBtn.getVisibility() == View.VISIBLE) {
            this.mDeleteBtn.setVisibility(View.GONE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, view);
        initView();

        AdRequest adRequest = new AdRequest.Builder().build();
        this.mAdView.loadAd(adRequest);

        return view;
    }

    protected void initView() {
        this.mPresenter.initView();
    }


    @Override
    public void setToolbarLayout() {

    }

    @Override
    public void showToolbarTitle(String message) {

    }


    @Override
    public void setHistoriesViewByItem(List<History> histories) {
        this.mHistoriesAdapter = new HistoriesAdapter(this.mPresenter, histories, mContext, R.layout.item_fragmenthistory);
        this.mHistoriesRv.setAdapter(this.mHistoriesAdapter);
        this.mHistoriesRv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void clearHistoriesAdapter() {
        if (this.mHistoriesAdapter != null) {
            this.mHistoriesAdapter = null;
        }
    }

    @Override
    public void setScrollViewOnScrollChangeListener() {
        this.mCardHistoryNsv.setOnScrollChangeListener(this);
    }

    @Override
    public void historiesAdapterNotifyItemRangeInserted(int startPosition, int itemCount) {
//        if (this.mHistoriesAdapter != null) {
//            this.mHistoriesAdapter.notifyItemRangeInserted(startPosition, itemCount);
//        }
    }

    @Override
    public void historiesAdapterNotifyItemChanged(int position) {
        if (this.mHistoriesAdapter != null) {
            this.mHistoriesAdapter.notifyItemChanged(position);
        }
    }


    @Override
    public void showHistoryEmptyContent() {
        if (this.mHistoryEmptyTv.getVisibility() == View.GONE) {
            this.mHistoryEmptyTv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void goneHistoryEmptyContent() {
        if (this.mHistoryEmptyTv.getVisibility() == View.VISIBLE) {
            this.mHistoryEmptyTv.setVisibility(View.GONE);
        }
    }



    @Override
    public void historiesAdapterNotifyItemRangeChanged(int startPosition, int size) {
        if (this.mHistoriesAdapter != null) {
            this.mHistoriesAdapter.notifyItemRangeChanged(startPosition, size);
        }
    }

    @Override
    public void navigateToHistoryWriteActivity() {
        Intent intent = new Intent(this.mContext, HistoryWriteActivity.class);
        startActivity(intent);
    }


    @Override
    public void onPause() {
        this.mPresenter.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        this.mPresenter.onResume();
        super.onResume();
    }


    @Override
    public void showWriteBtn() {
        if (this.mWriteBtn.getVisibility() == View.GONE) {
            this.mWriteBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideWriteBtn() {
        if (this.mWriteBtn.getVisibility() == View.VISIBLE) {
            this.mWriteBtn.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.tv_history_all_selection)
    @Override
    public void onClickAllSelection() {
        this.mPresenter.onClickAllSelection();
    }


    @OnClick(R.id.btn_history_write)
    @Override
    public void onClickWrite() {
        this.mPresenter.onClickWrite();
    }


    @OnClick(R.id.tv_history_delete)
    public void onClickHistoryDelete() {
        this.mPresenter.onClickHistoryDelete();
    }

    @Override
    public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        View view = nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1);
        int difference = (view.getBottom() - (nestedScrollView.getHeight() + nestedScrollView.getScrollY()));
        this.mPresenter.onScrollChange(difference);

    }

    @Override
    public void navigateToConfirmCancelDialogActivity(ConfirmCancelDialogDto confirmCancelDialogDto, int flag, int requestCode) {
        Intent intent = new Intent(this.mContext, ConfirmCancelActivity.class);
        intent.putExtra("flag", flag);
        intent.putExtra("confirmCancelDialogDto", confirmCancelDialogDto);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void navigateToShare(History history) {
        String text = history.getText();
        String dateTitle = history.getDateTitle();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, dateTitle);
        intent.putExtra(Intent.EXTRA_TEXT, text);

        Intent chooser = Intent.createChooser(intent, "금연일기 공유하기");
        startActivity(chooser);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case ActivityRequestResultFlag.CONFIRM_CANCEL_DIALOG_HISTORY_DELETE_REQUEST:
                switch (resultCode) {
                    case ActivityRequestResultFlag.RESULT_OK:
                        this.mPresenter.onActivityResultForHistoryDeleteDialogResultOk();
                        break;
                }
                break;
        }
    }
}
