package com.aiven.logisticsdemo.bean

import com.aiven.logisticsdemo.utils.DateFormatUtils

data class LogisticsBean(
    var date      : String,
    var title     : String?,
    var desc      : String,
    var state     : Int,
): Logistics {
    override fun getMonthDay(): String {
        return date.run {
            substring(indexOf("-") + 1, indexOf(" "))
        }
    }

    override fun getHourMin(): String {
        return date.run {
            substring(indexOf(" ") + 1, lastIndexOf(":"))
        }
    }

    override fun getNodeType(): Int {
        return state
    }

    companion object {
        fun getTaoBaoData(): List<LogisticsBean> {
            return listOf(
                LogisticsBean(
                    DateFormatUtils.getDateFormat(System.currentTimeMillis()),
                    "已完成",
                    "风里来雨里去，只为客官您满意。上有老下有小，赏个好评好不好？【请在Star处帮忙点亮星星哦~】",
                    Logistics.IS_DONE
                ),
                LogisticsBean(
                    "2022-07-12 18:32:11",
                    "已签收",
                    "[代收点]您的快件已签收，签收人在【代收点的塞维尔街道1号】领取。如有疑问请电联：33456789，投诉电话：9527-334578，您的快递" +
                            "已经妥投。",
                    Logistics.RECEIVED
                ),
                LogisticsBean(
                    "2022-07-12 16:23:09",
                    "待取件",
                    "[代收点]您的快件已经暂存至【代收点的塞维尔街道处】，地址：瓦罗兰大陆-诺克萨斯-不朽堡垒-塞维尔街道1号，请及时领取。如有疑问请电联：3345678，投诉电话：9527-3345678",
                    Logistics.PENDING_PICKUP
                ),
                LogisticsBean(
                    "2022-07-12 13:29:56",
                    "派送中",
                    "诺克萨斯-索弟快递（9527-3345678）的泰隆正在派件，联系电话：13579246810",
                    Logistics.DELIVERING
                ),
                LogisticsBean(
                    "2022-07-12 08:43:43",
                    "运输中",
                    "快件已抵达诺克萨斯-不朽堡垒-塞维尔街道",
                    Logistics.IN_TRANSIT
                ),
                LogisticsBean(
                    "2022-07-12 06:12:32",
                    null,
                    "快件离开不朽堡垒中转部已发往塞维尔街道",
                    Logistics.OTHER
                ),
                LogisticsBean(
                    "2022-07-12 02:01:48",
                    null,
                    "快件已到达不朽堡垒中转部",
                    Logistics.OTHER
                ),
                LogisticsBean(
                    "2022-07-10 22:56:03",
                    null,
                    "快件离开黎明城堡中转部已发往诺克萨斯不朽堡垒中转部",
                    Logistics.OTHER
                ),
                LogisticsBean(
                    "2022-07-10 17:08:41",
                    null,
                    "快件已抵达黎明城堡中转部",
                    Logistics.OTHER
                ),
                LogisticsBean(
                    "2022-07-10 09:38:11",
                    null,
                    "快件离开高地银湖发往黎明城堡中转部",
                    Logistics.OTHER
                ),
                LogisticsBean(
                    "2022-07-10 07:02:55",
                    "已揽件",
                    "高低银湖的盖伦[007-3345678]已揽件",
                    Logistics.ALREADY_TAKEN
                ),
                LogisticsBean(
                    "2022-07-09 21:23:08",
                    "已发货",
                    "包裹正在等待揽收",
                    Logistics.SHIPPED
                ),
                LogisticsBean(
                    "2022-07-09 19:10:09",
                    "已下单",
                    "您的订单已打物流单",
                    Logistics.ORDERED
                ),
                LogisticsBean(
                    "2022-07-09 19:05:32",
                    null,
                    "您的订单已通知配货",
                    Logistics.OTHER
                ),
                LogisticsBean(
                    "2022-07-09 19:01:53",
                    null,
                    "您的订单信息审核通过",
                    Logistics.OTHER
                ),
                LogisticsBean(
                    "2022-07-09 18:56:17",
                    null,
                    "您的订单开始处理",
                    Logistics.OTHER
                ),
                LogisticsBean(
                    "2022-07-09 18:40:39",
                    null,
                    "商品已经下单",
                    Logistics.OTHER
                )
            )
        }

        fun getJingDongData(): List<LogisticsBean> {
            return listOf(
                LogisticsBean(
                    DateFormatUtils.getDateFormat(System.currentTimeMillis()),
                    "已完成",
                    "风里来雨里去，只为客官您满意。上有老下有小，赏个好评好不好？【请在Star处帮忙点亮星星哦~】",
                    Logistics.IS_DONE
                ),
                LogisticsBean(
                    "2022-07-12 18:32:11",
                    "已签收",
                    "[恕瑞玛]已签收，他人代收：希维尔代收点。投递员：雷克顿，电话：3345678",
                    Logistics.RECEIVED
                ),
                LogisticsBean(
                    "2022-07-12 13:29:56",
                    "派送中",
                    "[恕瑞玛]【索昂萨沙瀑营业投递部】 安排投递，投递员：雷克顿，电话：3345678，揽投部电话：9527-3345678",
                    Logistics.DELIVERING
                ),
                LogisticsBean(
                    "2022-07-12 08:43:43",
                    null,
                    "[恕瑞玛]【索昂萨沙瀑营业投递部】 投递结果反馈-未妥投，备注（遇到沙尘暴），投递员：内瑟斯，电话：1008610010",
                    Logistics.OTHER
                ),
                LogisticsBean(
                    "2022-07-12 06:12:32",
                    null,
                    "[恕瑞玛]【索昂萨沙瀑营业投递部】 安排投递，投递员：内瑟斯，电话：1008610010，揽投部电话：9527-3345678",
                    Logistics.OTHER
                ),
                LogisticsBean(
                    "2022-07-12 02:01:48",
                    "运输中",
                    "[恕瑞玛]到达【希维尔营业投递部】",
                    Logistics.IN_TRANSIT
                ),
                LogisticsBean(
                    "2022-07-10 22:56:03",
                    null,
                    "[恕瑞玛]离开【恕瑞玛髓印集市处理中心】，下一站【希维尔营业投递部】",
                    Logistics.OTHER
                ),
                LogisticsBean(
                    "2022-07-10 17:08:41",
                    null,
                    "[恕瑞玛]到达【恕瑞玛髓印集市处理中心】",
                    Logistics.OTHER
                ),
                LogisticsBean(
                    "2022-07-10 09:38:11",
                    null,
                    "[比尔吉沃特]离开【屠宰码头2号港】，下一站【恕瑞玛髓印集市处理中心】",
                    Logistics.OTHER
                ),
                LogisticsBean(
                    "2022-07-10 07:02:55",
                    null,
                    "[比尔吉沃特]到达【屠宰码头2号港】",
                    Logistics.OTHER
                ),
                LogisticsBean(
                    "2022-07-09 21:23:08",
                    "",
                    "[比尔吉沃特]离开【芭茹神庙】，下一站【屠宰码头2号港】",
                    Logistics.OTHER
                ),
                LogisticsBean(
                    "2022-07-09 19:10:09",
                    "已揽收",
                    "[比尔吉沃特]【芭茹神庙】已收寄，揽投员：普朗克，电话：13515313515",
                    Logistics.ALREADY_TAKEN
                ),
                LogisticsBean(
                    "2022-07-09 19:05:32",
                    "仓库处理中",
                    "温馨提示：您的订单预计7月12日24:00前送达，请您做好收货安排",
                    Logistics.WAREHOUSE_PROCESSING
                ),
                LogisticsBean(
                    "2022-07-09 19:01:53",
                    null,
                    "您的订单由第三方卖家拣货完成，待出库交付卡萨邮递，运单号为112233445566",
                    Logistics.OTHER
                ),
                LogisticsBean(
                    "2022-07-09 18:56:17",
                    null,
                    "第三方卖家已经开始拣货",
                    Logistics.OTHER
                ),
                LogisticsBean(
                    "2022-07-09 18:40:39",
                    null,
                    "您的订单进入第三方卖家仓库，准备出库",
                    Logistics.OTHER
                ),
                LogisticsBean(
                    "2022-07-09 18:30:28",
                    "已下单",
                    "您提交了订单，请等待第三方卖家系统确认",
                    Logistics.ORDERED
                )
            )
        }

        const val GITEE_URL = "https://gitee.com/trydamer/logistics-demo"
    }
}