/**
 *Created by xiehui on 2017/4/21.
 */
class TestUtli {
    /**
     * 注释1
     * 两种变量 val:只读变量，只能赋值一次
     * abstract修饰变量时，类也必须被abstract修饰
     */
    /*
    注释2
    //的注释可以嵌套
     */
    //注释3
    val a1: Int = 3//
    val a2 = 3//// 类型是被推断出来
    var a3: Int = 1

    /**
     * 通用模式
     */
    fun sum(a: Int, b: Int): Int {
        a3 += 2
        return a + b
    }

    /**
     * 达式作为函数体，编译器推断返回类型的函数：
     */
    fun sum1(a: Int, b: Int) = a + b

    /**
     * 无副作用函数：
     */
    fun printf(a: Int): Unit {
        print(a)
    }

    /**
     * Unit可省略
     */
    fun printf1(a: Int) {
        printf(a)
    }

    /**
     * 字符串模版有两种使用形式：$<变量>、${<变量>}
     */
    fun stringtemp(args :Array<String>){
        if (args.size==0)
            return
        print("First:${args[0]}")
    }
    fun max(a:Int,b:Int):Int{
        if (a>b){
            return a
        }else{
            return b
        }
    }
    fun max1(a:Int,b:Int)=if (a>b) a else b

    fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            //在这个分支，obj自动转换为String类型
            return obj.length
        }
        return null
    }
    fun getStringLength1(obj: Any): Int? {
        if (obj !is String) {
            return null
        }
        //在这个分支，obj自动转换为String类型
        return obj.length
    }
    fun getStringLength2(obj: Any): Int? {
        //**在&&右边obj自动转换为String类型**
        if (obj is String && obj.length > 0) {
            return obj.length
        }
        return null
    }
    fun forLoop(array: Array<String>) {
        for (str in array) {
            println(str)
        }
    }
    fun forIndices(array: Array<String>) {
        for (i in array.indices) {
            println(array[i])
        }
    }

    /**
     * While
     */
    fun whileLoop(array: Array<String>) {
        var i = 0
        while (i < array.size) {
            println(array[i++])
        }
    }

    /**
     * WHEN
     */
    fun whenExp(obj: Any) {
        when (obj) {
            1 -> println("One")
            "Hello,Kotlin" -> println("Hello,Kotlin")
            is Long -> println("Number is Long")
            !is String -> println("Not String")
            else -> println("Nothing")
        }
    }
    fun inOpr(x: Int, y: Int) {
        if (x in 1..y-1) {
            println("OK")
        }
    }
}