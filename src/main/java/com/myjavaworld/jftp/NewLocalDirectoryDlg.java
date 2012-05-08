/**
 *
 * This software is the confidential and proprietary information of the author,
 * Sai Pullabhotla. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement you
 * entered into with the author.
 *
 * THE AUTHOR MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. THE AUTHOR SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 *
 */
package com.myjavaworld.jftp;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.Box;
import javax.swing.BoxLayout;

import com.myjavaworld.gui.GUIUtil;
import com.myjavaworld.gui.MButton;
import com.myjavaworld.gui.MDialog;
import com.myjavaworld.gui.MLabel;
import com.myjavaworld.gui.MTextField;
import com.myjavaworld.util.CommonResources;
import com.myjavaworld.util.ResourceLoader;

/**
 * A dialog box that allows the users to specify the name of a directory that is
 * to be created.
 * 
 * @author Sai Pullabhotla, psai [at] jMethods [dot] com
 * @version 2.0
 * 
 */
public class NewLocalDirectoryDlg extends MDialog implements ActionListener {

	private static ResourceBundle resources = ResourceLoader
			.getBundle("com.myjavaworld.jftp.NewLocalDirectoryDlg");
	private static final String HELP_ID = "local.newDirectory";
	private MTextField tfDirectory = null;
	private MButton butCreate = null;
	private MButton butCancel = null;
	private MButton butHelp = null;
	private boolean approved = false;

	/**
	 * Creates an instance of <code>NewLocalDirectoryDlg</code>.
	 * 
	 * @param frame
	 *            Parent frame
	 * 
	 */
	public NewLocalDirectoryDlg(Frame frame) {
		super(frame, resources.getString("title.dialog"), true);
		getContentPane().setLayout(new GridBagLayout());
		JFTPHelp2.getInstance().enableHelpKey(getRootPane(), HELP_ID);
		initComponents();
		pack();
	}

	/**
	 * Returns the name of the directory as entered by the user.
	 * 
	 * @return Name of the directory
	 * 
	 */
	public String getDirectory() {
		if (approved) {
			return tfDirectory.getText();
		}
		return null;
	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == butCreate) {
			if (!validateInput()) {
				return;
			}
			approved = true;
			close();
		} else if (evt.getSource() == butCancel) {
			close();
		}
	}

	private void close() {
		setVisible(false);
	}

	@Override
	protected void escape() {
		butCancel.doClick();
	}

	private boolean validateInput() {
		String message = null;
		Component errorComponent = null;
		if (tfDirectory.getText().trim().length() == 0) {
			message = resources.getString("error.directory.required");
			errorComponent = tfDirectory;
		}
		if (message != null) {
			GUIUtil.showError(this, message);
			if (errorComponent != null) {
				errorComponent.requestFocusInWindow();
			}
			return false;
		}
		return true;
	}

	private void initComponents() {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;

		MLabel labDirectory = new MLabel(resources.getString("text.directory"));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(12, 12, 11, 11);
		getContentPane().add(labDirectory, c);

		tfDirectory = new MTextField(20);
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1.0;
		c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(12, 0, 11, 11);
		getContentPane().add(tfDirectory, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 0, 0, 0);
		getContentPane().add(new MLabel(), c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(6, 12, 11, 11);
		getContentPane().add(getCommandButtons(), c);
	}

	private Component getCommandButtons() {
		Box panel = new Box(BoxLayout.X_AXIS);
		butCreate = new MButton(resources.getString("text.create"));
		butCreate.addActionListener(this);
		getRootPane().setDefaultButton(butCreate);
		butCancel = new MButton(CommonResources.getString("text.cancel"));
		butCancel.addActionListener(this);
		butHelp = new MButton(CommonResources.getString("text.help"));
		JFTPHelp2.getInstance().enableHelp(butHelp, HELP_ID);

		panel.add(butCreate);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(butCancel);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(butHelp);

		return panel;
	}
}
