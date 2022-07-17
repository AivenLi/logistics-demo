package com.aiven.logisticsdemo.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.aiven.logisticsdemo.base.adapter.viewholder.ViewBindingHolder

/**
 * RecyclerView的ViewBinding基类适配器
 *      T 数据类型
 *      VH ViewHolder，继承自{@link com.aiven.logisticsdemo.base.adapter.viewholder.ViewBindingHolder}
 *      VB ViewBinding
 * @param context 上下文
 * @param data    数据
 * @param inflate ViewBinding的inflate方法
 * */
abstract class RecyclerVIewHolderAdapter<T, VH: ViewBindingHolder<VB>, VB: ViewBinding>(
    val context         : Context,
    val data            : List<T>,
    private val inflate : (layoutInflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean) -> VB
) : RecyclerView.Adapter<VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return onCreateViewHolder(
            inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            viewType
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        onBind(holder.viewBinding, data[holder.adapterPosition], holder.adapterPosition)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    /**
     * 创建ViewHolder
     * @param binding
     * @param viewType
     * */
    abstract fun onCreateViewHolder(binding: VB, viewType: Int): VH

    /**
     * 绑定视图
     * @param viewBinding
     * @param item 数据项
     * @param position 第几项数据
     * */
    abstract fun onBind(viewBinding: VB, item: T, position: Int)
}