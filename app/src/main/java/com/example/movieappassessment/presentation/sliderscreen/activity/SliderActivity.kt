package com.example.movieappassessment.presentation.sliderscreen.activity

import android.content.Intent
import android.os.Bundle
import android.transition.TransitionManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import com.example.movieappassessment.databinding.ActivitySliderBinding
import com.example.movieappassessment.domain.adapter.slider.SliderAdapter
import com.example.movieappassessment.presentation.home.HomeActivity
import com.example.movieappassessment.presentation.sliderscreen.viewmodel.SliderViewModel
import com.example.movieappassessment.utils.removeView
import com.example.movieappassessment.utils.showView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SliderActivity : AppCompatActivity() {

    private var _binding: ActivitySliderBinding? = null

    private val binding get() = _binding as ActivitySliderBinding

    private val viewModel: SliderViewModel by viewModels()

    private lateinit var sliderAdapter: SliderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInstance()
        setContentView(binding.root)
        initViewPager()
    }

    private fun initViewPager() {
        with(binding) {
            vpSlider.apply {
                adapter = sliderAdapter
                addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                    }
                    override fun onPageSelected(position: Int) {
                        TransitionManager.beginDelayedTransition(container)
                        when (position) {
                            2 -> {
                                lifecycleScope.launch {
                                    viewModel.setFirstInstall(true)
                                }
                                btnNext.showView()
                            }
                            else -> {
                                btnNext.removeView()
                            }
                        }
                    }
                    override fun onPageScrollStateChanged(state: Int) {}
                })
            }
            indicator.attachTo(
                viewPager = vpSlider
            )

            btnNext.setOnClickListener {
                onDirection()
            }
        }
    }

    private fun onDirection() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun initInstance() {
        _binding = ActivitySliderBinding.inflate(layoutInflater)
        sliderAdapter = SliderAdapter.instance()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}