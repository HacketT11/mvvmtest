package net.test.cloudmade.screens.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.test.cloudmade.data.repositories.UserRepo
import net.test.cloudmade.data.user.User

class UserViewModel(private val interactor: UserInteractor) : ViewModel() {

    private lateinit var login: String

    private val userData by lazy {
        isLoading.postValue(true)
        interactor.loadUserData(login, ::onSuccess, ::onError)
        return@lazy MutableLiveData<Pair<User, List<UserRepo>>>()
    }

    val isLoading = MutableLiveData<Boolean>()

    fun getSubscription(login: String): MutableLiveData<Pair<User, List<UserRepo>>> {
        this.login = login
        return userData
    }

    private fun onSuccess(data: Pair<User, List<UserRepo>>) {
        isLoading.postValue(false)
        userData.postValue(data)
    }

    private fun onError(throwable: Throwable) {
        //todo
    }

    override fun onCleared() {
        super.onCleared()
        interactor.clear()
    }
}