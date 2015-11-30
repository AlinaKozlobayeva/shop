package dao;

import dao.impl.ProductDaoImpl;
import dao.impl.UserDaoImpl;

/**
 * Created by Alina on 02.11.2015.
 */
public class DaoFactory {

    public static UserDaoImpl getUserDao(){
        return new UserDaoImpl();
    }

    public static ProductDaoImpl getProductDao(){
            return new ProductDaoImpl();

    }

}