import java.awt.*;
import javax.swing.*;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
 
public class LoadImage extends JFrame {
  

public LoadImage(String imgStr,Mat m) {

	Highgui.imwrite(imgStr,m);
	JFrame frame = new JFrame("My GUI");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	frame.setResizable(true);
	frame.setLocationRelativeTo(null);
	 
	// Inserts the image icon
	ImageIcon image = new ImageIcon(imgStr);
	frame.setSize(image.getIconWidth()+10,image.getIconHeight()+35);
	// Draw the Image data into the BufferedImage
	JLabel label1 = new JLabel(" ", image, JLabel.CENTER);
	frame.getContentPane().add(label1);
	 
	frame.validate();
	frame.setVisible(true);}
}
