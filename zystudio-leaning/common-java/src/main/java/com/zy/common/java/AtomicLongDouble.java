package com.zy.common.java;

/**
 * @author by zy.
 */
public class AtomicLongDouble implements Runnable{
    private static long field = 0;

    private volatile long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public AtomicLongDouble(long value) {
        this.setValue(value);
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 100000) {
            AtomicLongDouble.field = this.getValue();
            i++;
            long temp = AtomicLongDouble.field;
            if (temp != 1L && temp != -1L) {
                System.out.println("出现错误结果" + temp);
                System.exit(0);
            }
        }
        System.out.println("运行正确");
    }

    public static void main(String[] args) throws InterruptedException {
        // 获取并打印当前JVM是32位还是64位的
        String arch = System.getProperty("sun.arch.data.model");
        System.out.println(arch+"-bit");
        AtomicLongDouble t1 = new AtomicLongDouble(1);
        AtomicLongDouble t2 = new AtomicLongDouble(-1);
        Thread T1 = new Thread(t1);
        Thread T2 = new Thread(t2);
        T1.start();
        T2.start();
        T1.join();
        T2.join();


        String s0= "kvill";
        String s1=new String("kvill");
        String s2=new String("kvill");
        System.out.println( s0==s1 );
        s1.intern();
        s2=s2.intern(); //把常量池中"kvill"的引用赋给s2
        System.out.println( s0==s1);
        System.out.println( s0==s1.intern() );
        System.out.println( s0==s2 );


        /*String a = "ab";
        final String bb = "b";
        String b = "a" + bb;
        System.out.println((a == b)); //result = true*/


        String a = "ab";
        final String bb = getBB();
        String b = "a" + bb;
        System.out.println((a == b)); //result = false

    }
    private static String getBB() {
        return "b";
    }
}
