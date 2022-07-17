package com.aiven.logisticsdemo.ui.activity

import android.Manifest
import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.aiven.logisticsdemo.base.activity.BaseActivity
import com.aiven.logisticsdemo.bean.LogisticsBean
import com.aiven.logisticsdemo.databinding.ActivityLogisticsBinding
import com.aiven.logisticsdemo.ui.adapter.DuoDuoLogisticsAdapter
import com.aiven.logisticsdemo.ui.adapter.JingDongLogisticsAdapter
import com.aiven.logisticsdemo.ui.adapter.MineLogisticsAdapter
import com.aiven.logisticsdemo.ui.adapter.TaoBaoLogisticsAdapter
import com.aiven.logisticsdemo.ui.adapter.itemdecoration.*

class LogisticsActivity: BaseActivity<ActivityLogisticsBinding>(ActivityLogisticsBinding::inflate) {

    private var phoneNumber: String? = null

    private lateinit var dialog: AlertDialog
    companion object {
        fun start(context: Context, title: String, type: Int) {
            Intent(context, LogisticsActivity::class.java).apply {
                putExtra("title", title)
                putExtra("type", type)
            }.let {
                context.startActivity(it)
            }
        }

        const val TAOBAO = 0
        const val JINGDONG = 1
        const val DUODUO = 2
        const val MINE = 3
    }

    override fun initView() {
        viewBinding.recyclerView.isNestedScrollingEnabled = false
        viewBinding.ildTitleLayout.tvTitle.text = intent.getStringExtra("title")
        viewBinding.ildTitleLayout.imgBack.setOnClickListener { finish() }
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        viewBinding.recyclerView.run {
            when (intent.getIntExtra("type", 0)) {
                TAOBAO -> {
                    addItemDecoration(TaoBaoDecoration(this@LogisticsActivity))
                    adapter = taoBaoAdapter()
                }
                JINGDONG -> {
                    addItemDecoration(JingDongDecoration(this@LogisticsActivity))
                    adapter = jingDongAdapter()
                }
                DUODUO -> {
                    addItemDecoration(DuoDuoDecoration(this@LogisticsActivity))
                    adapter = duoduoAdapter()
                }
                else -> {
                    addItemDecoration(MineDecoration(this@LogisticsActivity))
                    adapter = mineAdapter()
                }
            }
        }
    }

    private fun taoBaoAdapter(): TaoBaoLogisticsAdapter {
        return TaoBaoLogisticsAdapter(this@LogisticsActivity, LogisticsBean.getTaoBaoData()).apply {
            setOnPhoneClickListener { phoneNumber ->
                getDialogBuilder(phoneNumber)
                    .show()
            }
            setOnStarListener { url ->
                val uri = Uri.parse(url)
                val intent = Intent()
                intent.action = "android.intent.action.VIEW"
                intent.data = uri
                startActivity(intent)
            }
        }
    }

    private fun jingDongAdapter(): JingDongLogisticsAdapter {
        return JingDongLogisticsAdapter(this@LogisticsActivity, LogisticsBean.getJingDongData()).apply {
            setOnPhoneClickListener { phoneNumber ->
                getDialogBuilder(phoneNumber)
                    .setNeutralButton("复制文本") { _, _ ->
                        (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).run {
                            setPrimaryClip(ClipData.newPlainText(null, phoneNumber))
                            Toast.makeText(this@LogisticsActivity, "已复制", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .show()
            }
            setOnStarListener { url ->
                val uri = Uri.parse(url)
                val intent = Intent()
                intent.action = "android.intent.action.VIEW"
                intent.data = uri
                startActivity(intent)
            }
        }
    }

    private fun duoduoAdapter(): DuoDuoLogisticsAdapter {
        return DuoDuoLogisticsAdapter(this@LogisticsActivity, LogisticsBean.getJingDongData()).apply { 
            setOnPhoneClickListener { phoneNumber ->
                getDialogBuilder(phoneNumber)
                    .setNeutralButton("复制文本") { _, _ ->
                        (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).run {
                            setPrimaryClip(ClipData.newPlainText(null, phoneNumber))
                            Toast.makeText(this@LogisticsActivity, "已复制", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .show()
            }
            setOnStarListener { url ->
                val uri = Uri.parse(url)
                val intent = Intent()
                intent.action = "android.intent.action.VIEW"
                intent.data = uri
                startActivity(intent)
            }
        }
    }

    private fun mineAdapter(): MineLogisticsAdapter {
        return MineLogisticsAdapter(this@LogisticsActivity, LogisticsBean.getJingDongData()).apply {
            setOnPhoneClickListener { phoneNumber ->
                getDialogBuilder(phoneNumber)
                    .setNeutralButton("复制文本") { _, _ ->
                        (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).run {
                            setPrimaryClip(ClipData.newPlainText(null, phoneNumber))
                            Toast.makeText(this@LogisticsActivity, "已复制", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .show()
            }
            setOnStarListener { url ->
                val uri = Uri.parse(url)
                val intent = Intent()
                intent.action = "android.intent.action.VIEW"
                intent.data = uri
                startActivity(intent)
            }
        }
    }

    private fun getDialogBuilder(phoneNumber: String): AlertDialog.Builder {
        return AlertDialog.Builder(this@LogisticsActivity)
            .setTitle("拨打电话")
            .setPositiveButton("确定") { _, _ ->
                if (hasCallPhonePermission()) {
                    startCallPhone(phoneNumber)
                } else {
                    this@LogisticsActivity.phoneNumber = phoneNumber
                }
            }
            .setMessage(phoneNumber)
            .setNegativeButton("取消", null)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 9527) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCallPhone(phoneNumber)
            }
        }
    }

    private fun startCallPhone(phoneNumber: String?) {
        phoneNumber?.let {
            Intent().run {
                action = Intent.ACTION_DIAL
                data = Uri.parse("tel:$phoneNumber")
                startActivity(this)
            }
        }
    }

    private fun hasCallPhonePermission(): Boolean {
        if (ContextCompat.checkSelfPermission(this@LogisticsActivity, Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@LogisticsActivity,
                arrayOf(Manifest.permission.CALL_PHONE),
                9527
            )
            return false
        }
        return true
    }
}