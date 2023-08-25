// import java.io.*;
import java.sql.*;

public class getData{

    public static void main(String[] args){

        String url = "jdbc:mysql://localhost/brunoDB";
        // String user = "user";
        String user = "bruno";
        String password = "bob000";
        

        try {
            Connection conn;
            // Charge le pilote JDBC approprié en fonction du type de base de données
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            
            // Lit la requête SQL à partir du fichier
            // String query = readQueryFromFile(queryFile);
           
            String query = "SELECT * FROM testTable;\n";
            
            // System.out.println(query);

            // Crée un objet Statement pour exécuter la requête SQL
            Statement stmt = conn.createStatement();

            // Exécute la requête SQL et récupère le résultat
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int nbCols = rsmd.getColumnCount();

            System.out.println("NB col de rs : " + nbCols);


            while(rs.next()){
            for (int i = 1; i <= nbCols; i++)
               System.out.print(rs.getString(i) + ";");
            System.out.println();
            // System.out.println(data);
            }

            rs.close();
            stmt.close();
            conn.close();

            System.exit(0);

       } catch (SQLException e) {
            // Gère les erreurs liées à la base de données
            System.err.println("Database error: " + e.getMessage());
            System.exit(3);
        } catch (ClassNotFoundException e) {
            // Gère les erreurs liées au chargement du pilote JDBC
            System.err.println("JDBC driver not found: " + e.getMessage());
            System.exit(4);
        } /* catch (IOException e) {
            // Gère les erreurs liées à l'écriture dans le fichier de sortie
            System.err.println("Error writing output file: " + e.getMessage());
            System.exit(5);
        } */
    }
}
