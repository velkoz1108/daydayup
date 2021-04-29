### javap命令

```
用法: javap <options> <classes>
其中, 可能的选项包括:
  -help  --help  -?        输出此用法消息
  -version                 版本信息
  -v  -verbose             输出附加信息
  -l                       输出行号和本地变量表
  -public                  仅显示公共类和成员
  -protected               显示受保护的/公共类和成员
  -package                 显示程序包/受保护的/公共类
                           和成员 (默认)
  -p  -private             显示所有类和成员
  -c                       对代码进行反汇编
  -s                       输出内部类型签名
  -sysinfo                 显示正在处理的类的
                           系统信息 (路径, 大小, 日期, MD5 散列)
  -constants               显示最终常量
  -classpath <path>        指定查找用户类文件的位置
  -cp <path>               指定查找用户类文件的位置
  -bootclasspath <path>    覆盖引导类文件的位置

```

javap -v StringDemo.class
```
 javap -v StringDemo.class 
Classfile /home/eden/IdeaProjects/daydayup/out/production/java-string/com/eden/StringDemo.class
  Last modified 2021-4-28; size 618 bytes
  MD5 checksum 37155f11877c9ada444aa4858efba39d
  Compiled from "StringDemo.java"
public class com.eden.StringDemo
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #9.#25         // java/lang/Object."<init>":()V
   #2 = String             #26            // abc
   #3 = Class              #27            // java/lang/StringBuilder
   #4 = Methodref          #3.#25         // java/lang/StringBuilder."<init>":()V
   #5 = Methodref          #3.#28         // java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
   #6 = String             #29            // d
   #7 = Methodref          #3.#30         // java/lang/StringBuilder.toString:()Ljava/lang/String;
   #8 = Class              #31            // com/eden/StringDemo
   #9 = Class              #32            // java/lang/Object
  #10 = Utf8               <init>
  #11 = Utf8               ()V
  #12 = Utf8               Code
  #13 = Utf8               LineNumberTable
  #14 = Utf8               LocalVariableTable
  #15 = Utf8               this
  #16 = Utf8               Lcom/eden/StringDemo;
  #17 = Utf8               main
  #18 = Utf8               ([Ljava/lang/String;)V
  #19 = Utf8               args
  #20 = Utf8               [Ljava/lang/String;
  #21 = Utf8               a
  #22 = Utf8               Ljava/lang/String;
  #23 = Utf8               SourceFile
  #24 = Utf8               StringDemo.java
  #25 = NameAndType        #10:#11        // "<init>":()V
  #26 = Utf8               abc
  #27 = Utf8               java/lang/StringBuilder
  #28 = NameAndType        #33:#34        // append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
  #29 = Utf8               d
  #30 = NameAndType        #35:#36        // toString:()Ljava/lang/String;
  #31 = Utf8               com/eden/StringDemo
  #32 = Utf8               java/lang/Object
  #33 = Utf8               append
  #34 = Utf8               (Ljava/lang/String;)Ljava/lang/StringBuilder;
  #35 = Utf8               toString
  #36 = Utf8               ()Ljava/lang/String;
{
  public com.eden.StringDemo();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 3: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lcom/eden/StringDemo;

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=1
         0: ldc           #2                  // String abc
         2: astore_1
         3: new           #3                  // class java/lang/StringBuilder
         6: dup
         7: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
        10: aload_1
        11: invokevirtual #5                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        14: ldc           #6                  // String d
        16: invokevirtual #5                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        19: invokevirtual #7                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        22: astore_1
        23: return
      LineNumberTable:
        line 5: 0
        line 6: 3
        line 7: 23
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      24     0  args   [Ljava/lang/String;
            3      21     1     a   Ljava/lang/String;
}
SourceFile: "StringDemo.java"

```
javap -c StringDemo.class 
```
Compiled from "StringDemo.java"
public class com.eden.StringDemo {
  public com.eden.StringDemo();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: ldc           #2                  // String abc
       2: astore_1
       3: new           #3                  // class java/lang/StringBuilder
       6: dup
       7: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
      10: aload_1
      11: invokevirtual #5                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      14: ldc           #6                  // String d
      16: invokevirtual #5                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      19: invokevirtual #7                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
      22: astore_1
      23: return
}
```
