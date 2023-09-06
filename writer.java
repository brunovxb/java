import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class writer {

    public static void main(String[] args){
        Path fichier = Paths.get("monfichier.txt");
        Charset charset = Charset.forName("UTF-8");
        String contenu = "Contenu du fichier é à €";

        try (BufferedWriter writer = Files.newBufferedWriter(fichier, charset)) {
            writer.write(contenu, 0 , contenu.length());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

 
}
