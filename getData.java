import java.io.*;
import java.sql.*;

public class getData{

    public static void main(String[] args){

        String url = "jdbc:mysql://host/database";
        // String user = "user";
        String user = "user";
        String password = "passw";
        

        try {
            Connection conn;
            // Charge le pilote JDBC approprié en fonction du type de base de données
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            
            // Lit la requête SQL à partir du fichier
            // String query = readQueryFromFile(queryFile);
           
            String query = "select distinctrow te.V_ENAME as \"Graphe Automator\",\n" + 
                "cp2.V_PNAME as \"Règle de planification (Niveau graphe)\",\n" + 
                "toa.V_ANAME as \"Application (si absent : job à la racine)\",\n" + 
                "cp.V_PNAME as \"Règle de planification (Niveau application)\",\n" + 
                "toj.V_JNAME as \"Nom Job\",\n" + 
                "case left(toj.ID_AGENT,2)\n" + 
                "when 'AG' then\n" + 
                "(select distinct ca.M_HNAME\n" + 
                "from cf_agt ca\n" + 
                "where ca.ID_KEY = toj.ID_AGENT)\n" + 
                "when 'VA' then\n" + 
                "(select distinctrow case left(tov2.V_VALUE,2)\n" + 
                "when 'AG' then\n" + 
                "(select distinct cax.M_HNAME\n" + 
                "from automator370.cf_agt cax\n" + 
                "where cax.ID_KEY = tov2.V_VALUE)\n" + 
                "when 'US' then\n" + 
                "(select distinct cu.V_UNAME\n" + 
                "from automator370.cf_user cu\n" + 
                "where cu.ID_KEY = tov2.V_VALUE)\n" + 
                "when 'PL' then\n" + 
                "(select distinct cpx.V_PNAME\n" + 
                "from automator370.cf_plng cpx\n" + 
                "where cpx.ID_KEY = tov2.V_VALUE)\n" + 
                "else tov2.V_VALUE\n" + 
                "end as V_EXPOSED_VALUE\n" + 
                "FROM automator370.to_var tov2\n" + 
                "where tov2.ID_KEY = toj.ID_AGENT )\n" + 
                "else\n" + 
                "'Inconnu'\n" + 
                "end as \"Serveur de production\",\n" + 
                "case left(toj.V_SCRIPT,1)\n" + 
                "when '<' then\n" + 
                "toj.V_JNAME\n" + 
                "else\n" + 
                "toj.V_SCRIPT\n" + 
                "end as \"Script executé (nom job sinon)\",\n" + 
                "toj.V_DESC as \"Descritption\"\n" + 
                "from automator370.to_obj_job toj\n" + 
                "inner join automator370.to_env te on toj.ID_ENV = te.ID_KEY\n" + 
                "left join automator370.cf_plng cp2 on te.ID_PLNG = cp2.ID_KEY\n" + 
                "left join automator370.to_obj_app toa on toj .ID_APP = toa .ID_KEY\n" + 
                "left join automator370.cf_plng cp on toa.ID_PLNG = cp.ID_KEY\n" + 
                "where toj.ID_ENV like 'EB%'\n" + 
                " -- and ((te.V_ENAME like 'PADZ%' or te.V_ENAME like 'PROD%' ) and  te.V_ENAME not like '%_WPL' )\n" + 
                "and te.V_ENAME not like '%ARRET%'\n" + 
                "and te.V_ENAME not like '%fresh_Lys%'\n" + 
                "and toj.V_SCRIPT not in ('placebo.sh')\n" + 
                "and (toj.ID_AGENT in\n" + 
                "(SELECT ID_KEY\n" + 
                "FROM automator370.to_var\n" + 
                "where V_VALUE in\n" + 
                "(SELECT distinct ID_KEY\n" + 
                "FROM automator370.cf_agt\n" + 
                "-- where M_HNAME in ('crocus','fr-ex-batdom-x','lys')\n" + 
                "where M_HNAME in ('fr-rc-batdom-x')\n" + 
                "-- WHERE M_HNAME='crocus'\n" + 
                "-- WHERE M_HNAME='fr-ex-geocod-x1'\n" + 
                "))\n" + 
                "or toj.ID_AGENT in\n" + 
                "(SELECT distinct ID_KEY\n" + 
                "FROM automator370.cf_agt\n" + 
                "-- where M_HNAME in ('crocus','fr-ex-batdom-x','lys')\n" + 
                "where M_HNAME in ('fr-rc-batdom-x')\n" + 
                "-- where M_HNAME='crocus'\n" + 
                "-- WHERE M_HNAME='fr-ex-geoloc-x1'\n" + 
                ")\n" + 
                ")\n" + 
                "order by te.V_ENAME,\n" + 
                "toa.V_ANAME,\n" + 
                "toj.V_SCRIPT\n" + 
                ";\n";
            
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
