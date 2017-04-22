package com.learnrecord

/**
 *Created by Code4Android on 2017/4/22.
 */
class Student(id: String, name: String) : People(id, name) {
    fun beat(){
        println("beat $name")
    }
    override fun drink() {
        super.drink()
    }
}