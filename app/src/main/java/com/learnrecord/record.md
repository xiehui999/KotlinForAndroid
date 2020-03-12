
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
- 可见性修饰
```
在 Kotlin 中有这四个可见性修饰符：
private   它只会在声明它的文件内可见
protected 不适用于顶层声明。private一样 + 在子类中可见
internal  它会在相同模块内随处可见
public   （默认可见性）
```

- 密封类
虽然密封类也可以有子类，但是所有子类都必须在与密封类自身相同的文件中声明。（在 Kotlin 1.1 之前， 该规则更加严格：子类必须嵌套在密封类声明的内部）
1. 一个密封类是自身抽象的，它不能直接实例化并可以有抽象（abstract）成员。

2. 密封类不允许有非-private 构造函数（其构造函数默认为 private）。

3. 请注意，扩展密封类子类的类（间接继承者）可以放在任何位置，而无需在同一个文件中。

- 泛型
1. ? extends E 表示此方法接受 E 或者 E 的 一些子类型对象的集合，而不只是 E 自身。 这意味着我们可以安全地从其中（该集合中的元素是 E 的子类的实例）读取 E，
但不能写入，因为我们不知道什么对象符合那个未知的 E 的子类型。带 extends 限定（上界）的通配符类型使得类型是协变的（covariant）

2.逆变性（contravariance），并且对于 List <? super String> 你只能调用接受 String 作为参数的方法 （例如，你可以调用 add(String) 或者 set(int, String)），当然如果调用函数返回 List<T> 中的 T，你得到的并非一个 String 而是一个 Object

