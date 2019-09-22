package net.test.cloudmade.di

import android.app.Application
import dagger.Component
import net.test.cloudmade.core.network.ServiceProvider
import net.test.cloudmade.utils.Workers
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent{

    fun inject(application: Application)

    fun workers(): Workers

    fun serviceProvider(): ServiceProvider
}