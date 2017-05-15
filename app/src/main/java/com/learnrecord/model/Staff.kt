package com.learnrecord.model

import android.icu.util.TimeUnit.values


/**
 *Created by Code4Android on 2017/4/24.
 * 数据类用来保存数据，类似POJO类，使用 data 关键词进行定义
 * 编译器默认会为数据类生成以下四个方法：
 * equals()
 * hashCode()
 * toString()
 * copy()
 */
data class Staff<T>(var name: String, val position: String, var age: T) {
    override fun toString(): String {
        return super.toString()
    }
}

fun main(arg: Array<String>) {
    var staff = Staff("code4Android", "Android工程师", "22")  //实例化对象
    staff.name = "code4Android2"
    var staff1 = staff.copy()
    //指定默认值
    var name = ""
    var staff2 = staff.copy(name = "ccc", position = "kotlin")
    println("name:${staff2.name} position:${staff2.position} age ${staff2.age}")
    //staff.position="Kotiln" //val不能再次赋值
    var anotherStaff = Staff("code4Android", "Android工程师", 22) //实例化对象

    println("staff toString(): ${staff.toString()} anotherStaff toString(): ${anotherStaff.toString()}")
    println("staff hashCode(): ${staff.hashCode()} anotherStaff hashCode(): ${anotherStaff.hashCode()}")
    println("staff is equals another staff ? ${staff.equals(anotherStaff)}")
    val box = Box<String>("box")
    //通过值可以推断出类型，也可以省略类型参数：
    var box1 = Box("")
    displayColor()
    Singleton.doSomething()
    MyClass.doSomething() // 访问内部单例对象方法

}

/**
 * kotlin中并没有类型通配符，取而代之的是 declaration-site variance和type projections。
 * 参照C#的泛型特性，添加了协变和逆变概念 (covariant和contravariance)。
 * 其中，covariant表示参数只能是被用作read，只能用在方法的返回值，使用out符号进行修饰，如<out T>，表示类型为T或T的子类。
 */
//泛型作用类
class Box<T>(t: T) {
    var value = t
}

//泛型作用于接口
interface IBox<T> {
}

class Outer {
    private val name: String = "jason"

    /**嵌套类
     * 若嵌套类需要访问外部类成员，则需要使用inner关键词进行修饰
     * **/
    class Nested {
        fun doSomething() = Outer().name
    }
}

//若需要实例化内部类，需要首先获取到外部类对象，然后再进行实例化
val name1 = Outer.Nested().doSomething()

//若需要实例化内部类，需要首先获取到外部类对象，然后再进行实例化
class Outer1 {
    private val bar: Int = 1

    class Nested {
        fun something() = 2
    }
}

val demo = Outer1.Nested().something() // == 2

//枚举
enum class Color {
    RED, BLACK, BLUE, GREEN, WHITE
}

fun displayColor() {
    var color: Color = Color.BLACK
    Color.valueOf("BLACK") // 转换指定name为枚举值，若未匹配成功，会抛出IllegalArgumentException
    Color.values() //已数组的形式，返回枚举值
    println(color.name)////获取枚举名称
    println(color.ordinal)//获取枚举值在所有枚举数组中定义的顺序,0开始
}

//若需要指定值，则可以使用其构造函数
enum class Shape(value: Int) {
    ovel(100),
    rectangle(200)
}

//枚举还支持自定义方法
enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    },

    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}

/**
 * java允许使用匿名内部类;kotlin也有类似的概念，称为对象表达式-object expressions
 */
open class KeyBord {
    open fun onKeyEvent(code: Int): Unit = Unit
}

/**匿名内部类**/
var key = object : KeyBord() {
    override open fun onKeyEvent(code: Int): Unit = Unit
}


fun onClick(key: KeyBord) {
    //do nothing
}

fun print() {
    /**匿名内部类用作函数参数**/
    onClick(object : KeyBord() {
        override open fun onKeyEvent(code: Int): Unit = Unit
    })
}

//当仅需要对象时，可以不继承类，直接使用object进行定义
var value = object {
    var x = 1
    var y = 2

}

/**
 * 使用object定义类，该类的实例即为单例，访问单例直接使用类名，不能通过构造函数进行访问，不允许有构造函数
 * Place.doSomething() // 访问单例对象
 */
object Singleton {
    fun doSomething() {
        println("doSomething")
    }
}

/**
 * 实例化的时候，单例是懒加载，当使用的时候才去加载；而对象表达式是在初始化的地方去加载。
 *
 * 当在类内部使用 object 关键词定义对象时，允许直接通过外部类的类名访问内部对象进而访问其相关属性和方法，相当于静态变量
 * 可以使用companion修饰单例，则访问其属性或方法时，允许省略单例名
 * MyClass.doSomething() // 访问内部单例对象方法
 */
class MyClass {
    companion object Singleton {
        fun doSomething() {
            println("doSomething")
        }
    }
}


