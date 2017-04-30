package de.dfki.gui.renderers;

/**
 * Created by alvaro on 4/30/17.
 */
public class ImageLocator {
    public static final int MAX_VALUE_MOOD = 10;
    public static final int THREADSHOLD = 3;
    private static final int MID_VALUE_MOOD = MAX_VALUE_MOOD - THREADSHOLD;

    public String getImage(int value) throws Exception {
        if( (MAX_VALUE_MOOD - value) <= THREADSHOLD){
            return "imgs/happy.jpg";
        }else if((MID_VALUE_MOOD - value) <= THREADSHOLD){
            return "imgs/worried.png";
        }else if(value <= THREADSHOLD){
            return "imgs/dead.png";
        }
        throw new Exception("Value was not found");
    }
}
