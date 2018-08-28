package com.dao;

import com.dto.SysUser;
import com.utils.MySQLUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class SysUserDao {

    private static MySQLUtils mysqlUtils = null;

    public String getName(String name) {
        return "name:" + name;
    }

    public static String getByName(String userName) {
        mysqlUtils = new MySQLUtils();
        Statement statement = mysqlUtils.linkMySQL();
        String sql = "select * from sys_user WHERE USER_NAME='" + userName + "'";
        String str = "";
        String id = "";
        String name = "";
        String email = "";
        String phone = "";
        try {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                id = rs.getString("USER_ID");
                name = rs.getString("USER_NAME");
                email = rs.getString("EMAIL");
                phone = rs.getString("PHONE");
                str += id + "\t" + name + "\t" + email + "\t" + phone;
                System.out.println(id + "\t" + name + "\t" + email + "\t" + phone);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            System.err.println("TableInfoService get出错");
            System.err.println(e.getMessage());
        }
        mysqlUtils.closeMySQL(statement);
        return str;
    }

    public static List getAll() {
        mysqlUtils = new MySQLUtils();
        SysUser sysUser = null;
        Statement statement = mysqlUtils.linkMySQL();
        String sql = "select * from sys_user";
        List list = new ArrayList();
        try {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                sysUser = new SysUser();
                sysUser.setUserId(rs.getString(1));
                sysUser.setUserType(rs.getString(2));
                sysUser.setUserName(rs.getString(3));
                sysUser.setPassword(rs.getString(4));
                sysUser.setEmail(rs.getString(5));
                sysUser.setPhone(rs.getString(6));
                sysUser.setStartActiveDate(rs.getDate(7));
                sysUser.setEndActiveDate(rs.getDate(8));
                sysUser.setStatus(rs.getString(9));
                list.add(sysUser);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            System.err.println("TableInfoService get出错");
            System.err.println(e.getMessage());
        }
        mysqlUtils.closeMySQL(statement);
        return list;
    }

    public static SysUser getOne() {
        mysqlUtils = new MySQLUtils();
        SysUser sysUser = null;
        Statement statement = mysqlUtils.linkMySQL();
        String sql = "select * from sys_user";
        try {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                sysUser = new SysUser();
                sysUser.setUserId(rs.getString(1));
                sysUser.setUserType(rs.getString(2));
                sysUser.setUserName(rs.getString(3));
                sysUser.setPassword(rs.getString(4));
                sysUser.setEmail(rs.getString(5));
                sysUser.setPhone(rs.getString(6));
                sysUser.setStartActiveDate(rs.getDate(7));
                sysUser.setEndActiveDate(rs.getDate(8));
                sysUser.setStatus(rs.getString(9));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            System.err.println("TableInfoService get出错");
            System.err.println(e.getMessage());
        }
        mysqlUtils.closeMySQL(statement);
        return sysUser;
    }

    public static void main(String[] args) {

        SysUser one=getOne();
        System.out.println(one.getUserId());

        List<SysUser> list = getAll();

        for (SysUser s : list) {
            System.out.println(s.getUserId() + "\t" + s.getUserType() + "\t" +
                    s.getUserName() + "\t" + s.getPassword() + "\t" + s.getEmail() + "\t" + s.getPhone()
                    + "\t" + s.getStartActiveDate() + "\t" + s.getEndActiveDate() + "\t" + s.getStatus());
        }
    }

}