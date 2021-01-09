package com.blank.controller;

import com.blank.dao.CartDao;
import com.blank.dao.OrderDao;
import com.blank.dao.ProductDao;
import com.blank.domain.Cart;
import com.blank.domain.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubmitOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = null;
        CartDao cartDao = new CartDao();
        ProductDao productDao = new ProductDao();
        OrderDao orderDao = new OrderDao();
        request.setCharacterEncoding("utf-8");
        Object uid = request.getSession().getAttribute("userId");
        String total = request.getParameter("total");
        String address = request.getParameter("address");
        String username = request.getParameter("user");
        String phone = request.getParameter("phone");
        //����ʱ��
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy��/MM��/dd�� HHʱ:mm��:ss��");
        String time = sf.format(date);
        String state = "δ֧��";
        System.out.println(time);
        order = new Order(null,Double.valueOf(total),address,username,phone,time,(Integer)uid,state);
        int flag = orderDao.insertOrder(order);
        List<Cart> cartList = new ArrayList<>();
        cartList= cartDao.queryCart((Integer) uid);
        //������Ʒ�Ŀ��������Լ�ɾ�����ﳵ�еĲ�Ʒ
        for (Cart cart:cartList){
            int rs = productDao.updateProducts(cart.getPid(),cart.getPnum());
            int rse = cartDao.deleteProduct(cart.getPid());
        }
        if (flag!=0){
            response.sendRedirect(request.getContextPath()+"/html/order.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
