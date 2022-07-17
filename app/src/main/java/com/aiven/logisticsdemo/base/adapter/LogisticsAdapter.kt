package com.aiven.logisticsdemo.base.adapter

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewbinding.ViewBinding
import com.aiven.logisticsdemo.base.adapter.viewholder.ViewBindingHolder
import com.aiven.logisticsdemo.bean.Logistics
import com.aiven.logisticsdemo.bean.LogisticsBean
import com.aiven.logisticsdemo.utils.PhoneNumberUtil

/**
 * 物流基类适配器
 * @param context
 * @param data
 * @param inflate
 * */
abstract class LogisticsAdapter<T: Logistics, VH: ViewBindingHolder<VB>, VB: ViewBinding>(
    context: Context,
    data: List<T>,
    inflate: (layoutInflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean) -> VB
) : RecyclerVIewHolderAdapter<T, VH, VB>(
    context,
    data,
    inflate
){
    /**
     * 点击电话事件监听
     * */
    private var onPhoneClickListener: ((phoneNumber: String) -> Unit)? = null
    private var onStarListener: ((url: String) -> Unit)? = null

    /**
     * 获取第position项数据
     * @param position
     * @return data[position]
     * */
    fun getDataByPosition(position: Int): T {
        return data[position]
    }

    /**
     * 设置点击电话事件监听
     * @param listener
     * */
    fun setOnPhoneClickListener(listener: ((phoneNumber: String) -> Unit)?) {
        onPhoneClickListener = listener
    }

    fun setOnStarListener(listener: ((url: String) ->Unit)?) {
        this.onStarListener = listener
    }

    /**
     * 设置点击电话事件
     * @param textView
     * @param text
     * */
    protected fun setPhoneClickText(textView: TextView, text: String, underLine: Boolean) {
        SpannableString(text).apply {
            for (phoneNumberIndex in PhoneNumberUtil.getAllPhoneNumberIndex(text)) {
                object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        onPhoneClickListener?.invoke(textView.text.toString().substring(phoneNumberIndex.startIndex, phoneNumberIndex.endIndex).trim().replace("-", ""))
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        ds.isUnderlineText = underLine
                    }
                }.apply {
                    setSpan(this@apply, phoneNumberIndex.startIndex, phoneNumberIndex.endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                ForegroundColorSpan(getPhoneColor()).apply {
                    setSpan(this@apply, phoneNumberIndex.startIndex, phoneNumberIndex.endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
            }
        }.apply {
            textView.text = this
            textView.movementMethod = LinkMovementMethod.getInstance()
        }
    }

    protected fun setStarTextView(textView: TextView, text: String, startIndex: Int, endIndex: Int) {
        SpannableString(text).apply {
            object : ClickableSpan() {
                override fun onClick(widget: View) {
                    onStarListener?.invoke(LogisticsBean.GITEE_URL)
                }

                override fun updateDrawState(ds: TextPaint) {
                    ds.isUnderlineText = true
                }
            }.apply {
                setSpan(this@apply, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            ForegroundColorSpan(getPhoneColor()).apply {
                setSpan(
                    this@apply,
                    startIndex,
                    endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }.apply {
            textView.text = this
            textView.movementMethod = LinkMovementMethod.getInstance()
        }
    }

    /**
     * 获取电话文字的颜色
     * */
    abstract fun getPhoneColor(): Int
}