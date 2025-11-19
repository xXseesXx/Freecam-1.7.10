# Automation Scripts Reference

This template includes several automation scripts to streamline your mod development workflow.

## Setup Scripts

### `setup-new-mod.bat` / `setup-new-mod.ps1`
**Purpose:** Automatically configure a new mod from the blank template

**What it does:**
- Prompts for mod details (name, ID, description, version, URL)
- Updates `gradle.properties` with your mod info
- Updates `mcmod.info` with metadata
- Renames package from `mymodid` to your mod ID
- Updates all Java files with correct package names and constants
- Updates proxy paths in `@SidedProxy` annotations
- Updates README.md
- Cleans build artifacts
- Runs Spotless to format code

**Usage:**
```bash
.\setup-new-mod.bat
```

**Interactive prompts:**
1. Mod display name (e.g., "My Awesome Mod")
2. Mod ID (lowercase, no spaces, e.g., "myawesomemod")
3. Short description
4. Initial version (optional, leave blank for git-based)
5. URL/GitHub link (optional)
6. Confirmation (y/n)

**Example:**
```
Enter your mod's display name: Super Tools
Enter your mod's ID: supertools
Enter a short description: Adds super-powered tools
Enter initial version: 1.0.0
Enter mod URL: https://github.com/xXseesXx/supertools
Is this correct? (y/n): y
```

**Result:**
- Package: `com.xXseesXx.supertools`
- Files updated: 7+ files
- Ready to build and run

---

## Reset Scripts

### `reset-to-template.bat` / `reset-to-template.ps1`
**Purpose:** Reset your project back to the blank template state

**What it does:**
- Resets `gradle.properties` to default values
- Resets `mcmod.info` to template
- Renames any custom package back to `mymodid`
- Overwrites all Java files with template versions
- Resets README.md
- Cleans build artifacts
- Runs Spotless to format code

**Usage:**
```bash
.\reset-to-template.bat
```

**Safety:**
- Requires typing "YES" to confirm
- ⚠️ **WARNING:** This will delete all custom code!
- Use this when you want to start completely fresh

---

## Script Details

### File Modifications

#### `gradle.properties`
```properties
modName = MyMod          → Your Mod Name
modId = mymodid          → yourmodid
modGroup = com.xXseesXx.mymodid → com.xXseesXx.yourmodid
generateGradleTokenClass = com.xXseesXx.mymodid.Tags → com.xXseesXx.yourmodid.Tags
```

Optional version override:
```properties
# Version override (remove to use git-based versioning)
VERSION = 1.0.0
```

#### `mcmod.info`
```json
{
  "modid": "${modId}",
  "name": "${modName}",
  "description": "Your description",
  "url": "Your URL",
  "authorList": ["xXseesXx"]
}
```

#### Java Files
- Package declarations: `package com.xXseesXx.yourmodid;`
- Import statements: `import com.xXseesXx.yourmodid.*;`
- Constants: `MODID = "yourmodid"`, `MODNAME = "Your Mod Name"`
- Proxy paths: `@SidedProxy(clientSide = "com.xXseesXx.yourmodid.ClientProxy", ...)`

#### Directory Structure
```
src/main/java/com/xXseesXx/mymodid/  →  src/main/java/com/xXseesXx/yourmodid/
```

---

## Advanced Usage

### Custom Script Modifications

You can modify the scripts to add custom behavior:

#### Add custom files to setup
Edit `setup-new-mod.ps1` and add:
```powershell
# Update custom file
$customFile = Get-Content "path/to/file" -Raw
$customFile = $customFile -replace 'old', 'new'
Set-Content "path/to/file" -Value $customFile -NoNewline
```

#### Skip certain steps
Comment out sections you don't need:
```powershell
# Write-Host "Cleaning build artifacts..." -ForegroundColor Cyan
# if (Test-Path "build") {
#     Remove-Item "build" -Recurse -Force -ErrorAction SilentlyContinue
# }
```

### Running Scripts Directly

#### PowerShell
```powershell
powershell -ExecutionPolicy Bypass -File setup-new-mod.ps1
```

#### With Parameters (Advanced)
Modify scripts to accept parameters:
```powershell
param(
    [string]$modName,
    [string]$modId
)
```

Then run:
```powershell
.\setup-new-mod.ps1 -modName "My Mod" -modId "mymod"
```

---

## Troubleshooting

### Script won't run
**Error:** "Execution of scripts is disabled on this system"

**Solution:**
```powershell
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

Or use the `.bat` files which bypass this automatically.

### Script fails partway through
**Solution:**
1. Check the error message
2. Run `.\reset-to-template.bat` to start fresh
3. Try setup again

### Files not updating
**Solution:**
1. Close any open files in your IDE
2. Run the script again
3. Check file permissions

### Spotless fails
**Solution:**
```bash
.\gradlew.bat spotlessApply
```

---

## Script Workflow Diagram

```
┌─────────────────────┐
│  Blank Template     │
│  (mymodid)          │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│ setup-new-mod.bat   │◄─── Run this to configure
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│  Configured Mod     │
│  (yourmodid)        │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│  Development        │
│  (add features)     │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│ reset-to-template   │◄─── Run this to start over
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│  Blank Template     │
│  (mymodid)          │
└─────────────────────┘
```

---

## Best Practices

1. **Always confirm** before running reset script
2. **Commit to git** before running setup script
3. **Test build** after running setup: `.\gradlew.bat build`
4. **Test client** after setup: `.\gradlew.bat runClient`
5. **Keep scripts updated** if you modify the template

---

## Creating Your Own Scripts

You can create additional automation scripts for common tasks:

### Example: Quick Build and Run
`build-and-run.bat`:
```batch
@echo off
echo Building mod...
call gradlew.bat build
if %errorlevel% neq 0 exit /b %errorlevel%
echo Launching client...
call gradlew.bat runClient
```

### Example: Clean and Build
`clean-build.bat`:
```batch
@echo off
call gradlew.bat clean
call gradlew.bat build
```

### Example: Format All Code
`format-code.bat`:
```batch
@echo off
call gradlew.bat spotlessApply
echo Code formatted!
pause
```

---

## Support

If you encounter issues with the automation scripts:
1. Check this documentation
2. Review the script source code
3. Check PowerShell execution policy
4. Ensure Java 21 is properly configured

---

**Author:** xXseesXx  
**Last Updated:** 2025
