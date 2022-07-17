package com.aiven.logisticsdemo.ui.adapter

import android.content.Context
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import com.aiven.logisticsdemo.R
import com.aiven.logisticsdemo.base.adapter.LogisticsAdapter
import com.aiven.logisticsdemo.base.adapter.viewholder.ViewBindingHolder
import com.aiven.logisticsdemo.bean.LogisticsBean
import com.aiven.logisticsdemo.databinding.ItemJingdongLogisticsBinding

/**
 * @author  : AivenLi
 * @date    : 2022/7/15 23:13
 * */
class JingDongLogisticsAdapter(
    context: Context,
    data: List<LogisticsBean>
) : LogisticsAdapter<LogisticsBean, ViewBindingHolder<ItemJingdongLogisticsBinding>, ItemJingdongLogisticsBinding>(
    context,
    data,
    ItemJingdongLogisticsBinding::inflate
){
    override fun getPhoneColor(): Int {
        return ContextCompat.getColor(context, R.color.color_jingdong_phone)
    }

    override fun onCreateViewHolder(
        binding: ItemJingdongLogisticsBinding,
        viewType: Int
    ): ViewBindingHolder<ItemJingdongLogisticsBinding> {
        return ViewBindingHolder<ItemJingdongLogisticsBinding>(binding)
    }

    override fun onBind(
        viewBinding: ItemJingdongLogisticsBinding,
        item: LogisticsBean,
        position: Int
    ) {
        if (item.title.isNullOrEmpty()) {
            viewBinding.tvTitle.visibility = View.GONE
        } else {
            viewBinding.tvTitle.visibility = View.VISIBLE
            viewBinding.tvTitle.text = item.title
            if (position == 0) {
                viewBinding.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.color_jingdong_selected_title))
            } else {
                viewBinding.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.color_jingdong_normal))
            }
        }
        val color =
            if (position == 0) {
                viewBinding.tvDesc.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)
                ContextCompat.getColor(context, R.color.color_jingdong_selected_desc)
            } else {
                viewBinding.tvDesc.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14F)
                ContextCompat.getColor(context, R.color.color_jingdong_normal)
            }
        viewBinding.tvDesc.setTextColor(color)
        viewBinding.tvDate.setTextColor(color)
        viewBinding.tvDate.text = item.date
        setPhoneClickText(viewBinding.tvDesc, item.desc, true)
        if (position == 0) {
            val startIndex = item.desc.indexOf("【")
            val endIndex = item.desc.indexOf("】")
            if (startIndex != -1 && endIndex != -1) {
                setStarTextView(viewBinding.tvDesc, item.desc, startIndex + 1, endIndex)
            }
        }
    }
}