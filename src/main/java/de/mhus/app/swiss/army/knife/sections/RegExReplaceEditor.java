package de.mhus.app.swiss.army.knife.sections;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RegExReplaceEditor extends JPanel {

    private static final long serialVersionUID = 1L;
    private static JTextArea from;
	private static JTextField rule;
	private static JTextField repl;
	private static JTextArea result;

	public RegExReplaceEditor() {

		 from = new JTextArea();
		 from.setText( "Source text" );
		 rule = new JTextField();
		 rule.setText( ".*" );
		 repl = new JTextField();
		 repl.setText( "Replace" );
		 result = new JTextArea();
		JButton bExecute = new JButton( " Execute " );
		setLayout( new BorderLayout() );
		JPanel panel2 = new JPanel();
		panel2.setLayout( new GridLayout( 3, 1 ) );
		panel2.add( bExecute );
		panel2.add( rule );
		panel2.add( repl );
		add( panel2, BorderLayout.NORTH );
		JSplitPane split = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
		split.setTopComponent( new JScrollPane( from ));
		split.setBottomComponent( new JScrollPane( result ) );
        split.setOneTouchExpandable(true);
        split.setResizeWeight(0.5);
		add( split, BorderLayout.CENTER );
		
		bExecute.addActionListener( new ActionListener() {

			@Override
            public void actionPerformed(ActionEvent e) {
				try {
					result.setText( 
							from.getText().replaceAll( rule.getText(), repl.getText() )
							);
				} catch ( Exception ex ) {
					result.setText( "ERROR: " + ex.getMessage() );
				}
			}
			
		});
	}

}
