package com.aiven.logisticsdemo.ui.adapter.itemdecoration

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.aiven.logisticsdemo.R
import com.aiven.logisticsdemo.base.adapter.itemdecoration.LogisticsItemDecoration
import com.aiven.logisticsdemo.bean.Logistics
import kotlin.math.roundToInt

/**
 * @author  : AivenLi
 * @date    : 2022/7/16 15:26
 * */
class DuoDuoDecoration(
    context: Context
) : LogisticsItemDecoration(context) {

    private val x: Float = dp2px(DECORATION_START / 3F * 2F)

    init {
        paint.color = ContextCompat.getColor(context, R.color.color_taobao_normal)
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
        val radius: Float
        /**
         * isDone只是用来提醒Star的
         * */
        if (data.isDone()) {
            radius = dp2px(10f)
            rect.top = (y - radius).roundToInt()
            rect.bottom = (y + radius).roundToInt()
            rect.left = (x - radius).roundToInt()
            rect.right = (x + radius).roundToInt()
            canvas.drawBitmap(iconStarAndroid, null, rect, paint)
        } else {
            radius = normalRadius
            canvas.drawCircle(x, y, radius, paint)
        }
        /**
         * 用这个就可以了
         * */
//        val radius = normalRadius
//        canvas.drawCircle(x, y, radius, paint)
        if (drawLine) {
            canvas.drawLine(x, y + radius, x, parentView.bottom.toFloat(), paint)
        }
    }

    override fun drawItemDecoration(canvas: Canvas, parentView: ViewGroup, data: Logistics) {
        val childView = parentView.getChildAt(0) as TextView
        setTextViewFirstLineRect(childView, rect)
        val y = parentView.top + childView.top + rect.centerY().toFloat()
        canvas.drawCircle(x, y, nodeRadius, paint)
        /**
         * 上半部分的时间线，时间线最好不要与节点圆重叠，因为如果节点不是圆，而是图片（例如京东物流），
         * 那么界面看起来就重叠了。
         * */
        canvas.drawLine(x, parentView.top.toFloat(), x, y - nodeRadius, paint)
        /**
         * 下半部分的时间线
         * */
        canvas.drawLine(x, y + nodeRadius, x, parentView.bottom.toFloat(), paint)
    }

    override fun drawLastItemDecoration(canvas: Canvas, parentView: ViewGroup, data: Logistics) {
        val childView = parentView.getChildAt(0) as TextView
        setTextViewFirstLineRect(childView, rect)
        val y = parentView.top + childView.top + rect.centerY().toFloat()
        canvas.drawCircle(x, y, nodeRadius, paint)
        canvas.drawLine(x, parentView.top.toFloat(), x,y - nodeRadius, paint)
    }

    override fun setOutRect(outRect: Rect) {
        outRect.set(dp2px(DECORATION_START), 0, 0, 0)
    }
}