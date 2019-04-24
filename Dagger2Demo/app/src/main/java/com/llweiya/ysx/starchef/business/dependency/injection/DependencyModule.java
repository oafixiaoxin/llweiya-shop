package com.llweiya.ysx.starchef.business.dependency.injection;

import com.llweiya.ysx.starchef.business.dependency.interactor.DependencyInteractor;
import com.llweiya.ysx.starchef.business.dependency.interactor.DependencyInteractorImpl;
import com.llweiya.ysx.starchef.common.injection.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ysx on 2018/1/24.
 */

@Module
public class DependencyModule {

    public DependencyModule() {

    }

    @Provides
    @ActivityScope
    DependencyInteractor provideUserInteractor() {
        return new DependencyInteractorImpl();
    }

}
