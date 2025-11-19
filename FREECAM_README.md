# Freecam Mod for Minecraft 1.7.10

A simple freecam mod for Minecraft 1.7.10 GTNH by xXseesXx.

## Features

- Toggle freecam mode with F4 key
- Fly freely through the world without moving your player
- No collision - fly through blocks
- Use standard movement controls (WASD, Space, Shift)

## Controls

- **F4** - Toggle freecam on/off
- **W/A/S/D** - Move forward/left/backward/right
- **Space** - Move up
- **Shift** - Move down
- **Mouse** - Look around

## How to Use

1. Launch Minecraft with the mod installed
2. Join a world
3. Press **F4** to enable freecam
4. Move around freely using the controls above
5. Press **F4** again to return to your player

## Technical Details

- Mod ID: `seesfreecam`
- Mod Name: `freecam`
- Version: 0.1
- Minecraft Version: 1.7.10

## Building

```bash
.\gradlew.bat build
```

## Running

```bash
.\gradlew.bat runClient
```

## Files

- `FreecamHandler.java` - Main freecam logic
- `FreecamEntity.java` - Freecam camera entity
- `KeyHandler.java` - Keyboard input handling
- `TickHandler.java` - Update tick handling
- `ClientProxy.java` - Client-side initialization

## Author

xXseesXx
