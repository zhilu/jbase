package corejava.swing;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.plaf.LayerUI;

/**
 * This program demonstrates how a layer can decorate a Swing component.
 * 
 * @version 1.0 2012-06-08
 * @author Cay Horstmann
 */
public class LayerTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new ColorFrame();
				frame.setTitle("LayerTest");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

/**
 * A frame with three text fields to set the background color.
 */

@SuppressWarnings("serial")
class ColorFrame extends JFrame {
	private JPanel panel;
	private JTextField redField;
	private JTextField greenField;
	private JTextField blueField;

	public ColorFrame() {
		panel = new JPanel();

		panel.add(new JLabel("Red:"));
		redField = new JTextField("255", 3);
		panel.add(redField);

		panel.add(new JLabel("Green:"));
		greenField = new JTextField("255", 3);
		panel.add(greenField);

		panel.add(new JLabel("Blue:"));
		blueField = new JTextField("255", 3);
		panel.add(blueField);

		LayerUI<JPanel> layerUI = new PanelLayer();
		JLayer<JPanel> layer = new JLayer<JPanel>(panel, layerUI);

		add(layer);
		pack();
	}

	class PanelLayer extends LayerUI<JPanel> {
		public void installUI(JComponent c) {
			super.installUI(c);
			((JLayer<?>) c).setLayerEventMask(AWTEvent.KEY_EVENT_MASK | AWTEvent.FOCUS_EVENT_MASK);
		}

		public void uninstallUI(JComponent c) {
			((JLayer<?>) c).setLayerEventMask(0);
			super.uninstallUI(c);
		}

		protected void processKeyEvent(KeyEvent e, JLayer<? extends JPanel> l) {
			l.repaint();
		}

		protected void processFocusEvent(FocusEvent e, JLayer<? extends JPanel> l) {
			if (e.getID() == FocusEvent.FOCUS_GAINED) {
				Component c = e.getComponent();
				c.setFont(getFont().deriveFont(Font.BOLD));
			}
			if (e.getID() == FocusEvent.FOCUS_LOST) {
				Component c = e.getComponent();
				c.setFont(getFont().deriveFont(Font.PLAIN));
			}
		}

		public void paint(Graphics g, JComponent c) {
			super.paint(g, c);

			Graphics2D g2 = (Graphics2D) g.create();
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .3f));
			//bug :: 字符串不是数字或是0都有错, 范围限定
			
			
			int red =0,green =0,blue =0;
			Pattern pattern = Pattern.compile(("[0-9]+"));
			String stred = redField.getText().trim();
			Matcher m = pattern.matcher(stred);
			if(m.matches()){
				red = Integer.parseInt(stred.equals("")?"0":stred);
				if(red<0){
					red = 0;
				}else if (red >255){
					red =255;
				}
			}
			
			String stgreen = greenField.getText().trim();
			m = pattern.matcher(stgreen);
			if(m.matches()){
				green = Integer.parseInt(stgreen.equals("")?"0":stgreen);
				if(green<0){
					green = 0;
				}else if (green >255){
					green =255;
				}
			}
			
			String stblue = blueField.getText().trim();
			m = pattern.matcher(stblue);
			if(m.matches()){
				blue = Integer.parseInt(stblue.equals("")?"0":stblue);
				if(blue<0){
					blue = 0;
				}else if (blue >255){
					blue =255;
				}
			}
			
			g2.setPaint(new Color(red, green, blue));
			g2.fillRect(0, 0, c.getWidth(), c.getHeight());
			g2.dispose();
		}
	}
}
