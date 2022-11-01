package com.example.movieappassessment.presentation.home.activity.ui.home.fragment

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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieappassessment.R
import com.example.movieappassessment.databinding.FragmentHomeBinding
import com.example.movieappassessment.domain.adapter.popular.PopularAdapter
import com.example.movieappassessment.domain.adapter.upcoming.UpcomingAdapter
import com.example.movieappassessment.presentation.home.activity.ui.home.viewmodel.HomeViewModel
import com.example.movieappassessment.utils.MarginItemDecorationHorizontal
import com.example.movieappassessment.utils.removeView
import com.example.movieappassessment.utils.showView
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
        }
        popularAdapter?.let { adapter ->
            binding.rvPopular.apply {
                this.adapter = adapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(MarginItemDecorationHorizontal(requireContext(), R.dimen.fragment_horizontal_8_margin))
                ViewCompat.setNestedScrollingEnabled(this, true)
            }
        }
    }

    private fun initView() {
        binding.tvUpcomingViewAll.underline()
        viewmodel.getUpcoming(1, "en-US")
        viewmodel.getPopular(1, "en-US")

        binding.tvUpcomingViewAll.setOnClickListener { view ->
            view.findNavController().navigate(R.id.nav_viewall)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        upcomingAdapter = null
        popularAdapter = null
    }
}