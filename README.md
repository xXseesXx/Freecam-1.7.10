# Freecam Mod for Minecraft 1.7.10

A smooth and feature-rich freecam mod for Minecraft 1.7.10 GTNH.

## Features

- **Toggle freecam with F4** - Easy on/off
- **Smooth camera movement** - No flickering or artifacts
- **WASD controls** - Move forward/backward/left/right
- **Space/Shift** - Move up/down
- **Sprint boost** - Hold sprint key for 50% speed increase
- **Adjustable speed** - Use mouse wheel to change camera speed (0.2x - 10x)
- **Visual feedback** - Speed display when adjusting
- **Player frozen** - Your player stays completely still while in freecam
- **Gravity support** - Player falls naturally if in the air
- **Configurable** - Set default speed in config file

## Installation

1. Download the latest release from the [Releases](../../releases) page
2. Place the `.jar` file in your `mods` folder
3. Launch Minecraft 1.7.10 with Forge

## Usage

1. Press **F4** to toggle freecam on/off
2. Use **WASD** to move around
3. Use **Space** to move up, **Shift** to move down
4. Hold **Sprint** (Left Ctrl) to move 50% faster
5. Scroll **mouse wheel** to adjust camera speed
6. Press **F4** again to return to your player

## Configuration

Edit `config/seesfreecam.cfg` to change the default camera speed:

```properties
# Default freecam movement speed (0.2 - 10.0)
freecamSpeed=1.0
```

## Technical Details

- **Mod ID**: `seesfreecam`
- **Version**: 1.0
- **Minecraft Version**: 1.7.10
- **Forge Version**: 10.13.4.1614+

## Building from Source

```bash
git clone https://github.com/YOUR_USERNAME/seesfreecam.git
cd seesfreecam
./gradlew build
```

The built jar will be in `build/libs/`

## Credits

- **Author**: xXseesXx
- **Based on**: GTNH ExampleMod template

## License

MIT License - See LICENSE file for details
