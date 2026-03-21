package si.vegamind.ftccompanion.actions.filetree;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import si.vegamind.ftccompanion.services.CustomTemplateService;

import java.util.Map;

public class CustomTemplateActionGroup extends DefaultActionGroup {

    private static final Logger LOG = Logger.getInstance(CustomTemplateActionGroup.class);

    public CustomTemplateActionGroup() {
        super("Custom Templates", true);
        getTemplatePresentation().setIcon(AllIcons.FileTypes.Custom);
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) {
            e.getPresentation().setEnabledAndVisible(false);
            return;
        }

        // Refresh templates from configured directory
        CustomTemplateService service = CustomTemplateService.getInstance(project);
        service.refreshTemplates();

        // Clear existing children
        removeAll();

        // Add action for each custom template
        Map<String, CustomTemplateService.CustomTemplateInfo> templates = service.getCustomTemplates();

        if (templates.isEmpty()) {
            e.getPresentation().setEnabledAndVisible(false);
            return;
        }

        for (CustomTemplateService.CustomTemplateInfo templateInfo : templates.values()) {
            CustomTemplateAction action = new CustomTemplateAction(
                    templateInfo.getName(),
                    templateInfo.getDisplayName()
            );
            add(action);
        }

        e.getPresentation().setEnabledAndVisible(true);
    }

    @NotNull
    @Override
    public AnAction[] getChildren(@NotNull AnActionEvent e) {
        return super.getChildren(e);
    }
}
