package tortoisesinapen.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
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
    private Random random = new Random();
    
    public void stepForward(){
        switch (direction){
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
        
        if(this.x < 0){
            this.x = 0;
        }else if(this.x > (Utils.getInstance().getCanvasWidth() - this.width)){
            this.x = Utils.getInstance().getCanvasWidth() - this.width;
        }
        
        if(this.y < 0){
            this.y = 0;
        }else if(this.y > (Utils.getInstance().getCanvasHeight() - this.height)){
            this.y = Utils.getInstance().getCanvasHeight()- this.height;
        }
                
    }
    
    public void changeDirection(){
        int newDirection = direction;
        int temp = random.nextInt(2);
        
        double angle;
        double locationX = image.getHeight(null)/2;
        double locationY = image.getWidth(null)/2;
        
        if(temp%2 == 0){
            newDirection++;
            angle = Math.PI/2;
        }else{
            newDirection--;
            angle = (Math.PI/2)*3;
        }
        newDirection = (newDirection < Utils.UP) ? Utils.LEFT : newDirection;
        newDirection = (newDirection > Utils.LEFT) ? newDirection : Utils.UP;
        AffineTransform transform = AffineTransform.getRotateInstance(angle, locationX, locationY);
        ((Graphics2D) image.getGraphics()).setTransform(transform);
    }
    
    public int getNextXStep(){        
        int nextX = this.x;
        
        switch (direction){
            case Utils.LEFT:
                nextX -= speed;
                break;
            case Utils.RIGHT:
                nextX += speed;
                break;            
        }        
        
        if(nextX < 0){
            nextX = 0;
        }else if(nextX > Utils.getInstance().getMovableX()){
            nextX = Utils.getInstance().getMovableX();
        }
        
        return nextX;
    }
    
    public int getNextYStep(){        
        int nextY = this.y;
        
        switch (direction){
            case Utils.UP:
                nextY -= speed;
                break;
            case Utils.DOWN:
                nextY += speed;
                break;            
        }        
        
        if(nextY < 0){
            nextY = 0;
        }else if(nextY > Utils.getInstance().getMovableY()){
            nextY = Utils.getInstance().getMovableY();
        }
        
        return nextY;
    } 
    
    public Rectangle getRectangle(){
        return new Rectangle(x, y, width, height);
    }
    
    public void setSize(int width, int height){
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
    
}
