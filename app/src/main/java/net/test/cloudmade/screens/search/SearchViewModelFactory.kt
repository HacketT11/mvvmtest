package net.test.cloudmade.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class SearchViewModelFactory(val interactor: SearchInteractor) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == SearchViewModel::class.java)
            return SearchViewModel(interactor) as T
        throw IllegalArgumentException()
    }
}