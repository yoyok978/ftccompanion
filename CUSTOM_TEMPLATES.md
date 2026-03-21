# Custom Templates Guide

**FTC Companion Improved v1.3.3+**

Create your own `.ft` (FileTemplate) templates to standardize your team's OpMode boilerplate code!

## Setup

1. **Create a templates directory** anywhere on your system (e.g., `C:\FTC\templates` or `~/ftc-templates`)

2. **Configure the plugin**:
   - Open IntelliJ IDEA or Android Studio
   - Go to `File` → `Settings` → `Tools` → `FTC Companion`
   - Set the "Custom Templates Directory" to your templates folder
   - Click `Apply`

3. **Create template files** with the `.ft` extension in your templates directory

## Template File Format

Template files use the `.ft` extension and support IntelliJ's template variable syntax:

### Example: `MyCustomTeleOp.java.ft`

```java
package ${PACKAGE_NAME};

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Custom TeleOp template for Team ${TEAM_NUMBER}
 * Author: ${USER}
 */
@TeleOp(name="${NAME}", group="Custom")
public class ${NAME} extends LinearOpMode {

    // Hardware components
    // TODO: Add your hardware declarations here

    @Override
    public void runOpMode() {
        // Initialize hardware
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // TeleOp loop

            // Update telemetry
            telemetry.update();
        }
    }
}
```

### Available Template Variables

- `${PACKAGE_NAME}` - The package of the file being created
- `${NAME}` - The name you enter when creating the file
- `${USER}` - Your system username
- `${DATE}` - Current date
- `${TIME}` - Current time
- `${YEAR}` - Current year
- `${MONTH}` - Current month
- `${DAY}` - Current day

## Using Custom Templates

Once configured, your custom templates will appear in the IDE:

1. Right-click on a package in the Project view
2. Select `New` → `FTC` → `Custom Templates`
3. Choose your custom template from the submenu
4. Enter the class name and create!

## Template Naming

The template filename (without `.ft`) determines how it appears in the menu:
- `MyTeleOp.java.ft` → "My Tele Op"
- `PedroAuto.java.ft` → "Pedro Auto"
- `CustomDriver.java.ft` → "Custom Driver"

## Example Templates

### PedroPathing Auto: `PedroAuto.java.ft`

```java
package ${PACKAGE_NAME};

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.pedropathing.follower.Follower;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.Path;

@Autonomous(name="${NAME}")
public class ${NAME} extends LinearOpMode {

    private Follower follower;

    @Override
    public void runOpMode() {
        follower = new Follower(hardwareMap);

        // Build your path here
        Path path = new PathBuilder()
                .build();

        waitForStart();

        if (opModeIsActive()) {
            follower.followPath(path);

            while (opModeIsActive() && follower.isBusy()) {
                follower.update();
                telemetry.update();
            }
        }
    }
}
```

### Hardware Config: `HardwareConfig.java.ft`

```java
package ${PACKAGE_NAME};

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ${NAME} {

    // Drive motors
    public DcMotor leftFront;
    public DcMotor leftBack;
    public DcMotor rightFront;
    public DcMotor rightBack;

    // Additional hardware
    // TODO: Add your hardware here

    public ${NAME}(HardwareMap hardwareMap) {
        // Initialize motors
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        // Set motor directions
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
    }
}
```