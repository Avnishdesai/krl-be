package org.baps.krl.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class LoggerProducer(){
    fun getLogger(forClass: Class<*>): Logger =
            LoggerFactory.getLogger(forClass)
}