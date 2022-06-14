# MVC-Architecture--Java-Swing-Calculator
A personal project to sharpen my understanding of the Model-View-Controller architecture: A desktop Java Swing Calculator. 
(It is in Spanish but my next projects will be in English).

The calculator is implemented as a Finite State Machine with a combination of MVC architecture and State Pattern. 

* Architecture: 
  - Model / View / Controller
 
 * Design Patterns:
    - State Pattern
    - Strategy Pattern
    - The five SOLID principles
 
 * I included Java 8 features whenever possible, such as Streams, Lambdas and Method references. 



 
 Project overview: 
 
* Model:  
   - 'Operation' base interface for the Strategy pattern. 
   - Four Strategies that implement 'Operation', which are 'Add, Substract, Divide and Multiply'
   - 'Calculator', which holds a collection with all the implementations of 'Operation'. 
     The Calculator class actually does not perform calculations, it delegates to the corresponding Strategy class. 
     This follows the Open-Closed principle, as functionality can be easily extended by adding a new Strategy class. 
   - 'CalculatorState', base interface for the State pattern  

* Controller: 
   - Two implementations of the 'CalculatorState' interface, which handle the user input in different ways. 
    Depending on the user inputs, the Calculator will enter one state or another. 
  
* View: 
   - 'CalculatorView', which performs the task of setting up the layout with a Java Swing user interface.  
   - 'CalculatorViewPreferences', which holds a set of configurable variables that include background colors
     and sizes for the components shown in screen. 
 
