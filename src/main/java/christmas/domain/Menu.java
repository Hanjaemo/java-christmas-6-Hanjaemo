package christmas.domain;

public enum Menu {

    MUSHROOM_SOUP("양송이 수프", 6_000, MenuCategory.APPETIZER), TAPAS("타파스", 5_500, MenuCategory.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, MenuCategory.APPETIZER),
    T_BONE_STEAK("티본스테이크", 55_000, MenuCategory.MAIN), BARBECUE_RIBS("바비큐립", 54_000, MenuCategory.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, MenuCategory.MAIN), CHRISTMAS_PASTA("크리스마스파스타", 25_000, MenuCategory.MAIN),
    CHOCOLATE_CAKE("초코케이크", 15_000, MenuCategory.DESSERT), ICE_CREAM("아이스크림", 5_000, MenuCategory.DESSERT),
    COKE_ZERO("제로콜라", 3_000, MenuCategory.BEVERAGE), RED_WINE("레드와인", 60_000, MenuCategory.BEVERAGE),
    CHAMPAGNE("샴페인", 25_000, MenuCategory.BEVERAGE);

    private final String name;
    private final int price;
    private final MenuCategory category;

    Menu(String name, int price, MenuCategory category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public boolean isMain() {
        return this.category == MenuCategory.MAIN;
    }

    public boolean isDessert() {
        return this.category == MenuCategory.DESSERT;
    }

    public int getPrice() {
        return price;
    }
}
