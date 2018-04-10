package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;



/*DriverManager类作用于用户与驱动程序之间，并在数据库与驱动之间创建连接
 * Connection接口 用于创建Statement对象
 * Statement接口：
 *   execute 执行静态的select语句，该语句返回多个结果集。
 *   executeQuery 执行给定的SQL语句，返回单个对象
 *   executeUpdate 执行给定的SQL语句，语句为 insert update delete
 *PrepareStatement 继承与Statement
 *  execute 在PrepareStatement对象中 执行任何类型的SQL语句
 *  executeQuery 在PrepareStatement对象中 执行，返回结果为查询结果集Result对象
 *  executeUpdate 在PrepareStatement对象中 执行，必须是inset update delete 没有返回值的DDL语句
 *  SQLyog - 32 bit Trial
 */ 
public class JDBCtest {

	//加载数据库驱动
	public static void main(String[] args) {
		JDBCtest jdbc = new JDBCtest();
//		jdbc.test();
		jdbc.selectByBlur();
	}
	public void test(){
		//加载数据库驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 System.out.println("数据库驱动加载成功"); 
			 
			 //创建数据库的连接
			 String url = "jdbc:mysql://localhost:3306/person?useUnicode=true&characterEncoding=utf-8&useSSL=false";
			//? 需要设置useSSL=false
			 String user = "root";
			 String password = "mysql";
		
			 try {
				Connection conn = DriverManager.getConnection(url,user,password);
				
				
				String sql = "INSERT INTO person.student (id, name, age, scoer) " +
						"VALUES (4, 'tt', 12, 92); ";
				String sql2 = "insert into person.student values(5,'tt',14,89)";
				String sql3 = "insert into person.student values(?,?,?,?)";
				String sql4 = "select * from person.student";
				String sql5 = "update person.student set age = 33 where id=5";
				String sql6 = "update person.student set name =?,age=?,scoer=? where id =?";
				String sql7 = "delete from person.student where id = 5";
				/*
				 * 增
				 */
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				stmt.executeUpdate(sql2);
				
					
					java.sql.PreparedStatement ps =conn.prepareStatement(sql3);
					ps.setInt(1, 6);
					ps.setString(2, "kk");
					ps.setInt(3, 12);
					ps.setInt(4, 78);
					ps.execute();
					
				/*
				 * 查
				 */
				Statement stmt1 = conn.createStatement();
				ResultSet rs = stmt1.executeQuery(sql4);
				int id,age,score;
				String name;
				System.out.println("id\tname\tage\tscore");
				while(rs.next()){
					id = rs.getInt(1);
					name = rs.getString(2);
					age = rs.getInt("age");
					score = rs.getInt("scoer");
					System.out.println(id + "\t"+name+"\t"+ age+"\t" + score);
				}
				 /*
				  * 改
				  */
				Statement stmt2 = conn.createStatement();
				stmt2.executeUpdate(sql5);
				System.out.println("数据库修改成功");
				PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(sql6);
				ps1.setString(1, "lili");
				ps1.setInt(2, 27);
				ps1.setInt(3,54);
				ps1.setInt(4,3);
				 int result = ps1.executeUpdate();  
				System.out.println("PreparedStatement 成功修改输数据库：" + result + "条");
				/*
				 * 删除
				 */
				stmt.executeUpdate(sql7);
				System.out.println("成功删除id = 5");
				
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void selectByBlur(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/person?useSSL=false";
//			"jdbc:mysql://localhost:3306/person?useUnicode=true&characterEncoding=utf-8&useSSL=false"
			String user = "root";
			String password = "mysql";
			
			try {
				Connection conn = DriverManager.getConnection(url,user,password);
				Statement  state = conn.createStatement();
				String sql = "select * from person.employee";
				ResultSet rs = state.executeQuery(sql);
				int id;
				String name,lunch;
				float salary;
				System.out.println("id\tname\tsalary\tlunch");
				while(rs.next()){
					id = rs.getInt("id");
					name = rs.getString(2);
					salary = rs.getFloat(3);
					lunch = rs.getString(4);
					System.out.println(id +"\t"+name+"\t"+salary+"\t"+lunch);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
