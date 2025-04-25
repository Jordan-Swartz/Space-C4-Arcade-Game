# 🎮 Connect-Four-Game 🎮
Connect-Four Game Implementation in Java

---

## 1: 📌 About the Project:
A Java-based Connect Four game that supports multiple modes of play: GUI, console-based, and a work-in-progress 2-player server mode. The game is designed to provide gameplay for both single-player and multiplayer modes by using a simple computer player AI. It is designed for learning and practicing data structures, client-server architectures, game engine development, and algorithmic strategies.

---

## 2: ⚙️ Featured Concepts:
1. Breadth-First Search (BFS) AI
   - Simulates all possible future moves, selecting the one with the highest winning potential or blocking the opponent.
2. Real-Time Game Analytics
   - Utilizes a TokenCounter system with 4 matrices to track token sequences. This acts as a cache for localized updates that support the AI algorithm and allow for more efficient game state management.
4. FXML and JavaFX GUI
   - Responsive interface partially designed with SceneBuilder (FXML) and JavaFX.
5. Modular Game Engine
   - Handles logic separately from UI for easy testing and future scalability.
6. Console Mode
   - Text-based game version for environments where GUI is not supported.
7. Client-Server Architecture (in-progress)
   - Multiplayer support via a dedicated server using sockets and JSON protocol (currently under development).
8. Unit Testing
   - Implemented for function validation.
      
---

## 3: 🚀 Running the Project:
- ▶️ The project can be run using `gradle run`  or `./gradlew run`. This will launch into the GUI that will allow for further game mode selections, such as console mode, client connection, single-player or multiplayer.
- 🧪 The JUnit tests can be run using `gradle test` or `./gradlew test`. The tests for the core logic can be found in the [js_game directory](https://github.com/Jordan-Swartz/Space-C4-Arcade-Game/tree/master/src/test/java/js_game).
- 🎥 Screencast: [Project Demo](https://youtu.be/jJKHuc3hb-g)
---

## 4: 🔍 Design Highlights:

1. Real-Time Token Tracking
- The TokenCounter system uses four matrices to track token sequences:
- horizontalCounts, verticalCounts, diagonalLTRCounts, diagonalRTLCounts
- Each cell stores a token and its count in that direction, enabling:
- Fast win-condition checks
- Clean separation of game logic and analytics
- Simple AI evaluation and debugging

2. AI with BFS-Style Move Evaluation
- Simulates all valid moves
- Scores each based on potential token alignments
- Prioritizes wins, blocks threats, and prefers the center column

---

## 5: 🛠 Technologies Used:
1. Java 17
   - core programming language
2. JavaFX + SceneBuilder
   - project build system
3. FXML
   - GUI rendering
4. JUnit
   - unit testing
5. JavaDoc
   - documentation
7. GitHub Actions
   - CI for automated builds
  
---

## 6: 📚Resources Used:
1. SceneBuilder
   - [SceneBuilder](https://gluonhq.com/products/scene-builder/) - GluonHQ
2. JUnit Testing
   - [JUnit Testing](https://www.baeldung.com/courses/learn-junit-course) - JUnit Resource Guide
3. BFS Algorithm
   - [BFS, DFS, Dijkstra](https://www.baeldung.com/cs/dfs-vs-bfs-vs-dijkstra) - BFS VS DFS
4. Graphs
   - [Graph Theory](https://www.baeldung.com/cs/graphs-series) - Graph Data Structures and Algorithms
5. Network Programming
   - [Network Interfaces](https://www.baeldung.com/java-network-interfaces) - Client/Server Architecture
     
---
![C4.png](resources/images/C4-title.png)
---

