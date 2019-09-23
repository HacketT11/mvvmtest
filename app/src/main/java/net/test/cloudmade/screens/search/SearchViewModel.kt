package net.test.cloudmade.screens.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.test.cloudmade.data.user.User

class SearchViewModel(private val interactor: SearchInteractor) : ViewModel() {
    private val users = mutableListOf<User>()
    private var page = 1

    val liveDataUsers by lazy {
        interactor.subscribeOnUserSearch(::onDataLoaded)
        return@lazy MutableLiveData<List<User>>()
    }

    fun onSearch(query: String) {
        page = 1
        users.clear()
        interactor.postQuery(query, page, {}, {})
    }

    fun onNextPage(query: String) {
        page++
        interactor.postQuery(query, page, {}, {})
    }

    override fun onCleared() {
        interactor.clear()
    }

    private fun onDataLoaded(data: List<User>) {
        users.addAll(data)
        liveDataUsers.postValue(users)
    }
}