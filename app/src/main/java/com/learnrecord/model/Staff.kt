package com.learnrecord.model


/**
 *Created by Code4Android on 2017/4/24.
 * 数据类用来保存数据，类似POJO类，使用 data 关键词进行定义
 * 编译器默认会为数据类生成以下四个方法：
 * equals()
 * hashCode()
 * toString()
 * copy()
 */
data class Staff(var name: String,  val position: String)

fun main(arg:Array<String>){
    var staff = Staff("code4Android","Android工程师")  //实例化对象
    staff.name="code4Android2"
    var staff1=staff.copy()
    //指定默认值
    staff.copy(name="ccc",position = "kotlin")
    //staff.position="Kotiln" //val不能再次赋值
    var anotherStaff= Staff("code4Android","Android工程师") //实例化对象

    println("staff toString(): ${staff.toString()} anotherStaff toString(): ${anotherStaff.toString()}")
    println("staff hashCode(): ${staff.hashCode()} anotherStaff hashCode(): ${anotherStaff.hashCode()}")
    println("staff is equals another staff ? ${staff.equals(anotherStaff)}")
}