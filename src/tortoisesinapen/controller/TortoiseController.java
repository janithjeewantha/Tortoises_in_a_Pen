package tortoisesinapen.controller;

import java.awt.Rectangle;
import java.util.List;
import tortoisesinapen.model.Tortoise;

public class TortoiseController {
    
    private static final int T_SIZE = Utils.getInstance().getTortoiseSize();
    
    public static void move() {
        List<Tortoise> tortoiseList = Utils.getInstance().getTortoiseList();

        for(Tortoise t : tortoiseList){
            if(t.getDirectionChangeTimeout() < 0){
                t.changeDirection();
                t.resetDirectionChangeTimeout();
            }else{
                t.decreaseDirectionChangeTimeout();
            }
            if(t.getSpeedChangeTimeout() < 0){
                t.changeSpeed();
                t.resetSpeedChangeTimeout();
            }else{
                t.decreaseSpeedChangeTimeout();
            }
            Rectangle nextRect = t.getNextPositionRectangle();
            if(!Utils.getInstance().getCanvasBounds().contains(nextRect)){
                t.changeDirection();
            }            
            t.stepForward();
        }        
                
    }
    
}
