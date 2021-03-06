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
package com.myjavaworld.gui;

import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

/**
 * An extension of <code>javax.swing.JComboBox</code>.
 * 
 * @author Sai Pullabhotla, psai [at] jMethods [dot] com
 * @version 2.0
 * 
 */
public class MComboBox extends JComboBox {

	/**
	 * Constructs an object of <code>MComboBox</code>.
	 * 
	 */
	public MComboBox() {
		super();
	}

	/**
	 * Constructs an object of <code>MComboBox</code>.
	 * 
	 * @param items
	 *            Vector of items.
	 * 
	 */
	public MComboBox(Vector items) {
		super(items);
	}

	/**
	 * Constructs an object of <code>MComboBox</code>.
	 * 
	 * @param items
	 *            An array of items
	 * 
	 */
	public MComboBox(Object[] items) {
		super(items);
	}

	/**
	 * Constructs an object of <code>MComboBox</code>.
	 * 
	 * model Model for this combo box.
	 * 
	 */
	public MComboBox(ComboBoxModel model) {
		super(model);
	}

	/**
	 * Sets the data in this combobox to the given data.
	 * 
	 * @param data
	 *            An array of objects.
	 * 
	 */
	public void setData(Object[] data) {
		removeAllItems();
		for (int i = 0; i < data.length; i++) {
			addItem(data[i]);
		}
	}

	/**
	 * Sets the data in this combobox to the given data.
	 * 
	 * @param data
	 *            Vector of objects.
	 * 
	 */
	public void setData(Vector data) {
		removeAllItems();
		for (int i = 0; i < data.size(); i++) {
			addItem(data.get(i));
		}
	}
}