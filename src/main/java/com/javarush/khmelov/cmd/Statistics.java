package com.javarush.khmelov.cmd;

import com.javarush.khmelov.service.StatService;
import jakarta.servlet.http.HttpServletRequest;

import static com.javarush.khmelov.util.Key.LIST_USER_STATISTICS;
import static com.javarush.khmelov.util.Key.TOTAL_USER_STATISTICS;

@SuppressWarnings("unused")
public class Statistics implements Command {

    private final StatService statService;

    public Statistics(StatService statService) {
        this.statService = statService;
    }

    @Override
    public String doGet(HttpServletRequest req) {
        req.setAttribute(LIST_USER_STATISTICS, statService.getUserStatistics());
        req.setAttribute(TOTAL_USER_STATISTICS, statService.getTotalUserStatistics());
        return getView();
    }
}
