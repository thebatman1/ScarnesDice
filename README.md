# Scarne's Dice
Scarne's Dice or the game of Pig is a jeopardy dice game where the aim of the player is to score 100 points.
Each player’s turn consists of repeatedly rolling a die. After each roll,
the player is faced with two choices: roll again, or hold (decline to roll again).
<ul>
<li>If the player rolls a 1, the player scores nothing and it becomes the opponent’s
turn.
<li>If the player rolls a number other than 1, the number is added to the player’s
turn total and the player’s turn continues.
<li>If the player holds, the turn total, the sum of the rolls during the turn, is
added to the player’s score, and it becomes the opponent’s turn.
</ul>

# Layout
The layout consists of three buttons :Roll, Hold and Reset.<br>
<ul>
<li><b>Hold</b>: Clicked by the user when he/she decides to hold
<li><b>Roll</b>: Clicked by the user when he/she decides to roll
<li><b>Reset</b>: Clicked by the user when the game is over
</ul>
There are three textviews to display the userscore, computerscore and turnscore respectively.<br>
And there is an imageview to show the dice face which has been rolled.

# Functions
<ul>
<li><b>roll</b> : handles the roll click events of the user
<li><b>hold</b> : handles the hold click events of the user
<li><b>computerTurn</b> : handles the computer player of the game
<li><b>reset</b> : resets the game
<li><b>setComputerOverallScore</b> : sets the computer score after it decides to hold or rolls a 1
<li><b>declareWinner</b> : declares the winner of the game

