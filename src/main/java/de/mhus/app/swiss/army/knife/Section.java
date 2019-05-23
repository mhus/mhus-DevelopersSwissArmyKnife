package de.mhus.app.swiss.army.knife;

import java.awt.Component;

public interface Section {

    String getTitle();

    Component createComponent(GuiWindow window);

}
