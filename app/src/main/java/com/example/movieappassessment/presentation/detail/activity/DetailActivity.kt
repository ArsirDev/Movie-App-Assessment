package com.example.movieappassessment.presentation.detail.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.movieappassessment.R
import com.example.movieappassessment.data.remote.dto.DetailResponse
import com.example.movieappassessment.databinding.ActivityDetailBinding
import com.example.movieappassessment.presentation.detail.viewmodel.DetailViewModel
import com.example.movieappassessment.utils.Extended.ID
import com.example.movieappassessment.utils.loadImage
import com.example.movieappassessment.utils.removeView
import com.example.movieappassessment.utils.showView
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null

    private val binding get() = _binding as ActivityDetailBinding

    private val viewModel: DetailViewModel by viewModels()

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInstance()
        setContentView(binding.root)
        initBundle()
        initToolBar()
        initLaunch()
    }

    private fun initLaunch() {
        lifecycleScope.launchWhenStarted {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    viewModel.state.collectLatest { state ->
                        with(binding.lyContent) {
                            if (state.isLoading){
                                pbLoading?.showView()
                            } else {
                                pbLoading?.removeView()
                            }
                        }
                        iniView(state.detail)
                    }
                }
            }
        }
    }

    private fun iniView(detailResponse: DetailResponse?) {
        with(binding) {
            ivImage.loadImage(detailResponse?.backdropPath.toString())
            binding.toolbarLayout.title = detailResponse?.title

        }
        with(binding.lyContent) {
            tvTitle?.text = detailResponse?.title
        }
    }

    private fun initBundle() {
        intent.extras?.getInt(ID)?.let { id ->
            viewModel.getDetail(id)
        }
    }

    private fun initToolBar() {
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    private fun initInstance() {
        _binding = ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}