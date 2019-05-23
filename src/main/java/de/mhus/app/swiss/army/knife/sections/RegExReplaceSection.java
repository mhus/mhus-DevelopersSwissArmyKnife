package de.mhus.app.swiss.army.knife.sections;

import java.awt.Component;

import de.mhus.app.swiss.army.knife.GuiWindow;
import de.mhus.app.swiss.army.knife.Section;

public class RegExReplaceSection implements Section {

    @Override
    public String getTitle() {
        return "RegEx Replace";
    }

    @Override
    public Component createComponent(GuiWindow window) {
        return new RegExReplaceEditor();
    }

}
