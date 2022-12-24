package com.example.movieappassessment.presentation.detail.activity

import android.annotation.SuppressLint
import android.os.Bundle
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
import com.example.movieappassessment.utils.*
import com.example.movieappassessment.utils.Extended.ID
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.chip.Chip
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
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
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collectLatest { state ->
                        with(binding.lyContent) {
                            if (state.isLoading) {
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
        with(binding.lyContent) {
            tvTitle?.text = detailResponse?.title
            tvRelease?.text = detailResponse?.releaseDate?.dateConverter()
            tvVote?.text = detailResponse?.voteAverage.toString()
            tvDescription?.text = detailResponse?.overview
            detailResponse?.genres?.forEach {
               addChip(it.name)
            }
        }
    }

    private fun addChip(genre: String) {
        val chip = layoutInflater.inflate(R.layout.item_chip, binding.lyContent.cGroup, false) as Chip
        chip.text = genre
        binding.lyContent.cGroup?.addView(chip)
    }


    private fun initYoutube(
        youtubeResponse: List<VideoResultsItem>?,
        detailResponse: DetailResponse?
    ) {
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

        binding.ytVideo.apply {
            lifecycle.addObserver(this)
            addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youtubeResponse?.map { data ->
                        youTubePlayer.cueVideo(data.key, 0f)
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
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                binding.toolbarLayout.title = detailResponse?.title
                isShow = true
            } else if (isShow) {
                youTubePlayer.pause()
                binding.ytVideo.removeView()
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
    }
}