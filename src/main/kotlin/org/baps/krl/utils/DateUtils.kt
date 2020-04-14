package org.baps.krl.utils

import java.util.Date
import java.time.ZoneId
import java.time.LocalDateTime



class DateUtils {
    fun asDate(localDateTime: LocalDateTime): Date {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
    }
}