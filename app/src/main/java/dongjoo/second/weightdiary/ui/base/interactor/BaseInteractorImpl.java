package dongjoo.second.weightdiary.ui.base.interactor;

import android.content.Context;

import dongjoo.second.weightdiary.ui.base.presenter.BasePresenter;

/**
 * BaseInteractorImpl
 *
 * @author 김동주
 * @since 2021.10.07
 */

public class BaseInteractorImpl<V extends BasePresenter> implements BaseInteractor<V> {
    private V mBasePresenter;

    //Reposiltory

    private Context mContext;

    public BaseInteractorImpl() {

    }

    @Override
    public void onAttach(V basePresenter) {
        this.mBasePresenter = basePresenter;
        this.mContext = mBasePresenter.getContext();
    }

    @Override
    public void init() {

    }

    public V getBasePresenter() {
        return this.mBasePresenter;
    }


}
