package com.example.kotlinmvvm_7.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.kotlinmvvm_7.extension.show

object ViewBinding {
    @JvmStatic
    @BindingAdapter("toast")
    fun bindToast(view: View, message: String?) {
        message?.let {
            //extension
            view.context.show(it)
        }
    }
}