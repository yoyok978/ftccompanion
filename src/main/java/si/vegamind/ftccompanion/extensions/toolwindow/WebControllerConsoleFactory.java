package si.vegamind.ftccompanion.extensions.toolwindow;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.icons.AllIcons;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.jcef.JBCefApp;
import com.intellij.ui.jcef.JBCefBrowser;
import org.jetbrains.annotations.NotNull;
import si.vegamind.ftccompanion.extensions.settings.AppSettings;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import com.intellij.ide.BrowserUtil;
import com.intellij.ui.components.labels.LinkLabel;

public class WebControllerConsoleFactory implements ToolWindowFactory {

    private static final String IP_CONTROL_HUB = "192.168.43.1";
    private static final String IP_RC_PHONE = "192.168.49.1";
    private static final String IP_LOCALHOST = "127.0.0.1"; // REV Hardware Client

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        // Check if JCEF is supported in the current IDE environment
        if (!JBCefApp.isSupported()) {
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.anchor = GridBagConstraints.CENTER;

            panel.add(new JBLabel("JCEF (Web Browser) is not supported in this Android Studio runtime."), gbc);

            LinkLabel<String> helpLink = new LinkLabel<>("Learn how to enable JCEF in Android Studio", null);
            helpLink.setListener((aSource, aLinkData) ->
                BrowserUtil.browse("https://github.com/yoyok978/ftccompanion/blob/master/ENABLE_JCEF.md")
            , null);
            panel.add(helpLink, gbc);

            JPanel btnPanel = new JPanel(new FlowLayout());
            JButton openDashboardBtn = new JButton("Open FTC Dashboard in External Browser");
            openDashboardBtn.addActionListener(e -> BrowserUtil.browse("http://" + IP_CONTROL_HUB + ":8080/dash"));

            JButton openConsoleBtn = new JButton("Open Robot Console in External Browser");
            openConsoleBtn.addActionListener(e -> BrowserUtil.browse("http://" + IP_CONTROL_HUB + ":8080"));

            btnPanel.add(openDashboardBtn);
            btnPanel.add(openConsoleBtn);

            panel.add(btnPanel, gbc);

            Content content = ContentFactory.getInstance().createContent(panel, "", false);
            toolWindow.getContentManager().addContent(content);
            return;
        }

        // Map display names to their specific paths
        Map<String, String> paths = new LinkedHashMap<>();
        paths.put("Homescreen", ":8080");
        paths.put("Panels Dashboard", ":8001");
        paths.put("FTC Dashboard", ":8080/dash");
//        connectivity test
//        paths.put("Google (Test)", "https://www.google.com");

        JBCefBrowser browser = new JBCefBrowser("about:blank");

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JBLabel("Navigate to: "));

        // Create a dropdown menu using the keys from the map
        ComboBox<String> pageSelector = new ComboBox<>(paths.keySet().toArray(new String[0]));

        // Scalable dropdown for device selection
        ComboBox<String> deviceSelector = new ComboBox<>(new String[]{"Control Hub (Wi-Fi)", "RC Phone (Wi-Fi)", "USB / REV Client", "Custom IP"});

        JButton refreshButton = new JButton(AllIcons.Actions.Refresh);
        refreshButton.setToolTipText("Reload Page");

        Runnable loadSelectedUrl = () -> {
            String selected = (String) pageSelector.getSelectedItem();
            String device = (String) deviceSelector.getSelectedItem();
            if (selected != null) {
                String path = paths.get(selected);
                String targetUrl;

                if (path.startsWith("http")) {
                    targetUrl = path;
                } else {
                    String ip = IP_CONTROL_HUB;
                    if ("RC Phone (Wi-Fi)".equals(device)) {
                        ip = IP_RC_PHONE;
                    } else if ("USB / REV Client".equals(device)) {
                        ip = IP_LOCALHOST;
                    } else if ("Custom IP".equals(device)) {
                        ip = AppSettings.getInstance().robotIp;
                        if (ip == null || ip.trim().isEmpty()) {
                            ip = IP_CONTROL_HUB; // Fallback to Control Hub if not configured
                        }
                    }

                    targetUrl = "http://" + ip + path;
                }

                // Briefly show a loading message.
                // When testing Google on a normal connection, navigating to a local
                // Robot IP that isn't connected will cause the browser to silently wait
                // to timeout, making it feel "stuck" on the previous page.
                browser.loadHTML("<html><body style='color: white; font-family: sans-serif; padding: 20px; text-align: center;'>" +
                        "<h2>Loading...</h2><p>" + targetUrl + "</p>" +
                        "<p style='color: gray; font-size: 12px;'>If this takes a long time, ensure you are connected to the correct Wi-Fi network.</p>" +
                        "</body></html>");

                // Execute the actual URL load after a slight delay so the loading UI can render
                Timer timer = new Timer(250, ev -> {
                    if (browser.getCefBrowser() != null) {
                        browser.loadURL(targetUrl);
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        };

        pageSelector.addActionListener(e -> loadSelectedUrl.run());
        deviceSelector.addActionListener(e -> loadSelectedUrl.run());
        refreshButton.addActionListener(e -> {
            if (browser.getCefBrowser() != null) {
                browser.getCefBrowser().reload();
            }
        });

        topPanel.add(pageSelector);
        topPanel.add(deviceSelector);
        topPanel.add(refreshButton);

        // Construct the layout
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(browser.getComponent(), BorderLayout.CENTER);

        // Create the tool window content and attach the browser UI component
        ContentFactory contentFactory = ContentFactory.getInstance();
        Content content = contentFactory.createContent(mainPanel, "", false);

        toolWindow.getContentManager().addContent(content);

        // Load the initial URL
        loadSelectedUrl.run();
    }
}