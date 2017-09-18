package com.code4android.kotlinforandroid.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet
import android.view.View

/**
 *Created by Code4Android on 2017/9/11.
 */
class ScrollBehavior : CoordinatorLayout.Behavior<View> {
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: View?, dependency: View?): Boolean {
        return super.layoutDependsOn(parent, child, dependency)

    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: View?, dependency: View?): Boolean {
        return super.onDependentViewChanged(parent, child, dependency)
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout?, child: View?, directTargetChild: View?, target: View?, nestedScrollAxes: Int): Boolean {
        return (nestedScrollAxes and ViewCompat.SCROLL_AXIS_VERTICAL) != 0
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout?, child: View?, target: View?, dx: Int, dy: Int, consumed: IntArray?) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed)

    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout?, child: View?, target: View?, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
        var leftScrolled = target?.scaleY
        child?.scaleY = leftScrolled ?: 0F
    }
    override fun onNestedFling(coordinatorLayout: CoordinatorLayout?, child: View?, target: View?, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        (child as NestedScrollView).fling(velocityY.toInt())
        return true
    }
}