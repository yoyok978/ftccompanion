package si.vegamind.ftccompanion.extensions.settings;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class AppSettingsComponent {
	private final JPanel panel;
	private final JBTextField robotIpText = new JBTextField();
	private final TextFieldWithBrowseButton customTemplatesDirectoryField = new TextFieldWithBrowseButton();

	public AppSettingsComponent() {
		customTemplatesDirectoryField.addBrowseFolderListener(
				"Select Custom Templates Directory",
				"Choose the directory containing your custom .ft template files",
				null,
				FileChooserDescriptorFactory.createSingleFolderDescriptor()
		);

		panel = FormBuilder.createFormBuilder()
				.addLabeledComponent(new JBLabel("Control Hub IP:"), robotIpText, 1, false)
				.addLabeledComponent(
						new JBLabel("Custom Templates Directory:"),
						customTemplatesDirectoryField,
						1,
						false
				)
				.addTooltip("Directory containing custom .ft template files (e.g., MyTeleOp.java.ft)")
				.addComponentFillVertically(new JPanel(), 0)
				.getPanel();
	}

	public JPanel getPanel() {
		return panel;
	}

	public JComponent getPreferredFocusedComponent() {
		return robotIpText;
	}

	@NotNull
	public String getRobotIpText() {
		return robotIpText.getText();
	}

	public void setRobotIpText(@NotNull String newText) {
		robotIpText.setText(newText);
	}

	@NotNull
	public String getCustomTemplatesDirectory() {
		return customTemplatesDirectoryField.getText();
	}

	public void setCustomTemplatesDirectory(@NotNull String path) {
		customTemplatesDirectoryField.setText(path);
	}
}