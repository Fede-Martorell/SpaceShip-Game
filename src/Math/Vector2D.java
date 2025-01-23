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

    //escala para aceleracion.
    public Vector2D scale(double value){
        return new Vector2D(x * value , y * value);
    }

    //limitador de velocidad de objetos.
    public void limit(double value){
        if(x>value){
            x = value;
        }
        if(x< -value){
            x = -value;
        }
        if(y>value){
            y = value;
        }
        if(y< -value){
            y = -value;
        }
    }

    //Normalizamos los vectores para que den 1.
    //la forma mas sencilla de hacerlo es diviendolos por si mismos.
    public Vector2D normalize(){
        return new Vector2D(x / getMagnitude(), y / getMagnitude());
    }

    public double getMagnitude(){
        return Math.sqrt(x*x + y*y);
    }

    public Vector2D setDirection(double angle){
        return new Vector2D(Math.cos(angle)*getMagnitude(), Math.sin(angle)*getMagnitude());
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