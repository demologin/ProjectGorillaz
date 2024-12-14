package com.javarush.borisov.cmd;

import com.javarush.borisov.config.ClassCreator;
import com.javarush.borisov.db.Db;
import com.javarush.borisov.entity.Contragent;
import com.javarush.borisov.util.EntityCreator;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;


public class RequestsList implements Command {
    private Db db = ClassCreator.get(Db.class);
    String contragentBarTop = """
              <nav class="navbar navbar-expand-md bg-body" style="position: fixed;width: 100%;">
                    <div class="container-fluid"><a class="navbar-brand" href="#">Brand</a><button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-1"><span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                        <div class="collapse navbar-collapse" id="navcol-1">
                            <ul class="navbar-nav">
                                
            """;
    String getContragentBarData;

    String tableTop = """
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
    String tableHeadData = """
                                    
                <th style="border-width: 3px;border-style: outset;border-bottom-width: 5px;">Column 1</th>
                <th style="border-width: 3px;border-style: outset;border-bottom-width: 5px;">Column 2</th>
                                    
            """;

    String tableData = """
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
    String tableLow = """
              </tr>
                        </thead>
              </tbody>
                                </table>
                            </div>
            """;


    @Override
    public String doGet(HttpServletRequest req) {
        EntityCreator entityCreator = ClassCreator.get(EntityCreator.class);

        db.setContragents(entityCreator.create("contragents.json", Contragent.class));


        getContragentBarData = ContragentNavBarFilling();  //наполнение навБара контрагентами

        req.setAttribute("contragentBarTop", contragentBarTop);
        req.setAttribute("getContragentBarData",getContragentBarData);
        req.setAttribute("tableTop", tableTop);
        req.setAttribute("tableHeadData", tableHeadData);
        req.setAttribute("tableData", tableData);
        req.setAttribute("tableLow", tableLow);
        return getView();


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
