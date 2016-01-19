package com.app.servlets;

import dao.DaoFactory;
import dao.impl.ProductDaoImpl;
import entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * Created by Alina on 11.12.2015.
 */
@MultipartConfig
public class UploadServlet extends HttpServlet {

   private static final String UPLOAD_LOCATION = "../downloads_image";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello");


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("productName");
        String productPrice = req.getParameter("price");
        String productManufacture = req.getParameter("manufacturer");


        Part filePart = req.getPart("fileImg");
        String fileName = getSubmittedFileName(filePart);
        InputStream fileContent = filePart.getInputStream();
        File uploadFile = new File(new File("d:/Soft/downloads_image"), fileName);

        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, uploadFile.toPath());
        }

        ProductDaoImpl productDao = DaoFactory.getProductDao();
        productDao.create(new Product(productName,Double.parseDouble(productPrice), fileName));


    }

    private static String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
}
