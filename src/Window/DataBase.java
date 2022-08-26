package Window;
import Objects.Player;
import java.sql.*;
import java.util.Scanner;

public class DataBase {
    public static void createTable(Connection c) throws SQLException {
        String sql = "" +
                "CREATE TABLE Scor " +
                "(" +
                "Monede integer NOT NULL, " +
                "Decese integer NOT NULL ," +
                "Omoruri integer NOT NULL "+
                "); ";
        Statement stmt = c.createStatement();
        stmt.execute(sql);
    }

    public static void deleteTable(Connection c) throws SQLException {
        String sql = "DROP TABLE Scor";
        Statement stmt = c.createStatement();
        stmt.execute(sql);
    }

    public static void insertRecord(Connection c, int Monede, int Decese,int Omoruri) throws SQLException {
        String sql = "INSERT INTO Scor " +
                "VALUES (?,?,?);";
        PreparedStatement pstmt = c.prepareStatement(sql);

        pstmt.setInt(1, Monede);
        pstmt.setInt(2, Decese);
        pstmt.setInt(3, Omoruri);

        pstmt.executeUpdate();
    }


    public static void updateRecord(Connection c, int Monede) throws SQLException {
        String sql = "UPDATE Scor set timp = \"" + Monede + "\";";
//        String sql = "UPDATE Ranking set Monede = \"" + Monede + "\" WHERE May2019 = " + ranking2019 + ";";
//        String sql = "UPDATE Ranking set Change = \"" + change + "\" WHERE May2019 = " + ranking2019 + ";";
        Statement stmt = c.createStatement();
        stmt.executeUpdate(sql);
    }
}