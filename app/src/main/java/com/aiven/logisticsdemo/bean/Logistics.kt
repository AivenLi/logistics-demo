package com.aiven.logisticsdemo.bean

interface Logistics {

    /**
     * 获取日期，格式：01-02
     * */
    fun getMonthDay(): String

    /**
     * 获取时间，格式：09:00
     * */
    fun getHourMin(): String

    /**
     * 获取节点类型
     * */
    fun getNodeType(): Int

    fun isDone(): Boolean {
        return (getNodeType() and IS_DONE) != 0
    }
    /**
     * 是否是节点，完成也算节点
     * @return
     * */
    fun isNode(): Boolean {
        return (getNodeType() and 0x000001ff) != 0
    }

    /**
     * 是否是节点，不包括完成节点
     * @return
     * */
    fun isNodeNoDone(): Boolean {
        return (getNodeType() and 0x000001fe) != 0
    }

    companion object {

        /**
         * 完成
         * */
        const val IS_DONE = 0x00000001

        /**
         * 已签收
         * */
        const val RECEIVED = 0x00000002

        /**
         * 待取件
         * */
        const val PENDING_PICKUP = 0x00000004

        /**
         * 派送中
         * */
        const val DELIVERING = 0x00000008

        /**
         * 运输中
         * */
        const val IN_TRANSIT = 0x000000010

        /**
         * 已收揽
         * */
        const val ALREADY_TAKEN = 0x000000020

        /**
         * 仓库处理中
         * */
        const val WAREHOUSE_PROCESSING = 0x00000040

        /**
         * 已发货
         * */
        const val SHIPPED = 0x00000080

        /**
         * 已下单
         * */
        const val ORDERED = 0x00000100

        /**
         * 其他，小节点
         * */
        const val OTHER = 0x00000000
    }
}