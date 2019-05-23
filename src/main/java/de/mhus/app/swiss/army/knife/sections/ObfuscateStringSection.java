package de.mhus.app.swiss.army.knife.sections;

import java.awt.Component;

import de.mhus.app.swiss.army.knife.GuiWindow;
import de.mhus.app.swiss.army.knife.Section;

public class ObfuscateStringSection implements Section {

    @Override
    public String getTitle() {
        return "Obfuscate String";
    }

    @Override
    public Component createComponent(GuiWindow window) {
        return new ObfuscateStringEditor();
    }

}
