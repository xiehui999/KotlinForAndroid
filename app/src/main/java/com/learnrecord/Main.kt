package com.learnrecord

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * Created on 2020-03-11 13:53
 * author：Code4Android
 * function:
 */
//程序入口点

fun main(args: Array<String>) {

    map()
    foo("11")
    Constructors(2)
    Derived("x", "2")
    //如果一个类定义有一个成员函数与一个扩展函数，而这两个函数又有相同的接收者类型、
// 相同的名字，并且都适用给定的参数，这种情况总是取成员函数
    Example().printFunctionType()
    Example().printFunctionType(1)
    //伴生对象扩展
    Example.printCompanion()

}


class Example {
    //伴生对象
    companion object {}  // 将被称为 "Companion"

    fun printFunctionType() {
        println("Class method")
    }
}

fun Example.printFunctionType(i: Int) {
    println("Extension function")
}

fun Example.Companion.printCompanion() {
    println("companion")
}


fun map() {
    val map = mapOf("key" to 42)
    // 返回不可空 Int 值 42
    val value: Int = map.getValue("key")

    val mapWithDefault = map.withDefault { k -> k.length }
    // 返回 4
    val value2 = mapWithDefault.getValue("key2")

    // map.getValue("anotherKey") // <- 这将抛出 NoSuchElementException

    println("value is $value")
    println("value2 is $value2")
}

fun foo(s: String) {
    //字符串模板
    println("输入参数是${s}")
    listOf(1, 2, 3, 4, 5).forEach {
        println("111111")
        if (it == 3) return // 非局部直接返回到 foo() 的调用者
        print(it)
    }
    println("this point is unreachable")
}

class Constructors {
    init {
        println("Init block")
    }

    constructor(i: Int) {
        println("Constructor")
    }
}

//没有主构造方法，每个次构造函数必须使用 super 关键字初始化其基类型
class MyView : View {
    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)
}

open class Base(val name: String) {

    //先执行
    init {
        println("Initializing Base")
    }

    //后执行
    open val size: Int =
            name.length.also { println("Initializing size in Base: $it") }
}

class Derived(
        name: String,
        val lastName: String
) : Base(name.capitalize().also { println("Argument for Base: $it") }) {

    init {
        println("Initializing Derived")

    }

    override val size: Int =
            (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
}