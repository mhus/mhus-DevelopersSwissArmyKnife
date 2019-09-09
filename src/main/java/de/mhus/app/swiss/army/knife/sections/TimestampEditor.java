package de.mhus.app.swiss.army.knife.sections;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.mhus.lib.core.MCast;
import de.mhus.lib.core.MDate;

public class TimestampEditor extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextArea from;
    private JComboBox<String> type;
    private static JTextArea result;

    public TimestampEditor() {
        
        from = new JTextArea();
        from.setMaximumSize(new Dimension(100, 300));
        type = new JComboBox<>();
        type.addItem("To Date");
        type.addItem("To Timestamp");
        type.addItem("Max Values");
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
            case "To Date":
                long ts = MCast.tolong(from.getText(), 0);
                out = ts + "\n" + new Date(ts) + "\n" + MDate.toIso8601(ts);
                break;
            case "To Timestamp":
                out = "" + MCast.toDate(from.getText(), null);
                break;
            case "Max Values":
                out = Long.MIN_VALUE + "=" + new Date(Long.MIN_VALUE) + "\n" + 
                      Long.MAX_VALUE + "=" + new Date(Long.MAX_VALUE) + "\n" + 
                      new Date(-( 1980l * 24l * 31l * 24l * 60l * 60l * 1000l )) + "\n" +
                      "0=" + new Date(0);
                break;
            }
            
            result.setText(out);
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(this, t.toString(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

}
