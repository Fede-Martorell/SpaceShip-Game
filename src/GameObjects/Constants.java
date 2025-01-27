package GameObjects;

public class Constants {
    // frame dimensions
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    // player properties
    public static final int FIRERATE = 150;
    public static final double DELTAANGLE = 0.1;
    public static final double ACC = 0.2;
    public static final double PLAYER_MAX_VEL = 7.0;

    // Laser properties
    public static final double LASER_VEL = 15.0;

    //meteor
    public static final double METEOR_VEL = 2.0;

    //UFO
    public static final int NODE_RADIUS = 160;
    public static final double UFO_MASS = 60;
    public static final int UFO_MAX_VEL = 3;
    public static final long UFO_FIRE_RATE = 1500;
    public static final double UFO_ANGLE_RANGE = Math.PI/2;

    //Score
    public static final int UFO_SCORE = 40;
    public static final int METEOR_SCORE = 20;
}
