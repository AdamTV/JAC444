import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDBExample {
	public static void main(String[] args) {
		try {
			//Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\databases\\testjava2.db");
			Statement statement = conn.createStatement();
			statement.execute("CREATE TABLE contacts (name TEXT, phone INTEGER, email TEXT)");
			
			//closing resources manually
			statement.close();
			conn.close();
			
		}catch(SQLException e) {
			System.out.println("Something went wrong: "+e.getMessage());
		}
	}

}
