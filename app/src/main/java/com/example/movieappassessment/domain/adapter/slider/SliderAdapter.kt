package com.example.movieappassessment.domain.adapter.slider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.movieappassessment.domain.model.SliderDataLayer

class SliderAdapter: PagerAdapter() {
    override fun getCount(): Int {
        return SliderDataLayer.values().size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return LayoutInflater.from(container.context).inflate(SliderDataLayer.values()[position].layoutResId, container, false).also { view ->
            container.addView(view)
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.apply {
            removeView(`object` as View)
        } as ViewPager
    }

    companion object {
        fun instance() = SliderAdapter()
    }
}