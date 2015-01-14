package com.joantolos.spring.mvc.backend.dao;

import com.joantolos.spring.mvc.common.exception.DAOException;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;

/**
 *  
 * Created by jtolos on 14/01/2015.
 */
public abstract class BaseDAO {
    
    public Connection con = null;

    @Value("${db.url}")
    private String url = "jdbc:mysql://localhost:3306/";
    @Value("${db.schema}")
    private String schema = "webapp_db_wc";
    @Value("${db.driver}")
    private String driver = "com.mysql.jdbc.Driver";
    @Value("${db.user}")
    private String user = "root";
    @Value("${db.password}")
    private String password = "1234";
    
    public BaseDAO() throws DAOException {
        try {
            if(this.con == null) {
                Class.forName(this.driver).newInstance();
                this.con = DriverManager.getConnection(url + schema, user, password);
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    /**
     *
     * @param preparedStatement
     * @param resultSet
     */
    public void closeAll(PreparedStatement preparedStatement, ResultSet resultSet) throws DAOException {
        try {
            if(preparedStatement!=null){
                preparedStatement.close();
            }
            if(resultSet!=null){
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }
}