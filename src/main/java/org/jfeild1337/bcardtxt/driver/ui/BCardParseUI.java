package org.jfeild1337.bcardtxt.driver.ui;

/**
 * Simple UI for exercising the BusinessCardParser. Built using the Eclipse 
 * WindowBuilder tool.
 * 
 * @author jfeild
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Component;
import javax.swing.Box;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BCardParseUI extends JFrame {

	private JPanel layoutMain;
	private JTextField txtParsedName;
	private JTextField txtParsedPhoneNumber;
	private JTextField txtParsedEmailAddr;
	private JButton btnParse;
	private JButton btnClearAll;
	private JTextPane txtInput;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BCardParseUI frame = new BCardParseUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BCardParseUI() {
                setTitle("Business Card Parser");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setPreferredSize(new Dimension(350, 350));
		setBounds(100, 100, 450, 450);
		layoutMain = new JPanel();
		layoutMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(layoutMain);
		layoutMain.setLayout(new BoxLayout(layoutMain, BoxLayout.Y_AXIS));
		
		JPanel layoutHeader = new JPanel();
		layoutMain.add(layoutHeader);
		layoutHeader.setLayout(new BoxLayout(layoutHeader, BoxLayout.X_AXIS));
		
		JLabel lblBusinessCardData = new JLabel("Business Card Data:");
		layoutHeader.add(lblBusinessCardData);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		layoutHeader.add(horizontalGlue);
		
		JScrollPane inputScrollPane = new JScrollPane();
		inputScrollPane.setEnabled(true);
		layoutMain.add(inputScrollPane);
		
		txtInput = new JTextPane();
		inputScrollPane.setViewportView(txtInput);
		
		JPanel layoutActionButtons = new JPanel();
		layoutMain.add(layoutActionButtons);
		layoutActionButtons.setLayout(new BoxLayout(layoutActionButtons, BoxLayout.X_AXIS));
		
		btnParse = new JButton("Parse Text");
		btnParse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		layoutActionButtons.add(btnParse);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(5, 0));
		horizontalStrut.setMinimumSize(new Dimension(5, 0));
		horizontalStrut.setMaximumSize(new Dimension(5, 1));
		layoutActionButtons.add(horizontalStrut);
		
		btnClearAll = new JButton("Clear");
		layoutActionButtons.add(btnClearAll);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		layoutActionButtons.add(horizontalGlue_1);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 10));
		verticalStrut.setMinimumSize(new Dimension(0, 10));
		verticalStrut.setMaximumSize(new Dimension(32767, 10));
		layoutMain.add(verticalStrut);
		
		JPanel outputGrid = new JPanel();
		outputGrid.setMaximumSize(new Dimension(32767, 75));
		layoutMain.add(outputGrid);
		GridBagLayout gbl_outputGrid = new GridBagLayout();
		gbl_outputGrid.columnWidths = new int[]{0, 0, 0};
		gbl_outputGrid.rowHeights = new int[]{0, 0, 0, 0};
		gbl_outputGrid.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_outputGrid.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		outputGrid.setLayout(gbl_outputGrid);
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		outputGrid.add(lblName, gbc_lblName);
		
		txtParsedName = new JTextField();
		GridBagConstraints gbc_txtParsedName = new GridBagConstraints();
		gbc_txtParsedName.insets = new Insets(0, 0, 5, 0);
		gbc_txtParsedName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtParsedName.gridx = 1;
		gbc_txtParsedName.gridy = 0;
		outputGrid.add(txtParsedName, gbc_txtParsedName);
		txtParsedName.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		GridBagConstraints gbc_lblPhoneNumber = new GridBagConstraints();
		gbc_lblPhoneNumber.anchor = GridBagConstraints.EAST;
		gbc_lblPhoneNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhoneNumber.gridx = 0;
		gbc_lblPhoneNumber.gridy = 1;
		outputGrid.add(lblPhoneNumber, gbc_lblPhoneNumber);
		
		txtParsedPhoneNumber = new JTextField();
		GridBagConstraints gbc_textParsedPhoneNumber = new GridBagConstraints();
		gbc_textParsedPhoneNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_textParsedPhoneNumber.insets = new Insets(0, 0, 5, 0);
		gbc_textParsedPhoneNumber.gridx = 1;
		gbc_textParsedPhoneNumber.gridy = 1;
		outputGrid.add(txtParsedPhoneNumber, gbc_textParsedPhoneNumber);
		txtParsedPhoneNumber.setColumns(10);
		
		JLabel lblEmailAddress = new JLabel("Email Address:");
		GridBagConstraints gbc_lblEmailAddress = new GridBagConstraints();
		gbc_lblEmailAddress.anchor = GridBagConstraints.EAST;
		gbc_lblEmailAddress.insets = new Insets(0, 0, 0, 5);
		gbc_lblEmailAddress.gridx = 0;
		gbc_lblEmailAddress.gridy = 2;
		outputGrid.add(lblEmailAddress, gbc_lblEmailAddress);
		
		txtParsedEmailAddr = new JTextField();
		GridBagConstraints gbc_txtPArsedEmailAddr = new GridBagConstraints();
		gbc_txtPArsedEmailAddr.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPArsedEmailAddr.gridx = 1;
		gbc_txtPArsedEmailAddr.gridy = 2;
		outputGrid.add(txtParsedEmailAddr, gbc_txtPArsedEmailAddr);
		txtParsedEmailAddr.setColumns(10);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setPreferredSize(new Dimension(0, 10));
		verticalStrut_1.setMinimumSize(new Dimension(0, 10));
		verticalStrut_1.setMaximumSize(new Dimension(32767, 10));
		layoutMain.add(verticalStrut_1);
	}

	public JPanel getLayoutMain() {
		return layoutMain;
	}

	public void setLayoutMain(JPanel layoutMain) {
		this.layoutMain = layoutMain;
	}

	public JTextField getTxtParsedName() {
		return txtParsedName;
	}

	public void setTxtParsedName(JTextField txtParsedName) {
		this.txtParsedName = txtParsedName;
	}

	public JTextField getTxtParsedPhoneNumber() {
		return txtParsedPhoneNumber;
	}

	public void setTxtParsedPhoneNumber(JTextField textParsedPhoneNumber) {
		this.txtParsedPhoneNumber = textParsedPhoneNumber;
	}

	public JTextField getTxtParsedEmailAddr() {
		return txtParsedEmailAddr;
	}

	public void setTxtParsedEmailAddr(JTextField txtParsedEmailAddr) {
		this.txtParsedEmailAddr = txtParsedEmailAddr;
	}

	public JButton getBtnParse() {
		return btnParse;
	}

	public void setBtnParse(JButton btnParse) {
		this.btnParse = btnParse;
	}

	public JButton getBtnClearAll() {
		return btnClearAll;
	}

	public void setBtnClearAll(JButton btnClearAll) {
		this.btnClearAll = btnClearAll;
	}

	public JTextPane getTxtInput() {
		return txtInput;
	}

	public void setTxtInput(JTextPane txtInput) {
		this.txtInput = txtInput;
	}	

}

