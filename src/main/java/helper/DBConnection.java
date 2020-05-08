package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public Connection mySqlConnection;
    public Connection connection(){
        if (mySqlConnection == null){
            try {
                String DB="jdbc:mysql://localhost:3306/DBsmartlurah?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                String user="root"; // user database
                String pass="wisnudewasaputra050700"; // password database
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                mySqlConnection = (Connection) DriverManager.getConnection(DB,user,pass);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mySqlConnection;
    }
}
