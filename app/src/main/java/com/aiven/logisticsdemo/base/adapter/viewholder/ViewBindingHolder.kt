package com.aiven.logisticsdemo.base.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class ViewBindingHolder<V: ViewBinding>(
    val viewBinding: V
) : RecyclerView.ViewHolder(viewBinding.root) {
}