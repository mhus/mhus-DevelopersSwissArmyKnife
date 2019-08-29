package de.mhus.app.swiss.army.knife.sections;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.mhus.lib.core.MString;
import de.mhus.lib.core.util.Base64;

public class Base64Editor extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextArea from;
    private JComboBox<String> type;
    private static JTextArea result;

    public Base64Editor() {
        
        from = new JTextArea();
        from.setMaximumSize(new Dimension(100, 300));
        type = new JComboBox<>();
        type.addItem("Encode");
        type.addItem("Decode");
        result = new JTextArea();
        JButton bExecute = new JButton( " Execute " );
        bExecute.addActionListener(e -> doExecute());
        setLayout( new BorderLayout() );
        JPanel panel2 = new JPanel();
        panel2.setLayout( new GridLayout( 3, 1 ) );
        panel2.add( from );
        panel2.add( type );
        panel2.add( bExecute );
        add( panel2, BorderLayout.NORTH );

        add( result, BorderLayout.CENTER );

        
    }

    private void doExecute() {
        try {
            String out = "";
            switch (type.getSelectedItem().toString()) {
            case "Encode":
                out = Base64.encode(from.getText());
                break;
            case "Decode":
                out = MString.toString(Base64.decode(from.getText()));
                break;
            }
            
            result.setText(out);
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(this, t.toString(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

}
