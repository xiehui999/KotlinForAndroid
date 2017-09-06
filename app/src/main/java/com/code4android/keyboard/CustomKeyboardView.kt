package com.code4android.keyboard

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.util.AttributeSet
import com.code4android.kotlinforandroid.R

/**
 *Created by Code4Android on 2017/9/5.
 */
class CustomKeyboardView : KeyboardView {
    private var mKeyBoard: Keyboard? = null

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        //
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mKeyBoard = this.keyboard
        var keys: MutableList<Keyboard.Key>? = null
        if (mKeyBoard != null) {
            keys = mKeyBoard!!.keys
        }
        if (keys != null) {
            for (key in keys) {
                //可以自定义自己的绘制（例如某个按钮绘制背景图片和文字，亦或者更改某个按钮颜色等）
                if (key.codes[0] == Keyboard.KEYCODE_CANCEL) {
/*                    drawBackground(R.color.btnnormal, canvas, key)
                    drawText(canvas, key)*/
                }
            }
        }
    }

    //绘制文字
    fun drawText(canvas: Canvas, key: Keyboard.Key) {
        var bounds = Rect()
        var paint = Paint()
        paint.color = Color.WHITE
        paint.isAntiAlias = true
        paint.textAlign = Paint.Align.CENTER
        paint.typeface = Typeface.DEFAULT
        if (key.label != null) {
            var label = key.label.toString()
            paint.getTextBounds(label, 0, label.length, bounds)
            canvas.drawText(label, (key.x + key.width / 2).toFloat(), (key.y + key.height / 2 + bounds.height() / 2).toFloat(), paint)
        } else if (key.icon != null) {
            key.icon.bounds = Rect(key.x + (key.width - key.icon.intrinsicWidth) / 2, key.y + (key.height - key.icon.intrinsicHeight) / 2, key.x + (key.width - key.icon.intrinsicWidth) / 2 + key.icon.intrinsicWidth, key.y + (key.height - key.icon.intrinsicHeight) / 2 + key.icon.intrinsicHeight)
            key.icon.draw(canvas)
        }
    }

    //绘制背景
    fun drawBackground(color: Int, canvas: Canvas, key: Keyboard.Key) {
        var colorDraw = ColorDrawable(color)
        colorDraw.bounds = Rect(key.x, key.y, key.x + key.width, key.height + key.y)
        colorDraw.draw(canvas)
    }

}