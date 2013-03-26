package com.theboxbrigade.quantumchaos.general;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class DialogManager {
	private Array<String> dialog;
	private Iterator it;
	private int numStrings;
	private String currentString;
	
	public DialogManager() {
		dialog = new Array<String>();
	}
	
	public boolean loadFile(String filePath) {
		if (dialog != null) clearDialog();
		FileHandle handle = Gdx.files.internal(filePath);
		try {
			String text = handle.readString();
			String[] tmp = text.split("[\t\n]");
			for (int i = 0; i < tmp.length; i++) {
				dialog.add(tmp[i]);
			}
			it = dialog.iterator();
			
			return true;
		} catch (GdxRuntimeException npe) { return false; }
	}
	
	public void clearDialog() {
		dialog.clear();
	}
	
	public String getString() {
		currentString = (String)it.next();
		if (currentString != null) return currentString;
		return null;
	}
}