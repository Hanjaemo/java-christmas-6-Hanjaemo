package christmas.controller;

import christmas.domain.VisitDay;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {

    public void run() {
        OutputView.printGreetingMessage();
        VisitDay visitDay = new VisitDay(InputView.readVisitDay());
    }
}
