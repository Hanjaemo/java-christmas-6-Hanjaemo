package christmas;

import christmas.controller.Controller;
import christmas.service.Service;

public class Application {
    public static void main(String[] args) {
        Controller controller = new Controller(new Service());
        controller.run();
    }
}
