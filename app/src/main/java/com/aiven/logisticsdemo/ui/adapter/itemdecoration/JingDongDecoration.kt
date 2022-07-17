package com.aiven.logisticsdemo.ui.adapter.itemdecoration

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aiven.logisticsdemo.R
import com.aiven.logisticsdemo.base.adapter.itemdecoration.LogisticsItemDecoration
import com.aiven.logisticsdemo.bean.Logistics
import kotlin.math.roundToInt

/**
 * @author  : AivenLi
 * @date    : 2022/7/15 22:01
 * */
class JingDongDecoration(
    context: Context
) : LogisticsItemDecoration(
    context,
    10F
    ) {

    private val x: Float = dp2px(DECORATION_START / 3F * 2F)

    private val iconReceived by lazy {
        BitmapFactory.decodeResource(context.resources, R.drawable.ic_jingdong_received)
    }
    private val iconDelivering by lazy {
        BitmapFactory.decodeResource(context.resources, R.drawable.ic_jingdong_delivering)
    }
    private val iconInTransit by lazy {
        BitmapFactory.decodeResource(context.resources, R.drawable.ic_jingdong_in_transit)
    }
    private val iconAlreadyTaken by lazy {
        BitmapFactory.decodeResource(context.resources, R.drawable.ic_jingdong_already_taken)
    }
    private val iconWareHouse by lazy {
        BitmapFactory.decodeResource(context.resources, R.drawable.ic_jingdong_warehouse_processing)
    }
    private val iconOrdered by lazy {
        BitmapFactory.decodeResource(context.resources, R.drawable.ic_jingdong_ordered)
    }

    override fun drawFirstItemDecoration(
        canvas: Canvas,
        parentView: ViewGroup,
        data: Logistics,
        drawLine: Boolean
    ) {
        var childView = parentView.getChildAt(0) as TextView
        if (childView.visibility == View.GONE) {
            childView = parentView.getChildAt(1) as TextView
        }
        setTextViewFirstLineRect(childView, rect)
        val y = parentView.top + childView.top + rect.centerY().toFloat()
        val radius = if (data.isNode()) nodeRadius else normalRadius
        if (data.isNode()) {
            rect.top = (y - radius).roundToInt()
            rect.bottom = (y + radius).roundToInt()
            rect.left = (x - radius).roundToInt()
            rect.right = (x + radius).roundToInt()
            when (data.getNodeType()) {
                Logistics.RECEIVED -> iconReceived
                Logistics.DELIVERING -> iconDelivering
                Logistics.IN_TRANSIT -> iconInTransit
                Logistics.ALREADY_TAKEN -> iconAlreadyTaken
                Logistics.WAREHOUSE_PROCESSING -> iconWareHouse
                Logistics.ORDERED -> iconOrdered
                /**
                 * 提醒Star的，哈哈哈
                 * */
                else -> iconStarAndroid
            }.let {
                canvas.drawBitmap(it, null, rect, paint)
            }
        } else {
            paint.color = ContextCompat.getColor(context, R.color.color_jingdong_selected_title)
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
        var childView = parentView.getChildAt(0) as TextView
        if (childView.visibility == View.GONE) {
            childView = parentView.getChildAt(1) as TextView
        }
        setTextViewFirstLineRect(childView, rect)
        val y = parentView.top + childView.top + rect.centerY().toFloat()
        val radius = if (data.isNode()) nodeRadius else normalRadius
        if (data.isNode()) {
            rect.top = (y - radius).roundToInt()
            rect.bottom = (y + radius).roundToInt()
            rect.left = (x - radius).roundToInt()
            rect.right = (x + radius).roundToInt()
            when (data.getNodeType()) {
                Logistics.RECEIVED -> iconReceived
                Logistics.DELIVERING -> iconDelivering
                Logistics.IN_TRANSIT -> iconInTransit
                Logistics.ALREADY_TAKEN -> iconAlreadyTaken
                Logistics.WAREHOUSE_PROCESSING -> iconWareHouse
                else -> iconOrdered
            }.let {
                canvas.drawBitmap(it, null, rect, paint)
            }
        } else {
            canvas.drawCircle(x, y, radius, paint)
        }
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

    override fun drawLastItemDecoration(canvas: Canvas, parentView: ViewGroup, data: Logistics) {
        /**
         * 这些view的关系根据item的布局文件来确定
         * */
        var childView = parentView.getChildAt(0) as TextView
        if (childView.visibility == View.GONE) {
            childView = parentView.getChildAt(1) as TextView
        }
        setTextViewFirstLineRect(childView, rect)
        val y = parentView.top + childView.top + rect.centerY().toFloat()
        val radius = if (data.isNode()) nodeRadius else normalRadius
        if (data.isNode()) {
            rect.top = (y - radius).roundToInt()
            rect.bottom = (y + radius).roundToInt()
            rect.left = (x - radius).roundToInt()
            rect.right = (x + radius).roundToInt()
            when (data.getNodeType()) {
                Logistics.RECEIVED -> iconReceived
                Logistics.DELIVERING -> iconDelivering
                Logistics.IN_TRANSIT -> iconInTransit
                Logistics.ALREADY_TAKEN -> iconAlreadyTaken
                Logistics.WAREHOUSE_PROCESSING -> iconWareHouse
                else -> iconOrdered
            }.let {
                canvas.drawBitmap(it, null, rect, paint)
            }
        } else {
            canvas.drawCircle(x, y, radius, paint)
        }
        canvas.drawLine(x, parentView.top.toFloat(), x,y - radius, paint)
    }

    override fun setOutRect(outRect: Rect) {
        outRect.set(dp2px(DECORATION_START), 0, 0, 0)
    }
}