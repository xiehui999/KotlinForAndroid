package com.learnrecord.file

/**
 *Created by Code4Android on 2017/4/24.
 */
class Employee(var name: String) {
    fun print() {
        println("Employee")
    }
}

/**
 * fun receiverType.functionName(params){
 *body
 *}
 * receiverType : 待扩展的类名
 * .            ：修饰符为扩展符
 * functionName ：为自定义的扩展函数名,
 * params       ：为自定义的扩展函数参数，可为空
 * 扩展函数作用域，受函数的visibility modifiers影响
 * 扩展函数并没有对原类做修改，而是为被扩展类的对象添加新的函数。
 * 有一条规则，若扩展函数和类原有函数一致，则使用该函数时，会优先使用类本身的函数。
 */
//扩展函数
fun Employee.print() {
    print("print：Employee name is $name")
}

//扩展函数
fun Employee.println() {
    print("println:Employee name is $name")
}

/**
 * 可以扩展一个空对象
 */
fun Any?.toString1(): String {
    if (this == null)
        return "toString1:null"
    else {
        return "toString1" + toString()
    }
}

/**
 * 扩展属性
 * 由于扩展属性实际上不会向类添加新的成员,
 * 因此无法让一个扩展属性拥有一个后端域变量. 所以,对于扩展属性不允许存在初始化器.
 * 扩展属性的行为只能通过明确给定的取值方法与设值方法来定义，也就意味着扩展属性只
 * 能被声明为val而不能被声明为var.如果强制声明为var，即使进行了初始化，
 * 在运行也会报异常错误，提示该属性没有后端域变量。
 */
val Employee.lastName: String
    get() {
        return "get:"+name
    }

fun main(arg: Array<String>) {
    var employee = Employee("Code4Android")
    employee.print()
    employee.println()
    println(employee.toString1())
    println(employee.lastName)
}
