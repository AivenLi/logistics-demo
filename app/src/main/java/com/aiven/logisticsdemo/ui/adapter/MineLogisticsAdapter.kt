package com.aiven.logisticsdemo.ui.adapter

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.aiven.logisticsdemo.R
import com.aiven.logisticsdemo.base.adapter.LogisticsAdapter
import com.aiven.logisticsdemo.base.adapter.viewholder.ViewBindingHolder
import com.aiven.logisticsdemo.bean.LogisticsBean
import com.aiven.logisticsdemo.databinding.ItemMineLogisticsBinding

/**
 * @author  : AivenLi
 * @date    : 2022/7/16 16:10
 * */
class MineLogisticsAdapter(
    context: Context,
    data: List<LogisticsBean>
) : LogisticsAdapter<LogisticsBean, ViewBindingHolder<ItemMineLogisticsBinding>, ItemMineLogisticsBinding>(
    context,
    data,
    ItemMineLogisticsBinding::inflate
) {
    override fun getPhoneColor(): Int {
        return ContextCompat.getColor(context, R.color.color_mine_selected_title)
    }

    override fun onCreateViewHolder(
        binding: ItemMineLogisticsBinding,
        viewType: Int
    ): ViewBindingHolder<ItemMineLogisticsBinding> {
        return ViewBindingHolder(binding)
    }

    override fun onBind(viewBinding: ItemMineLogisticsBinding, item: LogisticsBean, position: Int) {
        if (item.title.isNullOrEmpty()) {
            viewBinding.tvTitle.visibility = View.GONE
        } else {
            viewBinding.tvTitle.visibility = View.VISIBLE
            viewBinding.tvTitle.text = item.title
        }
        if (position == 0) {
            viewBinding.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.color_mine_selected_title))
            viewBinding.tvDesc.setTextColor(ContextCompat.getColor(context, R.color.color_mine_selected_desc))
            val startIndex = item.desc.indexOf("【")
            val endIndex = item.desc.indexOf("】")
            if (startIndex != -1 && endIndex != -1) {
                setStarTextView(viewBinding.tvDesc, item.desc, startIndex, endIndex)
            }
        } else {
            viewBinding.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.color_mine_normal))
            viewBinding.tvDesc.setTextColor(ContextCompat.getColor(context, R.color.color_mine_normal))
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
}