package com.code4android.kotlinforandroid.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import android.widget.Button

/**
 *Created by Code4Android on 2017/9/11.
 */
class DepententBehavior : CoordinatorLayout.Behavior<View> {
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

    }

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: View?, dependency: View?): Boolean {
        return dependency is Button
    }

    override fun onAttachedToLayoutParams(params: CoordinatorLayout.LayoutParams) {
        super.onAttachedToLayoutParams(params)
    }
    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: View?, dependency: View?): Boolean {
        var offset = dependency?.top ?: 0
        ViewCompat.offsetTopAndBottom(child, -offset)
        return true
    }
}