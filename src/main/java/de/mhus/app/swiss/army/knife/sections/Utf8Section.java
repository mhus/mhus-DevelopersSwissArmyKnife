package de.mhus.app.swiss.army.knife.sections;

import java.awt.Component;

import de.mhus.app.swiss.army.knife.GuiWindow;
import de.mhus.app.swiss.army.knife.Section;

public class Utf8Section implements Section {

    @Override
    public String getTitle() {
        return "UTF-8";
    }

    @Override
    public Component createComponent(GuiWindow window) {
        return new Utf8Editor();
    }

}
