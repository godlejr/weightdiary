package dongjoo.second.weightdiary.common.entity;

import java.io.Serializable;

public class User extends Base implements Serializable {

    private String startDays; //"yyyy-MM-dd"
    private int year;
    private int month;
    private int day;
    private String startHours;
    private int hour;
    private int minute;
    private int meanOfSmoking;
    private int costOfSmoking;

    public User() {
    }

    public String getStartDays() {
        return startDays;
    }

    public void setStartDays(String startDay) {
        this.startDays = startDay;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getStartHours() {
        return startHours;
    }

    public void setStartHours(String startHours) {
        this.startHours = startHours;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getMeanOfSmoking() {
        return meanOfSmoking;
    }

    public void setMeanOfSmoking(int meanOfSmoking) {
        this.meanOfSmoking = meanOfSmoking;
    }

    public int getCostOfSmoking() {
        return costOfSmoking;
    }

    public void setCostOfSmoking(int costOfSmoking) {
        this.costOfSmoking = costOfSmoking;
    }
}
