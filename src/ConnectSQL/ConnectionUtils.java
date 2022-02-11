package ConnectSQL;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {

	public static void main(String[] args) throws SQLException,
    ClassNotFoundException {

		System.out.println("Get connection ... ");

	      Connection conn = ConnectionUtils.getMyConnection();

	      System.out.println("Get connection " + conn);

	      System.out.println("Done!");
		
	}
	
	public static Connection getMyConnection() throws SQLException,
    ClassNotFoundException {
		 return SQLServerConnUtil.getSQLServerConnection();
	 }
}
