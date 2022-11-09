package com.android.vncalling.ui.custom.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatButton
import com.android.vncalling.R

class CommonAlertDialog constructor(context: Context) : Dialog(context) {

    lateinit var btnConfirm: AppCompatButton
    lateinit var tvTitle: TextView
    lateinit var tvSubTitle: TextView
    lateinit var point: TextView
    lateinit var tvCancel: TextView
    lateinit var tvmypoint: TextView
    lateinit var price: TextView
    lateinit var tvbuypoint: TextView
    lateinit var view1: View
    lateinit var view2: View
    lateinit var view3: View
    lateinit var imgPoint: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.layout_alert_error)

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(window?.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.apply {
            attributes = lp
            setGravity(Gravity.CENTER_VERTICAL)
            setBackgroundDrawableResource(android.R.color.transparent)
        }
        btnConfirm = findViewById(R.id.btn_confirm)
        tvTitle = findViewById(R.id.tv_title)
        tvSubTitle = findViewById(R.id.tv_subTitle)
        tvCancel = findViewById(R.id.tv_cancel)
        tvmypoint = findViewById(R.id.tv_mypoint)
        point = findViewById(R.id.point)
        price = findViewById(R.id.price)
        tvbuypoint = findViewById(R.id.tv_buypoint)
        view1 = findViewById(R.id.view1)
        view2 = findViewById(R.id.view2)
        view3 = findViewById(R.id.view3)
        imgPoint = findViewById(R.id.img_icpoint)
        setCanceledOnTouchOutside(false)
        setOnPositivePressed {
            this.dismiss()
        }
    }

    fun setOnPositivePressed(onPositivePressed: (CommonAlertDialog) -> Unit): CommonAlertDialog {
        btnConfirm.setOnClickListener {
            onPositivePressed(this)
        }
        return this
    }

    fun setOnNegativePressed(onNegativePressed: (CommonAlertDialog) -> Unit): CommonAlertDialog {
        tvCancel.setOnClickListener {
            onNegativePressed(this)
        }
        return this
    }

    fun setTextNegativeButton(@StringRes textId: Int): CommonAlertDialog {
        tvCancel.text = context.resources.getString(textId)
        tvCancel.visibility = View.VISIBLE
        return this
    }

    fun setTextPositiveButton(@StringRes textId: Int): CommonAlertDialog {
        btnConfirm.text = context.resources.getString(textId)
        return this
    }

    fun setDialogTitle(res: Int): CommonAlertDialog {
        tvTitle.text = context.resources.getString(res)
        return this
    }

    fun setDialogTitle(title: String): CommonAlertDialog {
        tvTitle.text = title
        return this
    }

    fun setDialogTitleWithString(title: String): CommonAlertDialog {
        tvTitle.text = title
        return this
    }

    override fun setTitle(res: Int) {
        tvTitle.text = context.resources.getString(res)
    }

    fun setContent(vararg res: Int): CommonAlertDialog {
        var content = ""
        for (i in res.indices) {
            content += context.resources.getString(res[i])
            if (i < res.size - 1) content += "\n"
        }
        tvSubTitle.text = content
        tvSubTitle.visibility = View.VISIBLE
        return this
    }

    fun setContent(vararg contents: String): CommonAlertDialog {
        var content = ""
        for (i in contents.indices) {
            content += contents[i]
            if (i < contents.size - 1) content += "\n"
        }
        tvSubTitle.text = content
        tvSubTitle.visibility = View.VISIBLE
        return this
    }

    fun showDialog(): CommonAlertDialog {
        if (!isShowing)
            super.show()
        return this
    }

    fun setCancelableDialog(flag: Boolean): CommonAlertDialog {
        if (isShowing) {
            super.setCancelable(flag)
        }
        return this
    }

    fun setBuyPointDialog(): CommonAlertDialog {
        tvmypoint.visibility = View.VISIBLE
        point.visibility = View.VISIBLE
        price.visibility = View.VISIBLE
        tvbuypoint.visibility = View.VISIBLE
        view1.visibility = View.VISIBLE
        view2.visibility = View.VISIBLE
        view3.visibility = View.VISIBLE
        imgPoint.visibility = View.VISIBLE
        return this
    }

    fun setMyPoint(flag: String): CommonAlertDialog {
        tvmypoint.text = flag
        return this
    }

    fun setBuyPoint(flag: String): CommonAlertDialog {
        tvbuypoint.text = flag
        return this
    }

    var typeDialog = TYPE_DEFAULT

    companion object {
        const val TYPE_DEFAULT = -1

        @SuppressLint("StaticFieldLeak")
        var instance: CommonAlertDialog? = null
        fun getInstanceCommonAlertdialog(
            context: Context,
            type: Int = TYPE_DEFAULT
        ): CommonAlertDialog {
            if (instance != null && instance?.isShowing == true) {
                if (((instance?.typeDialog ?: TYPE_DEFAULT == TYPE_DEFAULT) || (type != instance?.typeDialog ?: TYPE_DEFAULT))) {
                    instance?.dismiss()
                } else {
                    return instance!!
                }
            } else {
                instance = CommonAlertDialog(context)
            }
            instance?.typeDialog = type
            return instance!!
        }
    }

    override fun dismiss() {
        super.dismiss()
        typeDialog = TYPE_DEFAULT
    }

}