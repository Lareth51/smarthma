package pl.wasat.smarthma.helper;

/*
 * A simple class representing date and time.
 */

class SimpleDate {
    private final int year;
    private final int month;
    private final int day;
    private final int hours;
    private final int minutes;
    private final int seconds;

    public SimpleDate(String date) {
        //String str = date;
        String[] values = date.split("-");
        this.year = Integer.parseInt(values[0]);
        this.month = Integer.parseInt(values[1]);
        values = values[2].split("T");
        this.day = Integer.parseInt(values[0]);

        values = values[1].split(":");
        this.hours = Integer.parseInt(values[0]);
        this.minutes = Integer.parseInt(values[1]);
        this.seconds = Integer.parseInt(values[2].substring(0, 2));
    }

    public String toString() {
        return year + "Y " + month + "M " + day + "D " + hours + ":" + minutes + ":" + seconds;
    }

    public int compareTo(SimpleDate another) {
        if (year != another.year) {
            return year - another.year;
        } else if (month != another.month) {
            return month - another.month;
        } else if (day != another.day) {
            return day - another.day;
        } else if (hours != another.hours) {
            return hours - another.hours;
        } else if (minutes != another.minutes) {
            return minutes - another.minutes;
        } else if (seconds != another.seconds) {
            return seconds - another.seconds;
        }
        return 0;
    }
}
