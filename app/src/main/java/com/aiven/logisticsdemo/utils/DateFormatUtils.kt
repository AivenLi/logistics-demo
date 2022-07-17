package com.aiven.logisticsdemo.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author  : AivenLi
 * @date    : 2022/7/16 20:49
 * */
class DateFormatUtils {

    companion object {

        const val DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss"

        @SuppressLint("SimpleDateFormat")
        fun getDateFormat(timestamp: Long): String {
            return SimpleDateFormat(DEFAULT_FORMAT).format(Date(timestamp))
        }
    }
}