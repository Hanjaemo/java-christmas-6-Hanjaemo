package christmas.domain;

import java.util.List;

public class SpecialDays {

    private final List<Integer> specialDays = List.of(3, 10, 17, 24, 25, 31);

    public boolean contains(int day) {
        return specialDays.contains(day);
    }
}
