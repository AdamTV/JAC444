/*
 * Workshop # 10
 * Course: JAC444 - Fall 2020
 * Last Name: Stinziani
 * First Name: Adam
 * ID: 124521188
 * Section: NDD
 * This assignment represents my own work in accordance with Seneca Academic Policy.
 * Signature: Adam Stinziani
 * Date: 2020-12-07
 */

package ca.sict.adamstinziani;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

// Encapsulates time.
public class Time implements Cloneable, Comparable<Time> {
    long secsSinceEpoch;
    int hour, minute, second;

    // Current time.
    @SuppressWarnings("unused")
    Time() {
        LocalDateTime localNow = LocalDateTime.now(TimeZone.getTimeZone(TimeZone.getDefault().getID()).toZoneId());
        secsSinceEpoch = localNow.atZone(ZoneId.systemDefault()).toEpochSecond();
        hour = localNow.getHour();
        minute = localNow.getMinute();
        second = localNow.getSecond();
    }

    // Time object created with hour, minute, and second parameters.
    Time(int hour, int minute, int second) {
        this.hour = (hour % 24) + (minute / 60);
        this.minute = (minute % 60) + (second / 60);
        this.second = second % 60;
        secsSinceEpoch = (long) hour * 60 * 60 + minute * 60L + second;
    }

    // Time object created with seconds parameter.
    Time(long secsSinceEpoch) {
        this.secsSinceEpoch = secsSinceEpoch;
        hour = (int) (secsSinceEpoch / 60 / 60 % 24);
        minute = (int) (secsSinceEpoch / 60) % 60;
        second = (int) (secsSinceEpoch % 60);
    }

    // Get hour.
    int getHour() {
        return hour;
    }

    // Get minute.
    int getMinute() {
        return minute;
    }

    // Get second.
    public int getSecond() {
        return second;
    }

    // Get seconds.
    public long getSeconds() {
        return secsSinceEpoch;
    }

    // Override of Cloneable.clone function
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Override of Object.toString function
    @Override
    public String toString() {
        return String.format("%s hour%s, %s minute%s, %s second%s", getHour(), getHour() > 1 ? "s" : "", getMinute(),
                getMinute() > 1 ? "s" : "", getSecond(), getSecond() > 1 ? "s" : "");
    }

    // Override of Comparable<Time>.compareTo function
    @Override
    public int compareTo(Time o) {
        return Math.toIntExact(getSeconds() - o.getSeconds());
    }
}
