package com.code4android.keyboard

import android.app.Activity
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.os.Build
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.FrameLayout
import com.code4android.kotlinforandroid.R
import org.jetbrains.anko.find
import org.jetbrains.anko.inputMethodManager
import java.lang.RuntimeException
import java.lang.reflect.Method
import java.util.*

/**
 *Created by Code4Android on 2017/9/6.
 */
class KeyBoardUtil {

    var mActivity: Activity
    var mIsRandom: Boolean = false
    var mKeyboard: Keyboard
    lateinit var mKeyBoardViewContainer: View
    lateinit var mKeyBoardView: CustomKeyboardView
    var mEditText: EditText? = null
    val TAG = "Keyboard"
    var mOnOkClick: OnOkClick? = null
    var mOnCancelClick: OnCancelClick? = null
    var mIsDouble = false

    constructor(activity: Activity) : this(activity, true, false)
    constructor(activity: Activity, isRandom: Boolean, isDouble: Boolean) {
        mActivity = activity
        mIsRandom = isRandom
        mIsDouble = true
        mKeyboard = Keyboard(mActivity, R.xml.keyboard)
        addViewToRoot()

    }


    private fun addViewToRoot() {
        mKeyBoardViewContainer = mActivity.layoutInflater.inflate(R.layout.keyboardview, null)
        //var frameLayout: FrameLayout = mActivity.window.decorView as FrameLayout//不要直接往DecorView(状态栏，内容，导航栏)中addView，如使用这个则最后显示布局不全（一部分内容在导航栏区域）
        var frameLayout: FrameLayout = mActivity.window.decorView.find(android.R.id.content)
        var lp = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        lp.gravity = Gravity.BOTTOM
        frameLayout.addView(mKeyBoardViewContainer, lp)
        mKeyBoardView = mKeyBoardViewContainer.find(R.id.keyboard_view)
        mKeyBoardView.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (mKeyBoardView.visibility == View.VISIBLE) {
                    mKeyBoardView.visibility = View.GONE
                    startAnimation(false)
                    true
                }
                false
            }
            false
        }
    }

    fun attachTo(editText: EditText) {
        if (editText !is EditText) throw RuntimeException("Type error")
        if (mEditText != null && mEditText == editText) return
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
                startAnimation(true)
            } else if (!hasFocus && mKeyBoardView.visibility == View.VISIBLE) {
                mKeyBoardView.visibility = View.GONE
                startAnimation(false)
            }
        }

        mEditText!!.setOnClickListener {
            Log.e(TAG, "setOnClickListener")
            if (mKeyBoardView.visibility == View.GONE) {
                mKeyBoardView.visibility = View.VISIBLE
                startAnimation(true)
            }
        }

    }

    private fun hideSystemSoftKeyboard() {
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
            generateRandomKey()
        } else {
            mKeyBoardView.keyboard = mKeyboard
        }
        mKeyBoardView.isEnabled = true
        mKeyBoardView.isPreviewEnabled = true
        mKeyBoardView.visibility = View.VISIBLE
        mKeyBoardView.setOnKeyboardActionListener(mOnKeyboardActionListener())
    }

    private fun generateRandomKey() {
        var keys = mKeyboard.keys
        var numberKeys = mutableListOf<Keyboard.Key>()
        //保存数字
        var nums = mutableListOf<Int>()
        //0的ASCII码是48,之后顺序加1
        for (key in keys) {
            if (key.label != null && "0123456789".contains(key.label)) {
                nums.add(Integer.parseInt(key.label.toString()))
                numberKeys.add(key)
            }
        }
        var random = Random()
        var changeKey = 0//更改numberKeys对应的数值
        while (nums.size > 0) {
            var size = nums.size
            var randomNum = nums[random.nextInt(size)]
            var key = numberKeys[changeKey++]
            key.codes[0] = 48 + randomNum
            key.label = randomNum.toString()
            nums.remove(randomNum)
        }
        mKeyBoardView.keyboard = mKeyboard
    }


    inner class mOnKeyboardActionListener : KeyboardView.OnKeyboardActionListener {
        override fun swipeRight() {
            Log.e(TAG, "swipeRight")
        }

        override fun onPress(primaryCode: Int) {
            Log.e(TAG, "onPress")
            //指定隐藏删除不显示预览
            mKeyBoardView.isPreviewEnabled = !(primaryCode == Keyboard.KEYCODE_CANCEL || primaryCode == Keyboard.KEYCODE_DELETE)
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
                var textString = editable.toString()
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
                    else -> {
                        //   由于promaryCode是用的ASCII码，则直接转换字符即可
                        if ((primaryCode != 46 && !mIsDouble) || mIsDouble && primaryCode != 46) {
                            editable.insert(start, Character.toString(primaryCode.toChar()))
                        } else {
                            if (mIsDouble && primaryCode == 46) {
                                if ("" == textString) {
                                    editable.insert(start, "0.")
                                } else if (!textString.contains(".")) {
                                    if (start == 0) {
                                        editable.insert(start, "0.")
                                    } else {
                                        editable.insert(start, ".")
                                    }
                                } else {
                                }
                            } else {
                            }
                        }
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
            startAnimation(false)
            mKeyBoardView.visibility = View.GONE
        }
    }

    fun startAnimation(isIn: Boolean) {
        Log.e(TAG, "startAnimation")
        var anim: Animation
        if (isIn) {
            anim = AnimationUtils.loadAnimation(mActivity, R.anim.anim_bottom_in)
        } else {
            anim = AnimationUtils.loadAnimation(mActivity, R.anim.anim_bottom_out)
        }
        mKeyBoardViewContainer.startAnimation(anim)
    }
}


