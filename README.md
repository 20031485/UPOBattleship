# UPOBattleship - Programming Paradigms Java project
Java project for the Programming Paradigms course at University of Eastern Piedmont, Vercelli (IT).

# Latest additions
I have tried to implement the MVC paradigm, decoupling views from models mostly. It appears we can have just one size-shifting frame and a bunch of panels to be switched inside of it. Let us say the user presses **NEW GAME**: then the panel he/she is viewing will be set invisible (or disposed?) and the frame dimensions will be stretched to host the `BattleshipNewGameFrame` and all of its buttons. Then, when the user clicks on **CONFIRM**, he/she will see the frame stretch to its new dimensions to load the new panel, which will be the one Paco is implementing (ship positioning panel whatsoever). We will see about what is remaining!

# Useful links
 - [Markdown cheatsheet](https://www.markdownguide.org/cheat-sheet/) because Lorenzo  _always_  forgets stuff;
 - [Swing Timer](https://docs.oracle.com/javase/tutorial/uiswing/misc/timer.html) for game loss condition;
 
`Paco and Lorenzo's repository`