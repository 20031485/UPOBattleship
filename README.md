# UPOBattleship - Programming Paradigms Java project
Java project for the Programming Paradigms course at University of Eastern Piedmont, Vercelli (IT).

# Latest additions
Trying to merge Paco's code into Lorenzo's + package hierarchy established.

# TODO
 - [x] Implement `Ship` class and its methods for positioning;
 - [x] Implement `Player` class and its methods for positioning ships (delegation calls to `Ship`'s methods);
 - [ ] Implement `Player`'s methods for shooting[ ]/getting shot[x] at certain coordinates;
 - [ ] Join `Player` and `Ship` into `BattleshipModel` and implement remaining methods (i.e. P1bombsP2(), turn() etc);
 - [ ] Implement `Computer extends Player` and its methods by using `Player`'s methods;
 - [ ] Implement `SetShipsPanel`;
 - [ ] Implement `SetShipsController`;
 - [ ] Implement `BattlePanel`;
 - [ ] Implement `BattleController`;
 - [ ] Implement `PlayerTest` and `ShipTest`;
 - [ ] Check if some `Exception`s can be launched - add them if so;
 - [ ] Implement `Timer` from javax.swing for a basic countdown if the "Timed" box is checked;
 - [ ] JavaDOC.

# Useful links
 - [Markdown cheatsheet](https://www.markdownguide.org/cheat-sheet/) because Lorenzo  _always_  forgets stuff;
 - [Swing Timer](https://docs.oracle.com/javase/tutorial/uiswing/misc/timer.html) for game loss condition;
 - [MVC paradigm](https://www.oracle.com/technical-resources/articles/javase/application-design-with-mvc.html) to clearly understand how to implement things;
 - [PropertyChangeSupport and PropertyChangeListener](https://docs.oracle.com/javase/tutorial/javabeans/writing/properties.html#bound) in JavaBeans, because `Observable` and `Observer` are deprecated since Java 9.
 
`Paco and Lorenzo's repository`