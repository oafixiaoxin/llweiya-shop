package com.llweiya.ysx.starchef.common.presenter;

import android.content.Context;

import com.llweiya.ysx.starchef.common.interactor.BaseInteractor;
import com.llweiya.ysx.starchef.common.view.BaseView;

/**
 * Created by ysx on 2018/1/15.
 */

public class BasePresenter<I extends BaseInteractor, V extends BaseView> {

    protected Context currentContext;
    protected V view;
    protected I interactor;

    protected BasePresenter() {

    }

    protected BasePresenter(Context context, V baseView, I interactor) {
        initPresenter(context, baseView, interactor);
    }

    public void initPresenter(Context context, V baseView, I interactor) {
        this.currentContext = context;
        this.view = baseView;
        this.interactor = interactor;
    }

    public final static class Builder<I extends BaseInteractor, V extends BaseView> {
        private Context currentContext;
        private V view;
        private I interactor;

        public Builder<I, V> setInteractor(I interactor) {
            this.interactor = interactor;
            return this;
        }

        public Builder<I, V> setCurrentContext(Context context) {
            this.currentContext = context;
            return this;
        }

        @SuppressWarnings("unchecked")
        public Builder<I, V> attachView(BaseView baseView) {
            this.view = (V)baseView;
            return this;
        }

        public <P extends BasePresenter<I, V>> P build(Class<P> pClass) {
            if (currentContext == null) {
                throw new IllegalStateException("Current context is required.");
            }

            if (view == null) {
                throw new IllegalStateException("BaseView is required.");
            }

            if (interactor == null) {
                throw new IllegalStateException("Interactor is required.");
            }

            try {
                P presenter = pClass.newInstance();
                presenter.initPresenter(currentContext, view, interactor);
                return presenter;
            } catch (IllegalAccessException exception) {
                exception.printStackTrace();
                return null;
            } catch (InstantiationException exception) {
                exception.printStackTrace();
                return null;
            } catch (SecurityException exception) {
                exception.printStackTrace();
                return null;
            }
        }
    }

}
