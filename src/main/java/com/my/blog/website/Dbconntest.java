package com.my.blog.website;

import java.sql.*;

public class Dbconntest {
    Connection conn = null;

    Statement stmt = null;

    ResultSet rst = null;



    private static final String DRIVERNAME="com.mysql.jdbc.Driver";

    private static final  String url="jdbc:mysql://localhost:3306/tale?useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&autoReconnect=true";

    private static final String username="root";

    private static final String password="zhang7758";



    //main测试

    public static void main(String[] args)

    {

        Dbconntest mytest=new Dbconntest();



        String sql="select * from t_attach";

        ResultSet rs = mytest.executeQuery(sql);



        try

        {

            if(rs.next())

            {

                String qyid = rs.getString(1);

                System.out.println("qyid="+qyid);

            }

            rs.close();

        }

        catch (SQLException ex)

        {



        }





    }



    public Dbconntest()

    {



    }



    public void setAutoCommit(boolean b) throws SQLException

    {

        conn.setAutoCommit(b);

    }



    public void commit() throws SQLException

    {

        conn.commit();

    }



    public void rollback() throws SQLException

    {

        conn.rollback();

    }



//连接数据库

    public void Open()

    {

        try

        {

            if(conn!=null && !conn.isClosed()) {
                return;
            }





            Class.forName(DRIVERNAME);

            conn=DriverManager.getConnection(url,username,password);



            if(conn != null)

            {
                stmt = conn.createStatement();
            }

        }

        catch (Exception e)

        {

            e.printStackTrace(System.err); // 输出异常信息

        }

    }

//关闭数据库连接

    public void Close()

    {

        try

        {

            if (rst != null) { // 当ResultSet对象的实例rs不为空时

                rst.close(); // 关闭ResultSet对象

            }

            if (stmt != null) { // 当Statement对象的实例stmt不为空时

                stmt.close(); // 关闭Statement对象

            }

            if(conn!=null && !conn.isClosed())

            {

                conn.close();

            }

        }

        catch (Exception e)

        {

            e.printStackTrace(System.err); // 输出异常信息

        }

    }



    public java.sql.Connection getConnection()

    {

        try

        {

            if(conn==null || conn.isClosed()) {
                Open();
            }

            return conn;

        }

        catch (Exception e)

        {

            e.printStackTrace(System.err); // 输出异常信息

            return null;

        }

    }



//用于执行 INSERT、UPDATE 或 DELETE 语句

//成功返回0

    public int executeUpdate(String sql)

    {

        try

        {

            if(conn==null || conn.isClosed()) {
                Open();
            }

            int ret;

            stmt = conn.createStatement();

            ret=stmt.executeUpdate(sql);

            if(ret>0)

            {
                ret=0;
            } else

            {
                ret=1;
            }

            stmt.close();

            return ret;

        }

        catch (Exception e)

        {

            e.printStackTrace(System.err); // 输出异常信息

            return -1;

        }

    }

//用于查询语句  如 SELECT 语句

//成功输出返回结果集

    public ResultSet executeQuery(String sql)

    {

        try

        {

            if(conn==null || conn.isClosed()) {
                Open();
            }

            stmt = conn.createStatement();

            rst = stmt.executeQuery(sql);

            return rst;

        }

        catch (Exception e)

        {

            e.printStackTrace(System.err); // 输出异常信息

            return null;

        }

    }



}
