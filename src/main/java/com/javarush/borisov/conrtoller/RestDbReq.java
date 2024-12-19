package com.javarush.borisov.conrtoller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.javarush.borisov.config.ClassCreator;
import com.javarush.borisov.db.Db;
import com.javarush.borisov.entity.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/rest/db")
public class RestDbReq extends HttpServlet {
    Db db = ClassCreator.get(Db.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<Request> requestsToShow = db.requestsToShow;
        StringBuilder jsonResp = new StringBuilder();
        String jsonArray="";
        if(req.getParameter("requestsToShow")!=null){

             jsonArray = mapper.writeValueAsString(requestsToShow);


            jsonResp.append(jsonArray);

       }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonArray);
    }
}
