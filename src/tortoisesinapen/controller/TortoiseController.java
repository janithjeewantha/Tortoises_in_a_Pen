package tortoisesinapen.controller;

import java.util.List;
import tortoisesinapen.model.Tortoise;

public class TortoiseController {
    
    public static void move() {
        List<Tortoise> tortoiseList = Utils.getInstance().getTortoiseList();
        for(Tortoise t : tortoiseList){
            t.stepForward();
//            t.setX(t.getNextXStep());
//            t.setY(t.getNextYStep());
        }
    }
    
}
