package com.projectbase.mainapp.main.home.dailyblog

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.projectbase.R
import com.projectbase.base.api.model.DailyBlog
import com.projectbase.base.api.model.User
import com.projectbase.base.local.database.entity.DailyBlogEntity
import com.projectbase.base.ultils.extentions.setHidden
import com.projectbase.mainapp.main.home.dailyblog.bottomfunc.ItemBottomFuncListener
import kotlinx.android.synthetic.main.item_daily_blog.view.*

class DailyBlogAdapter(val context: Context) : RecyclerView.Adapter<DailyBlogAdapter.ViewHolder>() {

    private var data = mutableListOf<DailyBlog>()
    private var dataUser = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyBlogAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_daily_blog, parent, false))
    }

    override fun onBindViewHolder(holder: DailyBlogAdapter.ViewHolder, position: Int) {
        val item = data[position]
        val user = dataUser.find { userX -> userX.id == item.userId }

        // set user name
        holder.view.user_name.text = user?.userName ?: "?"

        // set avatar's user
        Glide.with(context)
            .load(user?.avatar)
            .error(R.drawable.ic_image_error)
            .into(holder.view.avatar_user)

        // set date submitted
        holder.view.date_submitted.text = item.dateSubmitted ?: "?"

        // set text for blog if textBlog null or blank, gone text_daily_blog
        holder.view.text_daily_blog.text = item.textBlog?.trim()
        holder.view.text_daily_blog.setHidden(item.textBlog.isNullOrBlank())

        // set image for blog if imageBlog null or blank, gone image_daily_blog
        Glide.with(context)
            .load(item.imageBlog)
            .error(R.drawable.ic_image_error)
            .into(holder.view.image_daily_blog)
        holder.view.image_daily_blog.setHidden(item.imageBlog.isNullOrBlank())

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

    @SuppressLint("NotifyDataSetChanged")
    fun setDataBlog(data: MutableList<DailyBlog>) {
        for(item in data) {
            this.data.add(item)
        }
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataUser(data: MutableList<User>) {
        this.dataUser = data
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataBlogLocal(data: MutableList<DailyBlogEntity>) {
        for(item in data) {
            this.data.add(DailyBlog(item.id, item.userId, item.dateSubmitted, item.textBlog, item.imageBlog))
        }
        notifyDataSetChanged()
    }

    fun removeAllDataBlog() {
        data.removeAll(data)
    }
}
