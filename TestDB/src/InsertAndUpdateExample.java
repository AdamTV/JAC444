import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsertAndUpdateExample {
	public static void main(String[] args) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\databases\\testjava2.db");
			Statement statement = conn.createStatement();
			statement.execute("CREATE TABLE IF NOT EXISTS contacts "+
								" (name TEXT, phone INTEGER, email TEXT)");
			
			statement.execute("INSERT INTO contacts (name, phone, email)" +
								"VALUES ('Ali', 123456, 'ali@myemail.com')");
			
			statement.execute("INSERT INTO contacts (name, phone, email)" +
					"VALUES ('John', 789456, 'john@myemail.com')");
			
			statement.execute("INSERT INTO contacts (name, phone, email)" +
					"VALUES ('Roy', 753159, 'roy@myemail.com')");
			
			statement.execute("UPDATE contacts SET phone=159357 WHERE name='Ali'");
			statement.execute("DELETE FROM contacts WHERE name='Ali'");
			//closing resources manually
			statement.close();
			conn.close();
			
		}catch(SQLException e) {
			System.out.println("Something went wrong: "+e.getMessage());
		}
	}


}
