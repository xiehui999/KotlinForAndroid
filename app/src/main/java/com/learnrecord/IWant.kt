package com.learnrecord

/**
 *Created by Code4Android on 2017/4/22.
 * 接口可以定义属性，但是不能初始化
 * 接口不会保存属性值，实现接口时，必须重载属性
 */
interface IWant {
    var name:String
    fun eat()
}