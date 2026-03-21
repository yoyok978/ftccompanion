package si.vegamind.ftccompanion.actions.filetree;

import com.intellij.icons.AllIcons;
import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.ide.fileTemplates.FileTemplateUtil;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import si.vegamind.ftccompanion.services.CustomTemplateService;

import java.util.Properties;

public class CustomTemplateAction extends CreateFileFromTemplateAction {

    private static final Logger LOG = Logger.getInstance(CustomTemplateAction.class);
    private final String templateName;
    private final String displayName;

    public CustomTemplateAction(String templateName, String displayName) {
        super(displayName, "Creates a new " + displayName + " from custom template", AllIcons.FileTypes.Java);
        this.templateName = templateName;
        this.displayName = displayName;
    }

    @Override
    protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle("New " + displayName)
                .addKind(displayName, AllIcons.FileTypes.Java, templateName);
    }

    @Override
    protected String getActionName(PsiDirectory directory, @NotNull String newName, String templateName) {
        return "Create " + displayName + ": " + newName;
    }

    @Override
    protected PsiFile createFileFromTemplate(String name, FileTemplate template, PsiDirectory dir) {
        try {
            // Get the custom template content
            CustomTemplateService service = CustomTemplateService.getInstance(dir.getProject());
            CustomTemplateService.CustomTemplateInfo templateInfo = service.getCustomTemplates().get(templateName);

            if (templateInfo == null) {
                LOG.error("Custom template not found: " + templateName);
                return null;
            }

            // Create a temporary file template with the custom content
            FileTemplateManager templateManager = FileTemplateManager.getInstance(dir.getProject());
            FileTemplate customTemplate = templateManager.addTemplate(templateName, "java");
            customTemplate.setText(templateInfo.getContent());

            // Use default properties
            Properties properties = FileTemplateManager.getInstance(dir.getProject()).getDefaultProperties();

            // Create the file
            return (PsiFile) FileTemplateUtil.createFromTemplate(customTemplate, name, properties, dir);
        } catch (Exception e) {
            LOG.error("Error creating file from custom template", e);
            return null;
        }
    }
}
