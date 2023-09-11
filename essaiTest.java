import java.io.*;


public class essaiTest{

    protected String forName;
    protected String lastName;
    protected File inf;
    protected File outf;

    public essaiTest(String fname,String lname){
        this.forName=fname;
        this.lastName=lname;
        this.outf=new File(fname+"_"+lname+"_out.txt");
        this.inf=new File(fname+"_"+lname+"_in.txt");
        traitement();
    }
 
    public static void main(String[] args){
        if(args.length>=2){
            new essaiTest(args[0],args[1]);
        }else{
            new essaiTest("Jean","Dupont");
        }
        System.exit(0);
    }

    private void traitement(){
        System.out.println(this.forName+" "+this.lastName);

        if(!this.inf.exists()){
            System.out.println(this.inf+" n'existe pas !");
            System.exit(1);
        }

        if(this.outf.exists()){
            System.out.println(this.outf+" existe !");
            outf.delete();
        }else{
            System.out.println(this.outf+" n'existe pas !");
        }


        try{
            BufferedReader rdr=new BufferedReader(new FileReader(this.inf));

            String rstr;
            while((rstr=rdr.readLine())!=null){
                System.out.println(rstr);
            }
            rdr.close();

            BufferedWriter wrt=new BufferedWriter(new FileWriter(this.outf));
            wrt.write(this.forName+";"+this.lastName);
            wrt.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    
        

    }

}