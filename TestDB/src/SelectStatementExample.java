import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectStatementExample {
	public static void main(String[] args) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\databases\\testjava2.db");
			Statement statement = conn.createStatement();
			statement.execute("CREATE TABLE IF NOT EXISTS contacts "+
								" (name TEXT, phone INTEGER, email TEXT)");
			
			statement.execute("SELECT * FROM contacts");
			//ResultSet results = statement.getResultSet();
			ResultSet results = statement.executeQuery("SELECT * from contacts");
			if(!results.isBeforeFirst()) {
				System.out.println("No data");
			}
			int one = 0;
			while(results.next()) {
				
				System.out.println("Record: " + one);
				System.out.println(results.getString("name") + " " +
									results.getInt("phone") + " " +
									results.getString("email"));
				System.out.println();
				one++;
			}
			results.close();
			
			
			//closing resources manually
			statement.close();
			conn.close();
			
		}catch(SQLException e) {
			System.out.println("Something went wrong: "+e.getMessage());
		}
	}

}
