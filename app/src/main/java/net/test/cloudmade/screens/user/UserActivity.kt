package net.test.cloudmade.screens.user

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_user.*
import net.test.cloudmade.R
import net.test.cloudmade.core.base.BaseActivity
import net.test.cloudmade.data.repositories.UserRepo
import net.test.cloudmade.data.user.User
import net.test.cloudmade.screens.user.di.DaggerUserComponent
import net.test.cloudmade.screens.user.di.UserModule
import net.test.cloudmade.screens.user.ui.UserRepoAdapter
import javax.inject.Inject

class UserActivity : BaseActivity() {

    companion object {
        const val LOGIN_EXTRA = "LOGIN_EXTRA"
    }

    private val component by lazy {
        DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .userModule(UserModule(this))
                .build()
    }

    private lateinit var adapter: UserRepoAdapter

    @Inject
    lateinit var viewModel: UserViewModel
    lateinit var login: String

    override val layout: Int
        get() = R.layout.activity_user


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        login = intent.getStringExtra(LOGIN_EXTRA)
        viewModel.getSubscription(login).observe(this, Observer { onUserDataLoaded(it) })
    }

    override fun initViews() {
        adapter = UserRepoAdapter()
        reposRv.layoutManager = LinearLayoutManager(this)
        reposRv.adapter = adapter
    }

    private fun onUserDataLoaded(data: Pair<User, List<UserRepo>>) = with(data) {
        val user = first
        val repositories = second
        adapter.items = repositories
        with(user) {
            scoreEt.text = score.toString()
            loginEt.text = login
            idEt.text = id.toString()
        }
    }
}