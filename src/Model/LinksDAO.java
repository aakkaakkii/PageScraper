package Model;

import java.sql.*;
import java.util.ArrayList;

public class LinksDAO {
    private static LinksDAO linksDAO=null;
    private Statement statement;

    private LinksDAO(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
             Connection con = DriverManager.getConnection
                    ("jdbc:mysql://" + MyDBInfo.DATABASE_SERVER, MyDBInfo.USERNAME, MyDBInfo.PASSWORD);
            statement=con.createStatement();
        }catch (ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
        }
    }

    public void add(String url, String link){
        try {
            statement.executeQuery("USE " + MyDBInfo.DATABASE_NAME);
            statement.executeUpdate("INSERT INTO links(url,link) VALUES ('" + url + "','" + link + "')");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public ArrayList<String> get(String url){
        ArrayList<String> arrayList=new ArrayList<>();
        try {
            statement.executeQuery("USE " + MyDBInfo.DATABASE_NAME);
            ResultSet rs=statement.executeQuery("SELECT link FROM links WHERE url='"+url+"'");
            while (rs.next()){
                String str=rs.getString("link");
                arrayList.add(str);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return arrayList;
    }

    public void delete(String url){
        try {
            statement.executeQuery("USE " + MyDBInfo.DATABASE_NAME);
            statement.executeUpdate("DELETE FROM links WHERE url='"+url+"'");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static LinksDAO createLinksDAO(){
        if (linksDAO==null)
            linksDAO=new LinksDAO();

        return linksDAO;
    }
}
