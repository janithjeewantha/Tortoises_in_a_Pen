package tortoisesinapen.controller;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import tortoisesinapen.model.Tortoise;

public class Utils {
    
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;    
    
    private static Utils instance;
    private Rectangle canvasBounds;
    private Rectangle movableBounds;
    private int tortoiseSize;
    private List<Tortoise> tortoiseList = new ArrayList<>();

    private Utils() {
    }
    
    public static Utils getInstance(){
        if(instance == null){
            instance = new Utils();
        }
        return instance;
    }
    
    public Rectangle getMaximumWindowBounds(){
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        setCanvasBounds(env.getMaximumWindowBounds());
        return env.getMaximumWindowBounds();
    }

    private void setCanvasBounds(Rectangle bounds) {
        this.canvasBounds = bounds;
        this.tortoiseSize = (int) (bounds.getHeight() / 10);
        this.movableBounds = new Rectangle(bounds.width - tortoiseSize, bounds.height - tortoiseSize);
    }

    public void addTortoise(Tortoise tortoise){
        this.tortoiseList.add(tortoise);
    }
    
    public Rectangle getCanvasBounds() {
        return canvasBounds;
    }

    public int getCanvasWidth() {
        return this.canvasBounds.width;
    }

    public int getCanvasHeight() {
        return this.canvasBounds.height;
    }

    public int getMovableX() {
        return this.movableBounds.width;
    }

    public int getMovableY() {
        return this.movableBounds.height;
    }

    public int getTortoiseSize() {
        return tortoiseSize;
    }

    public List<Tortoise> getTortoiseList() {
        return tortoiseList;
    }

    public void initialize() {
        getMaximumWindowBounds();
    }
    
}
