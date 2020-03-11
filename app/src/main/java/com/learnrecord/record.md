
- @JvmStatic 与 @JvmField 标记接口的 companion 对象成员。1.3
```
interface Foo {
    companion object {
        @JvmField
        val answer: Int = 42

        @JvmStatic
        fun sayHello() {
            println("Hello, world!")
        }
    }
}
//相当于这段 Java 代码：


interface Foo {
    public static int answer = 42;
    public static void sayHello() {
        // ……
    }
}
```
- 内联类
1.3 引入了一种新的声明方式——inline class。内联类可以看作是普通类的受限版，尤其是内联类必须有且只有一个属性：
```
inline class Name(val s: String)
```

- 使用区间
```
val x = 10
val y = 9
if (x in 1..y+1) {
    println("fits in range")
}
for (i in 1..100) { …… }  // 闭区间：包含 100
for (i in 1 until 100) { …… } // 半开区间：不包含 100
//可以指定步长
for (x in 1..10 step 2) {
    print(x)
}
//可以倒序
for (x in 9 downTo 0 step 3) {
    print(x)
}
```
- 创建 DTOs（POJOs/POCOs）(data class )
```
所有属性的 getters （对于 var 定义的还有 setters）
equals()
hashCode()
toString()
copy()
所有属性的 component1()
```
- 基本类型
```
Byte	8	-128	127
Short	16	-32768	32767
Int	32	-2,147,483,648 (-231)	2,147,483,647 (231 - 1)
Long	64
```
注意: 不支持八进制
val oneMillion = 1_000_000
```
shl(bits) – 有符号左移
shr(bits) – 有符号右移
ushr(bits) – 无符号右移
and(bits) – 位与
or(bits) – 位或
xor(bits) – 位异或
inv() – 位非
```

- 标签
```
loop@ for (i in 1..100) {
    for (j in 1..100) {
        if (……) break@loop
    }
}
```

