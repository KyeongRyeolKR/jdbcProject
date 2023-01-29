package jdbcCRUDexample;
import java.sql.*;

public class DeleteExam {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        int id = 5;

        String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
        String sql = "DELETE NOTICE WHERE ID = ?";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, "sys as sysdba", "kkr983224871!");
        //Statement st = con.createStatement();
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, id);

        int result = st.executeUpdate();

        System.out.println(result);

        st.close();
        con.close();
    }

}
