# 🎮 START HERE - Minecraft 1.7.10 Mod Template

Welcome to your Minecraft 1.7.10 mod development template by xXseesXx!

## 🚀 Quick Start (3 Steps)

### Step 1: Setup Your Mod
```bash
.\setup-new-mod.bat
```
Answer the prompts with your mod details.

### Step 2: Build
```bash
.\gradlew.bat build
```

### Step 3: Test
```bash
.\gradlew.bat runClient
```

**That's it!** You're ready to start coding. 🎉

---

## 📚 Documentation Index

### For Beginners
1. **[START_HERE.md](START_HERE.md)** ← You are here!
2. **[README_TEMPLATE.md](README_TEMPLATE.md)** - Overview and features
3. **[QUICK_REFERENCE.md](QUICK_REFERENCE.md)** - Common commands and tips

### Setup & Configuration
- **[AUTOMATION_SCRIPTS.md](AUTOMATION_SCRIPTS.md)** - How the automation works
- **[TEMPLATE_INSTRUCTIONS.md](TEMPLATE_INSTRUCTIONS.md)** - Manual setup guide

### Reference
- **[gradle.properties](gradle.properties)** - Main configuration file
- **[mcmod.info](src/main/resources/mcmod.info)** - Mod metadata

---

## 📁 Project Structure

```
Your Mod Template/
│
├── 🎯 AUTOMATION SCRIPTS
│   ├── setup-new-mod.bat          # Configure new mod (USE THIS FIRST!)
│   ├── reset-to-template.bat      # Reset to blank template
│   └── gradlew.bat                # Gradle wrapper
│
├── 📖 DOCUMENTATION
│   ├── START_HERE.md              # This file
│   ├── README_TEMPLATE.md         # Main readme
│   ├── QUICK_REFERENCE.md         # Command reference
│   ├── AUTOMATION_SCRIPTS.md      # Script documentation
│   └── TEMPLATE_INSTRUCTIONS.md   # Manual setup guide
│
├── ⚙️ CONFIGURATION
│   ├── gradle.properties          # Mod configuration
│   └── src/main/resources/
│       └── mcmod.info             # Mod metadata
│
└── 💻 SOURCE CODE
    └── src/main/java/com/xXseesXx/mymodid/
        ├── MyMod.java             # Main mod class
        ├── CommonProxy.java       # Server/common logic
        ├── ClientProxy.java       # Client logic
        └── Config.java            # Configuration handler
```

---

## 🎯 What to Do Next

### First Time Setup
1. ✅ Run `.\setup-new-mod.bat`
2. ✅ Build with `.\gradlew.bat build`
3. ✅ Test with `.\gradlew.bat runClient`
4. ✅ Start coding in `src/main/java/com/xXseesXx/[yourmodid]/`

### Adding Features
- **Blocks** → Create class extending `Block`, register in `CommonProxy.preInit()`
- **Items** → Create class extending `Item`, register in `CommonProxy.preInit()`
- **Recipes** → Add in `CommonProxy.init()` using `GameRegistry.addRecipe()`
- **Config** → Edit `Config.java` to add configuration options

### Common Tasks
| Task | Command |
|------|---------|
| Build mod | `.\gradlew.bat build` |
| Run client | `.\gradlew.bat runClient` |
| Run server | `.\gradlew.bat runServer` |
| Format code | `.\gradlew.bat spotlessApply` |
| Clean build | `.\gradlew.bat clean` |

---

## 🔧 Configuration Files

### gradle.properties
Main configuration - edit these values:
```properties
modName = MyMod              # Your mod's display name
modId = mymodid              # Unique identifier (lowercase)
modGroup = com.xXseesXx.mymodid  # Java package
```

### mcmod.info
Mod metadata shown in Minecraft:
```json
{
  "modid": "${modId}",
  "name": "${modName}",
  "description": "Your description here",
  "authorList": ["xXseesXx"]
}
```

---

## 💡 Tips & Tricks

### Version Control
Initialize git for version-based versioning:
```bash
git init
git add .
git commit -m "Initial commit"
git tag 1.0.0
```

### IDE Setup
- **IntelliJ IDEA**: Import as Gradle project
- **Eclipse**: Run `.\gradlew.bat eclipse` then import
- **VS Code**: Install Java extensions, open folder

### Debugging
- Client logs: `run/logs/latest.log`
- Build logs: `build/` directory
- Crash reports: `run/crash-reports/`

### Performance
- First build takes ~30 seconds (downloads dependencies)
- Subsequent builds are faster (~5-10 seconds)
- Use `--offline` flag for offline builds

---

## 🆘 Troubleshooting

### Build Fails
```bash
.\gradlew.bat clean
.\gradlew.bat build
```

### Formatting Errors
```bash
.\gradlew.bat spotlessApply
```

### "NO-GIT-TAG-SET" Warning
Either:
- Create git tag: `git tag 1.0.0`
- Or add to gradle.properties: `VERSION = 1.0.0`

### Script Won't Run
Use the `.bat` files instead of `.ps1` files, or:
```powershell
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

---

## 📖 Learning Resources

### Minecraft Modding
- [GTNH Wiki](https://gtnh.miraheze.org/wiki/Development)
- [Forge 1.7.10 Docs](https://mcforge.readthedocs.io/en/1.7.10/)
- [Minecraft Forge Forums](https://forums.minecraftforge.net/)

### Java
- [Java Tutorials](https://docs.oracle.com/javase/tutorial/)
- [Effective Java](https://www.oreilly.com/library/view/effective-java/9780134686097/)

### Gradle
- [Gradle User Guide](https://docs.gradle.org/current/userguide/userguide.html)

---

## 🎨 Example Mod Structure

Here's what a simple mod might look like:

```
src/main/java/com/xXseesXx/supertools/
├── SuperTools.java              # Main mod class
├── CommonProxy.java             # Common logic
├── ClientProxy.java             # Client logic
├── Config.java                  # Configuration
├── blocks/
│   ├── BlockSuperOre.java       # Custom block
│   └── ModBlocks.java           # Block registry
├── items/
│   ├── ItemSuperPickaxe.java    # Custom item
│   └── ModItems.java            # Item registry
└── recipes/
    └── ModRecipes.java          # Recipe registry

src/main/resources/
├── mcmod.info
└── assets/supertools/
    ├── textures/
    │   ├── blocks/
    │   │   └── super_ore.png
    │   └── items/
    │       └── super_pickaxe.png
    └── lang/
        └── en_US.lang
```

---

## ✅ Checklist

Before you start coding:
- [ ] Run `.\setup-new-mod.bat`
- [ ] Verify build works: `.\gradlew.bat build`
- [ ] Test client launches: `.\gradlew.bat runClient`
- [ ] Initialize git repository (optional)
- [ ] Read [QUICK_REFERENCE.md](QUICK_REFERENCE.md)

---

## 🎉 You're Ready!

Everything is set up and ready to go. Start coding your mod in:
```
src/main/java/com/xXseesXx/[yourmodid]/
```

**Happy modding!** 🚀

---

**Template by:** xXseesXx  
**Based on:** GTNH ExampleMod  
**License:** MIT  
**Year:** 2025

---

## 📞 Need Help?

1. Check [QUICK_REFERENCE.md](QUICK_REFERENCE.md)
2. Review [AUTOMATION_SCRIPTS.md](AUTOMATION_SCRIPTS.md)
3. Read [TEMPLATE_INSTRUCTIONS.md](TEMPLATE_INSTRUCTIONS.md)
4. Check the GTNH Wiki
5. Ask on Minecraft Forge Forums

**Good luck with your mod!** 🎮✨
