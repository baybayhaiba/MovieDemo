package com.example.kotlinmvvm_7.workmanager

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.kotlinmvvm_7.utils.Const.BASE_IMAGE
import java.io.File
import java.io.FileOutputStream


class SaveImageToFileWorker(context: Context, params: WorkerParameters) :
    Worker(context, params) {

    companion object {
        const val POSTER_PATH = "poster_path"
    }

    override fun doWork(): Result =
        try {


            Result.success()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Result.failure()
        }
}
