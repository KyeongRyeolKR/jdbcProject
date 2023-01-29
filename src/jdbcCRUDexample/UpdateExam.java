package jdbcCRUDexample;
import java.sql.*;

public class UpdateExam {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String title = "TEST3";
        String content = "hahaha3";
        String files = "";
        int id = 5;

        String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
        String sql = "UPDATE NOTICE "
                + "SET"
                + "    TITLE = ?,"
                + "    CONTENT = ?,"
                + "    FILES = ?"
                + "WHERE ID = ?";
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, "sys as sysdba", "kkr983224871!");
        //Statement st = con.createStatement();
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, title);
        st.setString(2, content);
        st.setString(3, files);
        st.setInt(4, id);

        int result = st.executeUpdate();

        System.out.println(result);

        st.close();
        con.close();
    }

}
