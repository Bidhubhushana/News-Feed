package com.example.newsfeed.util

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.widget.Toast


infix fun Context?.getFont(fontName: String) = Typeface.createFromAsset(this?.assets, fontName)

fun View?.visible() {
    this?.visibility = View.VISIBLE
}

fun View?.gone() {
    this?.visibility = View.GONE
}

fun Context?.toastMsg(msg: String) {
    Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
}

class CommonUtils {

    companion object{


    }
}