package com.blank.controller;

import com.blank.dao.CartDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("id");
        CartDao cartDao = new CartDao();
        int rs = 0;
        rs = cartDao.deleteProduct(Integer.parseInt(pid));
        if (rs==1){
            response.sendRedirect(request.getContextPath()+"/html/car.html");
        }
    }
}
