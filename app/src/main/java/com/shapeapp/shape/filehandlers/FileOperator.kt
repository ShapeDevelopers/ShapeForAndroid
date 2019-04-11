package com.shapeapp.shape.filehandlers

import android.annotation.SuppressLint
import android.content.Context
import android.os.Environment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Helper for performing file operations on disk
 */
object FileOperator {

    fun createImageFile(context: Context): File {
        return createFile("image", "jpg", Environment.DIRECTORY_PICTURES, context)
    }

    @Throws(IOException::class)
    fun createFile(prefix: String = "file", extension: String, environmentDirectory: String, context: Context): File {
        val timeStamp = generateTimeStamp()
        val fileName = "${prefix}_${timeStamp}_"
        val storageDir = context.getExternalFilesDir(environmentDirectory)

        return File.createTempFile(
            fileName,
            ".$extension",
            storageDir
        )
    }

    @SuppressLint("SimpleDateFormat")
    private fun generateTimeStamp(): String {
        return SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    }


}