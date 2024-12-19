package com.javarush.borisov.cmd;

import com.javarush.borisov.config.ClassCreator;
import com.javarush.borisov.db.Db;
import com.javarush.borisov.entity.Contragent;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class RequestsList implements Command {
    private final Db db = ClassCreator.get(Db.class);

    private final String contragentBarBtnDatesTop = """
                  <section>
                  <nav class="navbar navbar-expand-md bg-body" style="position: fixed;width: 100%;">
            <div class="container-fluid">
                 <div class="dropdown">
                               <button class="btn btn-primary dropdown-toggle" type="button" id="mainDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                                 Выбор Даты
                               </button>
                               <ul class="dropdown-menu" aria-labelledby="mainDropdown">
            """;
    private String contragentBarBtnDatesData;
    private final String contragentBarBtnDatesLow = """
            </div><button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-1"><span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
             <div class="collapse navbar-collapse" id="navcol-1">
                           <ul class="navbar-nav">
            """;
    private String contragentBarTop;
    private String getContragentBarData;

    private final String tableTop = """
                           </ul>
                        </div>
                    </div>
                </nav>
            </section>
            <section>
                <div class="table-responsive" style="margin-top: 78.375px;border-top-style: ridge;">
                    <table class="table">
                        <thead style="/*position: fixed;*/width: 100%;border-top-style: ridge;">
                        <tr>
            """;
    private String tableHeadData = """
                <th style="border-width: 3px;border-style: outset;border-bottom-width: 5px;">Column 1</th>
                <th style="border-width: 3px;border-style: outset;border-bottom-width: 5px;">Column 2</th>
            """;

    private String tableData = """
                    <tbody style="border-top-style: ridge;">
                    <tr style="border-top-style: ridge;">
                        <td style="border-width: 3px;border-style: outset;">Cell 1</td>
                        <td style="border-width: 3px;border-style: outset;">Cell 2</td>
                    </tr>
                    <tr>
                        <td style="border-width: 3px;border-style: outset;">Cell 3</td>
                        <td style="border-width: 3px;border-style: outset;">Cell 4</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            """;
    private final String tableLow = """
              </tr>
                        </thead>
              </tbody>
                                </table>
                            </div>
            </section>
            """;


    @Override
    public String doGet(HttpServletRequest req) {
        if (contragentBarBtnDatesData == null) {                                                                          //вычисление годов/месяцев с заявками
            contragentBarBtnDatesData = getReqByDate();
        }
        if (contragentBarTop == null) {                                                                                  //наполнение дропбокаса годами/месяцами
            contragentBarTop = contragentBarBtnDatesTop + contragentBarBtnDatesData + contragentBarBtnDatesLow;
        }
        if (getContragentBarData == null) {                                                                             //наполнение навБара контрагентами
            getContragentBarData = ContragentNavBarFilling();
        }


        req.setAttribute("contragentBarTop", contragentBarTop);
        req.setAttribute("getContragentBarData", getContragentBarData);
        req.setAttribute("tableTop", tableTop);
        req.setAttribute("tableHeadData", tableHeadData);
        req.setAttribute("tableData", tableData);
        req.setAttribute("tableLow", tableLow);
        return getView();


    }

    private String getReqByDate() {

        StringBuilder result = new StringBuilder();
        Map<String, List<String>> allDatesWeGot = db.allDatesWeGot();
        for (Map.Entry<String, List<String>> stringListEntry : allDatesWeGot.entrySet()) {
            result.append("<li class=\"dropdown\"><a class=\"dropdown-item dropdown-toggle\" href=\"#\">")
                    .append(stringListEntry.getKey())
                    .append("</a><ul class=\"dropdown-menu\">");
            for (String month : stringListEntry.getValue()) {
                result.append("<li><a class=\"dropdown-item\" href=\"#\">")
                        .append(month)
                        .append("</a></li>");
            }
            result.append("</ul>\n</li>");
        }
        result.append(" </ul>");

        return result.toString();
    }

    private String ContragentNavBarFilling() {
        StringBuilder result = new StringBuilder();
        List<Contragent> contragents = db.getContragents();
        for (Contragent contragent : contragents) {
            result.append("<li class=\"nav-item\"><a class=\"nav-link\" href=\"#\">").
                    append(contragent.name).
                    append("</a></li>");
        }
        return result.toString();
    }
}