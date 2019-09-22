package net.test.cloudmade.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.test.cloudmade.TestApplication

abstract class BaseActivity : AppCompatActivity() {

    abstract val layout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        initViews()
    }

    abstract fun initViews()

    protected fun getApplicationComponent() = (application as TestApplication).appComponent
}