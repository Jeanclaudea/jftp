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

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.myjavaworld.gui.GUIUtil;
import com.myjavaworld.jftp.FTPSession;
import com.myjavaworld.jftp.JFTP;
import com.myjavaworld.jftp.LocalFile;

/**
 * An Action Implementation for uploading a signle file with a different name.
 * 
 * @author Sai Pullabhotla, psai [at] jMethods [dot] com
 * @version 2.0
 * 
 */
public class EditLocalFileAction implements ActionListener {

	private JFTP jftp = null;
	private static EditLocalFileAction instance = null;

	private EditLocalFileAction(JFTP jftp) {
		this.jftp = jftp;
	}

	public static synchronized EditLocalFileAction getInstance(JFTP jftp) {
		if (instance == null) {
			if (instance == null) {
				instance = new EditLocalFileAction(jftp);
			}
		}
		return instance;
	}

	public void actionPerformed(ActionEvent evt) {
		final FTPSession session = jftp.getCurrentSession();
		if (session == null) {
			return;
		}
		final LocalFile selectedFile = session.getSelectedLocalFile();
		if (selectedFile == null || !selectedFile.isFile()) {
			return;
		}
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().edit(selectedFile.getFile());
			}
		} catch (Throwable t) {
			GUIUtil.showError(jftp, t);
		}
	}
}
