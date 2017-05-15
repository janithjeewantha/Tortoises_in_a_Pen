package tortoisesinapen.controller;

import java.awt.Rectangle;
import java.util.List;
import tortoisesinapen.model.Tortoise;

public class TortoiseController {
    
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
            for(Tortoise t_temp : tortoiseList){
                if(t == t_temp){
                    continue;
                }
                if(t.getNextPositionRectangle().intersects(
                        t_temp.getNextPositionRectangle())){
                    t.changeDirection();
                    break;
                }
            }
            t.stepForward();
        }
                
    }
    
}
