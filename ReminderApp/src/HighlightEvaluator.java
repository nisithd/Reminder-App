import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;

import com.toedter.calendar.IDateEvaluator;

public class HighlightEvaluator implements IDateEvaluator {
    private final ArrayList<Date> list = new ArrayList<>();
    
    public void add(java.util.Date date) {
        list.add(date);
    }

    @Override
    public Color getInvalidBackroundColor() {
        return Color.BLACK;
    }

    @Override
    public Color getInvalidForegroundColor() {
        return null;
    }

    @Override
    public String getInvalidTooltip() {
        return null;
    }

    @Override
    public Color getSpecialBackroundColor() {
        return new Color(255,150,150);
    }

    @Override
    public Color getSpecialForegroundColor() {
        return Color.RED;
    }

    @Override
    public String getSpecialTooltip() {
        return "filled";
    }

    @Override
    public boolean isInvalid(Date date) {
        return false;
    }

    @Override
    public boolean isSpecial(java.util.Date date) {
        return list.contains(date);
    }
}
