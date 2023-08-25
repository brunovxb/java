import java.io.*;

public class testFile{

    protected String fname;
    protected File fichier;
    
    public testFile(String fname){
        this.fname=fname;
        fichier=new File(fname);
        traitement();
    }

    public static void main(String[] args){
        
        if(args.length>0){
            new testFile(args[0]);
        }else{
            new testFile("test.csv");
        }

    }

    private void traitement(){

        if (!this.fichier.exists()) { 
            System.err.println("le fichier "+this.fname+" n'existe pas");
            System.exit(1);
        }  

        System.out.println(" Nom du fichier    : "+this.fichier.getName());
        System.out.println(" Chemin du fichier : "+this.fichier.getPath());
        System.out.println(" Chemin absolu     : "+this.fichier.getAbsolutePath());
        System.out.println(" Droit de lecture  : "+this.fichier.canRead()); 
        System.out.println(" Droit d'ecriture  : "+this.fichier.canWrite());
        System.out.println(" Taille  : "+this.fichier.length());

        try {

            BufferedReader bufreader  = new BufferedReader(new FileReader(this.fichier));
            String ligne;
        
            while((ligne = bufreader.readLine()) != null) {
                System.out.println(ligne);
            }

            bufreader.close();

        }catch(IOException e){
            e.printStackTrace();
        }
 
    }

}