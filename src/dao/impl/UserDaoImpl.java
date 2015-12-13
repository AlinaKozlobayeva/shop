package dao.impl;

import dao.UserDao;
import entity.User;
import entity.UserFields;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Alina on 02.11.2015.
 */
public class UserDaoImpl implements UserDao {

    private final String URL;
    private final String DB_PROPERTIES = "dao/db_prop.txt";

    private final String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE " + UserFields.LOGIN + "=?";
    private final String SQL_INSERT_USER = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?,?,?,?)";
    private final String SQL_SELECT_USER_BY_ID = "SELECT * FROM users WHERE " + UserFields.USER_ID + "=?";
    private static final String SQL_UPDATE_USER = "UPDATE users SET " + UserFields.FIRSTNAME + "=?," + UserFields.LASTNAME + "=?,"
            + UserFields.EMAIL + "=?," + UserFields.AGE + "=? WHERE " + UserFields.USER_ID + "=?";
    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE " + UserFields.LOGIN + "=?";



    public UserDaoImpl() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Properties props = new Properties();

        // this way we obtain db.properties file from the CLASSPATH
        InputStream in;
        try {
            in = getClass().getClassLoader().getResourceAsStream(DB_PROPERTIES);
            if (in == null) {
                throw new IllegalStateException(
                        "File must be resides in classpath: " + DB_PROPERTIES);
            }
            props.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        URL = props.getProperty("connection.url");
        if (URL == null) {
            throw new IllegalStateException(
                    "You have to define connection.url property in " + DB_PROPERTIES);
        }
    }


    @Override
    public User findUserByLogin(String login) {
        Connection con = null;
        User res = null;
        try {
            con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                res = extractUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                close(con);
            }
        }

        return res;
    }

    @Override
    public boolean insertUser(User user) {
        boolean res = false;
        Connection con = null;
        int k = 1;
        try {
            con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(k++, user.getFirstName());
            pstmt.setString(k++, user.getLastName());
            pstmt.setInt(k++, user.getAge());
            pstmt.setString(k++, user.getEmail());
            pstmt.setString(k++, user.getLogin());
            pstmt.setString(k++, user.getPassword());

            int count = pstmt.executeUpdate();
            if (count > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                res = true;
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            System.out.println("Cannot insert the user: " + user);
            e.printStackTrace();
        } finally {
            if (con != null) {
                close(con);
            }

        }

        return res;
    }

    @Override
    public User findById(int id) {
        Connection con = null;
        User res = null;
        try {
            con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQL_SELECT_USER_BY_ID);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                res = extractUser(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                close(con);
            }
        }

        return res;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQL_SELECT_ALL_USERS);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                users.add(extractUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean create(User user) {
        boolean res = false;
        Connection con = null;
        int k = 1;
        try {
            con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(k++, user.getFirstName());
            pstmt.setString(k++, user.getLastName());
            pstmt.setInt(k++, user.getAge());
            pstmt.setString(k++, user.getEmail());
            pstmt.setString(k++, user.getLogin());
            pstmt.setString(k++, user.getPassword());

            int count = pstmt.executeUpdate();
            if (count > 0) {
                res = true;
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Cannot insert the user: " + user);
            e.printStackTrace();
        } finally {
            if (con != null) {
                close(con);
            }
        }
        return res;
    }

    @Override
    public boolean update(User user) {
        Connection con = null;

        boolean res = false;
        int k = 1;
        try {
            con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_USER);
            pstmt.setString(k++, user.getFirstName());
            pstmt.setString(k++, user.getLastName());
            pstmt.setInt(k++, user.getAge());
            pstmt.setString(k++, user.getEmail());
            pstmt.setString(k++, user.getLogin());
            pstmt.setString(k++, user.getPassword());
            int count = pstmt.executeUpdate();
            if (count > 0) {
                res = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                close(con);
            }
        }
        return res;
    }

    @Override
    public boolean delete(String login) {
        boolean result = false;
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_USER);
            // adjust a prepared statement
            int k = 1;
            pstmt.setString(k++, login);
            int row = pstmt.executeUpdate();
            if(row > 0){
                result = true;
                System.out.println("Delete successful");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con);
        }

        return result;
    }

    private Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
        Connection con = DriverManager.getConnection(URL);
        return con;
    }


    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User(rs.getString(UserFields.FIRSTNAME), rs.getString(UserFields.LASTNAME), rs.getInt(UserFields.AGE),
                rs.getString(UserFields.EMAIL), rs.getString(UserFields.LOGIN), rs.getString(UserFields.PASSWORD));
        user.setId(rs.getInt(UserFields.USER_ID));
        return user;
    }

    private void close(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.out.println("Cannot close a connection");
            ex.printStackTrace();
        }
    }

}
