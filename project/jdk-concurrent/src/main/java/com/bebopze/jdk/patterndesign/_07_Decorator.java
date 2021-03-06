package com.bebopze.jdk.patterndesign;

import java.io.*;

/**
 * 7. 装饰器模式
 *
 * @author bebopze
 * @date 2020/8/7
 */
public class _07_Decorator {


    // 核心：          增强 原功能      --->    跟原始类相关


    // Java IO 类 的 装饰器模式 应用


    // 1、相同父类：
    //      InputStream


    // 2、原始类：
    //      FileInputStream extends InputStream


    // 中间层：
    //      Java IO 设计了多个 装饰器类，所有这里在 装饰器类和原始类之间 多了个中间层：
    //
    //      FilterInputStream extends InputStream


    // 3、多个 装饰器类：
    //      BufferedInputStream  extends  FilterInputStream
    //      DataInputStream      extends  FilterInputStream


    // 1、装饰器类和原始类 继承 同样的父类（InputStream）  --->   可以对原始类“嵌套”多个装饰器类
    //
    // 2、装饰器类是 对功能的增强 （支持缓存读取、支持按照基本数据类型来读取数据）
    //                                                --->   是装饰器模式应用场景的一个重要特点


    //  对于为什么中间要多继承一个 FilterInputStream 类，我的理解是这样的：
    //
    //      假如说 BufferedInputStream 类直接继承自InputStream类且没有进行重写，只进行了装饰
    //      创建一个InputStream is = new BufferedInputStream(new FileInputStream(FilePath));
    //      此时调用is的没有重写方法(如read方法)时，调用的是InputStream类中的read方法，而不是FileInputStream中的read方法，
    //      这样的结果不是我们想要的，所以要将方法再包装一次。
    //
    //       从而有FilterInputStream类，也是避免代码的重复，多个装饰器只用写一遍包装代码即可。


    // ---------------------------------------------------------------


    public static void main(String[] args) throws IOException {

        test_1();
    }


    /**
     * 第一个比较特殊的地方是：装饰器类 和 原始类 继承同样的父类，这样我们可以对原始类“嵌套”多个装饰器类。
     *
     * @throws IOException
     */
    private static void test_1() throws IOException {


        String note_path = getNoteAbsolutePath();


        // 原始类   -->  FileInputStream
        InputStream in = new FileInputStream(note_path + "/text/设计模式.txt");


        // 第一个比较特殊的地方是：装饰器类 和 原始类 继承同样的父类，这样我们可以对原始类“嵌套”多个装饰器类。

        //      装饰器类1 BufferedInputStream / 装饰器类2 DataInputStream    和    原始类 FileInputStream
        //      -->  继承同样的父类  InputStream

        // 从而对原始类 “嵌套”了 多个装饰器类：装饰器类1（BufferedInputStream）、装饰器类2（DataInputStream）


        // 装饰器类1  -->  BufferedInputStream      增强后 支持 缓存读 功能
        InputStream bin = new BufferedInputStream(in);
        // 装饰器类2  -->  DataInputStream          增强后 支持 按照基本数据类型 功能
        DataInputStream din = new DataInputStream(bin);


        int data = din.readInt();
        System.out.println(data);
    }


    /**
     * 项目根目录 /note 的绝对路径
     *
     * @return
     */
    public static String getNoteAbsolutePath() {

        // 当前项目jdk-concurrent 的
        // .class类文件 root目录 绝对路径  -->  /target/classes      ===>   .class文件的根目录
        String path = Class.class.getClass().getResource("/").getPath();

        //  --->  .../note/project/jdk-concurrent/target/classes
        File file = new File(path);

        //  note
        File note = file.getParentFile().getParentFile().getParentFile().getParentFile();

        return note.getAbsolutePath();
    }
}
