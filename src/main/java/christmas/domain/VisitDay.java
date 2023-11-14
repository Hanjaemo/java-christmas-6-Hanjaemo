package christmas.domain;

import christmas.error.ErrorMessage;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class VisitDay {

    private static final int MIN_VISIT_DAY = 1;
    private static final int MAX_VISIT_DAY = 31;
    private static final int EVENT_YEAR = 2023;
    private static final int EVENT_MONTH = 12;

    private final int visitDay;

    public VisitDay(int visitDay) {
        validate(visitDay);
        this.visitDay = visitDay;
    }

    private void validate(int visitDay) {
        if (invalidDay(visitDay)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_VISIT_DAY.getMessage());
        }
    }

    private boolean invalidDay(int visitDay) {
        return visitDay < MIN_VISIT_DAY || visitDay > MAX_VISIT_DAY;
    }

    public boolean isNotWithinChristmasPeriod(int christmasDDay) {
        return visitDay < MIN_VISIT_DAY || visitDay > christmasDDay;
    }

    public int decreaseOneDay() {
        return visitDay - 1;
    }

    public boolean isWeekend() {
        DayOfWeek visitDayOfWeek = LocalDate.of(EVENT_YEAR, EVENT_MONTH, visitDay).getDayOfWeek();
        return visitDayOfWeek == DayOfWeek.FRIDAY || visitDayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isNotSpecialDay(List<Integer> specialDays) {
        return !specialDays.contains(visitDay);
    }

    public int getVisitDay() {
        return visitDay;
    }
}
