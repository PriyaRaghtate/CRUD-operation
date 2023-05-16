package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Db {
	String driver="com.mysql.cj.jdbc.Driver";
	String dbName="register";
	String url="jdbc:mysql://localhost:3306/"+dbName;
	String dbuname="root";
	String dbpass="1234";
	String tblname="reg";
	
	Connection getConnect() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		return DriverManager.getConnection(url,dbuname,dbpass);
		
	}
	public int save(User u) throws ClassNotFoundException, SQLException {
		String sql="insert into reg (name, pass, email) values(?,?,?)";
        Connection con=getConnect();
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, u.getUname());
		ps.setString(2, u.getUpass());
		ps.setString(3, u.getUemail());
		int a= ps.executeUpdate();
		con.close();
		return a;
		
	}
	public boolean checkUser(String email) throws ClassNotFoundException, SQLException {

		String sql="select * from reg where email=?";
        Connection con=getConnect();
		PreparedStatement ps=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
		ps.setString(1, email);
		ResultSet  rs= ps.executeQuery();
		
		if(rs.absolute(1))
		return true;
		
		con.close();
		
		return false;
		
	}
	public boolean login(String email ,String pass) throws ClassNotFoundException, SQLException {
		boolean st=false;
		String sql="select * from reg where email=? and pass=?";
        Connection con=getConnect();
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, pass);
        ResultSet rs=ps.executeQuery(); 
        st=rs.next();
        	
        
		return st;
	}
	public ArrayList<User> getAllUser() throws ClassNotFoundException, SQLException {

		String sql="select * from reg";
        Connection con=getConnect();
		PreparedStatement ps=con.prepareStatement(sql);

		ResultSet  rs= ps.executeQuery();
		
		ArrayList<User> ul=new ArrayList<>();
		
		while(rs.next())
		{
			User u =new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			ul.add(u);
		}
		
		con.close();
		
		return ul;
		
	}
	public int deleteUser(int uid) throws ClassNotFoundException, SQLException  {

		String sql="DELETE FROM reg WHERE id=?";
        Connection con=getConnect();
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1,uid);

		int a= ps.executeUpdate();
		con.close();
		return a;
		
	}
	public ArrayList<User> getUserByPage(int start,int total) throws ClassNotFoundException, SQLException {

		start-=1;
		String sql="select * from reg limit "+start+","+total+"";
        Connection con=getConnect();
		PreparedStatement ps=con.prepareStatement(sql);

		ResultSet  rs= ps.executeQuery();
		
		ArrayList<User> ul=new ArrayList<>();
		
		while(rs.next())
		{
			User u =new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			ul.add(u);
		}
		
		con.close();
		
		return ul;
	
}
	
	public int update(User u) throws ClassNotFoundException, SQLException {
		int r=0;
		String sql="update * from reg name=?,pass=?,email=? where id=?";
		Connection con=getConnect();
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, u.getUname());
		ps.setString(2, u.getUpass());
		ps.setString(3, u.getUemail());
		ps.setInt(4, u.getUid());
		ResultSet rs=ps.executeQuery();
		con.close();
		return r;
		
	}
	
	
}







