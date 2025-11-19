# Minecraft 1.7.10 Mod Template by xXseesXx

A clean, automated template for creating Minecraft 1.7.10 Forge mods based on the GTNH ExampleMod build system.

## Features

✅ **Automated Setup** - One command to configure your entire mod  
✅ **Java 21 Support** - Uses Adoptium JDK 21 for Gradle (compiles to Java 8)  
✅ **GTNH Build System** - Stable, updatable build configuration  
✅ **Code Formatting** - Automatic Spotless formatting  
✅ **Ready to Code** - Minimal boilerplate, maximum flexibility  

## Quick Start

### 1. Setup Your Mod (Automated)
```bash
.\setup-new-mod.bat
```

You'll be prompted for:
- **Mod Name** - Display name (e.g., "My Awesome Mod")
- **Mod ID** - Unique identifier (e.g., "myawesomemod")
- **Description** - Short description
- **Version** - Optional version number
- **URL** - Optional GitHub/website URL

The script automatically:
- Updates all configuration files
- Renames packages and classes
- Updates Java code
- Formats code with Spotless
- Cleans build artifacts

### 2. Build Your Mod
```bash
.\gradlew.bat build
```

### 3. Test in Minecraft
```bash
.\gradlew.bat runClient
```

### 4. Start Coding!
Your mod files are in: `src/main/java/com/xXseesXx/[yourmodid]/`

## Project Structure

```
📁 Your Mod Project
├── 📁 src/main/java/com/xXseesXx/[modid]/
│   ├── MyMod.java          # Main mod class
│   ├── CommonProxy.java    # Server/common logic
│   ├── ClientProxy.java    # Client-only logic
│   └── Config.java         # Configuration handler
├── 📁 src/main/resources/
│   ├── mcmod.info          # Mod metadata
│   └── LICENSE             # MIT License
├── gradle.properties       # Mod configuration
├── setup-new-mod.bat       # Automated setup script
├── reset-to-template.bat   # Reset to blank template
├── QUICK_REFERENCE.md      # Command reference
└── TEMPLATE_INSTRUCTIONS.md # Manual setup guide
```

## Common Commands

| Command | Description |
|---------|-------------|
| `.\gradlew.bat build` | Build your mod JAR |
| `.\gradlew.bat runClient` | Launch Minecraft client |
| `.\gradlew.bat runServer` | Launch Minecraft server |
| `.\gradlew.bat spotlessApply` | Format code |
| `.\gradlew.bat clean` | Clean build artifacts |

See [QUICK_REFERENCE.md](QUICK_REFERENCE.md) for more commands.

## Configuration

### gradle.properties
Main configuration file for your mod:
- `modName` - Display name
- `modId` - Unique identifier
- `modGroup` - Java package
- `VERSION` - Version override (optional)

### mcmod.info
Mod metadata displayed in Minecraft:
- Description
- Author list
- Dependencies
- URL

## Manual Setup

If you prefer manual setup, see [TEMPLATE_INSTRUCTIONS.md](TEMPLATE_INSTRUCTIONS.md).

## Reset to Template

To start over with a blank template:
```bash
.\reset-to-template.bat
```

⚠️ **Warning:** This will delete all your custom code!

## Requirements

- **Java 21** - Adoptium JDK 21 (configured in gradle.properties)
- **Git** - For version tagging (optional)
- **Windows** - PowerShell scripts (can be adapted for Linux/Mac)

## Adding Content

### Blocks
1. Create class extending `Block`
2. Register in `CommonProxy.preInit()`
3. Add textures to `assets/[modid]/textures/blocks/`

### Items
1. Create class extending `Item`
2. Register in `CommonProxy.preInit()`
3. Add textures to `assets/[modid]/textures/items/`

### Recipes
Add in `CommonProxy.init()` using `GameRegistry.addRecipe()`

## Troubleshooting

### "NO-GIT-TAG-SET" warning
Either:
- Initialize git and create a tag: `git tag 1.0.0`
- Add `VERSION = 1.0.0` to gradle.properties

### Spotless formatting errors
```bash
.\gradlew.bat spotlessApply
```

### Build fails
1. Check Java 21 is installed and configured
2. Run `.\gradlew.bat clean`
3. Check logs in `build/` directory

## Resources

- [GTNH Wiki](https://gtnh.miraheze.org/wiki/Development)
- [Forge 1.7.10 Docs](https://mcforge.readthedocs.io/en/1.7.10/)
- [Minecraft Forge Forums](https://forums.minecraftforge.net/)

## License

MIT License - See [LICENSE](LICENSE) file

## Author

**xXseesXx** - 2025

Based on [GTNH ExampleMod](https://github.com/GTNewHorizons/ExampleMod1.7.10)

---

Happy modding! 🎮✨
