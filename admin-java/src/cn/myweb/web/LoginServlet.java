package cn.myweb.web;

import cn.myweb.sql.User;
import com.fasterxml.jackson.databind.ObjectMapper;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@WebServlet("/admin/auth/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        StringBuffer jb = new StringBuffer();
        String line = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
            User user = mapper.readValue(jb.toString(), User.class);
            System.out.println(user+"1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setStatus(200);
//        response.setContentType("text/html;charset=utf-8");
        response.addHeader("errmsg","密码错误");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(302);
        response.sendRedirect("http://localhost:8081/");
    }
}
