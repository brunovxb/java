 package lib.bruno.Shape;

import java.math;

public class Quadri {

    double lon;
    double lar;

    public Quadri(double longueur, double largeur){
        this.lon=longueur;
        this.lar=largeur;
    }

    public Perimetre(){
        return 2 * ( this.lar + this.lon );
    }

    public Surface(){
        return this.lon * this.lar;
    }
}
