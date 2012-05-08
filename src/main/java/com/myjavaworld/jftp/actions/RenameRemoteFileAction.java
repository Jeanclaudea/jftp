/*
 * Copyright 2012 jMethods, Inc. 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.myjavaworld.jftp.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.myjavaworld.ftp.RemoteFile;
import com.myjavaworld.jftp.FTPSession;
import com.myjavaworld.jftp.JFTP;
import com.myjavaworld.jftp.RenameRemoteFileDlg;

/**
 * An action used by the Rename Local File menu item and tool bar button.
 * 
 * @author Sai Pullabhotla, psai [at] jMethods [dot] com
 * @version 2.0
 * 
 */
public class RenameRemoteFileAction implements ActionListener {

	private JFTP jftp = null;
	private static RenameRemoteFileAction instance = null;

	private RenameRemoteFileAction(JFTP jftp) {
		this.jftp = jftp;
	}

	public static synchronized RenameRemoteFileAction getInstance(JFTP jftp) {
		if (instance == null) {
			if (instance == null) {
				instance = new RenameRemoteFileAction(jftp);
			}
		}
		return instance;
	}

	public void actionPerformed(ActionEvent evt) {
		FTPSession session = jftp.getCurrentSession();
		if (session == null || !session.isConnected()) {
			return;
		}
		String fromName = "";
		String toName = "";
		RemoteFile from = session.getSelectedRemoteFile();
		if (from != null) {
			fromName = from.getName();
		}
		RenameRemoteFileDlg dlg = new RenameRemoteFileDlg(jftp, fromName);
		dlg.setLocationRelativeTo(jftp);
		dlg.setVisible(true);
		fromName = dlg.getFromFile();
		toName = dlg.getToFile();
		dlg.dispose();
		if (!(fromName == null && toName == null)) {
			session.renameRemoteFile(fromName, toName);
		}
	}
}
