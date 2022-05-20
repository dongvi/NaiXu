package com.projectbase.mainapp.main.home.dailyblog.postblog

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.projectbase.R
import com.projectbase.base.local.database.entity.DailyBlogEntity
import com.projectbase.base.ui.BaseFragment
import com.projectbase.base.ui.widgets.Dialog
import com.projectbase.base.ultils.extentions.setHidden
import com.projectbase.mainapp.main.MainActivity
import com.projectbase.mainapp.main.REQUEST_CODE_PICK_IMAGE
import com.projectbase.mainapp.main.REQUEST_CODE_READ_STORAGE_PERMISSION
import kotlinx.android.synthetic.main.fragment_post_blog.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.scope.viewModel
import java.time.LocalDate
import java.time.LocalDateTime

class PostBlogFragment : BaseFragment() {

    companion object {
        val TAG: String = PostBlogFragment::class.java.name ?: "PostBlogFragment::class.java.name"
    }

    private val postBlogViewModel : PostBlogViewModel by currentScope.viewModel(this)
    private var mainActivity: MainActivity? = null
    private var pickData: Intent? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        return LayoutInflater.from(context).inflate(R.layout.fragment_post_blog, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_back.setOnClickListener {
            mainActivity?.supportFragmentManager?.popBackStack()
        }

        button_add_image.setOnClickListener {
            pickImageFromGallery()
        }

        button_remove_image_view_post.setOnClickListener {
            image_view_post.setImageDrawable(null)
            pickData = null
            it.setHidden(true)
        }

        button_post.setOnClickListener {
            if(edit_text_post.text.isNullOrBlank() && image_view_post.drawable == null ) return@setOnClickListener

            if (mainActivity?.isUserLogin!!) {
                val blog = mainActivity?.user?.let { it ->
                    DailyBlogEntity(
                        it.id + LocalDateTime.now().toString(),
                        it.id,
                        "${LocalDate.now().dayOfMonth}/${LocalDate.now().monthValue}/${LocalDate.now().year}",
                        edit_text_post.text.toString(),
                        pickData?.data?.let {it.toString()})
                }

                blog?.let { it ->
                    postBlogViewModel.insertDailyBlogLocal(it)
                    mainActivity?.supportFragmentManager?.popBackStack()
                }
            } else {
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
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE_READ_STORAGE_PERMISSION)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // pick data for post dailog fragment
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_PICK_IMAGE) {
            pickData = data
            Glide.with(this).load(data?.data).into(image_view_post)
            button_remove_image_view_post.setHidden(false)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == REQUEST_CODE_READ_STORAGE_PERMISSION) {
            // read storage permission
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // if the permission is granted.
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
    }
}
