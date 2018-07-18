## Language/Framework

- Scala/Sbt

## Problem statement

A model of a resource allocation problem:
• I have two data records: Hole, and Ball
• Hole and Ball each have an integer size property
• I need a function to assign balls to holes
• The function must
● take a collection of balls and a collection of holes
● return a collection of balls that could not be assigned
● return a collection of holes that could not be assigned
● return a collection of (hole, ball) pairs representing the assignments
  
### SBT

Use `sbt test` for running the unit tests (after cloning the repo and also assuming scala/sbt is available)

Please change the following variables in the test cases (3 & 4) for testing different holes/balls assignment scenarios

For inputs:
```sh
val ballsList = List(1, 2, 2, 2, 2, 2) //Collection of Balls
val holesList = List(2, 2) //Collection of Holes
```
For expected output:
```sh
//Right collection of balls that should be assigned
val unassignedBalls: List[Int] = List(1, 2, 2, 2)
//Right collection of holes that should be assigned
val unassignedHoles: List[Int] = List.empty[Int] 
//Right holes and ball placement
val holeWithBallList: List[(Int, Int)] = List[(Int, Int)]((2, 2), (2, 2))
```
