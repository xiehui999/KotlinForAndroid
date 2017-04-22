package com.learnrecord

/**
 *Created by Code4Android on 2017/4/22.
 * Number类型：Float,Double,Int,Byte,Short,Char
 */
class Student(id: String, name: String) : People(id, name) {
    var test:Number=3


    fun beat(){
        println("Int max value:"+Int.MAX_VALUE)
        println("Int min value:"+Int.MIN_VALUE)

        println("Byte max value:"+Byte.MAX_VALUE)
        println("Byte max value:"+Byte.MAX_VALUE)
        println("beat $name")
    }
    override fun drink() {
        super.drink()
    }
}