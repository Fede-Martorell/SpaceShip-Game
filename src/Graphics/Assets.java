package Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage player;
    //Effect speed
    public static BufferedImage speed;
    //Lasers
    public static BufferedImage blueLaser, redLaser, greenLaser;
    //meteors
    public static BufferedImage[] bigs = new BufferedImage[4];
    public static BufferedImage[] meds = new BufferedImage[2];
    public static BufferedImage[] smalls = new BufferedImage[2];
    public static BufferedImage[] tinies = new BufferedImage[2];
    //Explosion
    public static BufferedImage[] exp = new BufferedImage[9];
    //UFO
    public static BufferedImage ufo;
    //numers
    public static BufferedImage[] numbers = new BufferedImage[11];
    public static BufferedImage life;
    //FONTS
    public static Font fontBig;
    public static Font fontMed;


    public static void init()
    {
        player = Loader.ImageLoader("/PNG/player.png");
        speed = Loader.ImageLoader("/PNG/Effects/fire08.png");
        blueLaser = Loader.ImageLoader("/PNG/Lasers/laserBlue01.png");
        redLaser = Loader.ImageLoader("/PNG/Lasers/laserRed01.png");
        greenLaser = Loader.ImageLoader("/PNG/Lasers/laserGreen11.png");
        life = Loader.ImageLoader("/PNG/UI/playerLife1_blue.png");
        fontBig = Loader.loadFont("/Bonus/future.ttf",42);
        fontMed = Loader.loadFont("/Bonus/future.ttf",20);

        for(int i = 0; i < bigs.length; i++){
            bigs[i] = Loader.ImageLoader("/PNG/Meteors/meteorBrown_big" + (i+1) + ".png");
        }
        for(int i = 0; i < meds.length; i++){
            meds[i] = Loader.ImageLoader("/PNG/Meteors/meteorBrown_med" + (i+1) + ".png");
        }
        for(int i = 0; i < smalls.length; i++){
            smalls[i] = Loader.ImageLoader("/PNG/Meteors/meteorBrown_small" + (i+1) + ".png");
        }
        for(int i = 0; i < tinies.length; i++){
            tinies[i] = Loader.ImageLoader("/PNG/Meteors/meteorBrown_tiny" + (i+1) + ".png");
        }
        for(int i = 0; i < exp.length; i++){
            exp[i] = Loader.ImageLoader("/PNG/Explosion/exp_"+i+".png");
        }
        ufo = Loader.ImageLoader("/PNG/ufoGreen.png");
        for(int i = 0; i < numbers.length; i++){
            numbers[i] = Loader.ImageLoader("/PNG/UI/numeral" + (i) + ".png");
        }

    }

}