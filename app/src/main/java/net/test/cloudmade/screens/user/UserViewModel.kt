package net.test.cloudmade.screens.user

import androidx.lifecycle.MutableLiveData
import net.test.cloudmade.data.repositories.UserRepo
import net.test.cloudmade.data.user.User

class UserViewModel(private val interactor: UserInteractor) {

    val isLoading = MutableLiveData<Boolean>()

    val userData by lazy {
        isLoading.postValue(true)
        interactor.loadUserData("", ::onSuccess, ::onError)
        return@lazy MutableLiveData<Pair<User, List<UserRepo>>>()
    }

    private fun onSuccess(data: Pair<User, List<UserRepo>>) {
        userData.postValue(data)
    }

    private fun onError(throwable: Throwable) {
        //todo
    }
}