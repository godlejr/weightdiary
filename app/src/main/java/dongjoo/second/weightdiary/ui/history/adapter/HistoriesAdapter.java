package dongjoo.second.weightdiary.ui.history.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dongjoo.second.weightdiary.R;
import dongjoo.second.weightdiary.common.entity.History;
import dongjoo.second.weightdiary.ui.history.presenter.HistoryFragmentPresenter;

public class HistoriesAdapter extends RecyclerView.Adapter<HistoriesAdapter.HistoriesViewHolder> {
    private HistoryFragmentPresenter mPresenter;
    private List<History> mHistories;
    private Context context;
    private int layout;

    public HistoriesAdapter(HistoryFragmentPresenter historyFragmentPresenter, List<History> histories, Context context, int layout) {
        this.mPresenter = historyFragmentPresenter;
        this.mHistories = histories;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public HistoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HistoriesViewHolder historiesViewHolder = new HistoriesViewHolder(this.mPresenter, this.mHistories, LayoutInflater.from(context).inflate(layout, parent, false));
        return historiesViewHolder;
    }

    @Override
    public void onBindViewHolder(HistoriesViewHolder holder, int position) {
        History history = this.mHistories.get(position);

        String dateTitle = history.getDateTitle();
        String text = history.getText();
        int status = history.getStatus();
        boolean isChecked = history.isChecked();


        if (isChecked) {
            holder.mhistoryContent.setBackgroundColor(holder.mGrayColor);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                holder.mhistoryContent.setBackground(holder.mBackgroundDrawable);
            } else {
                holder.mhistoryContent.setBackgroundDrawable(holder.mBackgroundDrawable);
            }
        }

        String statusStr = "○";
        if (status == 2) {
            statusStr = "△";
            holder.mStatus.setTextColor(holder.mYellow2Color);
        }

        holder.mDateTitle.setText(dateTitle);
        holder.mText.setText(text);
        holder.mStatus.setText(statusStr);


    }

    @Override
    public int getItemCount() {
        return this.mHistories.size();
    }

    public static class HistoriesViewHolder extends RecyclerView.ViewHolder {
        private HistoryFragmentPresenter mPresenter;
        private List<History> mHistories;


        @BindView(R.id.tv_history_datetitle)
        TextView mDateTitle;

        @BindView(R.id.tv_history_status)
        TextView mStatus;

        @BindView(R.id.tv_history_text)
        TextView mText;

        @BindView(R.id.tv_history_sharing)
        ImageView mMsharing;

        @BindView(R.id.ll_history_content)
        LinearLayout mhistoryContent;

        @BindColor(R.color.gray)
        int mGrayColor;

        @BindColor(R.color.yellow2)
        int mYellow2Color;

        @BindDrawable(R.drawable.border_gray_top_and_bottom)
        Drawable mBackgroundDrawable;


        public HistoriesViewHolder(HistoryFragmentPresenter historyFragmentPresenter, List<History> histories, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mPresenter = historyFragmentPresenter;
            this.mHistories = histories;
        }

        @OnClick(R.id.ll_history_content)
        public void onClickHistoryContent() {
            int position = getAdapterPosition();
            this.mPresenter.onClickHistoryContent(position);
        }

        @OnClick(R.id.tv_history_sharing)
        public void onClickHistorySharing() {
            int position = getAdapterPosition();
            this.mPresenter.onClickHistorySharing(position);
        }
    }
}

