package hw.hw6;

import java.util.Collection;
import javax.swing.*;

@SuppressWarnings("serial")
public class FrameOutput extends JFrame implements WeatherOutput {   
	private DisplayPanel pnl;

    public FrameOutput(Collection<WeatherDisplay> displays, String title, ComponentStyler styler) {
   		for (WeatherDisplay wd : displays)
   			wd.setOutput(this);

   		setTitle(title);
   		setDefaultCloseOperation(EXIT_ON_CLOSE);
        pnl = new DisplayPanel(styler);
        setContentPane(pnl);
        pack();
        setVisible(true);
   }
   
   public void display(String s) {
   		pnl.display(s);
   }
}

@SuppressWarnings("serial")
class DisplayPanel extends JPanel {
   public JTextArea info;
	   
	   public DisplayPanel(ComponentStyler styler) {
            info = styler.createJTextArea(40, 30);
	   		add(info); 
	   }
	   
	   public void display(String s) {
	   		info.append(s + "\n");
	   }
}
