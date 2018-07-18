package com.ibm.model

case class Ball(size: Int)
case class Hole(size: Int)
case class HoleWithBall(hole: Hole, ball: Ball)
case class HoleWithBallResult(ballsUnassigned: List[Int], holesUnassigned: List[Int], assigned: List[(Int, Int)])