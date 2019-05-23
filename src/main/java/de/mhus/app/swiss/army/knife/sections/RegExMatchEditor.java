package de.mhus.app.swiss.army.knife.sections;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

public class RegExMatchEditor extends JPanel {

    private static final long serialVersionUID = 1L;
    private static JTextArea from;
	private static JTextArea rule;
	private static JLabel result;

	public RegExMatchEditor() {

		 from = new JTextArea();
		 from.setText( "Source text" );
		 rule = new JTextArea();
		 rule.setText( ".*" );
		 result = new JLabel();
		JButton bExecute = new JButton( " Execute " );
		setLayout( new BorderLayout() );
		JPanel panel2 = new JPanel();
		panel2.setLayout( new GridLayout( 1, 1 ) );
		panel2.add( bExecute );
		add( panel2, BorderLayout.NORTH );
		
      JSplitPane split = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
        split.setTopComponent( new JScrollPane( from ));
        split.setBottomComponent( new JScrollPane( rule ) );
        split.setOneTouchExpandable(true);
        split.setResizeWeight(0.5);
        add( split, BorderLayout.CENTER );

		add( result, BorderLayout.SOUTH);
		
		bExecute.addActionListener( new ActionListener() {

			@Override
            public void actionPerformed(ActionEvent e) {
				try {
					result.setText( 
							from.getText().matches( rule.getText() ) ? "Match" : "Not match"
							);
				} catch ( Exception ex ) {
					result.setText( "ERROR: " + ex.getMessage() );
				}
			}
			
		});
	}

}
