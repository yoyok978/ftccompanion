# FTC Companion Improved

An IntelliJ IDEA and Android Studio plugin for FIRST Tech Challenge (FTC) development, originally based on the FTC Companion plugin by First Slovenia. Enhanced with powerful new features for rapid OpMode development.

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

## Features

### 🚀 Quick OpMode Creation
Create OpModes instantly with built-in templates:
- **TeleOp** - Standard TeleOp template
- **Autonomous** - Standard Autonomous template
- **SolversAuto** - SolversLib autonomous template
- **SolversTeleOp** - SolversLib TeleOp template

### 🎨 Custom Templates
**NEW in v1.3.3** - Create and use your own custom OpMode templates!
- Configure a custom templates directory
- Write `.ft` (FileTemplate) files with your team's boilerplate code
- Templates appear automatically in the IDE menu
- Supports all IntelliJ template variables (`${PACKAGE_NAME}`, `${NAME}`, etc.)

[📖 Learn more about custom templates](CUSTOM_TEMPLATES.md)

### 🤖 Robot Controller Console
Built-in browser-based access to your Robot Controller:
- **Control Hub Wi-Fi** (192.168.43.1)
- **RC Phone Wi-Fi** (192.168.49.1)
- **USB / REV Hardware Client** (127.0.0.1)
- **Custom IP** - Configure your own robot IP

Access multiple dashboards:
- Robot Controller Homescreen (`:8080`)
- Panels Dashboard (`:8001`)
- FTC Dashboard (`:8080/dash`)

## Installation

### From JetBrains Marketplace
1. Open IntelliJ IDEA or Android Studio
2. Go to `File` → `Settings` → `Plugins`
3. Search for "FTC Companion Improved"
4. Click `Install`

## Usage

### Creating OpModes

1. Right-click on a package in the Project view
2. Select `New` → `FTC`
3. Choose from:
   - **Built-in Templates** (standard OpMode types)
   - **Custom Templates** (your custom templates)
4. Enter the class name and create!

### Configuring Custom Templates

1. Go to `File` → `Settings` → `Tools` → `FTC Companion`
2. Set "Custom Templates Directory" to your templates folder
3. Click `Apply`
4. Create `.ft` files in that directory

Example template (`MyTeleOp.java.ft`):
```java
package ${PACKAGE_NAME};

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="${NAME}")
public class ${NAME} extends LinearOpMode {
    @Override
    public void runOpMode() {
        waitForStart();
        while (opModeIsActive()) {
            // Your code here
        }
    }
}
```

### Accessing Robot Controller Console

1. Open the "Robot controller console" tool window (right side panel)
2. Select your device type from the dropdown
3. Choose the page you want to access
4. The browser will load the selected dashboard

For custom IPs:
1. Go to `Settings` → `Tools` → `FTC Companion`
2. Enter your robot's IP address
3. Select "Custom IP" in the tool window dropdown

## Supported Platforms

- **IntelliJ IDEA** 2024.2+
- **Android Studio** 2024.2+
- Java 17 or higher

## Requirements

- FTC SDK project
- Java Development Kit 17+

## Contributing

Contributions are welcome! This plugin is maintained by FTC Team 25262 – Aradiors.


## Credits

- **Original Plugin**: First Slovenia - FTC Companion
- **Enhanced Version**: FTC Team 25262 – Aradiors (yoyo)
- **License**: GNU General Public License v3.0

### Built With
- [IntelliJ Platform Plugin SDK](https://plugins.jetbrains.com/docs/intellij/)
- [Gradle IntelliJ Plugin](https://github.com/JetBrains/gradle-intellij-plugin)

## Support

- **Documentation**: [Custom Templates Guide](CUSTOM_TEMPLATES.md)

## License

This project is licensed under the GNU General Public License v3.0 - see the [LICENSE](LICENSE) file for details.

---

**Made with ❤️ for the FTC community**
