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
import com.projectbase.mainapp.main.home.dailyblog.bottomfunc.ItemBottomFuncListener
import kotlinx.android.synthetic.main.item_daily_blog.view.*

class DailyBlogAdapter(val context: Context) : RecyclerView.Adapter<DailyBlogAdapter.ViewHolder>() {

    private var data = mutableListOf<ItemDailyBlog>()

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

        // set text for blog
        if (item.textBlog?.trim() == "")
            holder.view.text_daily_blog.gone()
        else {
            holder.view.text_daily_blog.visible()
            holder.view.text_daily_blog.text = item.textBlog?.trim()
        }

        // set image for blog
        if (item.imageBlog == "") holder.view.image_daily_blog.gone()
        else {
            holder.view.image_daily_blog.visible()
            Glide.with(context)
                .load(item.imageBlog)
                .error(R.drawable.ic_image_error)
                .into(holder.view.image_daily_blog)
        }


        holder.view
            .bottom_func_item_daily_blog.setItemBottomFuncListener(object : ItemBottomFuncListener {
                override fun onClickButtonComment() {
                    // catch event click on bottom func of item daily blog
                }
        })
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) { val view = item.rootView }

    fun setData(data: MutableList<ItemDailyBlog>) {
        this.data = data
        notifyDataSetChanged()
    }

    private fun active(item: View) {

    }
}
