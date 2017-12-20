package com.aries.wawafans.Bean;

/**
 * Created by kyly on 2017/12/15.
 */

public enum Command {
    MOVE_LEFT((char) 0x03),
    MOVE_RIGHT((char) 0x04),
    MOVE_FRONT((char) 0x01),
    MOVE_BACK((char) 0x02),
    STOP_MOVE((char) 0x05),
    START_GRAB((char) 0x06);

    private char value;

    Command(char value) {
        this.value = value;
    }

    public char rawValue() {
        return this.value;
    }
}
