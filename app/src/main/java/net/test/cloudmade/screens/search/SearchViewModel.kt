package net.test.cloudmade.screens.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.test.cloudmade.data.user.User

class SearchViewModel(private val interactor: SearchInteractor) : ViewModel() {

    val liveDataUsers = MutableLiveData<List<User>>()
    private val users = mutableListOf<User>()
    private var page = 1

    init {
        interactor.subscribeOnDelayedQuery { interactor.onDebounce(it.first, it.second) }
    }

    fun onSearch(query: String) {
        page = 1
        users.clear()
    }

    fun onNextPage(query: String) {
        page++
    }

    override fun onCleared() {
        interactor.clear()
    }

    private fun search(pair: Pair<String, Int>){

    }
}