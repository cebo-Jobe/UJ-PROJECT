The purpose of this project was to create a game using the  visitor design patterns.
This game is a simple catch-the-ball game built with JavaFX. In this game, the player controls a bucket that moves left or right to catch falling balls. The balls can be either good (green) or bad (red):

Good Balls (Green): Increase the player’s score when caught.
Bad Balls (Red): Decrease the player’s lives when caught or missed.
Key Features:
Bucket Movement: The player can move the bucket left or right using the arrow keys to catch the falling balls.
Scoring and Lives:
Catching a good ball earns points.
Missing a good ball or catching a bad ball reduces the player's lives.
Game Progression:
The game speeds up over time, making it more challenging.
The player loses when all lives are depleted.
Purpose of the Game:
The goal is to collect as many good balls as possible while avoiding bad balls. The challenge increases as more balls fall faster, testing the player's reflexes and strategy in balancing movement and timing.

Design:
The game uses the Visitor design pattern to separate drawing and logic management. Each game element (like the Ball and Bucket) accepts a Visitor that manages how the objects are drawn, making the code more modular and maintainable.

For your presentation, you can focus on:

Game Objective: Catch green balls and avoid red ones.
Game Mechanics: Bucket movement, points, lives, and increasing difficulty.
Technical Design: Use of JavaFX for the user interface and the Visitor design pattern for object interaction.







