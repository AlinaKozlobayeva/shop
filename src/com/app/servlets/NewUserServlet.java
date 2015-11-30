package com.app.servlets;

import dao.DaoFactory;
import dao.impl.UserDaoImpl;
import entity.User;
import entity.UserFields;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alina on 06.11.2015.
 */
public class NewUserServlet extends HttpServlet {

   ;
    public static final String ATTRIBUTE_MODEL_TO_VIEW = "user";

    public static final String PAGE_OK = "index.jsp";



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter(UserFields.FIRSTNAME);
        String lastName = req.getParameter(UserFields.LASTNAME);
        Integer age = Integer.parseInt(req.getParameter(UserFields.AGE));
        String email = req.getParameter(UserFields.EMAIL);
        String login = req.getParameter(UserFields.LOGIN);
        String password = req.getParameter(UserFields.PASSWORD);

        UserDaoImpl userDao = DaoFactory.getUserDao();
        userDao.create(new User(firstName, lastName, age, email, login, password));
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
