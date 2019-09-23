package net.test.cloudmade.screens.search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_search.*
import net.test.cloudmade.R
import net.test.cloudmade.core.base.BaseActivity
import net.test.cloudmade.screens.search.ui.UsersAdapter
import net.test.cloudmade.screens.search.di.DaggerSearchComponent
import net.test.cloudmade.screens.search.di.SearchModule
import net.test.cloudmade.screens.user.UserActivity
import net.test.cloudmade.utils.StubTextWatcher
import javax.inject.Inject

class SearchActivity : BaseActivity(), StubTextWatcher {

    private companion object {
        const val DIRECTION_BOT = 1
    }

    private val component by lazy {
        DaggerSearchComponent.builder()
                .applicationComponent(getApplicationComponent())
                .searchModule(SearchModule(this))
                .build()
    }

    private lateinit var adapter: UsersAdapter

    @Inject
    lateinit var viewModel: SearchViewModel

    override val layout: Int
        get() = R.layout.activity_search

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        viewModel.liveDataUsers.observe(this, Observer { adapter.list = it })
    }

    override fun initViews() {
        adapter = UsersAdapter(::onItemClicked)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = adapter
        userRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(DIRECTION_BOT)
                        && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.onNextPage(searchEt.text.toString())
                }
            }
        })
    }

    override fun afterTextChanged(s: Editable?) {
        viewModel.onSearch(s.toString())
    }

    override fun onResume() {
        super.onResume()
        //to avoid extra calls for search
        searchEt.addTextChangedListener(this)
    }

    private fun onItemClicked(login: String) {
        val intent = Intent(this, UserActivity::class.java)
                .apply { putExtra(UserActivity.LOGIN_EXTRA, login) }
        startActivity(intent)
    }
}
