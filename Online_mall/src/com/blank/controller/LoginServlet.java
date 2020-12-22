package com.blank.controller;

import com.blank.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author blank
 * @date 2020/8/29 14:58
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName,password;
        int result = 0;
        UserDao dao = new UserDao();
//        �����ַ���
        request.setCharacterEncoding("utf-8");
//        ��ȡ������ύ�Ĳ���
        userName = request.getParameter("userName");
        password = request.getParameter("password");
        HttpSession session = request.getSession();
//       ��֤��¼��Ϣ
        result = dao.login(userName,password);
//        ��¼��Ϣ������ȷ
        if ( result == 1){
            //        ����¼������session��
            session.setAttribute("userName",userName);
            //�ض���
            response.sendRedirect(request.getContextPath()+"/html/welcome.html");
        }else {
            response.sendRedirect(request.getContextPath()+"/html/login_error.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
