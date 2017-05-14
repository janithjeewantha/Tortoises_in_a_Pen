package tortoisesinapen.model;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;
import tortoisesinapen.controller.Utils;

public class Tortoise {

    private int x;
    private int y;
    private int width;
    private int height;
    private int speed = 1;
    private int direction = Utils.UP;
    private Image image;
    private Image[] images;
    private Random random = new Random();
    private int directionChangeTimeout;
    private int speedChangeTimeout;

    public Tortoise() {
        directionChangeTimeout = Utils.getInstance().getRandomDirectionTimeout();
        speedChangeTimeout = Utils.getInstance().getRandomSpeedTimeout();
    }

    public void stepForward() {
        switch (direction) {
            case Utils.UP:
                this.y -= speed;
                break;
            case Utils.DOWN:
                this.y += speed;
                break;
            case Utils.RIGHT:
                this.x += speed;
                break;
            case Utils.LEFT:
                this.x -= speed;
                break;
        }
    }

    public void changeDirection() {
        int rand = random.nextInt(4);
        switch (direction) {
            case Utils.UP:
                if(rand == Utils.UP){
                    rand = Utils.DOWN;
                }
                break;
            case Utils.DOWN:
                if(rand == Utils.DOWN){
                    rand = Utils.UP;
                }
                break;
            case Utils.RIGHT:
                if(rand == Utils.RIGHT){
                    rand = Utils.LEFT;
                }
                break;
            case Utils.LEFT:
                if(rand == Utils.LEFT){
                    rand = Utils.RIGHT;
                }
                break;
        }
        direction = rand;
        rotate();
    }

    public void rotate() {
        switch (direction) {
            case Utils.UP:
                this.image = this.images[Utils.UP];
                break;
            case Utils.DOWN:
                this.image = this.images[Utils.DOWN];
                break;
            case Utils.RIGHT:
                this.image = this.images[Utils.RIGHT];
                break;
            case Utils.LEFT:
                this.image = this.images[Utils.LEFT];
                break;
        }
    }
    
    public Point getNextPosition() {
        return new Point(getNextXStep(), getNextYStep());
    }

    public int getNextXStep() {
        int nextX = this.x;

        switch (direction) {
            case Utils.LEFT:
                nextX -= speed;
                break;
            case Utils.RIGHT:
                nextX += speed;
                break;
        }

        return nextX;
    }

    public int getNextYStep() {
        int nextY = this.y;

        switch (direction) {
            case Utils.UP:
                nextY -= speed;
                break;
            case Utils.DOWN:
                nextY += speed;
                break;
        }

        return nextY;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }

    public Rectangle getNextPositionRectangle() {
        Point nextPosition = getNextPosition();
        Rectangle rect = new Rectangle(nextPosition);
        rect.setSize(width, height);
        return rect;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image[] getImages() {
        return images;
    }

    public void setImages(Image[] images) {
        this.images = images;
    }
    
    public int getDirectionChangeTimeout() {
        return directionChangeTimeout;
    }

    public int getSpeedChangeTimeout() {
        return speedChangeTimeout;
    }

    public void resetDirectionChangeTimeout() {
        this.directionChangeTimeout = Utils.getInstance().getRandomDirectionTimeout();
    }

    public void resetSpeedChangeTimeout() {
        this.speedChangeTimeout = Utils.getInstance().getRandomSpeedTimeout();
    }
    
    public void decreaseDirectionChangeTimeout() {
        this.directionChangeTimeout--;
    }

    public void decreaseSpeedChangeTimeout() {
        this.speedChangeTimeout--;
    }

    public void changeSpeed() {
        this.speed = Utils.getInstance().getRandomSpeed();
    }

}
