package de.mhus.app.swiss.army.knife.sections;

import java.awt.Component;

import de.mhus.app.swiss.army.knife.GuiWindow;
import de.mhus.app.swiss.army.knife.Section;

public class XPathSection implements Section {

    @Override
    public String getTitle() {
        return "XPath";
    }

    @Override
    public Component createComponent(GuiWindow window) {
        return new XPathEditor();
    }

}
