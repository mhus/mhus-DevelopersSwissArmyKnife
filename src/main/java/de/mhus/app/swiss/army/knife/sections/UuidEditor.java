package de.mhus.app.swiss.army.knife.sections;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UuidEditor extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextArea from;
    private JComboBox<String> type;
    private static JTextArea result;

    public UuidEditor() {
        
        from = new JTextArea();
        from.setMaximumSize(new Dimension(100, 300));
        type = new JComboBox<>();
        type.addItem("Validate");
        type.addItem("Generate");
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
            case "Validate":
                try {
                    UUID uuid = UUID.fromString(from.getText());
                    out = uuid + "\n";
                    out = out + "Least: " + uuid.getLeastSignificantBits() + "\n";
                    out = out + "Most : " + uuid.getMostSignificantBits() + "\n";
                    try {
                        out = out + "Clock    : " + uuid.clockSequence() + "\n";
                    } catch (Throwable e) {}
                    try {
                        out = out + "Timestamp: " + uuid.timestamp() + "\n";
                    } catch (Throwable e) {}
                    try {
                        out = out + "Variant  : " + uuid.variant() + "\n";
                    } catch (Throwable e) {}
                    try {
                        out = out + "Version  : " + uuid.version();
                    } catch (Throwable e) {}
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    out = e.toString();
                }
                break;
            case "Generate":
                out = "";
                for (int i = 0; i < 10; i++)
                    out = out + UUID.randomUUID() + "\n";
                break;
            }
            
            result.setText(out);
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(this, t.toString(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

}
