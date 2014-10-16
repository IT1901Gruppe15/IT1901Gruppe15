package gui;

import javafx.beans.property.SimpleBooleanProperty;

public class CheckListObject {
	private final SimpleBooleanProperty selected;
	private final String name;

	public CheckListObject(String name) {
		selected = new SimpleBooleanProperty(false);
		this.name = name;
	}

	public boolean getSelected() {
		return selected.get();
	}

	public String getName() {
		return name;
	}

	public SimpleBooleanProperty selectedProperty() {
		return selected;
	}

	@Override
	public String toString() {
		return getName();
	}
}