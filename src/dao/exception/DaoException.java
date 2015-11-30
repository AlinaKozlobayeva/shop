package dao.exception;

/**
 * Created by Alina on 03.11.2015.
 */
public class DaoException extends Exception {

    public DaoException (String message){
        super(message);
    }

    public DaoException(String message, Throwable cause){
        super(message, cause);
    }
}
