package si.vegamind.ftccompanion.actions.filetree;

import com.intellij.icons.AllIcons;
import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;

public class NewSolversAuto extends CreateFileFromTemplateAction {

    public NewSolversAuto() {
        super("SolversAuto", "Creates a new SolversAuto OpMode class with pedro pathing", AllIcons.Nodes.Class);
    }

    @Override
    protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle("New SolversAuto")
               .addKind("SolversAuto class", AllIcons.Nodes.Class, "SolversAuto");
    }

    @Override
    protected String getActionName(PsiDirectory directory, String newName, String templateName) {
        return "Create SolversAuto: " + newName;
    }
}