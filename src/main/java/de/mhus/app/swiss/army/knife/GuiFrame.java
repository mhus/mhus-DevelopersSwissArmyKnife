package de.mhus.app.swiss.army.knife;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import de.mhus.lib.core.MSwing;

public class GuiFrame {

	private ImageIcon icon;
	private String title = "TheKnife";

	public GuiFrame() {
		
		JPanel panel = new JPanel();
		
		JFrame frame = new JFrame();
		
		MSwing.halfFrame(frame);
		MSwing.centerFrame(frame);
		
		frame.getContentPane().add(panel);
//		if ( frame.getIconImage() != null )
//			icon = new ImageIcon( frame.getIconImage() );
		frame.addWindowListener(new WindowListener() {

			public void windowActivated(WindowEvent e) {
			}

			public void windowClosed(WindowEvent e) {
			}

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			public void windowDeactivated(WindowEvent e) {
			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowOpened(WindowEvent e) {
			}

		});

		// put frame into a list of available windows
		JMenuItem item = new JMenuItem(title);
		if ( icon != null ) item.setIcon(icon);
//		item.addActionListener(new WindowActionListener(panel));
//		windowManagerMenu.add(item);
		JButton button = new JButton( title );
		button.setMaximumSize(new Dimension(150,40));
		button.setToolTipText(title);
		button.setMargin(new Insets(2,2,2,2));
		if ( icon != null ) button.setIcon(icon);
//		button.addActionListener(new WindowActionListener(panel));
//		windowManagerPanel.add(button);
//		windowList.put(panel, new Object[] { frame, item, button });

		frame.setVisible(true);

	}
}
