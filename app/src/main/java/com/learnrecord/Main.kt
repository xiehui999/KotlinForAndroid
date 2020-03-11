package com.learnrecord

/**
 * Created on 2020-03-11 13:53
 * author：Code4Android
 * function:
 */
//程序入口点
fun main(args: Array<String>) {

    map()
    foo("11")
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