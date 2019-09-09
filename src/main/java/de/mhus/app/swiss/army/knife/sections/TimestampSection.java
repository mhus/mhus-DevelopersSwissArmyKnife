package de.mhus.app.swiss.army.knife.sections;

import java.awt.Component;

import de.mhus.app.swiss.army.knife.GuiWindow;
import de.mhus.app.swiss.army.knife.Section;

public class TimestampSection implements Section {

    @Override
    public String getTitle() {
        return "Timestamp";
    }

    @Override
    public Component createComponent(GuiWindow window) {
        return new TimestampEditor();
    }

}
