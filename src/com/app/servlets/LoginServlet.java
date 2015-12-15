package com.app.servlets;

import dao.DaoFactory;
import dao.impl.UserDaoImpl;
import entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Alina on 02.08.2015.
 */
public class LoginServlet extends HttpServlet {

    public static final String PARAM_LOGIN = "login";
    public static final String PARAM_PASSWORD = "password";


    public static final String PAGE_OK = "pages/user.jsp";
    public static final String PAGE_ADMIN = "pages/adminPage.jsp";
    public static final String PAGE_ERROR = "error.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(PARAM_LOGIN);
        String password = req.getParameter(PARAM_PASSWORD);
        log("Login=" + login + ":: password=" + password);

        UserDaoImpl userDao = DaoFactory.getUserDao();
        PrintWriter out = resp.getWriter();
//        if (login == null || password == null) {
//            req.getRequestDispatcher(PAGE_ERROR).forward(req, resp);
//            return;
//        }


        if (userDao.findUserByLogin(login).getPassword().equals(password)) {
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);

        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher(PAGE_ERROR);
            out.println("Username or Password incorrect");
            rd.include(req, resp);

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
