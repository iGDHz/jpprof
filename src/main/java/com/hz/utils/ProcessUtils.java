package com.hz.utils;

import java.io.File;

/**
 * 进程常用方法
 */
public class ProcessUtils {

    /**
     *
     * @return jps程序绝对路径
     */
    public static String findJps(){
        return ConfigUtils.JAVA_HOME+File.separator+"bin"+File.separator+"jps.exe";
    }
}
