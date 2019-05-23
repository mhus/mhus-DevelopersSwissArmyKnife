package de.mhus.app.swiss.army.knife.sections;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ScriptEditor extends JPanel {

    private static final long serialVersionUID = 1L;
    private static JTextArea script;
	private static JTextField engineName;
	private static JTextArea result;
	private static ScriptEngineManager manager = new ScriptEngineManager();

	public ScriptEditor() {

		 script = new JTextArea();
		 script.setText( "new Date().toString()" );
		 engineName = new JTextField();
		 engineName.setText( "js" );
		 result = new JTextArea();
		JButton bExecute = new JButton( " Execute " );
		setLayout( new BorderLayout() );
		JPanel panel2 = new JPanel();
		panel2.setLayout( new GridLayout( 3, 1 ) );
		panel2.add( bExecute );
		panel2.add( engineName );
		add( panel2, BorderLayout.NORTH );
		JSplitPane split = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
		split.setDividerLocation(300);
		split.setTopComponent( new JScrollPane( script ));
		split.setBottomComponent( new JScrollPane( result ) );
        split.setOneTouchExpandable(true);
        split.setResizeWeight(0.5);
		add( split, BorderLayout.CENTER );
		
		bExecute.addActionListener( new ActionListener() {

			@Override
            public void actionPerformed(ActionEvent e) {
				try {
					 ScriptEngine engine = manager.getEngineByName (engineName.getText());
					 Object ret = engine.eval(script.getText());
					 if (ret == null)
						 result.setText("[null]");
					 else
						 result.setText(ret.toString());
				} catch ( Throwable ex ) {
					result.setText( "ERROR: " + ex.getMessage() );
				}
			}
			
		});
	}

}
