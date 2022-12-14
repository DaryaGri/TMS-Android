package com.example.android.hilt.data

interface LoggerDataSource {
    fun addLog(msg: String)
    fun getAllLogs(callBack: (List <Log>) -> Unit)
    fun removeLogs()
}