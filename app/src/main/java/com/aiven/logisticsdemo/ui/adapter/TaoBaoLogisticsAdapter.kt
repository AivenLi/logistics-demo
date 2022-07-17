package com.aiven.logisticsdemo.ui.adapter

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.aiven.logisticsdemo.R
import com.aiven.logisticsdemo.base.adapter.LogisticsAdapter
import com.aiven.logisticsdemo.base.adapter.viewholder.ViewBindingHolder
import com.aiven.logisticsdemo.bean.LogisticsBean
import com.aiven.logisticsdemo.databinding.ItemTaobaoLogisticsBinding
import com.aiven.logisticsdemo.utils.PhoneNumberUtil

class TaoBaoLogisticsAdapter(
    context: Context,
    data: List<LogisticsBean>
) : LogisticsAdapter<LogisticsBean, ViewBindingHolder<ItemTaobaoLogisticsBinding>, ItemTaobaoLogisticsBinding>(
    context,
    data,
    ItemTaobaoLogisticsBinding::inflate
) {
    override fun onCreateViewHolder(
        binding: ItemTaobaoLogisticsBinding,
        viewType: Int
    ): ViewBindingHolder<ItemTaobaoLogisticsBinding> {
        return ViewBindingHolder(binding)
    }

    override fun onBind(
        viewBinding: ItemTaobaoLogisticsBinding,
        item: LogisticsBean,
        position: Int
    ) {
        if (position == 0) {
            viewBinding.tvDate.text = getDate(item)
            viewBinding.tvDate.setTextColor(ContextCompat.getColor(context, R.color.color_taobao_selected_title))
            viewBinding.tvDesc.setTextColor(ContextCompat.getColor(context, R.color.color_taobao_selected_desc))
            if (item.title != null && item.title!!.isNotEmpty()) {
                viewBinding.tvState.text = item.title!!
                viewBinding.tvState.setTextColor(ContextCompat.getColor(context, R.color.color_taobao_selected_title))
                viewBinding.tvState.visibility = View.VISIBLE
            } else {
                viewBinding.tvState.visibility = View.GONE
            }
        } else {
            viewBinding.tvDate.text = getDate(item)
            viewBinding.tvDate.setTextColor(ContextCompat.getColor(context, R.color.color_taobao_normal))
            viewBinding.tvDesc.setTextColor(ContextCompat.getColor(context, R.color.color_taobao_normal))
            if (item.title != null && item.title!!.isNotEmpty()) {
                viewBinding.tvState.text = item.title!!
                viewBinding.tvState.setTextColor(ContextCompat.getColor(context, R.color.color_taobao_normal))
                viewBinding.tvState.visibility = View.VISIBLE
            } else {
                viewBinding.tvState.visibility = View.GONE
            }
        }
        setPhoneClickText(viewBinding.tvDesc, item.desc, false)
        if (position == 0) {
            val startIndex = item.desc.indexOf("【")
            val endIndex = item.desc.indexOf("】")
            if (startIndex != -1 && endIndex != -1) {
                setStarTextView(viewBinding.tvDesc, item.desc, startIndex + 1, endIndex)
            }
        }
    }

    override fun getPhoneColor(): Int {
        return ContextCompat.getColor(context, R.color.color_taobao_selected_title)
    }

    private fun getDate(item: LogisticsBean) : String {
        return "${item.getMonthDay()} ${item.getHourMin()}"
    }
}