package si.vegamind.ftccompanion.extensions.settings;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class AppSettingsConfigurable implements Configurable {

    private AppSettingsComponent settingsComponent;

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "FTC Companion";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return settingsComponent.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        settingsComponent = new AppSettingsComponent();
        return settingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        AppSettings settings = AppSettings.getInstance();
        boolean modified = !settingsComponent.getRobotIpText().equals(settings.robotIp);
        modified |= !settingsComponent.getCustomTemplatesDirectory().equals(settings.customTemplatesDirectory);
        return modified;
    }

    @Override
    public void apply() {
        AppSettings settings = AppSettings.getInstance();
        settings.robotIp = settingsComponent.getRobotIpText();
        settings.customTemplatesDirectory = settingsComponent.getCustomTemplatesDirectory();
    }

    @Override
    public void reset() {
        AppSettings settings = AppSettings.getInstance();
        settingsComponent.setRobotIpText(settings.robotIp);
        settingsComponent.setCustomTemplatesDirectory(settings.customTemplatesDirectory);
    }

    @Override
    public void disposeUIResources() {
        settingsComponent = null;
    }
}
