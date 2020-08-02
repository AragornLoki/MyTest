package cn.myweb.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String realPath = this.getServletContext().getRealPath("/WEB-INF/classes/"+name);
        FileInputStream a=new FileInputStream(realPath);
//        response.setStatus(200);
        ServletContext context = this.getServletContext();
        String mimeType = context.getMimeType(name);
        response.setHeader("content-type",mimeType);
        System.out.println(mimeType);
        response.setHeader("content-disposition","attachment;filename="+name);
        System.out.println(name);
        ServletOutputStream sos = response.getOutputStream();
        byte[] buff = new byte[1024 * 8];
        int len = 0;
        while((len = a.read(buff)) != -1){
            sos.write(buff,0,len);
        }

        a.close();





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
