# 2048 Java Version

## Proposal
The application will allow user to play the classic mini-game *2048*. Anyone who feels bored and would like to 
have some fun are encouraged to use this application. The applicatinon was designed for bringing users some fun gaming 
experiences. I have been interested in game design since I was a kid so I think that this could be a good chance for me
to try making a basic game with the code I learned. 

## User Stories
- As a user, I want to move all numbers on the board as I expected.
- As a user, I want numbers to be multiplied and merged when two same numbers meet.
- As a user, I want the game to end when there is no more actions I can operate.
- As a user, I expect that I would receive a warning when the numbers can't be moved in a specific direction.
- As a user, I want it to generate 2 or 4 every round randomly when there are spaces left.
- As a user, I want to see how many number blocks I successfully merged during the game.
- As a user, I want to save the current game process including the current board and the
score list to a file for future loading.
- As a user, I want to load the last saved game to continue playing 2048.

### Adding multiple Xs to a Y
In my application, the realization of multiple Xs to a Y happens when the user is playing the game, multiple blocks that
the user successfully merged will be added to a list where each element in Y correspond to the number of different
blocks.