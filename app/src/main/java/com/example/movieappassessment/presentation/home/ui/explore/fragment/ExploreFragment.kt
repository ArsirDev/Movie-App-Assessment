package com.example.movieappassessment.presentation.home.ui.explore.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappassessment.R
import com.example.movieappassessment.databinding.FragmentExploreBinding
import com.example.movieappassessment.presentation.detail.activity.DetailActivity
import com.example.movieappassessment.presentation.home.ui.explore.adapter.discover.DiscoverAdapter
import com.example.movieappassessment.presentation.home.ui.explore.adapter.search.ExploreAdapter
import com.example.movieappassessment.presentation.home.ui.explore.event.ExploreEvent
import com.example.movieappassessment.presentation.home.ui.explore.viewmodel.ExploreViewModel
import com.example.movieappassessment.utils.Extended.ID
import com.example.movieappassessment.utils.GridMarginItemDecoration
import com.example.movieappassessment.utils.isLastVisible
import com.example.movieappassessment.utils.removeView
import com.example.movieappassessment.utils.setOnClickListenerWithDebounce
import com.example.movieappassessment.utils.showView
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null

    private val binding get() = _binding as FragmentExploreBinding

    private val viewModel: ExploreViewModel by viewModels()

    private var exploreAdapter: ExploreAdapter? = null

    private var discoverAdapter: DiscoverAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        exploreAdapter = ExploreAdapter.instance()
        discoverAdapter = DiscoverAdapter.instance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLaunch()
        initAdapter()
        initPagination()
        initPaginationDiscover()
        initView()
    }

    private fun initPaginationDiscover() {
        binding.rvExplore.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isLastVisible(recyclerView)) {
                    viewModel.onGenreEVent(
                        event = ExploreEvent.DiscoverLoadMore
                    )
                }
            }
        })
    }

    private fun initPagination() {
        binding.rvExplore.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isLastVisible(recyclerView)) {
                    viewModel.onEvent(
                        event = ExploreEvent.SearchLoadMore
                    )
                }
            }
        })
    }

    private fun initAdapter() {
        exploreAdapter?.let { adapter ->
            binding.rvExplore.apply {
                this.adapter = adapter
                layoutManager = GridLayoutManager(requireContext(), 2)
                overScrollMode = RecyclerView.OVER_SCROLL_NEVER
                addItemDecoration(GridMarginItemDecoration(8))
                ViewCompat.setNestedScrollingEnabled(this, true)
            }

            adapter.setOnItemClickListener { id ->
                startActivity(Intent(requireContext(), DetailActivity::class.java).putExtra(ID, id))
            }
        }

        discoverAdapter?.let { adapter ->
            binding.rvDiscover.apply {
                this.adapter = adapter
                layoutManager = GridLayoutManager(requireContext(), 2)
                overScrollMode = RecyclerView.OVER_SCROLL_NEVER
                addItemDecoration(GridMarginItemDecoration(8))
                ViewCompat.setNestedScrollingEnabled(this, true)
            }

            adapter.setOnItemClickListener { id ->
                startActivity(Intent(requireContext(), DetailActivity::class.java).putExtra(ID, id))
            }
        }
    }

    private fun initLaunch() {
        lifecycleScope.launchWhenStarted {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collectLatest { result ->
                        if (result.isLoading) {
                            binding.pbLoading.showView()
                        } else {
                            binding.pbLoading.removeView()
                        }

                        if (result.genre.isNotEmpty()) {
                            result.genre.forEach { genre ->
                                addChip(genre.name, genre.id)
                            }
                        }

                        if (result.search.isNotEmpty()) {
                            binding.rvExplore.showView()
                            exploreAdapter?.differ?.submitList(result.search)
                        } else {
                            binding.rvDiscover.showView()
                            discoverAdapter?.differ?.submitList(result.discover)
                        }

                        if (result.search.isEmpty() && result.discover.isEmpty()) {
                            binding.icEmpty.showView()
                        } else {
                            binding.icEmpty.removeView()

                        }
                    }
                }
            }
        }
    }

    private fun addChip(genre: String, genreId: Int) {
        val chip = layoutInflater.inflate(R.layout.item_chip, binding.cGroup, false) as Chip
        chip.text = genre
        chip.setOnClickListenerWithDebounce {
            binding.rvExplore.removeView()
            viewModel.onGenreEVent(
                event = ExploreEvent.DiscoverGenre(genreId.toString())
            )
        }
        binding.cGroup.addView(chip)
    }

    private fun initView() {
        binding.btnSearchAction.setOnClickListenerWithDebounce {
            binding.rvDiscover.removeView()
            val search = binding.etSearch.text.toString().trim()
            viewModel.onEvent(
                event = ExploreEvent.SearchUser(search)
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        exploreAdapter = null
        discoverAdapter = null
    }
}