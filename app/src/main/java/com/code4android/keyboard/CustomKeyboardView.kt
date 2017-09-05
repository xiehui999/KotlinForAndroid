package com.code4android.keyboard

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.util.AttributeSet
import com.code4android.kotlinforandroid.R
import kotlinx.android.synthetic.main.content_main.*

/**
 *Created by Code4Android on 2017/9/5.
 */
class CustomKeyboardView : KeyboardView {
    lateinit var mKeyBoard: Keyboard

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {}
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        //暂时没有自定义属性扩展
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mKeyBoard = keyboard
        var keys: MutableList<Keyboard.Key>? = null
        if (mKeyBoard != null) {
            keys = mKeyBoard.keys
        }

        if (keys != null) {
            for (key in keys) {
                if (key.codes[0] == -4) {
                    drawBackground(R.color.btnnormal, canvas, key)
                    drawText(canvas, key)
                }
            }
        }
    }

    fun drawText(canvas: Canvas, key: Keyboard.Key) {
        var bounds = Rect()
        var paint = Paint()
        paint.color = Color.WHITE
        paint.isAntiAlias = true
        paint.textAlign = Paint.Align.CENTER
        paint.typeface = Typeface.DEFAULT
        if (key.label != null) {

        } else if (key.icon != null) {

        }
    }

    fun drawBackground(color: Int, canvas: Canvas, key: Keyboard.Key) {
        var colorDraw = ColorDrawable(color)
        colorDraw.bounds = Rect(key.x, key.y, key.x + key.width, key.height + key.y)
        colorDraw.draw(canvas)
    }

}