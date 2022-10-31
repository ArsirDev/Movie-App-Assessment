package com.example.movieappassessment.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.view.animation.Animation

fun View.startAnimation(animation: Animation, onFinish: () -> Unit) {
    animation.setAnimationListener(object: Animation.AnimationListener {
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

fun View.setOnClickListenerWithDebounce(debounceTime: Long = 600L, action: () -> Unit) {
    this.setOnClickListener(object: View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(p0: View?) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
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