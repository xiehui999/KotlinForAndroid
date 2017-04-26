package com.learnrecord.file

/**
 * import导入类as设置别名
 */
import com.learnrecord.People
import com.learnrecord.Student as People1

/**
 *Created by Code4Android on 2017/4/22.
 */
fun main(args: Array<String>) {

    println("Hello World!")
    var number: Int = 1000  //Int类型
    //判断是否对象或基础类型，可通过===来判断，若是对象，===表示比较地址，
    // 若是基础类型，表示值本身，==比较值
    var boxNumber: Int? = number //赋值，boxNumber可为空，指向number
    var anotherBoxNumber: Int? = number //赋值，同上
    println(boxNumber === anotherBoxNumber)
    println(boxNumber == anotherBoxNumber)
    //操作符  shl 下面对Int和Long
    var a: Int = 4
    var shl: Int = a shl (1)  //Java中的左移运算符 <<
    var shr: Int = a shr (1) //Java中的右移运算符 >>
    var ushr: Int = a ushr (3) //无符号右移，高位补0 >>>
    var and: Int = 2 and (4)   //按位与操作 &
    var or: Int = 2 or (4) //按位或操作 |
    var xor: Int = 2 xor (6)  //按位异或操作 ^
    println("\nshl:" + shl + "\nshr:" + shr + " \nushr:" + ushr + "\nand：" + and + "\nor:" + or + "\nxor:" + xor)

    //当下面使用Boolean
    //or() 相当于 ||
    //and() 相当于 &&.如果是Int则是按位与
    // xor() 当操作符两边都是相反时为true，否则为false
    var isChange :Boolean= true
    var isRead = false
    println(isChange.not())
    println(isChange or (isRead))
    println(isChange and (isRead))
    println(isChange xor (isRead))
    println(1 and (3))
    var array = arrayOf(1, 2, 3, 4)
    val array1 = Array(3, { i -> (i * i).toString() })
    //intArrayOf(vararg elements: kotlin.Int): kotlin.IntArray
    // vararg表示content为可变参数
    val iArray: IntArray = intArrayOf(1, 2, 3)

    val sArray: Array<String> = Array<String>(3, { i -> i.toString() })

    val anyArray: Array<Any> = arrayOf(1, "2", 3.0, 4.1f) // 可将类型进行混排放入同一个数组中

    val lArray: LongArray = longArrayOf(1L, 2L, 3L)
    lArray[1]
    //escaped String: 普通的字符串，与Java中字符串一致
    //raw String: 元数据字符串，可以包括任意内容，使用三个双引号表示，在标识符中间的字符，都将是字符串的一部分
    val s = "kotlin is very interesting.\n"

    for (c in s)
        print(c)
    val s1 = """kotlin is very interesting.\n

  for(c in s1)
      print(c)
  """
    println(s1)

    var index = 1
    var s2 = "the index is $index" //s的值为 the index is 1

    //当字符串中使用对象时，可以使用${index.属性}进行嵌入
    data class User(var name: String)

    var user: User = User("jason")
    var description = "my name is ${user.name}"

    println(description)
    var people: People? = null
    people = People("1", "Code4Android")
    var people1 = com.learnrecord.People("2", "Code4Android")
    println(people.name)
    var aa: Int = 1
    var bb: Int = 3

    var valid = if (aa > bb) true else false //可达到三元运算符作用
    //若if后是多个表达式，则返回值是最后一个表达式：

    fun max1(a:Int,b:Int)= if (a > b) {
        println(a)
        a
    } else {
        println(b)
        //取消下面bb,运行会返回kotlin.Unit为无类型，返回值总是最后一个表达式或值
        b

    }
    println(max1(4,3))
}