package com.aiven.logisticsdemo.base.adapter.itemdecoration

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aiven.logisticsdemo.R
import com.aiven.logisticsdemo.base.adapter.LogisticsAdapter
import com.aiven.logisticsdemo.bean.Logistics


/**
 * @author  : AivenLi
 * @date    : 2022/7/15 19:40
 * @param context
 * @param nodeRadiusDp 节点半径圆，不需要转换单位
 * @param normalRadiusDp 小节点半径圆，不需要转换单位
 * */
abstract class LogisticsItemDecoration(
    protected var context: Context,
    nodeRadiusDp: Float = NODE_RADIUS,
    normalRadiusDp: Float = NORMAL_RADIUS
): RecyclerView.ItemDecoration() {

    /**
     * 屏幕密度
     * */
    private val scaledDensity: Float = context.resources.displayMetrics.scaledDensity

    /**
     * 画板
     * */
    protected val paint = Paint()

    /**
     * 用于暂存各个控件的坐标等数据
     * */
    protected val rect = Rect()

    /**
     * 大节点圆半径
     * */
    protected val nodeRadius: Float

    /**
     * 小节点圆半径
     * */
    protected val normalRadius: Float

    /**
     * 用来提醒Star的
     * */
    protected val iconStarAndroid by lazy {
        BitmapFactory.decodeResource(context.resources, R.drawable.ic_done_ul)
    }

    companion object {
        /**
         * 修饰器左边宽度，dp
         * */
        const val DECORATION_START = 30
        /**
         * 大节点圆半径
         * */
        const val NORMAL_RADIUS = 2.5f
        /**
         * 小节点圆半径
         * */
        const val NODE_RADIUS = 5.0f
    }

    init {
        paint.isAntiAlias = true
        paint.strokeWidth = dp2px(1f)
        nodeRadius = dp2px(nodeRadiusDp)
        normalRadius = dp2px(normalRadiusDp)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        /**
         * 左侧留出空间来绘制物流时间线
         * */
        setOutRect(outRect)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        /**
         * 如果没有显示的item，则不绘制
         * */
        if (parent.childCount == 0) {
            return
        }
        /**
         * 用于判断是否只有一条item
         * 如果只有一条item，则不用绘制时间线，只需绘制节点圆即可
         * */
        val onlyOneItem = parent.adapter?.itemCount == 1
        if (isFirstData(parent)) {
            /**
             * 第一条数据的item
             * 注意，是第一条数据的item，而不是parent的第一个子view，因为recyclerview的子view
             * 只是当前可见的item。
             * */
            drawFirstItemDecoration(
                c,
                parent.getChildAt(0) as ViewGroup,
                getDataByItemViewPosition(parent, parent.getChildAt(0)),
                !onlyOneItem
            )
        }
        if (onlyOneItem) {
            return
        }
        /**
         * 判断要绘制的item装饰器是否包含第一条数据和最后一条数据。
         * 第一条数据不需要绘制上半部分的时间线
         * 最后一条数据不要绘制下半部分的时间线
         * */
        val startIndex = if (isFirstData(parent)) 1 else 0
        val endIndex =
            if (
                parent.getChildAdapterPosition(parent.getChildAt(parent.childCount - 1))
                ==
                parent.adapter?.itemCount?.minus(1)
            ) {
                parent.childCount - 1
            } else {
                parent.childCount
            }
        for (i in startIndex until endIndex) {
            drawItemDecoration(c, parent.getChildAt(i) as ViewGroup, getDataByItemViewPosition(parent, parent.getChildAt(i)))
        }
        /**
         * 最后一个item是不是最后一条数据
         * */
        if (endIndex + 1 == parent.childCount) {
            drawLastItemDecoration(
                c,
                parent.getChildAt(parent.childCount - 1) as ViewGroup,
                getDataByItemViewPosition(parent, parent.getChildAt(parent.childCount - 1))
            )
        }
    }

    /**
     * 获取itemView对应的数据
     * @param parent recyclerView
     * @param itemView parent的子view
     * @return Logistics
     * */
    fun getDataByItemViewPosition(parent: RecyclerView, itemView: View): Logistics {
        return (parent.adapter as LogisticsAdapter<*, *, *>).getDataByPosition(parent.getChildAdapterPosition(itemView))
    }

    /**
     * 设置第一行文本的坐标等数据，用于让节点圆与文本水平居中
     * @param textView 文本控件
     * @param rect     存放数据
     * */
    fun setTextViewFirstLineRect(textView: TextView, rect: Rect) {
        textView.getLineBounds(0, rect)
    }

    /**
     * 判断是否是第一条数据
     * @return 如果是第一条数据返回true，反之返回false
     * */
    private fun isFirstData(parent: RecyclerView): Boolean = parent.getChildAdapterPosition(parent.getChildAt(0)) == 0

    /**
     * 绘制第一条数据的item，因为只有第一条数据的item的颜色总是不一样的，
     * 同时也只有一条数据的item不需要绘制上半部分的时间线。
     * @param canvas     布画
     * @param parentView item最外层的控件
     * @param data       物流节点数据
     * @param drawLine  是否需要绘制时间线，如果只有一条数据，那就不用绘制时间线
     * */
    abstract fun drawFirstItemDecoration(canvas: Canvas, parentView: ViewGroup, data: Logistics, drawLine: Boolean)

    /**
     * 绘制中间的item的时间线
     * @param canvas
     * @param parentView
     * @param data
     * */
    abstract fun drawItemDecoration(canvas: Canvas, parentView: ViewGroup, data: Logistics)

    /**
     * 绘制最后一条数据的item，因为最后一条数据不需要绘制节点圆下面的时间线
     * @param canvas     布画
     * @param parentView item最外层的控件
     * @param data       物流节点数据
     * */
    abstract fun drawLastItemDecoration(canvas: Canvas, parentView: ViewGroup, data: Logistics)

    /**
     * 设置item的边距
     * @param outRect
     * */
    abstract fun setOutRect(outRect: Rect)

    /**
     * Dp转Px（Float）
     * @param dp
     * @return dp对应的px
     * */
    protected fun dp2px(dp: Float): Float = scaledDensity * dp

    /**
     * Dp转Px（Int）
     * @param dp
     * @return dp对应的px
     * */
    protected fun dp2px(dp: Int): Int = (scaledDensity * dp).toInt()
}