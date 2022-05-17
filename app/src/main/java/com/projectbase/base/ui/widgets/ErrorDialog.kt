package com.projectbase.base.ui.widgets

import android.content.Context
import android.view.LayoutInflater
import com.projectbase.R
import com.projectbase.base.ui.BaseDialog
import com.projectbase.base.ultils.extentions.setHidden
import kotlinx.android.synthetic.main.dialog_error.*

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

        textTitle.text = title
        textTitle.setHidden(title.isBlank())

        textDescription.text = description
        textDescription.setHidden(description.isBlank())
    }
}
