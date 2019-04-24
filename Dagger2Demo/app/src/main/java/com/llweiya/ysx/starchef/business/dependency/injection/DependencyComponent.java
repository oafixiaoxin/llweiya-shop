package com.llweiya.ysx.starchef.business.dependency.injection;

import com.llweiya.ysx.starchef.business.dependency.view.AllLocalVideosActivity;
import com.llweiya.ysx.starchef.business.dependency.view.AnnouncementActivity;
import com.llweiya.ysx.starchef.business.dependency.view.RecordActivity;
import com.llweiya.ysx.starchef.common.injection.ActivityScope;
import com.llweiya.ysx.starchef.common.injection.AppComponent;
import com.llweiya.ysx.starchef.common.injection.BaseModule;

import dagger.Component;

/**
 * Created by ysx on 2018/1/24.
 */

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {
                BaseModule.class
        }
)

public interface DependencyComponent {
    void inject(AnnouncementActivity activity);
    void inject(RecordActivity activity);
    void inject(AllLocalVideosActivity activity);
}
