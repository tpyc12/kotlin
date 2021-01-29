package com.example.myproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

abstract class BaseActivity<T, VS : BaseViewState<T>> : AppCompatActivity() {

    abstract val viewModel: BaseViewModel<T, VS>
    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)

        viewModel.getViewState().observe(this, object : Observer<VS> {
            override fun onChanged(t: VS?) {
                if (t == null) return
                if (t.data != null) renderData(t.data)
                if (t.error != null) renderError(t.error)
            }
        })
    }

    abstract fun renderData(data: T)

    protected fun renderError(error: Throwable) {
        if (error.message != null) showError(error.message!!)
    }

    private fun showError(error: String) {
        print("ошибка")
    }
}