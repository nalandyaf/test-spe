package com.base.mvvm.di.component

import android.app.Application
import com.base.mvvm.App
import com.base.mvvm.di.builder.ActivityBuilder
import com.base.mvvm.di.builder.FragmentBuilder
import com.base.mvvm.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class, FragmentBuilder::class])
interface AppComponent {
    fun inject(app: App?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application?): Builder?

        fun build(): AppComponent?
    }
}