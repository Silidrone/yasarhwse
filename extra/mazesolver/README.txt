Maze solver - by Muhamed Cicak,

This was created to simulate Treumax algorithm for solving a maze. This was
later used as the core algorithm for a maze solver car, a physical remote
car (toy), controlled by a micro controller (esp8266), but that is out of
scope for this particular code. The algorithm is tested with maze1.txt file,
which is a complex maze with loops, and then the algorithm runs it for x
amount of times by placing the "robot" on a random beginning inside the maze
and providing a solution in brute_testing_output.txt

The main algorithm code is in Treumax.cpp