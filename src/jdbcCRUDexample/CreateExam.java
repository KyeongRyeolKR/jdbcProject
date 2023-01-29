package jdbcCRUDexample;
import java.sql.*;

public class CreateExam {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String title = "TEST2";
        String writerId = "newlec";
        String content = "hahaha";
        String files = "";

        String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
        String sql = "INSERT INTO notice ("
                + "    id,"
                + "    title,"
                + "    writer_id,"
                + "    content,"
                + "    files"
                + ") VALUES (NOTICE_SEQ.NEXTVAL,?,?,?,?)";
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, "sys as sysdba", "kkr983224871!");
        //Statement st = con.createStatement();
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, title);
        st.setString(2, writerId);
        st.setString(3, content);
        st.setString(4, files);

        int result = st.executeUpdate();

        System.out.println(result);

        st.close();
        con.close();
    }

}
