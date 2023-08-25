package lib.bruno.Shape;

import java.math;

public class Cercle {

    double rayon;

    public Cercle(double r){
        this.rayon = r;
    }

    public double Perimetre(){
        return 2 * this.rayon * Math.PI;
    }

    public double Surface(){
        return Math.PI * Math.pow(this.rayon, 2);
    }

    public double Rayon(){
        return this.rayon;
    }

}
