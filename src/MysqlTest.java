import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlTest {

	public static void main(String[] args) {
		try{
            //调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");
        }catch(ClassNotFoundException e1){
            System.out.println("找不到MySQL驱动!");
            e1.printStackTrace();
        }
        
        String url="jdbc:mysql://localhost:3306/python";    //JDBC的URL    
        //调用DriverManager对象的getConnection()方法，获得一个Connection对象
        Connection conn;
        try {
            conn = DriverManager.getConnection(url,"root","cqtddt@2016");
            //创建一个Statement对象
            Statement stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");
            
            String sql = "select * from employee";    //要执行的SQL
            ResultSet rs = stmt.executeQuery(sql);//创建数据对象
                System.out.println("名字"+"\t"+"姓氏"+"\t"+"年龄");
                while (rs.next()){
                    System.out.print(rs.getString(1) + "\t");
                    System.out.print(rs.getString(2) + "\t");
                    System.out.print(rs.getString(3) + "\t");
                    System.out.println();
                }
                
                String insql="insert into employee(FIRST_NAME,LAST_NAME,AGE,SEX,INCOME) values(?,?,?,?,?)";
                PreparedStatement psInsert = conn.prepareStatement(insql);
                psInsert.setString(1,"jerry");
                psInsert.setString(2,"shen");
                psInsert.setInt(3,30);
                psInsert.setString(4,"M");
                psInsert.setInt(5,50000);
                int result = psInsert.executeUpdate();
                if(result > 0){
                	System.out.println("插入数据成功");
                }
                else {
                	System.out.println("插入数据失败");
                }
                
              //修改数据的代码
                String sql2 = "update employee set AGE=? where FIRST_NAME=?";
                PreparedStatement pst = conn.prepareStatement(sql2);
                pst.setInt(1,29);
                pst.setString(2,"jack");
                pst.executeUpdate();
                
              //删除数据的代码
//                String sql3 = "delete from stu where number=?";
//                pst = conn.prepareStatement(sql3);
//                pst.setInt(1,701);
//                pst.executeUpdate();
                
                
                rs.close();
                stmt.close();
                conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        
	}

}
