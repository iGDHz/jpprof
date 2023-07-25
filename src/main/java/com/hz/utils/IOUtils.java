package com.hz.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * IO常用方法
 */
public class IOUtils {
    public static void close(Closeable closeable) throws IOException {
        closeable.close();
    }
}
