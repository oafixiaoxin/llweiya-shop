package com.llweiya.ysx.starchef.business.user.injection;

import com.llweiya.ysx.starchef.business.user.view.CardStackActivity;
import com.llweiya.ysx.starchef.business.user.view.LoginActivity;
import com.llweiya.ysx.starchef.business.user.view.RegisterActivity;
import com.llweiya.ysx.starchef.business.user.view.UserInfoActivity;
import com.llweiya.ysx.starchef.common.injection.ActivityScope;
import com.llweiya.ysx.starchef.common.injection.AppComponent;
import com.llweiya.ysx.starchef.common.injection.BaseModule;

import dagger.Component;

/**
 * Created by ysx on 2018/1/16.
 */

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {
                BaseModule.class,
                UserModule.class
        }
)
public interface UserComponent {
    void inject(LoginActivity activity);
    void inject(RegisterActivity activity);
    void inject(UserInfoActivity activity);
    void inject(CardStackActivity activity);
}
