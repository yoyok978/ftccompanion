package si.vegamind.ftccompanion.extensions.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
        name = "si.vegamind.ftccompanion.extensions.settings.AppSettings",
        storages = @Storage("FTCCompanionSettings.xml")
)
public class AppSettings implements PersistentStateComponent<AppSettings> {

    public String robotIp = "192.168.43.1";
    public String customTemplatesDirectory = "";

    public static AppSettings getInstance() {
        return ApplicationManager.getApplication().getService(AppSettings.class);
    }

    @Nullable
    @Override
    public AppSettings getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull AppSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}
