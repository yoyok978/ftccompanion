package si.vegamind.ftccompanion.actions.filetree;

import com.intellij.icons.AllIcons;
import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;

public class NewSolversTeleOp extends CreateFileFromTemplateAction {

    public NewSolversTeleOp() {
        super("SolversTeleOp", "Creates a new SolversTeleOp OpMode class with pedro pathing", AllIcons.Nodes.Class);
    }

    @Override
    protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle("New SolversTeleOp")
               .addKind("SolversTeleOp class", AllIcons.Nodes.Class, "SolversTeleOp");
    }

    @Override
    protected String getActionName(PsiDirectory directory, String newName, String templateName) {
        return "Create SolversTeleOp: " + newName;
    }
}