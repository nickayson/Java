#!/bin/bash

#Author: Nicholas Ayson
#Program: Sun and Earth
#Email: nick.ayson@csu.fullerton.edu


echo "Remove old byte-code files if any exist."
rm *.class


javac Earth_Sun_panel.java

echo "Compile the user interface"
javac Game_user_interface.java

echo "Compile the main method"
javac ball.java

echo "Run the program"
java ball

echo "This bash script file is quiting"
