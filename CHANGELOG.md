# Changelog

## [1.2] - 2024-11-21

### Added
- **F5 Mode Toggle**: Switch between freecam movement and player movement while in freecam
  - FREECAM mode: Camera moves freely, player frozen (default)
  - PLAYER mode: Camera stays static, player can move (perfect for drone shots/cinematic recordings)
- Mouse wheel speed adjustment with on-screen display
- Sprint key support for faster camera movement

### Changed
- **Interaction System**: All block/entity interactions now disabled in both modes
  - Provides clean spectator-like behavior
  - Prevents accidental world modifications while recording
  - No more complex raytrace workarounds

### Fixed
- **Critical**: Fixed self-attack crash when targeting player in PLAYER mode
  - Added three layers of protection to prevent crash
  - Player entity can no longer be targeted or attacked
- Removed non-functional raytrace override code
- Cleaned up unused position override methods
- Fixed F5 perspective change interception

### Technical
- Simplified codebase by removing 80+ lines of non-working raytrace code
- Improved interaction blocking at controller level
- Better code organization with documentation moved to `docs/` directory
- Added comprehensive testing guide and technical documentation

## [1.0] - Initial Release

### Added
- Basic freecam functionality
- F4 to toggle freecam on/off
- WASD/Space/Shift camera movement
- No-clip flying through blocks
- Player position/rotation preservation
