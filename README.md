# UPOBattleship - Programming Paradigms Java project
Java project for the Programming Paradigms course at University of Eastern Piedmont, Vercelli (IT).

Added:
 - `BattleshipStartLoadWindow`is the start screen: it allows the user to choose whether to start a new game or load an unfinished one;
 - `BattleshipExitFrame`: it displays a window checking the user really wants to quit the game;
 - `BattleshipWindowDestructor` to make `BattleshipExitFrame` visible when trying to close `BattleshipStartLoadWindow`'s window;
 - `BattleshipSaveBeforeExitFrame` to be displayed when pressing "exit" during a game **[TODO: another WindowDestructor to call in that case]**.
 - `loadGame()` in `Game` has been made  _static_ : that was the only solution since calling `gameObject.loadGame()` after declaring `Game gameObject = null` would throw a `NullPointerException`, but it works kind of fine now. 

# Useful links
 - [Markdown cheatsheet](https://www.markdownguide.org/cheat-sheet/)

`Paco and Lorenzo's repository`