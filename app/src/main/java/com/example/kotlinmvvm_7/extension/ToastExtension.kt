package com.example.kotlinmvvm_7.extension

import android.content.Context
import android.widget.Toast

fun Context.show(message: String): Unit {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

