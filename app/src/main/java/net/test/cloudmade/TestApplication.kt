package net.test.cloudmade

import android.app.Application
import net.test.cloudmade.di.ApplicationComponent
import net.test.cloudmade.di.ApplicationModule
import net.test.cloudmade.di.DaggerApplicationComponent

class TestApplication : Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}