package com.projectbase.mainapp.main.home.dailyblog

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.projectbase.R
import com.projectbase.base.api.model.DailyBlog
import com.projectbase.base.api.model.User
import com.projectbase.base.ultils.extentions.setHidden
import com.projectbase.mainapp.main.home.dailyblog.bottomfunc.BottomFunc
import com.projectbase.mainapp.main.home.dailyblog.bottomfunc.ItemBottomFunc
import com.projectbase.mainapp.main.home.dailyblog.bottomfunc.ItemBottomFuncListener
import kotlinx.android.synthetic.main.bottom_func.view.*
import kotlinx.android.synthetic.main.item_daily_blog.view.*

class DailyBlogAdapter(val context: Context) : RecyclerView.Adapter<DailyBlogAdapter.ViewHolder>() {

    private var blogData = mutableListOf<DailyBlog>()
    private var userData = mutableListOf<User>()
    private var userLogin: User? = null
    private var likeActionDataOfUserLogin = mutableListOf<String?>()

    private var itemBottomFuncListener: ItemBottomFuncListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyBlogAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_daily_blog, parent, false))
    }

    override fun onBindViewHolder(holder: DailyBlogAdapter.ViewHolder, position: Int) {
        val blog = blogData[position]
        val user = userData.find { userX -> userX.id == blog.userId }

        // set user name
        holder.view.user_name.text = user?.userName ?: "?"

        // set avatar's user
        Glide.with(context)
            .load(user?.avatar)
            .error(R.drawable.ic_image_error)
            .into(holder.view.avatar_user)

        // set date submitted
        holder.view.date_submitted.text = blog.dateSubmitted ?: "?"

        // set text for blog if textBlog null or blank, gone text_daily_blog
        holder.view.text_daily_blog.text = blog.textBlog?.trim()
        holder.view.text_daily_blog.setHidden(blog.textBlog.isNullOrBlank())

        // set image for blog if imageBlog null or blank, gone image_daily_blog
        Glide.with(context)
            .load(blog.imageBlog)
            .error(R.drawable.ic_image_error)
            .into(holder.view.image_daily_blog)
        holder.view.image_daily_blog.setHidden(blog.imageBlog.isNullOrBlank())

        // set actions: like, comment, share
        holder.buttonLike.setOnClickListener {
            holder.buttonLike.setActive(!holder.buttonLike.isActive)
            itemBottomFuncListener?.onClickButtonLike(holder.buttonLike, userLogin?.id, blog.id)
        }

        holder.buttonComment.setOnClickListener {
            itemBottomFuncListener?.onClickButtonComment()
        }

        holder.buttonShare.setOnClickListener {
            itemBottomFuncListener?.onClickButtonShare()
        }

        /**
         * if user is logged in, set like status and visible button like, comment
         * if user is not logged in, hide button like, comment
         */
        userLogin?.let {
            holder.view.bottom_func_item_daily_blog.setHiddenButtonLikeAndButtonComment(false)
            holder.buttonLike.setActive(likeActionDataOfUserLogin.contains(blog.id))
        } ?: run {
            holder.view.bottom_func_item_daily_blog.setHiddenButtonLikeAndButtonComment(true)
        }
    }

    override fun getItemCount(): Int {
        return blogData.size
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val view = item.rootView
        val buttonLike = item.bottom_func_item_daily_blog.button_like
        val buttonComment = item.bottom_func_item_daily_blog.button_comment
        val buttonShare = item.bottom_func_item_daily_blog.button_share
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setBlogData(data: MutableList<DailyBlog>) {
        blogData = data
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUserData(data: MutableList<User>) {
        userData = data
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUserLogin(user: User?) {
        userLogin = user
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setLikeActionDataOfUserLogin(data: MutableList<String?>) {
        likeActionDataOfUserLogin = data
        notifyDataSetChanged()
    }

    fun setItemBottomFuncListener(itemBottomFuncListener: ItemBottomFuncListener?) {
        this.itemBottomFuncListener = itemBottomFuncListener
    }
}
