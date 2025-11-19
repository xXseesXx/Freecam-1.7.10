# Quick Reference Guide

## Setup New Mod
```bash
.\setup-new-mod.bat
```

## Common Gradle Commands

### Build the mod
```bash
.\gradlew.bat build
```

### Run Minecraft client
```bash
.\gradlew.bat runClient
```

### Run Minecraft server
```bash
.\gradlew.bat runServer
```

### Format code (Spotless)
```bash
.\gradlew.bat spotlessApply
```

### Check code formatting
```bash
.\gradlew.bat spotlessCheck
```

### Clean build artifacts
```bash
.\gradlew.bat clean
```

### Update build script
```bash
.\gradlew.bat updateBuildScript
```

## Project Structure

```
src/main/java/com/xXseesXx/[modid]/
├── MyMod.java          - Main mod class with @Mod annotation
├── CommonProxy.java    - Server/common-side logic
├── ClientProxy.java    - Client-side only logic
└── Config.java         - Configuration file handler

src/main/resources/
├── mcmod.info          - Mod metadata
└── LICENSE             - License file
```

## Key Files to Edit

### gradle.properties
- `modName` - Display name of your mod
- `modId` - Unique identifier (lowercase, no spaces)
- `modGroup` - Java package name
- `VERSION` - Override version (optional)

### mcmod.info
- Mod description
- Author list
- URL
- Dependencies

### MyMod.java
- Main mod initialization
- Event handlers (preInit, init, postInit)
- Mod constants (MODID, MODNAME)

## Adding Content

### Blocks
1. Create block class extending `Block`
2. Register in `CommonProxy.preInit()`
3. Add textures to `src/main/resources/assets/[modid]/textures/blocks/`
4. Add localization to lang files

### Items
1. Create item class extending `Item`
2. Register in `CommonProxy.preInit()`
3. Add textures to `src/main/resources/assets/[modid]/textures/items/`
4. Add localization to lang files

### Recipes
Add in `CommonProxy.init()` using `GameRegistry.addRecipe()`

## Configuration

Edit `Config.java` to add configuration options:
```java
public static boolean myOption = true;

public static void synchronizeConfiguration(File configFile) {
    Configuration configuration = new Configuration(configFile);
    
    myOption = configuration.getBoolean(
        "myOption",
        Configuration.CATEGORY_GENERAL,
        myOption,
        "Description of my option"
    );
    
    if (configuration.hasChanged()) {
        configuration.save();
    }
}
```

## Troubleshooting

### Build fails with "NO-GIT-TAG-SET"
- Either initialize git and create a tag: `git tag 1.0.0`
- Or add `VERSION = 1.0.0` to gradle.properties

### Spotless formatting errors
```bash
.\gradlew.bat spotlessApply
```

### Client won't start
- Check logs in `run/logs/latest.log`
- Verify MODID matches in all files
- Ensure Java 21 is configured in gradle.properties

## Resources

- [GTNH Wiki](https://gtnh.miraheze.org/wiki/Development)
- [Forge Documentation](https://mcforge.readthedocs.io/en/1.7.10/)
- [Minecraft Forge Forums](https://forums.minecraftforge.net/)

## Author
xXseesXx - 2025
