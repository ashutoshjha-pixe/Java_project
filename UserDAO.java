package com.healthcare.dao;
import com.healthcare.model.User;
import com.healthcare.util.DBConnection;
import java.sql.*;

public class UserDAO {
    public User login(String username,String password) throws SQLException {
        String sql="SELECT * FROM users WHERE username=? AND password=? AND active=TRUE";
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(sql)) {
            p.setString(1,username); p.setString(2,password);
            try(ResultSet r=p.executeQuery()) {
                if(r.next()) return new User(r.getInt("user_id"),r.getString("username"),r.getString("password"),r.getString("role"));
            }
        }
        return null;
    }
}
