import lib.bruno.Shape.*;

public class autreTest{
    
    public static void main(String[] args){
        Cercle c=new Cercle(4.3);
        Quadri q=new Quadri(4,3);
        System.out.printf("c : %02.5f\n",c.Surface());
        System.out.printf("q : %02.5f\n",q.Perimetre());
    }

}