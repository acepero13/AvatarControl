package de.dfki.gui.renderers.moodbar;

/**
 * Created by alvaro on 4/30/17.
 */
public class MarkPositionCalculator {
    public static final int MAX_VALUE_MOOD = 10;
    private final double barWidth;

    public MarkPositionCalculator(double fitWidth){
        this.barWidth = fitWidth;
    }

    public String getImage(int value)  {

        return "";

    }

    public double calculatePosition(int value){
        double position = (barWidth * value) / MAX_VALUE_MOOD;
        return position;

    }
}
