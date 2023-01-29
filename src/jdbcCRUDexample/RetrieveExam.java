package jdbcCRUDexample;

import java.sql.*;

public class RetrieveExam {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
        String sql = "SELECT * FROM NOTICE ORDER BY ID DESC";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, "sys as sysdba", "kkr983224871!");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            int id = rs.getInt("ID");
            String title = rs.getString("TITLE");
            String writerId = rs.getString("WRITER_ID");
            Date regDate = rs.getDate("REGDATE");
            String content = rs.getString("CONTENT");
            int hit = rs.getInt("HIT");
            System.out.printf("ID: %d, TITLE: %s, WRITER_ID: %s, REGDATE: %s, CONTENT: %s, HIT: %d\n", id, title, writerId, regDate, content, hit);
        }

        rs.close();
        st.close();
        con.close();
    }

}