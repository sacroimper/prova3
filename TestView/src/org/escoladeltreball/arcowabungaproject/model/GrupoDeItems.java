package org.escoladeltreball.arcowabungaproject.model;

import java.util.ArrayList;
import java.util.List;

import android.widget.Button;

public class GrupoDeItems {
	public String string;
	public final List<String> children = new ArrayList<String>();

	public GrupoDeItems(String string) {
		this.string = string;
	}
}
