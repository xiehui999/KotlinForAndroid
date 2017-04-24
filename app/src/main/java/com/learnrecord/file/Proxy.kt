package com.learnrecord.file

import kotlin.properties.Delegates

/**
 *Created by Code4Android on 2017/4/24.
 */
/**
 * 被代理接口
 */
interface Base {
    fun display()
}

/**
 * 被代理类
 */
open class BaseImpl : Base {
    override fun display() = print("just display me.")
}

/**
 * 代理类，使用:以及关键词by进行声明
 * 许代理属性和其他继承属性共用
 * class Drived(base: Base) : Base by base,BaseImpl()
 */
class Drived(base: Base) : Base by base

//使用
fun show() {
    var b = BaseImpl()
    var drived = Drived(b)
    drived.display()

}

/**
 * 代理类型：
 * 懒加载：Lazy
 * 观察者：Delegates.observable()
 * 非空属性：Delegates.notNull<>()
 */
class Water {

    public var weight:Int by Delegates.notNull()
    /**
     * 代理属性
     */
    public val name: String by lazy {
        println("Lazy.......")
        "Code4Android"
    }
    public var value: String by Delegates.observable("init value") {
        d, old, new ->
        println("$old->$new")
    }
}

fun main(arg: Array<String>) {
    show()
    var water = Water()
    println(water.name)
    println(water.name)
    water.value = "11111"
    water.value = "22222"
    water.value = "33333"
    println(water.value)
    println(water.value)
    //必须先赋值否则IllegalStateException: Property weight should be initialized before get.
    water.weight=2
    print(water.weight)
}