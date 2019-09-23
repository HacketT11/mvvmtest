package net.test.cloudmade.screens.user.di

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import net.test.cloudmade.core.network.ServiceProvider
import net.test.cloudmade.data.repositories.ApiUserRepoRepository
import net.test.cloudmade.data.repositories.UserRepoRepository
import net.test.cloudmade.data.repositories.UserRepoService
import net.test.cloudmade.data.user.ApiUserRepository
import net.test.cloudmade.data.user.UserRepository
import net.test.cloudmade.data.user.UserService
import net.test.cloudmade.di.ActivityScope
import net.test.cloudmade.screens.user.UserActivity
import net.test.cloudmade.screens.user.UserInteractor
import net.test.cloudmade.screens.user.UserViewModel
import net.test.cloudmade.screens.user.UserViewModelFactory
import net.test.cloudmade.utils.Workers

@Module
class UserModule(private val activity: UserActivity) {

    @Provides
    @ActivityScope
    fun provideUserViewModule(interactor: UserInteractor) =
            ViewModelProviders.of(activity, UserViewModelFactory(interactor)).get(UserViewModel::class.java)

    @Provides
    @ActivityScope
    fun provideUserRepository(userService: UserService): UserRepository = ApiUserRepository(userService)

    @Provides
    @ActivityScope
    fun provideUserRepoRepository(userRepoService: UserRepoService): UserRepoRepository =
            ApiUserRepoRepository(userRepoService)

    @Provides
    @ActivityScope
    fun provideUserInteractor(workers: Workers,
                              userRepository: UserRepository,
                              userRepoService: UserRepoRepository) =
            UserInteractor(workers, userRepository, userRepoService)


    @Provides
    @ActivityScope
    fun provideUserService(serviceProvider: ServiceProvider) =
            serviceProvider.createService(UserService::class.java)

    @Provides
    @ActivityScope
    fun provideUserRepoService(serviceProvider: ServiceProvider) =
            serviceProvider.createService(UserRepoService::class.java)
}
