import lib.bruno.Shape.Cercle;

public class MaClasse{

    public static void main(String[] args){
        
        System.out.printf("Nombre d'arguments : %02d\n", args.length );
        System.out.printf("                pi : %02.7f\n" , Math.PI);
        System.out.println();
        
        /* for (int i=0,affn=1;i<10;i++,affn++){
            System.out.printf("%02d. Hello World !\n",affn);
        }  */  

        int i=0;
        for (String arg : args){
            System.out.println("Calculs pour args[" + ++i + "] = " + arg);
            try{
                 
                double r = Double.parseDouble(arg);
                Cercle c = new Cercle(r);
                System.out.printf("P(r=%4.2f) = %4.2f / S(r=%4.2f) = %4.2f\n",c.Rayon(),c.Perimetre(),c.Rayon(),c.Surface());

            }catch(Exception e){
                System.out.println("Probleme sur la conversion de : " + arg);
                e.printStackTrace();
            }
        }

    }

}