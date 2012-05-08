/*
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
import java.net.URL;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.ResourceBundle;

import com.myjavaworld.gui.GUIUtil;
import com.myjavaworld.util.ResourceLoader;

/**
 * @author Sai Pullabhotla, psai [at] jMethods [dot] com
 * @version 1.0
 */
public class AutoUpdater extends Thread {

	private static final String KEY_LATEST_VERSION = "latestVersion";
	private static final String KEY_INFO_URL = "infoURL";
	private static final String CHECK_URL = "http://www.jMethods.com/software/jftp/update.properties";
	private Component parent = null;
	private boolean delay = false;
	private boolean showNoUpdatesMessage = false;

	public AutoUpdater(Component parent, boolean delay,
			boolean ignoreIfNoUpdates) {
		super("Auto Updater Thread");
		this.parent = parent;
		this.delay = delay;
		this.showNoUpdatesMessage = ignoreIfNoUpdates;
	}

	@Override
	public void run() {
		if (delay) {
			try {
				Thread.sleep(15000);
			} catch (InterruptedException exp) {
			}
		}
		while (true) {
			try {
				URL url = new URL(CHECK_URL);
				Properties props = new Properties();
				props.load(url.openStream());
				String latestVersion = props.getProperty(KEY_LATEST_VERSION);
				String currentVersion = JFTPConstants.PRODUCT_VERSION;
				String infoURL = props.getProperty(KEY_INFO_URL);
				if (latestVersion != null && latestVersion.trim().length() > 0) {
					final ResourceBundle resources = ResourceLoader
							.getBundle("com.myjavaworld.jftp.AutoUpdater");
					String title = resources.getString("title.dialog");
					if (latestVersion.compareTo(currentVersion) > 0) {
						MessageFormat mf = new MessageFormat(
								resources.getString("message.updatesAvailable"));
						String message = mf.format(new Object[] {
								JFTPConstants.PRODUCT_NAME, currentVersion,
								latestVersion, infoURL });
						GUIUtil.showInformation(parent, title, message, true);
					} else {
						if (showNoUpdatesMessage) {
							GUIUtil.showInformation(parent, title, resources
									.getString("message.updatesNotAvailable"),
									false);
						}
					}
				}
				return;
			} catch (Exception exp) {
				exp.printStackTrace();
				try {
					Thread.sleep(5 * 60 * 1000);
				} catch (InterruptedException exp1) {
				}
			}
		}
	}
}