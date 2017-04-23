package com.learnrecord

/**
 *Created by Code4Android on 2017/4/22.
 * Number类型：Float,Double,Int,Byte,Short,Char
 * 当接口未实现方法时，默认为父类的方法,不需要调用所继承的方法
 * Kotlin中的抽象类允许有abstract修饰的成员方法，非抽象类不允许有抽象方法；
 * 抽象类默认是可被继承的，接口是特殊的抽象类，允许有抽象方法
 */

class Student(id: String, name: String) : People(id, name) {
    var test: Number = 3
    private var name1: String?
        get() {
            return name1
        }
        set(value) {
            name1 = value
        }

    override fun study() {
        super.study()
    }

    fun beat() {
        println("Int max value:" + Int.MAX_VALUE)
        println("Int min value:" + Int.MIN_VALUE)

        println("Byte max value:" + Byte.MAX_VALUE)
        println("Byte max value:" + Byte.MAX_VALUE)
        println("beat $name")
    }

    override fun drink() {
        super.drink()
    }
}