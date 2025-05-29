# Project: Minesweeper

## MVP

Recreate a simplified version of the game Minesweeper to be played in the java console
The game should be able to randomly generate 10 mines in a 10x10 grid
The user will be able to enter a command that represents a coordinate to check a location for a mine
The application will display a number from 0-8 depending on how many mines surround that location
If the user selects a mine, the game will respond "boom!" and the game will be lost
If every non-mine square has been revealed, the game is won
Render the grid to the console after every user command

## HINT:

- Although this can be done in a single file we want to see you use OOP
- You can create custom classes for this
- Try and seperate your code into logical classes
- Think about errors/edge cases
- Reach out for help early if you get stuck
- Break this into as many small tasks as possible so you can just focus on achieving one thing
  - Render a grind for a blank array
  - Generate unique cooridinates 10 times
  - Figure out how to take input that matches your coodinates
  - 2d co-oridinates are tricky. Don't be afraid to rename variables to make things easier to understand (eg row)

## Bonuses (optional)

Allow for the user to configure number of mines and grid size via a configuration.
(Difficult) Discovering an empty square should reveal all squares around it, and cascade into other nearby empty squares
