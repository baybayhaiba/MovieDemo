package com.example.kotlinmvvm_7.utils

import android.text.format.DateUtils


class TimeAgo {
    companion object {
        fun convertTimeAgo(time: Long): String {
            return DateUtils.getRelativeTimeSpanString(time).toString()
        }
    }
}