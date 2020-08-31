package com.Admin;


import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class SuggestText {
	public JTextField txtSuggest=new JTextField(16);
	public final JComboBox<String> cmbSuggest = new JComboBox<>();
	public final Vector<String> v = new Vector<String>();
	public SuggestText()
	{
		combowork();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void combowork(){
		cmbSuggest.setEditable(true);
		cmbSuggest.setPreferredSize(new Dimension(150, 30));
		txtSuggest = (JTextField) cmbSuggest.getEditor().getEditorComponent();
		txtSuggest.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						String text = txtSuggest.getText();
						if(text.length()==0) {
							cmbSuggest.hidePopup();
							setModel(new DefaultComboBoxModel(v), "");
						}else{
							DefaultComboBoxModel m = getSuggestedModel(v, text);
							if(m.getSize()==0 || hide_flag) {
								cmbSuggest.hidePopup();
								hide_flag = false;
							}else{
								setModel(m, text);
								cmbSuggest.showPopup();
							}
						}
					}
				});
			}
			@Override
			public void keyPressed(KeyEvent e) {
				String text = txtSuggest.getText();
				int code = e.getKeyCode();
				if(code==KeyEvent.VK_ENTER) {
					if(!v.contains(text)) {
						v.addElement(text);
						Collections.sort(v);
						setModel(getSuggestedModel(v, text), text);
					}
					hide_flag = true; 
				}else if(code==KeyEvent.VK_ESCAPE) {
					hide_flag = true; 
				}else if(code==KeyEvent.VK_RIGHT) {
					for(int i=0;i<v.size();i++) {
						String str = v.elementAt(i);
						if(str.toString().equalsIgnoreCase(text)) {
							cmbSuggest.setSelectedIndex(-1);
							txtSuggest.setText(str);
							return;
						}
					}
				}
			}
		});
		setModel(new DefaultComboBoxModel(v), "");
	}
	private boolean hide_flag = false;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setModel(DefaultComboBoxModel mdl, String str) {
		cmbSuggest.setModel(mdl);
		cmbSuggest.setSelectedIndex(-1);
		txtSuggest.setText(str);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static DefaultComboBoxModel getSuggestedModel(java.util.List<String> list, String text) {
		DefaultComboBoxModel m = new DefaultComboBoxModel();
		for(String s: list) {
			if(s.toLowerCase().contains(text.toLowerCase())){
				m.addElement(s);
			}
			// if(s.startsWith(text)==b)m.addElement(s);
		}
		return m;
	}
}
