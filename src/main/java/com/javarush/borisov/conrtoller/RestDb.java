package com.javarush.borisov.conrtoller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.javarush.borisov.config.ClassCreator;
import com.javarush.borisov.db.Db;
import com.javarush.borisov.entity.Request;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/rest/db")
public class RestDb extends HttpServlet {
    Db db = ClassCreator.get(Db.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        if(req.getParameter("requestsToShow")!=null){
            List<Request> requestsToShow = db.requestsToShow;
            sendResponse(resp, requestsToShow);
        }









    }

    private static void sendResponse(HttpServletResponse resp, List<?> toShow) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String jsonArray="";
        StringBuilder jsonResp = new StringBuilder();
        jsonArray = mapper.writeValueAsString(toShow);
        jsonResp.append(jsonArray);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonArray);
    }


}
