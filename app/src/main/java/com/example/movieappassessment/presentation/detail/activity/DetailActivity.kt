package com.example.movieappassessment.presentation.detail.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.movieappassessment.R
import com.example.movieappassessment.data.remote.dto.DetailResponse
import com.example.movieappassessment.data.remote.dto.VideoResultsItem
import com.example.movieappassessment.databinding.ActivityDetailBinding
import com.example.movieappassessment.presentation.detail.viewmodel.DetailViewModel
import com.example.movieappassessment.utils.Extended.ID
import com.example.movieappassessment.utils.loadImage
import com.example.movieappassessment.utils.removeView
import com.example.movieappassessment.utils.setOnClickListenerWithDebounce
import com.example.movieappassessment.utils.showView
import com.google.android.material.appbar.AppBarLayout
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null

    private val binding get() = _binding as ActivityDetailBinding

    private val viewModel: DetailViewModel by viewModels()

    var tracker = YouTubePlayerTracker()

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
                        initView(state.detail)
                        initYoutube(state.video, state.detail)
                    }
                }
            }
        }
    }

    private fun initView(detailResponse: DetailResponse?) {
        with(binding) {
            ivImage.apply {
                loadImage(detailResponse?.backdropPath.toString())
                setOnClickListenerWithDebounce {
                    ytVideo.showView()
                    toolbarLayout.title = " "
                }
            }
            binding.toolbarLayout.title = detailResponse?.title
        }
        with(binding.lyContent) {
            tvTitle?.text = detailResponse?.title
        }
    }

    private fun initYoutube(youtubeResponse: List<VideoResultsItem>?, detailResponse: DetailResponse?) {

        binding.ytVideo.apply {
            lifecycle.addObserver(this)
            addYouTubePlayerListener(tracker)
            isFullScreen()
            addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youtubeResponse?.map { data ->
                        youTubePlayer.loadVideo(data.key, 0f)
                        onCollaption(youTubePlayer, detailResponse)
                    }
                }
            })

        }
    }

    private fun onCollaption(youTubePlayer: YouTubePlayer, detailResponse: DetailResponse?) {
        var isShow = true
        var scrollRange = -1
        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1){
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0){
                youTubePlayer.pause()
                binding.toolbarLayout.title = detailResponse?.title
                isShow = true
            } else if (isShow){
                youTubePlayer.play()
                binding.toolbarLayout.title = " "
                isShow = false
            }
        })
    }

    private fun initBundle() {
        intent.extras?.getInt(ID)?.let { id ->
            viewModel.getDetail(id)
            viewModel.getVideoMovie(id)
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
        binding.ytVideo.release()
    }
}