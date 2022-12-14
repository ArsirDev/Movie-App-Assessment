package com.example.movieappassessment.presentation.home.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieappassessment.R
import com.example.movieappassessment.databinding.FragmentHomeBinding
import com.example.movieappassessment.domain.adapter.popular.PopularAdapter
import com.example.movieappassessment.domain.adapter.upcoming.UpcomingAdapter
import com.example.movieappassessment.presentation.detail.activity.DetailActivity
import com.example.movieappassessment.presentation.home.ui.home.viewmodel.HomeViewModel
import com.example.movieappassessment.utils.Extended.ID
import com.example.movieappassessment.utils.MESSAGE.STATUS_ERROR
import com.example.movieappassessment.utils.MarginItemDecorationHorizontal
import com.example.movieappassessment.utils.removeView
import com.example.movieappassessment.utils.showView
import com.example.movieappassessment.utils.snackbar
import com.example.movieappassessment.utils.underline
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private var upcomingAdapter: UpcomingAdapter? = null

    private var popularAdapter: PopularAdapter? = null

    private val viewmodel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        upcomingAdapter = UpcomingAdapter.instance()
        popularAdapter = PopularAdapter.instance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initView()
        initLaunch()
    }

    private fun initLaunch() {
        lifecycleScope.launchWhenStarted {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewmodel.state.collectLatest { result ->
                        if (result.isLoading) {
                            binding.pbLaoding?.showView()
                        } else {
                            binding.pbLaoding?.removeView()
                        }

                        if (result.upComing.isNotEmpty()) {
                            upcomingAdapter?.differ?.submitList(result.upComing)
                        }

                        if (result.popular.isNotEmpty()) {
                            popularAdapter?.differ?.submitList(result.popular)
                        }
                    }
                }
            }
        }
    }

    private fun initAdapter() {
        upcomingAdapter?.let { adapter ->
            binding.rvUpcoming.apply {
                this.adapter = adapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(MarginItemDecorationHorizontal(requireContext(), R.dimen.fragment_horizontal_8_margin))
                ViewCompat.setNestedScrollingEnabled(this, true)
            }

            adapter.setOnItemClickListener { id ->
                if (id <= 0) {
                    snackbar(binding.root, "Id Movie tidak ditemukan", STATUS_ERROR)
                    return@setOnItemClickListener
                }
                startActivity(Intent(requireContext(), DetailActivity::class.java).putExtra(ID, id))
            }
        }
        popularAdapter?.let { adapter ->
            binding.rvPopular.apply {
                this.adapter = adapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(MarginItemDecorationHorizontal(requireContext(), R.dimen.fragment_horizontal_8_margin))
                ViewCompat.setNestedScrollingEnabled(this, true)
            }

            adapter.setOnItemClickListener { id ->
                if (id <= 0) {
                    snackbar(binding.root, "Id Movie tidak ditemukan", STATUS_ERROR)
                    return@setOnItemClickListener
                }
                startActivity(Intent(requireContext(), DetailActivity::class.java).putExtra(ID, id))
            }
        }
    }

    private fun initView() {
        binding.tvUpcomingViewAll.underline()
        viewmodel.getUpcoming(1, "en-US")
        viewmodel.getPopular(1, "en-US")
        binding.tvUpcomingViewAll.setOnClickListener { view ->
            view.findNavController().navigate(R.id.nav_view_all_upcoming)
        }
        binding.tvPopularViewAll.setOnClickListener { view ->
            view.findNavController().navigate(R.id.nav_view_all_popular)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        upcomingAdapter = null
        popularAdapter = null
    }
}