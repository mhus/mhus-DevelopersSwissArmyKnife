package de.mhus.app.swiss.army.knife.sections;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.codec.Charsets;

public class ObfuscateStringEditor extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField from;
    private JComboBox<String> type;
    private static JTextArea result;

    public ObfuscateStringEditor() {
        
        from = new JTextField();
        type = new JComboBox<>();
        type.addItem("Java");
        type.addItem("C#");
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
        case "Java":
            out = obfuscateJava(from.getText());
            break;
        case "C#":
            out = obfuscateCSharp(from.getText());
            break;
        }
        
        result.setText(out);
    }

    private String obfuscateJava(String text) {
        // idea from https://github.com/shamanland/simple-string-obfuscator
        Random r = new Random(System.currentTimeMillis());
        byte[] b = text.getBytes(Charsets.UTF_8);
        int c = b.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream o = new PrintStream(out);

        o.print("(new Object() {");
        o.print("int t;");
        o.print("@Override public String toString() {");
        o.print("byte[] buf = new byte[");
        o.print(c);
        o.println("];");

        for (int i = 0; i < c; ++i) {
            int t = r.nextInt();
            int f = r.nextInt(24) + 1;

            t = (t & ~(0xff << f)) | (b[i] << f);

            o.print("t = ");
            o.print(t);
            o.println(";");
            o.print("buf[");
            o.print(i);
            o.print("] = (byte) (t >>> ");
            o.print(f);
            o.println(");");
        }

        o.print("return new String(buf);");
        o.print("}}.toString());");
        o.println();
        
        o.flush();
        
        return new String(out.toByteArray(), Charsets.UTF_8);
    }
    
    private String obfuscateCSharp(String text) {
        // idea from https://github.com/shamanland/simple-string-obfuscator
        Random r = new Random(System.currentTimeMillis());
        byte[] b = text.getBytes(Charsets.UTF_8);
        int c = b.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream o = new PrintStream(out);

        o.println("Encoding.UTF8.GetString(new byte[] {");

        for (int i = 0; i < c; ++i) {
            int t = r.nextInt();
            int f = r.nextInt(24) + 1;

            t = (t & ~(0xff << f)) | (b[i] << f);

            o.print("  ");
            if (i != 0)
                o.print(",");
            o.print("(byte)((");
            o.print(t);
            o.print(" >> ");
            o.print(f);
            o.println(") & 0x000000FF)");
        }

        o.print("});");
        o.println();
        
        o.flush();
        
        return new String(out.toByteArray(), Charsets.UTF_8);
    }

}
