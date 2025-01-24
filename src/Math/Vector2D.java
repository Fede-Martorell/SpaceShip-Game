package Math;

public class Vector2D {
    private double x,y;

    public Vector2D(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2D()
    {
        x = 0;
        y = 0;
    }

    public Vector2D add(Vector2D v){
        return new Vector2D(x + v.getX(), y + v.getY());
    }
    public Vector2D subtract(Vector2D v){
        return new Vector2D(x - v.getX(), y - v.getY());
    }

    //escala para aceleracion.
    public Vector2D scale(double value){
        return new Vector2D(x * value , y * value);
    }

    //limitador de velocidad de objetos.
    public Vector2D limit(double value){
        //de esta manera no perdemos su direccion por cuadrante.
        if(getMagnitude() > value){
            return  this.normalize().scale(value);
        }
        return this;
    }

    //Normalizamos los vectores para que den 1.
    //la forma mas sencilla de hacerlo es diviendolos por si mismos.
    public Vector2D normalize(){
        double magnitude = getMagnitude();
        //optimizamos el programa.
        return new Vector2D(x / magnitude, y / magnitude);
    }

    public double getMagnitude(){
        return Math.sqrt(x*x + y*y);
    }

    public Vector2D setDirection(double angle){
        double magnitude = getMagnitude();
        //optimizamos el programa.
        return new Vector2D(Math.cos(angle) * magnitude, Math.sin(angle) * magnitude);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


}