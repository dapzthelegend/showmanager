/**
 * Created by Sola-Aremu Oluwadara on 03/09/2021.
 */

package com.example.showmanager.commons.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("SimpleDateFormat")
fun formatDate(date: String): String {
    try {

        val formatIn = SimpleDateFormat("dd-MM-yyyy")
        val formatOut = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.US
        )
        val dateCreated = formatIn.parse(date)
        if (dateCreated != null) {
            return formatOut.format(dateCreated)
        }
    } catch (e: ParseException) {
    }
    return ""
}

@SuppressLint("SimpleDateFormat")
fun formatDateReverse(date: String): String {
    try {
        val formatCreated = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.US
        )
        val dateCreated = formatCreated.parse(date)
        val fmtOut = SimpleDateFormat("MMM d, yy")
        if (dateCreated != null) {
            return fmtOut.format(dateCreated)
        }
    } catch (e: ParseException) {
    }
    return ""
}
