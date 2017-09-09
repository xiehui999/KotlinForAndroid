package com.code4android.keyboard

import android.content.Context
import android.graphics.*
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.util.AttributeSet

/**
 *Created by Code4Android on 2017/9/5.
 */
class CustomKeyboardView : KeyboardView {
    private var mKeyBoard: Keyboard? = null

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs,0) {}

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
                if (key.codes[0] == -111) {//过滤指定某个键自定义绘制
                    //绘制后，原来xml中的keyLabel以及keyIcon会被覆盖,如需显示文字需要自己重新绘制，要后绘制文字，否则文字不显示
/*                    drawBackground(R.drawable.bg_keyboardview1, canvas, key)
                    drawTextOrIcon(canvas, key)*/
                }
            }
        }
    }

    //绘制文字或图标
    fun drawTextOrIcon(canvas: Canvas, key: Keyboard.Key) {
        var bounds = Rect()
        var paint = Paint()
        paint.color = Color.WHITE
        paint.isAntiAlias = true
        paint.textAlign = Paint.Align.CENTER
        paint.typeface = Typeface.DEFAULT
        if (key.label != null) {
            var label = key.label.toString()
            //为了将字体大小与默认绘制的Label字体大小相同，需要反射获取默认大小。然后在此处设置文字大小
            //还有一种取巧的方法在布局文件keyboardview中设置keyTextSize，labelTextSize
            var field = KeyboardView::class.java.getDeclaredField("mLabelTextSize")
            field.isAccessible = true
            var labelTextSize = field.get(this) as Int
            paint.textSize = labelTextSize.toFloat()
            paint.getTextBounds(label, 0, label.length, bounds)
            canvas.drawText(label, (key.x + key.width / 2).toFloat(), (key.y + key.height / 2 + bounds.height() / 2).toFloat(), paint)
        } else if (key.icon != null) {
            key.icon.bounds = Rect(key.x + (key.width - key.icon.intrinsicWidth) / 2, key.y + (key.height - key.icon.intrinsicHeight) / 2, key.x + (key.width - key.icon.intrinsicWidth) / 2 + key.icon.intrinsicWidth, key.y + (key.height - key.icon.intrinsicHeight) / 2 + key.icon.intrinsicHeight)
            key.icon.draw(canvas)
        }
    }

    //绘制背景
    fun drawBackground(drawableId: Int, canvas: Canvas, key: Keyboard.Key) {
        var drawable = resources.getDrawable(drawableId)
        var drawableState: IntArray = key.currentDrawableState
        if (key.codes[0] != 0) {
            drawable.state=drawableState
        }
        drawable.bounds = Rect(key.x, key.y, key.x + key.width, key.height + key.y)
        drawable.draw(canvas)
    }

}