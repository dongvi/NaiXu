package com.projectbase.base.ui.widgets

import android.content.Context
import android.view.LayoutInflater
import com.projectbase.R
import com.projectbase.base.ui.BaseDialog
import kotlinx.android.synthetic.main.dialog_error.*
import com.projectbase.base.ultils.extentions.gone
import com.projectbase.base.ultils.extentions.visible

class ErrorDialog : BaseDialog {

    interface OnConfirmListener {
        fun onConfirmOk()
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, themeId: Int) : super(context, themeId)

    private var onConfirmListener: OnConfirmListener? = null

    var title: String = ""
    var description: String = ""

    fun setOnConfirmListener(onListener: OnConfirmListener?) {
        this.onConfirmListener = onListener
    }

    override val isCancelable: Boolean
        get() = false

    override fun initView() {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_error, null, false)
        setContentView(view)

        buttonOk.setOnClickListener {
            dismiss()
            onConfirmListener?.onConfirmOk()
        }
    }

    override fun show() {
        super.show()

        if (title.isNotBlank()) {
            textTitle.visible()
            textTitle.text = title
        } else {
            textTitle.gone()
        }

        if (description.isNotBlank()) {
            textDescription.visible()
            textDescription.text = description
        } else {
            textDescription.gone()
        }
    }
}
