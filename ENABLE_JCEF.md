# 🛠 Troubleshooting: JCEF Not Found / Broken Preview

If you are seeing a blank screen, a "JCEF not found" error, or the interactive components of this plugin are not loading, it is likely because your IDE is running on a "slim" Java runtime that lacks the **Java Chromium Embedded Framework (JCEF)**.

Follow these steps to fix the issue:

### 1. Switch to a JCEF-Enabled Runtime
1. Press **Shift** twice (Search Everywhere) and type `Choose Boot Java Runtime for the IDE`.
2. Open the action and look for the dropdown menu.
3. Select a version that includes **"with JCEF"** (e.g., `21.0.x-...-jcef`).
    * *Note: You may see multiple identical-looking versions; these are usually minor build updates. Selecting the one at the top of the list is recommended.*
4. Click **OK** and **Restart** the IDE.

### 2. Disable the JCEF Sandbox (If the crash persists)
On some systems, hardware acceleration or security policies cause the browser process to crash immediately.
1. Press **Shift** twice and type `Registry`.
2. Find the key: `ide.browser.jcef.sandbox.enable`.
3. **Uncheck** it to disable the sandbox.
4. **Restart** the IDE.

### 3. Linux Dependencies (Linux Users Only)
If you are on Linux, ensure you have the necessary libraries for Chromium installed:
```bash
sudo apt-get install libnss3 libatk1.0-0 libatk-bridge2.0-0 libcups2 libdrm2 libxkbcommon0 libpango-1.0-0 libcairo2 libasound2