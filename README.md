# UPOBattleship - Programming Paradigms Java project
Java project for the Programming Paradigms course at University of Eastern Piedmont, Vercelli (IT).

# Latest additions/fixes
Since a rematch button after win/loss is not required, we are not going to implement one (we will leave it semi-implemented).

# TODO
 - [ ] **FIX** `Computer::lineCheck()`: it does not keep aligned cells and it does not return "unused" cells to `coordinatesList`;
 - [ ] **FIX** `BattlePanel::Timer`: it does not work LOL;
 - [ ] **FIX** `BattleshipModel::saveGame()` and `BattleshipModel::loadGame()`: they work in tests, but they don't communicate well with the GUI;
 - [ ] Implement `BattleshipModelTest`;
 - [x] Fix `NewGamePanel`'s layout;
 - [ ] Check if some `Exception`s can be launched - add them if so;
 - [ ] Implement `Timer` - **ongoing** but it still doesn't show up - working on it;
 - [ ] Write JavaDOC - **ongoing**.

# Useful links
 - [Markdown cheatsheet](https://www.markdownguide.org/cheat-sheet/) because Lorenzo  _always_  forgets stuff;
 - [Swing Timer](https://docs.oracle.com/javase/tutorial/uiswing/misc/timer.html) for game loss condition;
 - [MVC paradigm](https://www.oracle.com/technical-resources/articles/javase/application-design-with-mvc.html) to clearly understand how to implement things;
 - [PropertyChangeSupport and PropertyChangeListener](https://docs.oracle.com/javase/tutorial/javabeans/writing/properties.html#bound) in JavaBeans, because `Observable` and `Observer` are deprecated since Java 9.
 
`20027017's & 20031485's repository`