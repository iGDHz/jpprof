package com.hz.utils;

import com.hz.enums.TimeUnit;
import com.hz.excaptions.TimeUnitNotFoundException;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Jpprof 计时器
 *
 * @author Hz
 * @version 2022.11
 */
public class JpprofTimer {
    private long time;

    public JpprofTimer() throws TimeUnitNotFoundException {
        this.time = TimeTranslate(ConfigUtils.timeUnit);
    }

    /**
     *
     * @return 运行时间
     * @throws TimeUnitNotFoundException
     */
    public long duration() throws TimeUnitNotFoundException {
        return TimeTranslate(ConfigUtils.timeUnit)-time;
    }

    /**
     *
     * @param test 无参方法
     * @return 方法运行时间
     * @throws TimeUnitNotFoundException
     */
    public static long duration(Runnable test) throws TimeUnitNotFoundException {
        JpprofTimer timer = new JpprofTimer();
        test.run();
        return timer.duration();
    }

    /**
     *
     * @param test 一个参数无返回值方法
     * @param param 参数
     * @return
     * @throws TimeUnitNotFoundException
     */
    public static long duration(Consumer test,Object param) throws TimeUnitNotFoundException {
        JpprofTimer timer = new JpprofTimer();
        test.accept(param);
        return timer.duration();
    }

    /**
     *
     * @param test 两个参数无返回值方法
     * @param param1,param2 参数
     * @return
     * @throws TimeUnitNotFoundException
     */
    public static long duration(BiConsumer test, Object param1,Object param2) throws TimeUnitNotFoundException {
        JpprofTimer timer = new JpprofTimer();
        test.accept(param1,param2);
        return timer.duration();
    }
    /**
     *
     * @param test 一个参数有返回值方法
     * @param param 参数
     * @return
     * @throws TimeUnitNotFoundException
     */
    public static long duration(Function test, Object param) throws TimeUnitNotFoundException {
        JpprofTimer timer = new JpprofTimer();
        test.apply(param);
        return timer.duration();
    }

    /**
     *
     * @param test 两个参数有返回值方法
     * @param param1,param2 参数
     * @return
     * @throws TimeUnitNotFoundException
     */
    public static long duration(BiFunction test, Object param1, Object param2) throws TimeUnitNotFoundException {
        JpprofTimer timer = new JpprofTimer();
        test.apply(param1,param2);
        return timer.duration();
    }

    /**
     *
     * 获根据时间单位取当前时间戳
     * @param unit 时间单位(s,ms,ns)
     * @return 时间戳
     * @throws TimeUnitNotFoundException
     */
    public long TimeTranslate(TimeUnit unit) throws TimeUnitNotFoundException {
        switch (unit){
            case s -> {
                return System.currentTimeMillis();
            }
            case Ms -> {
                return System.currentTimeMillis();
            }
            case Ns -> {
                return System.nanoTime();
            }
            default ->
                    throw new TimeUnitNotFoundException();
        }
    }
}
