package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class VisitDay {

    private final int visitDay;

    public VisitDay(int visitDay) {
        this.visitDay = visitDay;
    }

    public boolean isNotWithinChristmasPeriod() {
        return visitDay < 1 || visitDay > 25;
    }

    public int decreaseOneDay() {
        return visitDay - 1;
    }

    public boolean isWeekend() {
        DayOfWeek visitDayOfWeek = LocalDate.of(2023, 12, visitDay).getDayOfWeek();
        return visitDayOfWeek == DayOfWeek.FRIDAY || visitDayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isNotSpecialDay(List<Integer> specialDays) {
        return !specialDays.contains(visitDay);
    }
}
