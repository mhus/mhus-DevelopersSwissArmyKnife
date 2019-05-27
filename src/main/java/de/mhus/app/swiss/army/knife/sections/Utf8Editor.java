package de.mhus.app.swiss.army.knife.sections;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.mhus.lib.core.MString;

public class Utf8Editor extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField from;
    private JComboBox<String> type;
    private static JTextArea result;

    public Utf8Editor() {
        
        from = new JTextField();
        type = new JComboBox<>();
        type.addItem("Encode Unicode");
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
        String out = "";
        switch (type.getSelectedItem().toString()) {
        case "Encode Unicode":
            out = MString.encodeUnicode(from.getText());
            break;
        }
        result.setText(out);
    }

}
