package com.aiven.logisticsdemo.ui.adapter.itemdecoration

import android.content.Context
import android.graphics.*
import android.util.Log
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
 * @author  : AivenLi
 * @date    : 2022/7/16 16:44
 * */
class MineDecoration(
    context: Context
) : LogisticsItemDecoration(
    context,
    10F
){
    private val x: Float = dp2px(80f)
    private val timeY: Float
    private val dateY: Float

    private val iconOrdered by lazy {
        BitmapFactory.decodeResource(context.resources, R.drawable.ic_mine_ordered)
    }
    private val iconNodeNormal by lazy {
        BitmapFactory.decodeResource(context.resources, R.drawable.ic_mine_node_normal)
    }
    private val iconNodeFirst by lazy {
        BitmapFactory.decodeResource(context.resources, R.drawable.ic_mine_node_first)
    }
    private val iconDone by lazy {
        BitmapFactory.decodeResource(context.resources, R.drawable.ic_mine_done)
    }
    private val endX: Float
    private val timeX: Float
    private val dateX: Float
    private val sX: Float
    init {
        val textPaint = Paint()
        val textRect = Rect()
        textPaint.textSize = dp2px(16F)
        textPaint.getTextBounds("44:44", 0, 5, textRect)
        timeX = x - dp2px(20f) - textRect.width()
        endX = timeX + textRect.width()
        sX = timeX
        val centerX = textRect.width() / 2
        timeY = textRect.height().toFloat()
        textPaint.textSize = dp2px(12F)
        textPaint.getTextBounds("44-44", 0, 5, textRect)
        dateX = timeX + centerX - textRect.width() / 2f
        dateY = textRect.height().toFloat()
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
        paint.color = ContextCompat.getColor(context, R.color.color_mine_selected_title)
        setTextViewFirstLineRect(childView, rect)
        val y = parentView.top + childView.top + rect.centerY().toFloat()
        val radius: Float
        if (data.isNode()) {
            rect.top = (y - nodeRadius).roundToInt()
            rect.bottom = (y + nodeRadius).roundToInt()
            rect.left = (x - nodeRadius).roundToInt()
            rect.right = (x + nodeRadius).roundToInt()
            when (data.getNodeType()) {
                Logistics.IS_DONE -> {
                    canvas.drawBitmap(iconStarAndroid, null, rect, paint)
                }
                Logistics.RECEIVED -> {
                    canvas.drawBitmap(iconDone, null, rect, paint)
                }
                Logistics.ORDERED -> {
                    canvas.drawBitmap(iconOrdered, null, rect, paint)
                }
                else -> {
                    canvas.drawBitmap(iconNodeNormal, null, rect, paint)
                }
            }
            radius = nodeRadius
        } else {
            canvas.drawCircle(x, y, normalRadius, paint)
            radius = normalRadius
        }
        drawDateTimeText(canvas, y, data)
        paint.color = ContextCompat.getColor(context, R.color.color_taobao_normal)
        if (drawLine) {
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
        val radius: Float
        if (data.isNode()) {
            rect.top = (y - nodeRadius).roundToInt()
            rect.bottom = (y + nodeRadius).roundToInt()
            rect.left = (x - nodeRadius).roundToInt()
            rect.right = (x + nodeRadius).roundToInt()
            when (data.getNodeType()) {
                Logistics.RECEIVED -> {
                    canvas.drawBitmap(iconDone, null, rect, paint)
                }
                Logistics.ORDERED -> {
                    canvas.drawBitmap(iconOrdered, null, rect, paint)
                }
                else -> {
                    canvas.drawBitmap(iconNodeNormal, null, rect, paint)
                }
            }
            radius = nodeRadius
        } else {
            canvas.drawCircle(x, y, normalRadius, paint)
            radius = normalRadius
        }
        canvas.drawLine(x, parentView.top.toFloat(), x, y - radius, paint)
        canvas.drawLine(x, y + radius, x, parentView.bottom.toFloat(), paint)
        drawDateTimeText(canvas, y, data)
    }

    override fun drawLastItemDecoration(canvas: Canvas, parentView: ViewGroup, data: Logistics) {
        var childView = parentView.getChildAt(0) as TextView
        if (childView.visibility == View.GONE) {
            childView = parentView.getChildAt(1) as TextView
        }
        setTextViewFirstLineRect(childView, rect)
        val y = parentView.top + childView.top + rect.centerY().toFloat()
        val radius: Float
        if (data.isNode()) {
            rect.top = (y - nodeRadius).roundToInt()
            rect.bottom = (y + nodeRadius).roundToInt()
            rect.left = (x - nodeRadius).roundToInt()
            rect.right = (x + nodeRadius).roundToInt()
            when (data.getNodeType()) {
                Logistics.RECEIVED -> {
                    canvas.drawBitmap(iconDone, null, rect, paint)
                }
                Logistics.ORDERED -> {
                    canvas.drawBitmap(iconOrdered, null, rect, paint)
                }
                else -> {
                    canvas.drawBitmap(iconNodeNormal, null, rect, paint)
                }
            }
            radius = nodeRadius
        } else {
            canvas.drawCircle(x, y, normalRadius, paint)
            radius = normalRadius
        }
        canvas.drawLine(x, parentView.top.toFloat(), x,y - radius, paint)
        drawDateTimeText(canvas, y, data)
    }

    override fun setOutRect(outRect: Rect) {
        outRect.set(dp2px(100), 0, 0, 0)
    }

    private fun drawDateTimeText(canvas: Canvas, y: Float, data: Logistics) {
        paint.textSize = dp2px(16F)
        canvas.drawText(data.getHourMin(), timeX, y + dp2px(4) - timeY / 2, paint)
        paint.textSize = dp2px(12F)
        canvas.drawText(data.getMonthDay(), dateX, y + dp2px(4) + dateY, paint)
    }
}