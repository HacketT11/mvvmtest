package net.test.cloudmade.screens.user.di

import dagger.Component
import net.test.cloudmade.di.ActivityScope
import net.test.cloudmade.di.ApplicationComponent
import net.test.cloudmade.screens.user.UserActivity

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [UserModule::class])
interface UserComponent {

    fun inject(activity: UserActivity)
}