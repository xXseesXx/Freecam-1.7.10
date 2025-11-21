# Freecam Mod for Minecraft 1.7.10

A simple freecam mod for Minecraft 1.7.10.

## Features

- **Press F4** to toggle freecam mode
- **Press F5** to switch between freecam movement and player movement
- **Static camera mode** - Position camera, then control your player for cinematic shots
- **Creative-style flight** - WASD to move, Space/Shift for up/down, Sprint for speed boost
- **Mouse wheel** to adjust camera speed
- **No interactions** - Block breaking/placing disabled in freecam (spectator-like)

## Installation

1. Download the latest release from the [Releases](../../releases) page
2. Place the `.jar` file in your `mods` folder
3. Launch Minecraft 1.7.10 with Forge

## Usage

Press **F4** to enter freecam mode. Your player stays in place while you fly around freely. 

Position your camera for the perfect shot, then press **F5** to toggle between:
- **Freecam mode**: Camera moves freely, player frozen in place
- **Player mode**: Camera stays STATIC, control your player (perfect for YouTube intros/drone shots)

**Note**: Block interactions are disabled in both modes (spectator-like behavior). This is a Minecraft engine limitation - the camera and interaction point cannot be separated.

Use the mouse wheel to adjust camera speed. Press **F4** again to exit freecam and return to your player.

## Configuration

Edit `config/seesfreecam.cfg` to change the default camera speed (0.2 - 10.0).

## Building from Source

```bash
git clone https://github.com/xXseesXx/Freecam-1.7.10.git
cd Freecam-1.7.10
./gradlew build
```

The built jar will be in `build/libs/`

## Credits

**Author**: xXseesXx

## License

MIT License
