package com.aiven.logisticsdemo.ui.adapter.itemdecoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aiven.logisticsdemo.R
import com.aiven.logisticsdemo.base.adapter.itemdecoration.LogisticsItemDecoration
import com.aiven.logisticsdemo.bean.Logistics
import kotlin.math.roundToInt

/**
 * 仿淘宝物流列表装饰器
 * @author AivenLi
 * */
class TaoBaoDecoration(
    context: Context
) : LogisticsItemDecoration(
    context
) {
    private val x: Float = dp2px(DECORATION_START - NODE_RADIUS * 2)

    override fun setOutRect(outRect: Rect) {
        outRect.set(dp2px(DECORATION_START), 0, 0, 0)
    }

    /**
     * 绘制第一条数据的item，因为只有第一条数据的item的颜色总是不一样的，
     * 同时也只有一条数据的item不需要绘制上半部分的时间线。
     * @param canvas     布画
     * @param parentView item最外层的控件
     * @param data       物流节点数据
     * @param drawLine  是否需要绘制时间线，如果只有一条数据，那就不用绘制时间线
     * */
    override fun drawFirstItemDecoration(canvas: Canvas, parentView: ViewGroup, data: Logistics, drawLine: Boolean) {
        /**
         * 这些view的关系根据item的布局文件来确定
         * */
        val childView = parentView.getChildAt(0) as ViewGroup
        val grandSonView = childView.getChildAt(1) as TextView
        setTextViewFirstLineRect(grandSonView, rect)
        paint.color = ContextCompat.getColor(context, R.color.color_taobao_selected_title)
        val y = parentView.top + childView.top + grandSonView.top + rect.centerY().toFloat()
        var radius = if (data.isNode()) nodeRadius else normalRadius
        if (data.isDone()) {
            radius = dp2px(10f)
            rect.top = (y - radius).roundToInt()
            rect.bottom = (y + radius).roundToInt()
            rect.left = (x - radius).roundToInt()
            rect.right = (x + radius).roundToInt()
            canvas.drawBitmap(iconStarAndroid, null, rect, paint)
        } else {
            canvas.drawCircle(x, y, radius, paint)
        }
        paint.color = ContextCompat.getColor(context, R.color.color_taobao_normal)
        if (drawLine) {
            /**
             * 时间线的起点不能从圆心开始，因为如果节点不是圆而是图片，展示
             * 出来的界面看起来可能是重叠的。
             * */
            canvas.drawLine(x, y + radius, x, parentView.bottom.toFloat(), paint)
        }
    }

    override fun drawItemDecoration(canvas: Canvas, parentView: ViewGroup, data: Logistics) {
        val childView = parentView.getChildAt(0) as ViewGroup
        val grandSonView = childView.getChildAt(1) as TextView
        setTextViewFirstLineRect(grandSonView, rect)
        val y = parentView.top + childView.top + grandSonView.top + rect.centerY().toFloat()
        val radius = if (data.isNodeNoDone()) nodeRadius else normalRadius
        canvas.drawCircle(x, y, radius, paint)
        /**
         * 上半部分的时间线，时间线最好不要与节点圆重叠，因为如果节点不是圆，而是图片（例如京东物流），
         * 那么界面看起来就重叠了。
         * */
        canvas.drawLine(x, parentView.top.toFloat(), x, y - radius, paint)
        /**
         * 下半部分的时间线
         * */
        canvas.drawLine(x, y + radius, x, parentView.bottom.toFloat(), paint)
    }

    /**
     * 绘制最后一条数据的item，因为最后一条数据不需要绘制节点圆下面的时间线
     * @param canvas     布画
     * @param parentView item最外层的控件
     * @param data       物流节点数据
     * */
    override fun drawLastItemDecoration(canvas: Canvas, parentView: ViewGroup, data: Logistics) {
        val childView = parentView.getChildAt(0) as ViewGroup
        val grandSonView = childView.getChildAt(1) as TextView
        setTextViewFirstLineRect(grandSonView, rect)
        val y = parentView.top + childView.top + grandSonView.top + rect.centerY().toFloat()
        val radius = if (data.isNodeNoDone()) nodeRadius else normalRadius
        canvas.drawCircle(x, y, radius, paint)
        canvas.drawLine(x, parentView.top.toFloat(), x,y - radius, paint)
    }
}