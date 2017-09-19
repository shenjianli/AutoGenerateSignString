import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SignDB {
	
	Connection conn;

	public boolean openDBConnection() {
		try {
			// 调用Class.forName()方法加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("成功加载MySQL驱动！");
		} catch (ClassNotFoundException e1) {
			System.out.println("找不到MySQL驱动!");
			e1.printStackTrace();
		}

		String url = "jdbc:mysql://localhost:3306/sign"; // JDBC的URL
		// 调用DriverManager对象的getConnection()方法，获得一个Connection对象

		try {
			conn = DriverManager.getConnection(url, "root", "cqtddt@2016");
			System.out.println("建立mysql链接成功");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<String> getUserId() {
		
		List<String> result = new ArrayList<String>();
		
		try {
			System.out.println("开始查询-----用户数据");
			Statement stmt;
			stmt = conn.createStatement();
			
			String sql = "select * from user"; // 要执行的SQL
			ResultSet rs = stmt.executeQuery(sql);// 创建数据对象
			while (rs.next()) {
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getString(2) + "\t");
				System.out.print(rs.getString(3) + "\t");
				System.out.print(rs.getString(4) + "\t");
				System.out.println();
				result.add(rs.getString(4));
			}
			rs.close();
			stmt.close();
			System.out.println("结束查询-----用户数据" + result.size());
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void closeConn() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean updateSignKey(String employeeId,String signKey,String signOutKey){
		String sql2 = "update user set sign_key=?,sign_out_key=? where employee_id=?";
		PreparedStatement pst;
		try {
			System.out.println(employeeId + "开始更新-----加密串");
			pst = conn.prepareStatement(sql2);
			pst.setString(1, signKey);
			pst.setString(2, signOutKey);
			pst.setString(3, employeeId);
			
			int result = pst.executeUpdate();
			
			pst.close();
			
			if(result > 0){
				return true;
			}
			System.out.println(employeeId + "完成更新-----加密串");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
//	public static void main(String[] args) {
//		try {
//			// 调用Class.forName()方法加载驱动程序
//			Class.forName("com.mysql.jdbc.Driver");
//			System.out.println("成功加载MySQL驱动！");
//		} catch (ClassNotFoundException e1) {
//			System.out.println("找不到MySQL驱动!");
//			e1.printStackTrace();
//		}
//
//		String url = "jdbc:mysql://localhost:3306/sign"; // JDBC的URL
//		// 调用DriverManager对象的getConnection()方法，获得一个Connection对象
//		Connection conn;
//		try {
//			conn = DriverManager.getConnection(url, "root", "cqtddt@2016");
//			// 创建一个Statement对象
//			Statement stmt = conn.createStatement(); // 创建Statement对象
//			System.out.println("成功连接到数据库！");
//
//			String sql = "select * from user"; // 要执行的SQL
//			ResultSet rs = stmt.executeQuery(sql);// 创建数据对象
//			System.out.println("名字" + "\t" + "姓氏" + "\t" + "年龄");
//			while (rs.next()) {
//				System.out.print(rs.getInt(1) + "\t");
//				System.out.print(rs.getString(2) + "\t");
//				System.out.print(rs.getString(3) + "\t");
//				System.out.print(rs.getString(8) + "\t");
//				System.out.println();
//			}
//
//			// String insql="insert into
//			// employee(FIRST_NAME,LAST_NAME,AGE,SEX,INCOME) values(?,?,?,?,?)";
//			// PreparedStatement psInsert = conn.prepareStatement(insql);
//			// psInsert.setString(1,"jerry");
//			// psInsert.setString(2,"shen");
//			// psInsert.setInt(3,30);
//			// psInsert.setString(4,"M");
//			// psInsert.setInt(5,50000);
//			// int result = psInsert.executeUpdate();
//			// if(result > 0){
//			// System.out.println("插入数据成功");
//			// }
//			// else {
//			// System.out.println("插入数据失败");
//			// }
//
//			// 修改数据的代码
//			String sql2 = "update user set sign_key=? where employee_id=?";
//			PreparedStatement pst = conn.prepareStatement(sql2);
//			pst.setString(1, "shenjianlikey");
//			pst.setString(2, "00000185");
//			pst.executeUpdate();
//
//			// 删除数据的代码
//			// String sql3 = "delete from stu where number=?";
//			// pst = conn.prepareStatement(sql3);
//			// pst.setInt(1,701);
//			// pst.executeUpdate();
//
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}

}
