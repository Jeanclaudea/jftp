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

import com.myjavaworld.jftp.ChangeRemoteDirectoryDlg;
import com.myjavaworld.jftp.FTPSession;
import com.myjavaworld.jftp.JFTP;

/**
 * An action implementation for changing the remote directory.
 * 
 * @author Sai Pullabhotla, psai [at] jMethods [dot] com
 * @version 2.0
 * 
 */
public class ChangeRemoteDirectoryAction implements ActionListener {

	private JFTP jftp = null;
	private static ChangeRemoteDirectoryAction instance = null;

	private ChangeRemoteDirectoryAction(JFTP jftp) {
		this.jftp = jftp;
	}

	public static synchronized ChangeRemoteDirectoryAction getInstance(JFTP jftp) {
		if (instance == null) {
			if (instance == null) {
				instance = new ChangeRemoteDirectoryAction(jftp);
			}
		}
		return instance;
	}

	public void actionPerformed(ActionEvent evt) {
		FTPSession session = jftp.getCurrentSession();
		if (session == null || !session.isConnected()) {
			return;
		}
		ChangeRemoteDirectoryDlg dlg = new ChangeRemoteDirectoryDlg(jftp);
		dlg.setLocationRelativeTo(jftp);
		dlg.setVisible(true);
		String directory = dlg.getDirectory();
		dlg.dispose();
		if (directory != null) {
			session.setRemoteWorkingDirectory(directory);
		}
	}
}
