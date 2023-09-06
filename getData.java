// import java.io.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class getData{

    public static void main(String[] args){

        String url = "jdbc:mysql://localhost:3307/brunoDB";
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
           
            String query = "SELECT nom,prenom,date_nais as 'date de naissance' FROM testTable ORDER BY date_nais;\n";
            
            // System.out.println(query);

            // Crée un objet Statement pour exécuter la requête SQL
            Statement stmt = conn.createStatement();

            // Exécute la requête SQL et récupère le résultat
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int nbCols = rsmd.getColumnCount();
            
            String entete="";
            System.out.println("NB col de rs : " + nbCols);
            for(int i=1;i<=nbCols;i++){
                entete+="\""+rsmd.getColumnLabel(i)+"\"";
                System.out.println(i+". "+rsmd.getColumnLabel(i));
                if(i<nbCols){
                    entete+=";";       
                }
            }
            entete+="\n"; 

            Path fichier = Paths.get("monfichier.csv");
            Charset charset = Charset.forName("UTF-8");

            try (BufferedWriter writer = Files.newBufferedWriter(fichier, charset)) {
                writer.write(entete, 0 ,entete.length());
                while(rs.next()){
                    String outdata="";
                    for (int i = 1; i <= nbCols; i++){
                        outdata+="\""+rs.getString(i)+"\"";
                        if(i<nbCols){
                            outdata+=";";
                        }
                    }

                    outdata+="\n";
                    System.out.print(outdata);
                    writer.write(outdata, 0 ,outdata.length());
                    // System.out.println(data);
                }
            } catch (IOException ioe) {
               ioe.printStackTrace();
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
