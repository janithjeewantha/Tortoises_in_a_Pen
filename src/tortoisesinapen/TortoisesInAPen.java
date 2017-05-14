package tortoisesinapen;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import tortoisesinapen.controller.Utils;
import tortoisesinapen.view.MainWindow;
import tortoisesinapen.view.Window;

public class TortoisesInAPen {

    private MainWindow mainWindow;

    public static void main(String[] args) {
        TortoisesInAPen tortoisesInAPen = new TortoisesInAPen();
        tortoisesInAPen.startProgram();
    }

    private void startProgram() {
        Utils.getInstance().initialize();
        setLookAndFeel();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mainWindow = new MainWindow();
                setupCanvas(mainWindow);
                
                mainWindow.setVisible(true);
            }
        });
        
    }

    private void setupCanvas(MainWindow mainWindow) {
        try {
            URL bgResource = getClass().getResource("/tortoisesinapen/view/img/grass-texture.jpg");
            Image background = ImageIO.read(new File(bgResource.getFile()));
            mainWindow.getCanvas().setBackgroundImage(background);
        } catch (IOException ex) {
            Logger.getLogger(TortoisesInAPen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setLookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

}
