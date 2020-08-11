package com.base.mvvm

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.base.mvvm.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class App : MultiDexApplication(), HasActivityInjector,HasSupportFragmentInjector {
    @kotlin.jvm.JvmField
    @Inject
    var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>? = null
    @kotlin.jvm.JvmField
    @Inject
    var mCalligraphyConfig: CalligraphyConfig? = null

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector!!
    }

    @JvmField
    @Inject
    var mFragmentInjector: DispatchingAndroidInjector<Fragment>? = null

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        instance = this
        appContext = applicationContext
        DaggerAppComponent.builder()
                .application(this)
                ?.build()
                ?.inject(this)
        CalligraphyConfig.initDefault(mCalligraphyConfig)
    }

    companion object {
        var appContext: Context? = null
            private set
        var instance: App? = null
            private set

    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return mFragmentInjector!!
    }
}