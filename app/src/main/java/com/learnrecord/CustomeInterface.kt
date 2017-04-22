package com.learnrecord

/**
 *Created by Code4Android on 2017/4/22.
 * 接口可以继承类
 */
interface CustomeInterface {
    var value:String//默认abstract
    fun reading()
    fun writing(){
        print("已经实现")
    }
}