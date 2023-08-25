 package lib.bruno.Shape;

public class Quadri {

    double lon;
    double lar;

    public Quadri(double longueur, double largeur){
        this.lon=longueur;
        this.lar=largeur;
    }

    public double Perimetre(){
        return ( this.lar + this.lon ) * 2;
    }

    public double Surface(){
        return this.lon * this.lar;
    }
}
