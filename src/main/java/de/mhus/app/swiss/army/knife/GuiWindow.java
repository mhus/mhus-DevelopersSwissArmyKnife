package de.mhus.app.swiss.army.knife;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import de.mhus.lib.core.MSwing;

public class GuiWindow {

    private JFrame frame;
    private GuiFrame gui;

    public GuiWindow(GuiFrame gui, Section section) {
        this.gui = gui;
        gui.register(this);
        
        frame = new JFrame();

        MSwing.halfFrame(frame);
        gui.setNextPosition(frame);
        
        frame.setTitle(section.getTitle());
        
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
                frame.setVisible(false);
                gui.unregister(GuiWindow.this);
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

        frame.getContentPane().add(section.createComponent(this));
        frame.setVisible(true);
        
    }

}
