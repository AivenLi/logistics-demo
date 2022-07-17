package com.aiven.logisticsdemo.ui.activity

import com.aiven.logisticsdemo.base.activity.BaseActivity
import com.aiven.logisticsdemo.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun initView() {
        viewBinding.taobao.setOnClickListener {
            LogisticsActivity.start(this@MainActivity, "淘宝物流", LogisticsActivity.TAOBAO)
        }
        viewBinding.jingdong.setOnClickListener {
            LogisticsActivity.start(this@MainActivity, "京东物流", LogisticsActivity.JINGDONG)
        }
        viewBinding.duoduo.setOnClickListener {
            LogisticsActivity.start(this@MainActivity, "多多物流", LogisticsActivity.DUODUO)
        }
        viewBinding.btnMine.setOnClickListener {
            LogisticsActivity.start(this@MainActivity, "时间在左边", LogisticsActivity.MINE)
        }
    }
}