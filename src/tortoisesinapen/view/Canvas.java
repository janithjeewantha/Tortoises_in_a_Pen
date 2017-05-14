package tortoisesinapen.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;
import tortoisesinapen.controller.TortoiseFactory;
import tortoisesinapen.controller.Utils;
import tortoisesinapen.model.Tortoise;

public class Canvas extends JPanel implements ActionListener{

    private Image backgroundImage;
    private Timer timer;
    private final int DELAY = 50;
    private int numTortoises;

    Canvas(int numTortoises) {
        this.numTortoises = numTortoises;
        init();
    }
    
    public void setBackgroundImage(Image background) {
        this.backgroundImage = background;
    }

    public void init() {
        setBackground(Color.BLACK);
        TortoiseFactory factory = new TortoiseFactory();
        try {
            for(int i = 0; i < numTortoises; i++){
                Tortoise tortoise = factory.getTortoise();
                Utils.getInstance().addTortoise(tortoise);
            }
        } catch (IOException ex) {
            Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, ex);
        }
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        
        draw(g);
        
        Toolkit.getDefaultToolkit().sync();
    }

    private void draw(Graphics g) {
        List<Tortoise> tortoiseList = Utils.getInstance().getTortoiseList();
        int tortoiseSize = Utils.getInstance().getTortoiseSize();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        for(Tortoise t : tortoiseList){
            g2d.drawImage(t.getImage(), t.getX(), t.getY(), tortoiseSize, tortoiseSize, this);
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(Tortoise t : Utils.getInstance().getTortoiseList()){
            t.stepForward();
        }
        repaint();
    }
    
}
