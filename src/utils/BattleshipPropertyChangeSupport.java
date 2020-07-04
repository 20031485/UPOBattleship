package utils;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class BattleshipPropertyChangeSupport extends PropertyChangeSupport implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public BattleshipPropertyChangeSupport(Object sourceBean) {
		super(sourceBean);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		removePropertyChangeListener(listener);
	}
}
