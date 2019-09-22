package net.test.cloudmade.screens.search.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import net.test.cloudmade.core.network.ServiceProvider
import net.test.cloudmade.data.user.ApiUserRepository
import net.test.cloudmade.data.user.UserRepository
import net.test.cloudmade.data.user.UserService
import net.test.cloudmade.di.ActivityScope
import net.test.cloudmade.screens.search.SearchActivity
import net.test.cloudmade.screens.search.SearchInteractor
import net.test.cloudmade.screens.search.SearchViewModel
import net.test.cloudmade.screens.search.SearchViewModelFactory
import net.test.cloudmade.utils.Workers

@Module
class SearchModule(private val activity: SearchActivity) {

    @ActivityScope
    @Provides
    fun provideViewModel(interactor: SearchInteractor) =
        ViewModelProviders.of(activity, SearchViewModelFactory(interactor)).get(SearchViewModel::class.java)

    @ActivityScope
    @Provides
    fun provideSearchInteractor(workers: Workers, userRepository: UserRepository) =
        SearchInteractor(workers, userRepository)

    @ActivityScope
    @Provides
    fun provideUserRepository(userService: UserService): UserRepository = ApiUserRepository(userService)

    @ActivityScope
    @Provides
    fun provideUserService(serviceProvider: ServiceProvider) = serviceProvider.createService(UserService::class.java)

}