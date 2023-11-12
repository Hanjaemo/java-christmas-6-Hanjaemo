package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class VisitDay {

    private final int visitDay;

    public VisitDay(int visitDay) {
        validate(visitDay);
        this.visitDay = visitDay;
    }

    private void validate(int visitDay) {
        if (invalidDay(visitDay)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private boolean invalidDay(int visitDay) {
        return visitDay < 1 || visitDay > 31;
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

    public int getVisitDay() {
        return visitDay;
    }
}
