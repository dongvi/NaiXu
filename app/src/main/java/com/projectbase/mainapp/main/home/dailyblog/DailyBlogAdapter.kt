package com.projectbase.mainapp.main.home.dailyblog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.projectbase.R
import com.projectbase.base.api.model.DailyBlog
import com.projectbase.base.ultils.extentions.setHidden
import com.projectbase.mainapp.main.home.dailyblog.bottomfunc.ItemBottomFuncListener
import kotlinx.android.synthetic.main.item_daily_blog.view.*

class DailyBlogAdapter(val context: Context) : RecyclerView.Adapter<DailyBlogAdapter.ViewHolder>() {

    private var data = mutableListOf<DailyBlog>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyBlogAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_daily_blog, parent, false))
    }

    override fun onBindViewHolder(holder: DailyBlogAdapter.ViewHolder, position: Int) {
        val item = data[position]

        // set user name
        holder.view.user_name.text = item.userName ?: "?"

        // set avatar's user
        Glide.with(context)
            .load(item.avatar)
            .error(R.drawable.ic_image_error)
            .into(holder.view.avatar_user)

        // set date submitted
        holder.view.date_submitted.text = item.dateSubmitted ?: "?"

        // set text for blog if textBlog null or blank, gone text_daily_blog
        holder.view.text_daily_blog.text = item.textBlog?.trim()
        holder.view.text_daily_blog.setHidden(item.textBlog?.trim().isNullOrBlank())

        // set image for blog if imageBlog null or blank, gone image_daily_blog
        Glide.with(context)
            .load(item.imageBlog)
            .error(R.drawable.ic_image_error)
            .into(holder.view.image_daily_blog)
        holder.view.image_daily_blog.setHidden(item.imageBlog?.trim().isNullOrBlank())

        holder.view.bottom_func_item_daily_blog
            .setItemBottomFuncListener(object : ItemBottomFuncListener {
                override fun onClickButtonComment() {
                    // catch event click on bottom func of item daily blog
                }
        })
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) { val view = item.rootView }

    fun setData(data: MutableList<DailyBlog>) {
        this.data = data
        notifyDataSetChanged()
    }
}
