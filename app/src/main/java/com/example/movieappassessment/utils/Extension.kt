package com.example.movieappassessment.utils

import android.graphics.Paint
import android.os.SystemClock
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.movieappassessment.BuildConfig.ORIGINAL_IMAGE_URL
import com.example.movieappassessment.R
import com.example.movieappassessment.utils.MESSAGE.STATUS_ERROR
import com.example.movieappassessment.utils.MESSAGE.STATUS_SUCCESS
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.JsonSyntaxException
import java.io.Reader
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

fun View.startAnimation(animation: Animation, onFinish: () -> Unit) {
    animation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(p0: Animation?) {

        }

        override fun onAnimationEnd(p0: Animation?) {
            onFinish()
        }

        override fun onAnimationRepeat(p0: Animation?) {

        }

    })
    this.startAnimation(animation)
}

fun String.dateConverter(): String {
    val defaultDate: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
    val convertDate: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH)
    val ld: LocalDate = LocalDate.parse(this, defaultDate)
    return convertDate.format(ld)
}

fun snackbar(view: View, message: String, type: String, duration: Int = Snackbar.LENGTH_SHORT) {
    when(type) {
        STATUS_SUCCESS -> {
            Snackbar.make(view, message, duration).apply {
                this.view.setBackgroundColor(ContextCompat.getColor(this.context, R.color.green_color))
            }.show()
        }
        STATUS_ERROR -> {
            Snackbar.make(view, message, duration).apply {
                this.view.setBackgroundColor(ContextCompat.getColor(this.context, R.color.red_color))
            }.show()
        }
    }
}

fun View.setOnClickListenerWithDebounce(debounceTime: Long = 600L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(p0: View?) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun String.createImageUrl(): String {
    return ORIGINAL_IMAGE_URL + this
}

fun TextView.underline() {
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.removeView() {
    this.visibility = View.GONE
}

fun ArrayList<View>.removeAllView() {
    this.forEach {
        it.removeView()
    }
}

fun ArrayList<View>.showAllView() {
    this.forEach {
        it.showView()
    }
}

fun ImageView.loadImage(imageUrl: String, roundedValue: Int = 24) {
    Glide.with(this.context)
        .load(imageUrl.createImageUrl())
        .error(ContextCompat.getDrawable(this.context, R.drawable.ic_broken_image_black))
        .placeholder(ContextCompat.getDrawable(this.context, R.drawable.ic_image_black))
        .transform(RoundedCorners(roundedValue))
        .into(this)
}

inline fun <reified T> String.fromJson(): T {
    return Gson().fromJson(this, T::class.java)
}

inline fun <reified T> Reader.fromJson(): T? {
    return try {
        Gson().fromJson(this, T::class.java)
    } catch (e: JsonSyntaxException) {
        null
    } catch (e: JsonIOException) {
        null
    }
}

inline fun <reified T> String.fromJson(type: Type): T? {
    return try {
        Gson().fromJson(this, type)
    } catch (e: JsonSyntaxException) {
        null
    } catch (e: JsonIOException) {
        null
    }
}

fun Any.toJson(): String {
    return Gson().toJson(this)
}

fun Any.toJson(type: Type): String {
    return Gson().toJson(this, type)
}

fun isLastVisible(rv: RecyclerView, shouldFindLastCompletelyVisible: Boolean = true): Boolean {
    val layoutManager = rv.layoutManager as LinearLayoutManager
    val pos = if (shouldFindLastCompletelyVisible) {
        layoutManager.findLastCompletelyVisibleItemPosition()
    } else {
        layoutManager.findFirstVisibleItemPosition()
    }
    val numItems: Int = rv.adapter?.itemCount ?: 0
    return pos >= numItems - 1
}