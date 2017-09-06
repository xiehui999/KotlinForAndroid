package com.code4android.keyboard

import android.app.Activity
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.os.Build
import android.text.Editable
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.EditText
import com.code4android.kotlinforandroid.R
import org.jetbrains.anko.find
import org.jetbrains.anko.inputMethodManager
import java.lang.RuntimeException
import java.lang.reflect.Method

/**
 *Created by Code4Android on 2017/9/6.
 */
class KeyBoardUtil {
    var mActivity: Activity
    var mIsRandom: Boolean = false
    var mKeyboard: Keyboard
    var mKeyBoardView: CustomKeyboardView
    var mEditText: EditText? = null
    val TAG = "Keyboard"
    var mOnOkClick: OnOkClick? = null
    var mOnCancelClick: OnCancelClick? = null

    constructor(activity: Activity) : this(activity, false)
    constructor(activity: Activity, isRandom: Boolean) {
        mActivity = activity
        mIsRandom = isRandom
        mKeyboard = Keyboard(mActivity, R.xml.keyboard)
        mKeyBoardView = mActivity.find(R.id.keyboard_view)
    }

    fun attachTo(editText: EditText) {
        if (!(editText is EditText)) throw RuntimeException("Type error")
        mEditText = editText
        Log.e(TAG, "attachTo")
        onFoucsChange()
        hideSystemSoftKeyboard()
        showSoftKeyboard()
    }

    private fun onFoucsChange() {
        mEditText!!.setOnFocusChangeListener { v, hasFocus ->
            Log.e(TAG, "onFoucsChange:$hasFocus" + v)
            if (hasFocus && mKeyBoardView.visibility != View.VISIBLE) {
                mKeyBoardView.visibility = View.VISIBLE
            } else if (!hasFocus && mKeyBoardView.visibility == View.VISIBLE) {
                mKeyBoardView.visibility = View.GONE
            }
        }
    }

    fun hideSystemSoftKeyboard() {
        if (Build.VERSION.SDK_INT > 10) {
            var clazz = EditText::class.java
            var setShowSoftInputOnFocus: Method? = null
            setShowSoftInputOnFocus = clazz.getMethod("setShowSoftInputOnFocus", Boolean::class.java)
            setShowSoftInputOnFocus.isAccessible = true
            setShowSoftInputOnFocus.invoke(mEditText, false)
        } else {
            mEditText!!.inputType = InputType.TYPE_NULL
        }
        var inputMethodManager = mActivity.applicationContext.inputMethodManager
        inputMethodManager.hideSoftInputFromWindow(mEditText!!.windowToken, 0)
    }

    fun setOnOkClick(onOkClick: OnOkClick) {
        mOnOkClick = onOkClick
    }

    fun setOnCancelClick(onCancelClick: OnCancelClick) {
        mOnCancelClick = onCancelClick
    }

    private fun showSoftKeyboard() {
        if (mIsRandom) {

        } else {
            mKeyBoardView.keyboard = mKeyboard
        }
        mKeyBoardView.isEnabled = true
        mKeyBoardView.isPreviewEnabled = false
        mKeyBoardView.visibility = View.VISIBLE
        mKeyBoardView.setOnKeyboardActionListener(mOnKeyboardActionListener())
    }


    inner class mOnKeyboardActionListener : KeyboardView.OnKeyboardActionListener {
        override fun swipeRight() {
            Log.e(TAG, "swipeRight")
        }

        override fun onPress(primaryCode: Int) {
            Log.e(TAG, "onPress")
        }

        override fun onRelease(primaryCode: Int) {
            Log.e(TAG, "onRelease")
        }

        override fun swipeLeft() {
            Log.e(TAG, "swipeLeft")
        }

        override fun swipeUp() {
            Log.e(TAG, "swipeUp")
        }

        override fun swipeDown() {
            Log.e(TAG, "swipeDown")
        }

        override fun onKey(primaryCode: Int, keyCodes: IntArray?) {
            Log.e(TAG, "onKey primaryCode:$primaryCode keyCodes:$keyCodes")
            if (mEditText == null) throw RuntimeException("The mEditText is null,Please call attachTo method")

            mEditText?.let {
                var editable: Editable = it.text
                var start = it.selectionStart
                when (primaryCode) {
                    Keyboard.KEYCODE_DELETE -> {
                        if (!editable.isNullOrEmpty()) {
                            if (start > 0) {
                                editable.delete(start - 1, start)
                            } else {
                            }
                        } else {
                        }
                    }
                    Keyboard.KEYCODE_CANCEL -> {
                        hideSoftKeyboard()
                        mOnCancelClick?.let {
                            it.onCancelClick()
                        }
                    }
                    Keyboard.KEYCODE_DONE -> {
                        hideSoftKeyboard()
                        mOnOkClick?.let {
                            it.onOkClick()
                        }
                    }
                    else -> {
                        editable.insert(start, Character.toString(primaryCode.toChar()))
                    }
                }
            }

        }

        override fun onText(text: CharSequence?) {
            Log.e(TAG, "onText:" + text.toString())
        }

    }

    fun showKeyboard() {
        var visibility = mKeyBoardView.visibility
        if (visibility == View.INVISIBLE || visibility == View.GONE) {
            mKeyBoardView.visibility = View.VISIBLE
        }
    }

    fun hideSoftKeyboard() {
        var visibility = mKeyBoardView.visibility
        if (visibility == View.VISIBLE) {
            mKeyBoardView.visibility = View.GONE
        }
    }
}


