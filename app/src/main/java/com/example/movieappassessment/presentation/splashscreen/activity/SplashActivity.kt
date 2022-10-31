package com.example.movieappassessment.presentation.splashscreen.activity

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.movieappassessment.R
import com.example.movieappassessment.databinding.ActivitySplashBinding
import com.example.movieappassessment.presentation.home.activity.HomeActivity
import com.example.movieappassessment.presentation.splashscreen.viewmodel.SplashViewModel
import com.example.movieappassessment.presentation.welcome.activity.WelcomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null

    private val binding get() = _binding as ActivitySplashBinding

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        initInstallSplashScreen()
        super.onCreate(savedInstanceState)
        initInstance()
        setContentView(binding.root)
        initialStartLoading()
    }

    private fun initialStartLoading() {
        findViewById<View>(R.id.content)?.apply {
            viewTreeObserver.addOnPreDrawListener(
                object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        return if (viewModel.isLoading.value) {
                            viewTreeObserver.removeOnPreDrawListener(this)
                            true
                        } else false
                    }
                }
            )
        }
    }

    private fun initInstallSplashScreen() {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
            initDirection()
            setOnExitAnimationListener { splashScreenView ->
                ObjectAnimator.ofFloat(
                    splashScreenView.view,
                    View.TRANSLATION_Y,
                    0f,
                    -splashScreenView.view.height.toFloat()
                ).apply {
                    interpolator = AnticipateInterpolator()
                    duration = 1000L
                    doOnEnd {
                        splashScreenView.remove()
                    }
                }.start()
            }
        }
    }

    private fun initDirection() {
        lifecycleScope.launchWhenStarted {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collectLatest { status ->
                        if (status) {
                            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                            finish()
                        } else {
                            startActivity(Intent(this@SplashActivity, WelcomeActivity::class.java))
                            finish()
                        }
                    }
                }
            }
        }
    }

    private fun initInstance() {
        _binding = ActivitySplashBinding.inflate(layoutInflater)
    }


    override fun onBackPressed() {
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishAffinity()
            }
        })
    }
}