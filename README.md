# Synthetic Division Calculator
##Developed by: Horatiu Lazu

#Purpose
The purpose of this application is to allow a user to find the quotient when dividing two polynomials - one by another. This application uses a GUI to do so, and uses a JTable to illustrate Ruffini's Law at work.


#Features:
* Develops chart demonstrating synthetic division (with coefficients and consideration of monic expressions)
* Can divide any two polynomials regardless of power, although reasonable division (aka dividend's degree > divisor's degree & degrees <1000 recommended)
* Chart automatically resizes based off of coefficients
* Two chart design, in two perspectives, with one output via Console and another via JTable to ensure user can comprehend data
* Ability to export table via File IO (*.txt)
* Analyzes table, and retrieves resultant ƒ(x) representing quotient and remainder
* Easy to use graphical user interface
* Identifies terms, sorts terms using built-in time complexity: O(n) = nlog(n) Collections.sort() by extending Comparable<T> class
* Automatically fills terms if required to ensure proper synthetic division occurs using filler algorithm
* Dynamically adjusts array size using ArrayList<Term>
* OOP (Object Oriented Programming) Paradigm designed to encapsulate data and be upgradable, usable through Console
* Efficient algorithm
* Developed in Java (multi-platform)
* JavaDoced Index files for program documentation

#OOP Design:
###Solver class:
Solves by returning three Arrays (one 1D, two 2D)
Analyzes arrays, then returns developed Object[][] array to be represented on JTable
###Utility class:
Acts as a utility class by doing an assortment of activities.
Term:
Acts as a term class to store coefficients & powers as objects.
###SyntheticApp:
Acts as a term class to develop the JFrame
Sets up the JPanel (extends it), handles actions, and handles all visual representation in terms of setting up the JTable
The other classes are smaller / insignificant in the larger spectrum. 

#Known issues:
Sometimes may be unstable if the dividend's degree is smaller than the degree of the divisor (however the program still formulates the table) resulting in a odd remainder. 

##YouTube Demonstration:
[Click here](https://www.youtube.com/watch?v=G-ySgKVjx-Y)

##Screenshot:
![Screenshot](http://horatiulazu.ca/software/images/SyntheticDivision.png "Screenshot")