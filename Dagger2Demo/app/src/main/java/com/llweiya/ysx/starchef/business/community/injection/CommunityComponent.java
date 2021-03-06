package com.llweiya.ysx.starchef.business.community.injection;

import com.llweiya.ysx.starchef.business.community.view.CommunityMainFragment;
import com.llweiya.ysx.starchef.business.community.view.FavoriteFragment;
import com.llweiya.ysx.starchef.business.community.view.HomeFragment;
import com.llweiya.ysx.starchef.business.community.view.UserDefinedCourseActivity;
import com.llweiya.ysx.starchef.common.injection.ActivityScope;
import com.llweiya.ysx.starchef.common.injection.AppComponent;
import com.llweiya.ysx.starchef.common.injection.BaseModule;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {
                BaseModule.class,
                CommunityModule.class
        }
)
public interface CommunityComponent {
    void inject(HomeFragment fragment);
    void inject(FavoriteFragment fragment);
    void inject(CommunityMainFragment fragment);
    void inject(UserDefinedCourseActivity activity);
}
