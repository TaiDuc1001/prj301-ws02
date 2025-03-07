/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducpt.registration;

import ducpt.database.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO-DUCKY
 */
public class RegistrationDAO {
    List<RegistrationDTO> listAccounts;

    public List<RegistrationDTO> getListAccounts() {
        return listAccounts;
    }
    
    public boolean insertRecord(String username, String password, String first_name, String last_name, boolean role) throws SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBUtils.makeConnection();
            if(con!= null){
                String sql = "Insert Into Users (username, password, first_name, last_name, isAdmin) "
                        + "Values(?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(3, password);
                stm.setString(4, first_name);
                stm.setString(5, last_name);
                stm.setBoolean(6, false);
                int row = stm.executeUpdate();
                if(row>0){
                    return true;
                }
            }
        }finally{
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return false;
    }
    
    public boolean addRecord(String username, String password, String firstname, String lastname, boolean role) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Users (username, password, first_name, last_name, role) VALUES (?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, firstname);
                stm.setString(4, lastname);
                stm.setBoolean(5, role);
                int row = stm.executeUpdate();
                return row > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    public boolean deleteRecord(String pk) throws SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBUtils.makeConnection();
            if(con!=null){
                String sql = "DELETE FROM users WHERE username=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, pk);
                int row = stm.executeUpdate();
                if(row>0){
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(stm!=null){
            stm.close();
        }
            if(con!=null){
            con.close();
        }
        }
        return false;
    }
    
    public boolean updateRecord(String password, String firstname, String lastname, Boolean isRole, String username) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "UPDATE users SET password=?, first_name=?, last_name=?, isAdmin=? WHERE username=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, firstname);
                stm.setString(3, lastname);
                stm.setBoolean(4, isRole); // Nếu `isAdmin` là boolean
                stm.setString(5, username);

                int row = stm.executeUpdate();
                return row > 0; // Nếu có ít nhất một dòng được cập nhật, trả về true
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public void searchLastname(String searchValue) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select username, first_name, password, last_name, isAdmin "
                        + "From users Where last_name Like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while(rs.next()){
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("last_name");
                    String firstname = rs.getString("first_name");
                    boolean role = rs.getBoolean("isAdmin");
                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, firstname, role);
                    if(listAccounts == null){
                        listAccounts = new ArrayList<>();
                    }
                    listAccounts.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    
    public boolean checkLogin(String username, String password){
        try {
            boolean result = false;
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            con = DBUtils.makeConnection();
            if (con != null){
                String sql = "SELECT username, password FROM users WHERE username = ? AND password = ?";
                try {
                    stm = con.prepareStatement(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    stm.setString(1, username);
                    stm.setString(2, password);
                    rs = stm.executeQuery();
                    if (rs.next())
                        result = true;
                } catch (SQLException ex) {
                    Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (con != null) con.close();
            
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
