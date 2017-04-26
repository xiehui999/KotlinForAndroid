package com.learnrecord

/**
 *Created by Code4Android on 2017/4/22.
 * abstract //抽象类标示
 * final  //标示类不可继承，默认属性
 * enum  //标示类为枚举
 * open  //类可继承，类默认是final的
 * annotation  //注解类
 *
 *
 * private //仅在同一个文件中可见
 * protected //同一个文件中或子类可见,不可修饰类
 * public //所有调用的地方都可见
 * internal //同一个模块中可见,若类不加修饰符，则默认为该修饰符，作用域为同一个应用的所有模块，起保护作用，防止模块外被调用。
 * constructor:构造函数
 * constructor无修饰符(如：private)时，constructor可以省略：
 * 当是无参构造函数时，整个构造函数部分也可以省略，省略的构造函数默认是public的
 * 由于primary constructor不能包含任何代码，因此使用 init 代码块对其初始化,
 * 同时可以在初始化代码块中使用构造函数的参数
 */
//primary constructor
open class People constructor(var id: String, override var name: String) : Iwant1 {
    //可以类中初始化属性：
    var customName = name.toUpperCase() //初始化属性

    //使用constructor前缀声明，且必须调用primary constructor，使用this关键字
    constructor( id: String, name: String, age: Int) : this(id, name) {
        println("constructor")
    }

    init {
        println("初始化操作，可使用constructor参数")
    }

    //需要open修饰，子类才可以
    open fun study() {
        print("study")
    }

    //override修饰的方法，默认是可以被继承的。若希望不被继承，可以使用 final 关键词修饰
    override fun drink() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
   //,Kotlin的class并不支持static变量,所以需要使用companion object来声明static变量,
   // 其实这个platformStatic变量也不是真正的static变量,而是一个伴生对象,
    companion object {
        val ID = 1
    }
}