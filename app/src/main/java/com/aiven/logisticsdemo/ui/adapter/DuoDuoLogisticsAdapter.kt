package com.aiven.logisticsdemo.ui.adapter

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.aiven.logisticsdemo.R
import com.aiven.logisticsdemo.base.adapter.LogisticsAdapter
import com.aiven.logisticsdemo.base.adapter.viewholder.ViewBindingHolder
import com.aiven.logisticsdemo.bean.Logistics
import com.aiven.logisticsdemo.bean.LogisticsBean
import com.aiven.logisticsdemo.databinding.ItemDuoduoLogisticsBinding
import com.aiven.logisticsdemo.utils.PhoneNumberUtil

/**
 * @author  : AivenLi
 * @date    : 2022/7/16 14:26
 * */
class DuoDuoLogisticsAdapter(
    context: Context,
    data: List<LogisticsBean>
) : LogisticsAdapter<LogisticsBean, ViewBindingHolder<ItemDuoduoLogisticsBinding>,ItemDuoduoLogisticsBinding>(
    context,
    data,
    ItemDuoduoLogisticsBinding::inflate
){
    override fun getPhoneColor(): Int {
        return ContextCompat.getColor(context, R.color.color_jingdong_phone)
    }

    override fun onCreateViewHolder(
        binding: ItemDuoduoLogisticsBinding,
        viewType: Int
    ): ViewBindingHolder<ItemDuoduoLogisticsBinding> {
        return ViewBindingHolder<ItemDuoduoLogisticsBinding>(binding)
    }

    override fun onBind(
        viewBinding: ItemDuoduoLogisticsBinding,
        item: LogisticsBean,
        position: Int
    ) {
        viewBinding.tvDate.text = item.date
        setPhoneClickText(viewBinding.tvDesc, item.desc, false)
        if (position == 0) {
            val startIndex = item.desc.indexOf("【")
            val endIndex = item.desc.indexOf("】")
            if (startIndex != -1 && endIndex != -1) {
                setStarTextView(viewBinding.tvDesc, item.desc, startIndex + 1, endIndex)
            }
        }
    }
}