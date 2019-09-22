package net.test.cloudmade.screens.search.di

import dagger.Component
import net.test.cloudmade.di.ActivityScope
import net.test.cloudmade.di.ApplicationComponent
import net.test.cloudmade.screens.search.SearchActivity

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [SearchModule::class])
interface SearchComponent{

    fun inject(activity: SearchActivity)
}