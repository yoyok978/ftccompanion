package si.vegamind.ftccompanion.actions.filetree;

import com.intellij.icons.AllIcons;
import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;

public class NewAutonomous extends CreateFileFromTemplateAction {

    public NewAutonomous() {
        super("Autonomous", "Creates a new Autonomous OpMode class", AllIcons.Nodes.Class);
    }

    @Override
    protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle("New Autonomous")
               .addKind("Autonomous class", AllIcons.Nodes.Class, "Autonomous");
    }

    @Override
    protected String getActionName(PsiDirectory directory, String newName, String templateName) {
        return "Create Autonomous: " + newName;
    }
}