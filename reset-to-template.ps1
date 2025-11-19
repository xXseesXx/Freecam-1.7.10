# Reset to Blank Template Script
# Author: xXseesXx
# WARNING: This will reset your mod back to the blank template state!

Write-Host "==================================" -ForegroundColor Red
Write-Host "RESET TO BLANK TEMPLATE" -ForegroundColor Red
Write-Host "==================================" -ForegroundColor Red
Write-Host ""
Write-Host "WARNING: This will reset your mod to the blank template state!" -ForegroundColor Yellow
Write-Host "All custom code and configurations will be lost!" -ForegroundColor Yellow
Write-Host ""

$confirm = Read-Host "Are you ABSOLUTELY sure you want to continue? (type 'YES' to confirm)"

if ($confirm -ne 'YES') {
    Write-Host "Reset cancelled." -ForegroundColor Green
    exit 0
}

Write-Host ""
Write-Host "Resetting to template..." -ForegroundColor Yellow

# Reset gradle.properties
Write-Host "Resetting gradle.properties..." -ForegroundColor Cyan
$gradleProps = Get-Content "gradle.properties" -Raw
$gradleProps = $gradleProps -replace 'modName = .*', 'modName = MyMod'
$gradleProps = $gradleProps -replace 'modId = .*', 'modId = mymodid'
$gradleProps = $gradleProps -replace 'modGroup = com\.xXseesXx\..*', 'modGroup = com.xXseesXx.mymodid'
$gradleProps = $gradleProps -replace 'generateGradleTokenClass = com\.xXseesXx\..*\.Tags', 'generateGradleTokenClass = com.xXseesXx.mymodid.Tags'
# Remove VERSION override if present
$gradleProps = $gradleProps -replace '(?m)^# Version override.*\r?\n.*VERSION = .*\r?\n', ''
Set-Content "gradle.properties" -Value $gradleProps -NoNewline

# Reset mcmod.info
Write-Host "Resetting mcmod.info..." -ForegroundColor Cyan
$mcmodInfo = @'
{
	"modListVersion": 2,
	"modList": [{
		"modid": "${modId}",
		"name": "${modName}",
		"description": "A Minecraft 1.7.10 mod",
		"version": "${modVersion}",
		"mcversion": "${minecraftVersion}",
		"url": "",
		"updateUrl": "",
		"authorList": ["xXseesXx"],
		"credits": "",
		"logoFile": "",
		"screenshots": [],
		"parent": "",
		"requiredMods": [],
		"dependencies": [],
		"dependants": [],
		"useDependencyInformation": false
	}]
}
'@
Set-Content "src\main\resources\mcmod.info" -Value $mcmodInfo -NoNewline

# Find and rename any custom package back to mymodid
Write-Host "Resetting package structure..." -ForegroundColor Cyan
$packages = Get-ChildItem "src\main\java\com\xXseesXx" -Directory
foreach ($pkg in $packages) {
    if ($pkg.Name -ne "mymodid") {
        Write-Host "  Renaming $($pkg.Name) to mymodid..." -ForegroundColor Yellow
        if (Test-Path "src\main\java\com\xXseesXx\mymodid") {
            Remove-Item "src\main\java\com\xXseesXx\mymodid" -Recurse -Force
        }
        Rename-Item $pkg.FullName "mymodid"
        break
    }
}

# Reset Java files to template
Write-Host "Resetting Java files..." -ForegroundColor Cyan

# MyMod.java
$myModContent = @'
package com.xXseesXx.mymodid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = MyMod.MODID, version = Tags.VERSION, name = MyMod.MODNAME, acceptedMinecraftVersions = "[1.7.10]")
public class MyMod {

    public static final String MODID = "mymodid";
    public static final String MODNAME = "MyMod";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @SidedProxy(clientSide = "com.xXseesXx.mymodid.ClientProxy", serverSide = "com.xXseesXx.mymodid.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }
}
'@
Set-Content "src\main\java\com\xXseesXx\mymodid\MyMod.java" -Value $myModContent -NoNewline

# CommonProxy.java
$commonProxyContent = @'
package com.xXseesXx.mymodid;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());
        MyMod.LOG.info("Loading " + MyMod.MODNAME + " version " + Tags.VERSION);
    }

    public void init(FMLInitializationEvent event) {}

    public void postInit(FMLPostInitializationEvent event) {}

    public void serverStarting(FMLServerStartingEvent event) {}
}
'@
Set-Content "src\main\java\com\xXseesXx\mymodid\CommonProxy.java" -Value $commonProxyContent -NoNewline

# ClientProxy.java
$clientProxyContent = @'
package com.xXseesXx.mymodid;

public class ClientProxy extends CommonProxy {

    // Override CommonProxy methods here for client-side specific behavior
}
'@
Set-Content "src\main\java\com\xXseesXx\mymodid\ClientProxy.java" -Value $clientProxyContent -NoNewline

# Config.java
$configContent = @'
package com.xXseesXx.mymodid;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static String exampleValue = "Default Value";

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        exampleValue = configuration.getString(
            "exampleValue",
            Configuration.CATEGORY_GENERAL,
            exampleValue,
            "An example configuration value"
        );

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
'@
Set-Content "src\main\java\com\xXseesXx\mymodid\Config.java" -Value $configContent -NoNewline

# Reset README
Write-Host "Resetting README.md..." -ForegroundColor Cyan
$readme = @'
# MyMod - Minecraft 1.7.10 Forge Mod Template

A blank template for Minecraft 1.7.10 Forge mods by xXseesXx, based on the GTNH ExampleMod.
'@
Set-Content "README.md" -Value $readme -NoNewline

# Clean build
Write-Host "Cleaning build artifacts..." -ForegroundColor Cyan
if (Test-Path "build") {
    Remove-Item "build" -Recurse -Force -ErrorAction SilentlyContinue
}
if (Test-Path ".gradle") {
    Remove-Item ".gradle" -Recurse -Force -ErrorAction SilentlyContinue
}

# Format code
Write-Host "Formatting code..." -ForegroundColor Cyan
& .\gradlew.bat spotlessApply | Out-Null

Write-Host ""
Write-Host "==================================" -ForegroundColor Green
Write-Host "Reset Complete!" -ForegroundColor Green
Write-Host "==================================" -ForegroundColor Green
Write-Host ""
Write-Host "Your project has been reset to the blank template." -ForegroundColor Green
Write-Host "Run .\setup-new-mod.bat to configure a new mod." -ForegroundColor Cyan
