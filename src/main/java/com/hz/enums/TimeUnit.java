package com.hz.enums;

/**
 * 时间单位
 */
public enum TimeUnit {
    s(0),Ms(1),Ns(2);
    private int unit;

    TimeUnit(int unit){
        this.unit = unit;
    }
}
