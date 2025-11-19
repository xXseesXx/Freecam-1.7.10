# Minecraft 1.7.10 Mod Setup Script
# Author: xXseesXx

Write-Host "==================================" -ForegroundColor Cyan
Write-Host "Minecraft 1.7.10 Mod Setup Script" -ForegroundColor Cyan
Write-Host "==================================" -ForegroundColor Cyan
Write-Host ""

# Prompt for mod details
$modName = Read-Host "Enter your mod's display name (e.g., 'My Awesome Mod')"
$modId = Read-Host "Enter your mod's ID (lowercase, no spaces, e.g., 'myawesomemod')"
$modDescription = Read-Host "Enter a short description for your mod"
$modVersion = Read-Host "Enter initial version (e.g., '1.0.0' or leave blank for git-based versioning)"
$modUrl = Read-Host "Enter mod URL/GitHub (optional, press Enter to skip)"

# Validate inputs
if ([string]::IsNullOrWhiteSpace($modName)) {
    Write-Host "Error: Mod name cannot be empty!" -ForegroundColor Red
    exit 1
}

if ([string]::IsNullOrWhiteSpace($modId)) {
    Write-Host "Error: Mod ID cannot be empty!" -ForegroundColor Red
    exit 1
}

# Ensure modId is lowercase and has no spaces
$modId = $modId.ToLower() -replace '\s+', ''

# Confirm details
Write-Host ""
Write-Host "==================================" -ForegroundColor Yellow
Write-Host "Please confirm your mod details:" -ForegroundColor Yellow
Write-Host "==================================" -ForegroundColor Yellow
Write-Host "Mod Name: $modName"
Write-Host "Mod ID: $modId"
Write-Host "Description: $modDescription"
Write-Host "Version: $(if ($modVersion) { $modVersion } else { 'Git-based' })"
Write-Host "URL: $(if ($modUrl) { $modUrl } else { 'None' })"
Write-Host ""
$confirm = Read-Host "Is this correct? (y/n)"

if ($confirm -ne 'y' -and $confirm -ne 'Y') {
    Write-Host "Setup cancelled." -ForegroundColor Red
    exit 0
}

Write-Host ""
Write-Host "Setting up your mod..." -ForegroundColor Green

# Update gradle.properties
Write-Host "Updating gradle.properties..." -ForegroundColor Cyan
$gradleProps = Get-Content "gradle.properties" -Raw
$gradleProps = $gradleProps -replace 'modName = MyMod', "modName = $modName"
$gradleProps = $gradleProps -replace 'modId = mymodid', "modId = $modId"
$gradleProps = $gradleProps -replace 'modGroup = com\.xXseesXx\.mymodid', "modGroup = com.xXseesXx.$modId"
$gradleProps = $gradleProps -replace 'generateGradleTokenClass = com\.xXseesXx\.mymodid\.Tags', "generateGradleTokenClass = com.xXseesXx.$modId.Tags"

# Add version override if specified
if ($modVersion) {
    if ($gradleProps -match 'autoUpdateBuildScript = false') {
        $gradleProps = $gradleProps -replace '(autoUpdateBuildScript = false)', "`$1`r`n`r`n# Version override (remove to use git-based versioning)`r`nVERSION = $modVersion"
    }
}

Set-Content "gradle.properties" -Value $gradleProps -NoNewline

# Update mcmod.info
Write-Host "Updating mcmod.info..." -ForegroundColor Cyan
$mcmodInfo = Get-Content "src\main\resources\mcmod.info" -Raw
$mcmodInfo = $mcmodInfo -replace '"description": "A Minecraft 1\.7\.10 mod"', "`"description`": `"$modDescription`""
if ($modUrl) {
    $mcmodInfo = $mcmodInfo -replace '"url": ""', "`"url`": `"$modUrl`""
}
Set-Content "src\main\resources\mcmod.info" -Value $mcmodInfo -NoNewline

# Rename package directory
Write-Host "Renaming package directory..." -ForegroundColor Cyan
$oldPackagePath = "src\main\java\com\xXseesXx\mymodid"
$newPackagePath = "src\main\java\com\xXseesXx\$modId"

if (Test-Path $oldPackagePath) {
    if (Test-Path $newPackagePath) {
        Write-Host "Warning: Target package directory already exists. Skipping rename." -ForegroundColor Yellow
    } else {
        Rename-Item $oldPackagePath $modId
    }
}

# Update Java files
Write-Host "Updating Java files..." -ForegroundColor Cyan
$javaFiles = Get-ChildItem "src\main\java\com\xXseesXx\$modId\*.java"

foreach ($file in $javaFiles) {
    $content = Get-Content $file.FullName -Raw
    
    # Update package declaration
    $content = $content -replace 'package com\.xXseesXx\.mymodid;', "package com.xXseesXx.$modId;"
    
    # Update imports
    $content = $content -replace 'import com\.xXseesXx\.mymodid\.', "import com.xXseesXx.$modId."
    
    # Update proxy paths in MyMod.java
    $content = $content -replace 'clientSide = "com\.xXseesXx\.mymodid\.ClientProxy"', "clientSide = `"com.xXseesXx.$modId.ClientProxy`""
    $content = $content -replace 'serverSide = "com\.xXseesXx\.mymodid\.CommonProxy"', "serverSide = `"com.xXseesXx.$modId.CommonProxy`""
    
    # Update MODID and MODNAME constants
    $content = $content -replace 'public static final String MODID = "mymodid";', "public static final String MODID = `"$modId`";"
    $content = $content -replace 'public static final String MODNAME = "MyMod";', "public static final String MODNAME = `"$modName`";"
    
    Set-Content $file.FullName -Value $content -NoNewline
}

# Update README.md
Write-Host "Updating README.md..." -ForegroundColor Cyan
$readme = Get-Content "README.md" -Raw
$readme = $readme -replace '# MyMod - Minecraft 1\.7\.10 Forge Mod Template', "# $modName"
$readme = $readme -replace 'A blank template for Minecraft 1\.7\.10 Forge mods by xXseesXx, based on the GTNH ExampleMod\.', "$modDescription"
Set-Content "README.md" -Value $readme -NoNewline

# Clean build artifacts
Write-Host "Cleaning build artifacts..." -ForegroundColor Cyan
if (Test-Path "build") {
    Remove-Item "build" -Recurse -Force -ErrorAction SilentlyContinue
}
if (Test-Path ".gradle") {
    Remove-Item ".gradle" -Recurse -Force -ErrorAction SilentlyContinue
}

# Run spotless to format code
Write-Host "Formatting code..." -ForegroundColor Cyan
& .\gradlew.bat spotlessApply | Out-Null

Write-Host ""
Write-Host "==================================" -ForegroundColor Green
Write-Host "Setup Complete!" -ForegroundColor Green
Write-Host "==================================" -ForegroundColor Green
Write-Host ""
Write-Host "Your mod '$modName' has been configured!" -ForegroundColor Green
Write-Host ""
Write-Host "Next steps:" -ForegroundColor Yellow
Write-Host "1. Run: .\gradlew.bat build" -ForegroundColor White
Write-Host "2. Run: .\gradlew.bat runClient" -ForegroundColor White
Write-Host "3. Start coding in: src\main\java\com\xXseesXx\$modId\" -ForegroundColor White
Write-Host ""
Write-Host "Happy modding! :)" -ForegroundColor Cyan
