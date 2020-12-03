import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UdatedCodeExample {
		
		public static final String DB_NAME = "testjava.db";
		public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\SQLite\\databases\\" + DB_NAME;
		
		public static final String TABLE_CONTACTS = "contacts";
		
		public static final String COLUMN_NAME = "name";
		public static final String COLUMN_PHONE = "phone";
		public static final String COLUMN_EMAIL = "email";
		
		public static void main(String[] args) {
			
			try {
				
				Connection conn = DriverManager.getConnection(CONNECTION_STRING);
				Statement statement = conn.createStatement();
				
				statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
				
				statement.execute("CREATE TABLE IF NOT EXISTS "+TABLE_CONTACTS + 
									" (" + COLUMN_NAME + " text, " +
										COLUMN_PHONE + " integer, " +
										COLUMN_EMAIL + " text" +
										")" );
				insertContacts(statement,"Ali", 159753,"ali@myemail.com");
				  insertContacts(statement,"Jhon", 123456,"jhon@jhonemail.com");
				  insertContacts(statement,"Roy", 7894562,"roy@royemail.com");
				  insertContacts(statement,"Nick", 753654,"nick@nickemail.com");
/*				statement.execute("INSERT INTO " + TABLE_CONTACTS +
									" (" + COLUMN_NAME + ", " +
											COLUMN_PHONE + ", " +
											COLUMN_EMAIL + 
									")" +
									"VALUES('Ali', 159753,'ali@myemail.com')");
				statement.execute("INSERT INTO " + TABLE_CONTACTS +
									"(" + COLUMN_NAME + ", " +
											COLUMN_PHONE + ", " +
											COLUMN_EMAIL + 
									")" +
									"VALUES('Jhon', 123456,'jhon@jhonemail.com')");
				statement.execute("INSERT INTO " + TABLE_CONTACTS +
									"(" + COLUMN_NAME + ", " +
											COLUMN_PHONE + ", " +
											COLUMN_EMAIL + 
									")" +
									"VALUES('Roy', 7894562,'roy@royemail.com')");
				statement.execute("INSERT INTO " + TABLE_CONTACTS +
									"(" + COLUMN_NAME + ", " +
											COLUMN_PHONE + ", " +
											COLUMN_EMAIL + 
									")" +
									"VALUES('Nick', 753654,'nick@nickemail.com')");
				statement.execute("UPDATE " + TABLE_CONTACTS + " SET "+ 
										COLUMN_PHONE + "=321654" + " WHERE "+
										COLUMN_NAME + "='Ali'");
				statement.execute("DELETE FROM " + TABLE_CONTACTS + " WHERE "+
										COLUMN_NAME + "='Nick'");*/
				
				ResultSet results = statement.executeQuery("SELECT * FROM "+ TABLE_CONTACTS);
				while(results.next()) {
					System.out.println(results.getString(COLUMN_NAME) + " " +
										results.getInt(COLUMN_PHONE) + " " +
										results.getString(COLUMN_EMAIL));
				}
				results.close();
				
				//closing resources manually
				statement.close();
				conn.close();
				
			}catch(SQLException e) {
				System.out.println("Something went wrong: "+e.getMessage());
				e.printStackTrace();
			}
	}
		
		private static void insertContacts(Statement statement, String name, int phone, String email) throws SQLException {
			
			statement.execute("INSERT INTO " + TABLE_CONTACTS +
					"(" + COLUMN_NAME + ", " +
							COLUMN_PHONE + ", " +
							COLUMN_EMAIL + 
					")" +
					"VALUES('" + name + "', " + phone + ", '" + email + "')");
		}

}
