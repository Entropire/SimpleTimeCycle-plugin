![banner](./img/Banner.png)

<div align="center">

[![Release v1.0.0](https://img.shields.io/badge/Release-v1.0.0-darkgreen?style=flat-square)](https://modrinth.com/plugin/simple-factions/version/2.1.1)
[![Installs 0](https://img.shields.io/badge/Installs-0-brightgreen?style=flat-square)](https://modrinth.com/plugin/simple-factions)
[![Minecraft 1.21.*](https://img.shields.io/badge/Minecraft-1.21.*-orange?style=flat-square&logo=minecraft)](https://minecraft.net/)
[![Paper Server](https://img.shields.io/badge/Server-Paper-blue?style=flat-square&logo=paper)](https://papermc.io/)
[![Join Discord](https://img.shields.io/badge/Discord-Join%20Server-5865F2?style=flat-square&logo=discord)](https://discord.com/invite/AwA7mXV8qba)
[![GPL 3.0 License](https://img.shields.io/badge/License-GPL_3.0-blue?style=flat-square)](https://www.gnu.org/licenses/gpl-3.0)
[![Java 21](https://img.shields.io/badge/Java-21-red?style=flat-square)](https://adoptium.net/)
[![Last Update](https://img.shields.io/github/last-commit/Entropire/SimpleTimeCycle-plugin?style=flat-square)](https://github.com/Entropire/SimpleFactions/commits/master)

</div>

<h5 align="center">
  <a href="#description">Description</a> •
  <a href="#features">Features</a> •
  <a href="#installation">Installation</a> •
  <a href="#installation">configuration</a>
</h5>

<h2 id="description">📝 Description</h2>

**Simple Time Cycle** enhances Minecraft’s day/night and sleep systems, giving server owners a powerful yet lightweight way to customize time mechanics while remaining easy to use, lightweight, and feature-rich. perfect for anyone who wants a clean, straightforward way to manage the day/night cycle and sleeping behavior.

<h2 id="features">⚡ Features</h2>

- **Custom day & night lengths:** Set the duration of day and night individually.  
- **Disable sleep:** Prevent players from sleeping entirely.  
- **Set % of players to skip night:** Define the percentage of players required to skip the night.  
- **Configurable night skip amount:** Control how much of the night is skipped when conditions are met.  
- **Instant skip or time acceleration:** Choose between instantly skipping the night or speeding up time.  
- **Per-world settings:** Configure these options separately for each world.

<h2 id="installation">🚀 Installation</h2>

1. Create a Paper server if you don’t have one: [PaperMC Docs](https://docs.papermc.io/paper/getting-started/)
2. Download the latest version of the plugin [here](https://modrinth.com/plugin/simple-factions/versions)
3. Place the JAR file in your server’s `plugins` folder
4. Start the server and enjoy

<h2 id="configuration">⚙️ Configuration</h2>

### config.yml

- Edit `config.yml` to customize day/night lengths, sleep settings, and per-world options.  
- Example: set `player-sleep-percentage: 50` to require half the players to sleep.  
- Reload the plugin with `/stc reload` after making changes.  

### Commands

You can manage all plugin settings via commands.  

- `/stc [setting] [value]` – Changes a **global** setting applied to all worlds.  
- `/stc [worldName] [setting] [value]` – Changes a setting **for a specific world**.  

#### Day/Night Cycle Settings
- `day-cycle-length` – Set the length of daytime (minutes)  
- `night-cycle-length` – Set the length of nighttime (minutes)  
- `affected-worlds` – Set which worlds use the custom day/night cycle  

#### Player Sleep Window Settings
- `sleep-start-percentage` – Set when players are allowed to start sleeping (0-100%)  
- `sleep-end-percentage` – Set when players can no longer sleep (0-100%)  

#### Player Sleep Settings
- `enable-player-sleep` – Enable or disable player sleeping (true/false)  
- `player-sleep-disabled-message` – Set the message when sleeping is disabled  
- `player-sleep-percentage` – Set the percentage of players required to skip the night  
- `player-sleep-percentage-message` – Set the message when not enough players are sleeping  
- `player-sleep-success-message` – Set the message when enough players are sleeping and night is skipped  
- `night-skip-percentage` – Set how much of the night is skipped when conditions are met  
- `night-already-skip-message` – Set the message when night has already been skipped  

#### Utility Commands
- `/stc reload` – Reloads the plugin configuration  
- `/stc worlds` – Lists all worlds on the server

