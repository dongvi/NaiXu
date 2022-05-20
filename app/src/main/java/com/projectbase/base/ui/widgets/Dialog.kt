package com.projectbase.base.ui.widgets

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projectbase.R
import com.projectbase.base.ui.BaseDialog
import com.projectbase.base.ultils.extentions.setHidden
import com.projectbase.mainapp.main.MainActivity
import kotlinx.android.synthetic.main.dialog.*

class Dialog : BaseDialog() {

    interface OnConfirmListener {
        fun onConfirmOk()
    }

    private var onConfirmListener: OnConfirmListener? = null

    var description: String = ""
    var cancelAble: Boolean = true
    var canceledOnTouchOutside: Boolean = true

    fun setOnConfirmListener(onListener: OnConfirmListener?) {
        this.onConfirmListener = onListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.setCancelable(cancelAble)
        dialog?.setCanceledOnTouchOutside(canceledOnTouchOutside)
        return inflater.inflate(R.layout.dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog_description.text = description
        dialog_description.setHidden(description.isBlank())

        button_dialog_ok.setOnClickListener {
            dismiss()
            onConfirmListener?.onConfirmOk()
        }

        button_dialog_cancel.setOnClickListener {
            dismiss()
        }
    }
}
