import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class GUI extends JFrame
{
	private static final long serialVersionUID = -4367874184643961328L;

	protected JPanel panel;
	
	protected JTextField directoryText, tagText;
	protected JButton fixButton, tagButton, cleanButton, untagButton;
	
	public GUI() {
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());} catch(Exception e) {}
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Fix and Tag Images");
		panel = new JPanel();
		setContentPane(panel);
		
		initComponents();
		setLayout();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void initComponents()
	{
		directoryText = new JTextField();
//		fixText.setColumns(60);
		tagText = new JTextField();
//		tagText.setColumns(60);
		
		fixButton = new JButton("Fix Medialink Images");
		fixButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Logic.fixButton(directoryText.getText());
			}
		});
		
		tagButton = new JButton("Tag");
		tagButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Logic.tagButton(directoryText.getText(),tagText.getText());
			}
		});
		
		untagButton = new JButton("Untag");
		untagButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Logic.untagButton(directoryText.getText(),tagText.getText());
			}
		});
		
		cleanButton = new JButton("Clean Filenames");
		cleanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Logic.cleanButton(directoryText.getText());
			}
		});
	}

	private void setLayout() {
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		int fieldWidth = 600;
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(directoryText,fieldWidth,fieldWidth,fieldWidth)
						.addComponent(fixButton,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup()
						.addComponent(tagText,fieldWidth,fieldWidth,fieldWidth)
						.addComponent(tagButton,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
						.addComponent(untagButton,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE))
				.addComponent(cleanButton));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(directoryText)
						.addComponent(fixButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(tagText)
						.addComponent(tagButton)
						.addComponent(untagButton))
				.addComponent(cleanButton));
	}
}