package net.test.cloudmade.di

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.test.cloudmade.BuildConfig
import net.test.cloudmade.utils.Workers
import net.test.cloudmade.core.network.RetrofitServiceProvider
import net.test.cloudmade.core.network.ServiceProvider
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideWorkers(): Workers =
            Workers(AndroidSchedulers.mainThread(), Schedulers.io())

    @Provides
    @Singleton
    fun provideServiceProvider(): ServiceProvider = RetrofitServiceProvider(BuildConfig.BASE_URL)
}