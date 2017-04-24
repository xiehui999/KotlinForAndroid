package com.learnrecord.file

import com.learnrecord.Student

/**
 *Created by Code4Android on 2017/4/23.
 */
/**数据实体：User**/
data class User(var name: String, var age: Int)

/**实现Iterator接口，对user数组进行取下一个元素以及判断**/
class UserIterator(var users: Array<User>) : Iterator<User> {
    var index = 0; //当前元素索引

    //判断是否还有下一个元素
    override fun hasNext(): Boolean = users.size > index

    //去下一个元素
    override fun next(): User = users[index++]

}

/**自定义User数组，实现Iterable接口**/
class UsersArray : Iterable<User> {
    //默认初始化3个元素的数组
    var users: Array<User> = Array<User>(3, { i -> User("user" + i, i) })

    //获取iterator
    override fun iterator(): Iterator<User> {
        return UserIterator(users)
    }
}

var x = 1
//主函数遍历数组
fun main(arg: Array<String>) {
    var usersArray: UsersArray = UsersArray()

    for (u in usersArray) {
        print("name:" + u.name)
        println(" age:" + u.age)
    }
//@定义label，一般用在内层循环中跳到外层循环:
    loop@ for (i in 0..2) {
        for (j in 0..3) {
            println("i:" + i + "  j:" + j)
            if (j == 2)
            //continue@loop//跳到外层循环，继续往下执行
                break@loop  //跳到外层循环label处，跳出改层循环
        }
    }
    foo()
    foo1()
    /**
     * 左边表达式非空，elvis操作符就会返回左边的结果，否则返回右边的结果。
     *请注意，仅在左侧为空的时候，右侧的表达式才会计算
     */
    val b: String = "111"
    val a1 = b?.length ?: -1
//等同于：
    val a: Int = if (b != null) b.length else -1
//as?安全转换，当转型不成功的时候，它会返回 null。
    val m: Int? = a as? Int
    for (i in 1..10) { // 等价于 1 <= i && i <= 10
        println(i)
    }
    //downTo倒序
    for (i in 4 downTo 1) {
        println(i)
    }
    //step
    for (i in 1..4 step 2) print(i) // prints "13"
    for (i in 4 downTo 1 step 2) print(i) // prints "42"
//反射得到运行时的类引用:
    val c = Student::class
    //函数引用
    //使用 :: 操作符:

    fun isOdd(x: Int) = x % 2 != 0
    val numbers = listOf(1, 2, 3)
    println(numbers.filter(::isOdd)) //prints [1, 3]
//属性引用
    println(::x.get())
    ::x.set(2)
    println(x)

    //::x 表达式评估为 KProperty<Int> 类型的属性，它允许我们使用 get() 读它的值或者使用名字取回它的属性
    val prop = A::p
    println(prop.get(A(1))) // prints "1"
    //对于扩展属性
    println(String::lastChar.get("abc")) // prints "c"
    //与 java 反射调用
    println(A::p.javaClass) // prints "public final int A.getP()"
    println(A::p.javaClass.declaredFields) // prints "public final int A.getP()"
    //构造函数引用
    //只需要使用 :: 操作符并加上类名。下面的函数是一个没有参数并且返回类型是 Foo
    function(::Foo)
}

class Foo

fun function(factory: () -> Foo) {
    val x: Foo = factory()
}

val String.lastChar: Char
    get() = this[this.length - 1]

class A(val p: Int)

/**
 * 在函数中使用return,当函数没有返回值时，
 * 可以使用return或者return@label退出函数；当函数有返回值时，不可以使用label返回
 */
fun foo() {
    var intArray = arrayOf(1, 2, 3)
    intArray.forEach {
        if (it == 2) return //遍历到第二个元素时退出函数
        println(it)
    }
    println("end")
}

//当使用label时，表示退出当前循环：
fun foo1() {
    var intArray = arrayOf(1, 2, 3)
    intArray.forEach {
        if (it == 2) return@forEach //遍历到第二个元素时退出函数
        println(it)
    }
    println("end")
}