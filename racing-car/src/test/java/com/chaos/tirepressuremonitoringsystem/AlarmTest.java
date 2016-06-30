package com.chaos.tirepressuremonitoringsystem;

import org.junit.*;
import static org.junit.Assert.*;

public class AlarmTest {

    @Test
    public void foo() {
        Alarm alarm = new Alarm();
        assertEquals(false, alarm.isAlarmOn());
    }
}