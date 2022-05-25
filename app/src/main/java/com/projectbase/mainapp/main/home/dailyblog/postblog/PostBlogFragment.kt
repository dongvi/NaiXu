package com.projectbase.mainapp.main.home.dailyblog.postblog

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.projectbase.R
import com.projectbase.base.api.model.User
import com.projectbase.base.local.database.entity.DailyBlogEntity
import com.projectbase.base.ui.BaseFragment
import com.projectbase.base.ui.widgets.Dialog
import com.projectbase.base.ultils.extentions.setHidden
import com.projectbase.mainapp.main.MainActivity
import com.projectbase.mainapp.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_post_blog.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.scope.viewModel
import java.text.SimpleDateFormat
import java.util.*

class PostBlogFragment : BaseFragment() {

    companion object {
        val TAG: String = PostBlogFragment::class.java.name ?: "PostBlogFragment::class.java.name"
    }

    private val mainViewModel: MainViewModel by sharedViewModel()
    private val postBlogViewModel : PostBlogViewModel by currentScope.viewModel(this)
    private var user: User? = null
    private var mainActivity: MainActivity? = null
    private var linkImage: String? = null
    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            linkImage = it.toString()
            Glide.with(this).load(linkImage).into(image_view_post)
            button_remove_image_view_post.setHidden(false)
        }
    }
    private val requestStoragePermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
        if (isGranted) {
            pickImageFromGallery()
        } else {
            val snackBar = Snackbar.make(
                root_post_blog,
                resources.getString(R.string.suggest_grant_storage_permission),
                Snackbar.LENGTH_SHORT)
            snackBar.view.setBackgroundColor(resources.getColor(R.color.blue_85, null))
            snackBar.show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        return LayoutInflater.from(context).inflate(R.layout.fragment_post_blog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        mainViewModel.getUser().observe(viewLifecycleOwner) {
            user = it
        }
        handleObservable()
    }

    @SuppressLint("SimpleDateFormat")
    private fun initView() {
        button_back.setOnClickListener {
            mainActivity?.supportFragmentManager?.popBackStack()
        }

        button_add_image.setOnClickListener {
            pickImageFromGallery()
        }

        button_remove_image_view_post.setOnClickListener {
            image_view_post.setImageDrawable(null)
            linkImage = null
            it.setHidden(true)
        }

        button_post.setOnClickListener {
            if(edit_text_post.text.isNullOrBlank() && image_view_post.drawable == null ) return@setOnClickListener

            user?.let {
                val blog = DailyBlogEntity(
                    it.id + SimpleDateFormat("ddMMyyyyhhmmss").format(Date()),
                    it.id,
                    SimpleDateFormat("dd/MM/yyyy").format(Date()),
                    edit_text_post.text.toString(),
                    linkImage)

                postBlogViewModel.insertDailyBlogLocal(blog)
                mainActivity?.supportFragmentManager?.popBackStack()
            } ?: run {
                val requestLoginDialog = Dialog()
                requestLoginDialog.description = resources.getString(R.string.request_login_dialog_description)
                mainActivity?.supportFragmentManager?.let { it -> requestLoginDialog.show(it, null) }

                requestLoginDialog.setOnConfirmListener(object : Dialog.OnConfirmListener {
                    override fun onConfirmOk() {
                        // add fragment login
                    }
                })
            }
        }
    }

    private fun handleObservable() {}

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(!hidden) {
            mainActivity?.setCurrentFragmentTag(TAG)
        }
    }

    private fun pickImageFromGallery() {
        /**
         * Check if the permission has been granted ?
         * if the permission is granted, access to storage
         * else create a request the permission
         */

        if(mainActivity?.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            pickImage.launch("image/*")
        } else {
            requestStoragePermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }
}
