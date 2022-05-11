package com.projectbase.mainapp.main.home.dailyblog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.projectbase.R
import com.projectbase.base.api.model.ItemDailyBlog
import com.projectbase.base.ultils.extentions.gone
import com.projectbase.base.ultils.extentions.visible
import kotlinx.android.synthetic.main.item_daily_blog.view.*

class DailyBlogAdapter(val context: Context?) : RecyclerView.Adapter<DailyBlogAdapter.ViewHolder>() {

    private var data: List<ItemDailyBlog>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyBlogAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_daily_blog, parent, false))
    }

    override fun onBindViewHolder(holder: DailyBlogAdapter.ViewHolder, position: Int) {
        val item = data?.get(position)

        if (item?.userId == "") {
            holder.view.user_name.text = "?"
        }

        holder.view.date_submitted.text = item?.dateSubmitted ?: "?"

        if (item?.textBlog?.trim() == "")
            holder.view.text_daily_blog.gone()
        else {
            holder.view.text_daily_blog.visible()
            holder.view.text_daily_blog.text = item?.textBlog?.trim()
        }

        if (item?.imageBlog == "") {
            holder.view.image_daily_blog.gone()
        } else
            holder.view.image_daily_blog.visible()
            context?.let {
                Glide.with(it)
                    .load(item?.imageBlog)
                    .error(R.drawable.ic_image_error)
                    .into(holder.view.image_daily_blog)
            }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) { val view = item.rootView }

    fun setData(data: List<ItemDailyBlog>) {
        this.data = data
        notifyDataSetChanged()
    }
}