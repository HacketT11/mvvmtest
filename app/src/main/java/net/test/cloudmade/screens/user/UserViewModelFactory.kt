package net.test.cloudmade.screens.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserViewModelFactory(val interactor: UserInteractor) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == UserViewModel::class.java)
            return UserViewModel(interactor) as T
        throw IllegalArgumentException()
    }
}