package com.shapeapp.shape.networkinfo

import java.io.IOException

/**
 * Provides information about network connection
 */
object NetworkInformer {

    private const val GOOGLE_DNS_SERVER_IP = "8.8.8.8"

    /**
     * Checks if device is online
     *
     * based on: https://stackoverflow.com/a/27312494
     */
    fun isOnline(): Boolean {
        val runtime = Runtime.getRuntime()
        try {
            val packetsNumber = "1"
            val ipProcess = runtime.exec("/system/bin/ping -c $packetsNumber $GOOGLE_DNS_SERVER_IP")
            //  wait until the process has terminated
            val exitValue = ipProcess.waitFor()
            return (exitValue == 0)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        return false
    }

}