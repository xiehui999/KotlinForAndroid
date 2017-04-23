package com.learnrecord

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
}

/**
 * 在函数中使用return,当函数没有返回值时，
 * 可以使用return或者return@label退出函数；当函数有返回值时，不可以使用label返回
 */
fun foo(){
    var intArray = arrayOf(1,2,3)
    intArray.forEach{
        if(it==2)return //遍历到第二个元素时退出函数
        println(it)
    }
    println("end")
}
//当使用label时，表示退出当前循环：
fun foo1(){
    var intArray = arrayOf(1,2,3)
    intArray.forEach{
        if(it==2)return@forEach //遍历到第二个元素时退出函数
        println(it)
    }
    println("end")
}