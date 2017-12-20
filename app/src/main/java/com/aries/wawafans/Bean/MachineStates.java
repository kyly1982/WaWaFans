package com.aries.wawafans.Bean;

/**
 * Created by kyly on 2017/11/27.
 * 机器状态
 */

public enum MachineStates {

    OFFLINE((short) 0),
    READY((short) 1),
    BUSY((short) 2);

    private short value = 0;

    private MachineStates(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }
}
