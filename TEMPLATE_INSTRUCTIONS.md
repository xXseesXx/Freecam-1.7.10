# Blank Mod Template - Quick Start Guide

This is a blank Minecraft 1.7.10 Forge mod template by xXseesXx.

## Quick Setup (Automated):

Simply run the setup script:
```bash
.\setup-new-mod.bat
```

The script will prompt you for:
- Mod display name
- Mod ID
- Description
- Version (optional)
- URL (optional)

It will automatically:
- Update all configuration files
- Rename packages
- Update Java code
- Format code with Spotless
- Clean build artifacts

## Manual Setup:

If you prefer to set up manually:

### 1. Update `gradle.properties`:
- Change `modName` from "MyMod" to your mod's display name
- Change `modId` from "mymodid" to your mod's unique ID (lowercase, no spaces)
- Update `modGroup` from "com.xXseesXx.mymodid" to "com.xXseesXx.yourmodid"
- Update `generateGradleTokenClass` to match your new package

### 2. Rename Package Structure:
- Rename folder: `src/main/java/com/xXseesXx/mymodid/` to `src/main/java/com/xXseesXx/yourmodid/`
- Update package declarations in all Java files to match

### 3. Update Java Files:
- In `MyMod.java`: Update `MODID` and `MODNAME` constants
- Update proxy paths in `@SidedProxy` annotation
- Rename `MyMod.java` to your preferred main class name if desired

### 4. Update `mcmod.info`:
- Change description
- Add your mod's URL if you have one

### 5. Build and Test:
```bash
.\gradlew.bat build
.\gradlew.bat runClient
```

## Current Configuration:
- Java: Adoptium JDK 21 (for Gradle)
- Minecraft: 1.7.10
- Forge: 10.13.4.1614
- Author: xXseesXx
- License: MIT

## Files Ready to Customize:
- `MyMod.java` - Main mod class
- `CommonProxy.java` - Server/common-side logic
- `ClientProxy.java` - Client-side logic
- `Config.java` - Configuration file handler
- `mcmod.info` - Mod metadata
- `README.md` - Project documentation

All files are formatted and ready to build!
