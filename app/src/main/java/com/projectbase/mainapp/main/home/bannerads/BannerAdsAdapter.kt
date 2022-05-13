package com.projectbase.mainapp.main.home.bannerads

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.projectbase.R
import com.projectbase.base.api.model.BannerAds
import com.projectbase.base.api.model.ItemDailyBlog
import kotlinx.android.synthetic.main.item_banner_ads.view.*
import java.util.*

class BannerAdsAdapter(val context: Context) : PagerAdapter() {

    private var data = mutableListOf<BannerAds>()
    private val inflater = LayoutInflater.from(context)
    private var bannerAdsAdapterListener: BannerAdsAdapterListener? = null

    override fun getCount(): Int {
        return data.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = inflater.inflate(R.layout.item_banner_ads, container, false)
        val obj = view.root_item_banner_ads

        Glide.with(context)
            .load(data[position].imageUrl)
            .into(obj.image_item_banner_ads)

        // removeView on the child's parent first
        if (obj.parent != null)
            (obj.parent as ViewGroup).removeView(obj)

        // catch click event
        obj.setOnClickListener {
            bannerAdsAdapterListener?.onClickBannerAds(position)
        }

        Objects.requireNonNull(container).addView(obj)
        return obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun setData(data: MutableList<BannerAds>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun setBannerAdsAdapterListener(bannerAdsAdapterListener: BannerAdsAdapterListener?) {
        this.bannerAdsAdapterListener = bannerAdsAdapterListener
    }
}
