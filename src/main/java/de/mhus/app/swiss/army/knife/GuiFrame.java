package de.mhus.app.swiss.army.knife;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashSet;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import de.mhus.app.swiss.army.knife.sections.Base64Section;
import de.mhus.app.swiss.army.knife.sections.ObfuscateStringSection;
import de.mhus.app.swiss.army.knife.sections.RegExMatchSection;
import de.mhus.app.swiss.army.knife.sections.RegExReplaceSection;
import de.mhus.app.swiss.army.knife.sections.ScriptSection;
import de.mhus.app.swiss.army.knife.sections.TimestampSection;
import de.mhus.app.swiss.army.knife.sections.Utf8Section;
import de.mhus.app.swiss.army.knife.sections.UuidSection;
import de.mhus.app.swiss.army.knife.sections.XPathSection;
import de.mhus.lib.core.MSwing;

public class GuiFrame {

	private static final int WINDOW_POS_X_START = 40;
    private static final int WINDOW_POS_Y_START = 40;
    private String title = "The Knife";
    private JPanel mainPanel;
    private JPanel sectionsPanel;
    private JScrollPane sectionsScroll;
    private LinkedList<Section> sections;
    private JFrame frame;
    private HashSet<GuiWindow> windows = new HashSet<>();
    private int nextPosX = WINDOW_POS_X_START;
    private int nextPosY = WINDOW_POS_Y_START;

	public GuiFrame() {
		
	    collectSections();

		frame = new JFrame();
		
		MSwing.halfFrame(frame);
        frame.setSize(300, frame.getHeight());
		setNextPosition(frame);
		
//		if ( frame.getIconImage() != null )
//			icon = new ImageIcon( frame.getIconImage() );
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowListener() {

			@Override
            public void windowActivated(WindowEvent e) {
			}

			@Override
            public void windowClosed(WindowEvent e) {
			}

			@Override
            public void windowClosing(WindowEvent e) {
			    if ( JOptionPane.showConfirmDialog(frame, "Really close application", title, JOptionPane.WARNING_MESSAGE) != JOptionPane.OK_OPTION)
			        return;
				System.exit(0);
			}

			@Override
            public void windowDeactivated(WindowEvent e) {
			}

			@Override
            public void windowDeiconified(WindowEvent e) {
			}

			@Override
            public void windowIconified(WindowEvent e) {
			}

			@Override
            public void windowOpened(WindowEvent e) {
			}

		});

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		sectionsPanel = new JPanel();
		sectionsScroll = new JScrollPane(sectionsPanel);
		
		mainPanel.add(sectionsScroll, BorderLayout.CENTER);

        frame.getContentPane().add(mainPanel);
		frame.setVisible(true);

		updateMainPanel();
		
	}

    private void updateMainPanel() {
        sectionsPanel.removeAll();
        sectionsPanel.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(sections.size(), 1));
        
        for (Section section : sections) {
            JButton button = new JButton(section.getTitle());
            button.setSize(new Dimension(500, 50));
            button.addActionListener(l -> new GuiWindow(this, section ) );
            
            panel.add(button);
        }
        sectionsPanel.add(panel,BorderLayout.NORTH);
        mainPanel.updateUI();
    }

    public void register(GuiWindow window) {
        windows.add(window);
    }
    
    public void unregister(GuiWindow window) {
        windows.remove(window);
    }

    public void setNextPosition(JFrame frame) {
        
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (nextPosX + frame.getWidth() > screenSize.width) {
            nextPosX = WINDOW_POS_X_START;
            nextPosY = nextPosY + 100;
        }
            
        if (nextPosY + frame.getHeight() > screenSize.height) {
            nextPosY = WINDOW_POS_Y_START;
        }
        frame.setLocation(nextPosX, nextPosY);
        
        nextPosX = nextPosX + 300;
        nextPosY = nextPosY +15;
    }

    private void collectSections() {
        // TODO dynamic loading from dependencies
        sections = new LinkedList<>();
        sections.add(new RegExReplaceSection());
        sections.add(new RegExMatchSection());
        sections.add(new XPathSection());
        sections.add(new ScriptSection());
        sections.add(new ObfuscateStringSection());
        sections.add(new Utf8Section());
        sections.add(new Base64Section());
        sections.add(new TimestampSection());
        sections.add(new UuidSection());
    }


}
