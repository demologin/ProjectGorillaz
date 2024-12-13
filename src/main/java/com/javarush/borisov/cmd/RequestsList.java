package com.javarush.borisov.cmd;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet ("/requests-list")
public class RequestsList extends HttpServlet {
    String table = """
            <div class="table-responsive" style="margin-top: 78.375px;border-top-style: ridge;">
                <table class="table">
                    <thead style="/*position: fixed;*/width: 100%;border-top-style: ridge;">
                    <tr>
                        <th style="border-width: 3px;border-style: outset;border-bottom-width: 5px;">Column 1</th>
                        <th style="border-width: 3px;border-style: outset;border-bottom-width: 5px;">Column 2</th>
                    </tr>
                    </thead>
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
            </div>""";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


            //req.setAttribute("tbl",table);
            req.getRequestDispatcher("/WEB-INF/requests-list.jsp").forward(req, resp);


    }
}
