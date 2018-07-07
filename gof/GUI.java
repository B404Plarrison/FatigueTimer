import java.awt.*;
import javax.swing.*;
public class GUI {
	public static void main(String[] args) {
		String Frame=JOptionPane.showInputDialog("Player?");
		JFrame window=new JFrame(Frame);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FatiguePanel winner=new FatiguePanel();
		
		window.getContentPane().add(winner);
		window.pack();
		window.setVisible(true);
	}
}
