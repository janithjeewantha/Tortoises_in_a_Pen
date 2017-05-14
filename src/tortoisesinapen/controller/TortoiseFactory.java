package tortoisesinapen.controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import tortoisesinapen.model.Tortoise;

public class TortoiseFactory {

    private Random random = new Random();
    private int tortoiseSize = Utils.getInstance().getTortoiseSize();
    private URL tortoiseResource;

    public TortoiseFactory() {
        tortoiseResource = getClass().getResource("/tortoisesinapen/view/img/tortoise.png");        
    }

    public Tortoise getTortoise() throws IOException{
        return createOne();
    }
    
    private Tortoise createOne() throws IOException {
        Tortoise tortoise = new Tortoise();
        List<Tortoise> tortoiseList = Utils.getInstance().getTortoiseList();
        Point point = null;

        mainLoop:
        while (true) {
            point = generateRandomPoint();
            Rectangle rect = new Rectangle(point.x, point.y, tortoiseSize, tortoiseSize);
            for (Tortoise t : tortoiseList) {
                if (rect.intersects(t.getRectangle())) {
                    continue mainLoop;
                }
            }
            tortoise.setX(point.x);
            tortoise.setY(point.y);
            break mainLoop;
        }
        tortoise.setDirection(random.nextInt(4));
        int speed = random.nextInt(4) + 1;
        tortoise.setSpeed(speed);
        tortoise.setSize(tortoiseSize, tortoiseSize);
        tortoise.setImage(getImage());
        return tortoise;
    }

    private Point generateRandomPoint() {
        int x = random.nextInt(Utils.getInstance().getMovableX());
        int y = random.nextInt(Utils.getInstance().getMovableY());
        return new Point(x, y);
    }

    private BufferedImage getImage() throws IOException{
        BufferedImage tortoiseImage = null;
        try {
            tortoiseImage = ImageIO.read(new File(tortoiseResource.getFile()));
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return tortoiseImage;
    }
}
