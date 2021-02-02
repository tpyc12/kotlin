package com.example.myproject.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.myproject.R
import com.example.myproject.ui.states.BaseViewState
import com.example.myproject.viewmodel.BaseViewModel
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity<T, S : BaseViewState<T>> : AppCompatActivity() {

    abstract val viewModel: BaseViewModel<T, S>
    abstract val layoutRes: Int
    abstract val ui: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ui.root)

        viewModel.getViewState().observe(this) { t ->
            t?.apply {
                data?.let { data -> renderData(data) }
                error?.let { error -> renderError(error) }
            }
        }
    }

    abstract fun renderData(data: T)

    protected open fun renderError(error: Throwable) {
        error.message?.let { showError(it) }
    }

    protected open fun showError(error: String) {
        Snackbar.make(ui.root, error, Snackbar.LENGTH_INDEFINITE).apply {
            setAction(R.string.snackbar_action) { dismiss() }
            show()
        }
    }
}