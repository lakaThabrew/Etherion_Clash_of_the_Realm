# 🎮 Etherion: Clash of Realms

**Etherion: Clash of Realms** is a turn-based RPG game written in Java. The game combines fast-paced elemental combat, strategic power selection, and player progression through mystical kingdoms.

---

## 🧩 Game Overview

- 🔥 Choose your elemental affinity: Fire, Water, Earth, or Air  
- 🛡️ Battle unique villains in 12 epic missions  
- 🧙‍♂️ Use special powers and equip shields to dominate your enemies  
- 🛍️ Earn coins and buy new characters, powers, and shields  
- 📈 Progress your player level and unlock harder challenges  

---

## 🏗️ Features

- Turn-based combat system with dynamic damage calculation  
- Character classes using Object-Oriented Design (OOP)  
- Game data loaded from CSV files (Heroes, Villains, Powers, Shields, Missions)  
- Save/Load game functionality with Java Serialization  
- CLI-based menu system with battles, shop, and profile views  

---

## 🗂️ Project Structure

```bash
Etherion_Clash_of_Realms/
  ├── data/ # CSV files for game data (powers, shields, characters, etc.)
  ├── src/
  │ ├── Main.java # Game entry point
  │ ├── GameEngine.java # Game menu, player creation, main loop
  │ ├── Battle.java # Battle system logic
  │ ├── Player.java # Player model with inventory, stats
  │ ├── Character.java # Abstract base class
  │ ├── HeroChar.java # Player-controlled characters
  │ ├── Villain.java # Mission enemies
  │ ├── Power.java # Powers with attack value and usage
  │ ├── Shield.java # Shields with defense boost
  │ └── GameDataLoader.java # Loads game data from CSV files
  └── README.md

```

---


---

## 🚀 Getting Started

### ✅ Prerequisites

- Java JDK 17 or higher
- A terminal or IDE (e.g. IntelliJ, Eclipse, VS Code)

### ▶️ How to Run

```bash
# Navigate into project directory
cd Etherion_Clash_of_Realms

# Compile
javac src/*.java

# Run the game
java src/Main
```
---
## 🧠 Damage Formula
```bash
  Damage = (AttackerAttack × PowerAttack × (1 + AttackerSpeed))  / (OpponentDefense + ShieldDefense + 10 × (1 - OpponentSpeed))
```
- All characters start with 100% HP.
- Combat continues until one side’s health drops to 0.
- Victory = Level up + coins; Defeat = lose 20% of spent coins.
---

## 🛒 Shop System
  - Buy powers (up to 4 equipped at once)
  - Unlock and buy shields based on level
  - Upgrade to more powerful hero characters

--- 

## 💾 Save/Load Support
 - The game state (player, inventory, level) is saved to savegame.dat using Java's Serializable mechanism.
 - Main Menu → Save Game to save progress
 - Main Menu → Load Game to resume
---
## 📊 Data Files
Located in /data:
```bash
  Data_Heros.csv
  Data_Villians.csv
  Data_Mission.csv
  Data_Powers.csv
  Data_Shields.csv
```
- All loaded via GameDataLoader.java.

---

## 🙌 Owned By:
  Lakmana Thabrew
---

## 🛠 Tools Used
  - Java 17
  - Canva (for project planning & visual assets)
  - Diagrams.net (for UML diagrams)
  - Git & GitHub

## 📜 License
This project is for academic and personal development.
Could you please contact the contributors for permission to reuse or modify their work?

python
Copy
Edit

---

Let me know if you'd like:
- A GitHub Actions CI workflow for Java
- `.gitignore` to include in the repo
- Help creating a GitHub project board or issue templates

### You're nearly done polishing this game into a solid open-source repo 👏
