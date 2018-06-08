package com.fantasybaby.test.pool;

import com.fantasybaby.basic.threadpool.forkjoin.ExecutoServiceCalculatior;
import org.junit.Test;

import java.util.stream.LongStream;

public class ForkJoinTest {
    /**
     * 通过线程池来完成分治
     */
    @Test
    public void test1(){
        LongStream longStream = LongStream.rangeClosed(1, 100);
        long sum = new ExecutoServiceCalculatior().sumUp(longStream.toArray());
        System.out.println(sum);
    }
}