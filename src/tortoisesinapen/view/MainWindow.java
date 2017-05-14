package tortoisesinapen.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tortoisesinapen.controller.Utils;

public class MainWindow extends JFrame{

    private Canvas canvas;
    private int numTortoises;
    
    public MainWindow() {
        initComponents();        
    }

    private void initComponents() {
        numTortoises = getNumOfTortoises();
        canvas = new Canvas(numTortoises);
        canvas.setDoubleBuffered(true);
        add(canvas);
        
        setBounds(Utils.getInstance().getMaximumWindowBounds());
        setResizable(false);
        
        setTitle("Tortoises In A Pen");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    private int getNumOfTortoises() {
        String input = JOptionPane.showInputDialog(this,
                "Enter the number of Tortoises:\nMaximum is 20",
                "", JOptionPane.QUESTION_MESSAGE);
        int number = 1;
        try{
            number = Integer.parseInt(input);
        }catch(NumberFormatException e){            
        }
        number = number < 1 ? 1 : number;
        number = number > 20 ? 20 : number;
        return number;
    }
    
}
