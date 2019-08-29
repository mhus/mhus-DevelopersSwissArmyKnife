package de.mhus.app.swiss.army.knife.sections;

import java.awt.Component;

import de.mhus.app.swiss.army.knife.GuiWindow;
import de.mhus.app.swiss.army.knife.Section;

public class Base64Section implements Section {

    @Override
    public String getTitle() {
        return "Base64";
    }

    @Override
    public Component createComponent(GuiWindow window) {
        return new Base64Editor();
    }

}
