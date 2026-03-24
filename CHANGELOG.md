# Changelog

All notable changes to the FTC Companion Improved plugin will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.4.1] - 2024-03-24

### Fixed
- Fixed a bug where Android Studio instances missing JCEF support would show an empty tool window or throw errors. Now gracefully displays a fallback UI with options to open the console and dashboard in an external browser, along with a help link to enable JCEF in Android Studio.

## [1.4.0] - 2024-03-21

### Added
- **Custom Templates Feature** – Users can now create and use custom `.ft` template files
  - Settings page to configure custom templates directory
  - Dynamic menu that loads templates from configured directory
  - Support for all IntelliJ template variables (`${PACKAGE_NAME}`, `${NAME}`, etc.)
  - Comprehensive documentation in `CUSTOM_TEMPLATES.md`
- **Custom IP Option** in Robot Controller Console
  - Added "Custom IP" device selector option
  - Uses IP configured in plugin settings
  - Falls back to Control Hub IP if not configured
- **Template Organization** - Built-in templates now grouped in a dedicated submenu
  - "Built-in Templates" submenu for standard OpMode templates
  - "Custom Templates" submenu for user-defined templates
  - Cleaner menu structure for better usability

### Changed
- Updated plugin settings UI with templates directory picker
- Improved Robot Controller Console with 4 device options
- Changed Java/Kotlin target from 21 to 17 for broader compatibility

### Fixed
- Build compatibility issues with IntelliJ Platform 2024.2.1
- Return type mismatch in custom template creation

## [1.3.2] – Previous Release

### Changed
- QOL improvements
- Version bump

## [1.3.1] – Previous Release

### Added
- Refactored OpMode creation to use IntelliJ file templates
- Added Solvers templates (SolversAuto, SolversTeleOp)

### Changed
- Migrated from custom action classes to IntelliJ's built-in file template system
- Improved template infrastructure

## [1.3.0] – Previous Release

### Added
- Robot Controller Console tool window
- Built-in browser support using JCEF
- Multiple dashboard access (Homescreen, Panels Dashboard, FTC Dashboard)
- Device selector for Control Hub, RC Phone, and USB/REV Client
- Initial file templates for TeleOp and Autonomous OpModes

### Changed
- Complete UI overhaul for OpMode creation
- Modernized action system

## Earlier Versions

Based on the original FTC Companion plugin by First Slovenia.

---

## Upgrade Guide

### From 1.3.2 to 1.3.3
1. Update the plugin via the IDE's plugin manager
2. (Optional) Configure custom templates directory in `Settings` → `Tools` → `FTC Companion`
3. (Optional) Set custom robot IP if using non-standard IP addresses

### From 1.3.1 to 1.3.2+
No special migration steps required. The plugin now uses IntelliJ's internal file template system which is more robust and maintainable.
