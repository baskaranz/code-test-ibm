package com.ibm.service

import com.ibm.model.{Ball, Hole, HoleWithBall, HoleWithBallResult}

import scala.collection.mutable.ListBuffer

object BallsToHolesAssigner {

  def assignBallsToHoles(ballsList: List[Int], holesList: List[Int]): HoleWithBallResult = {

    val (ballsModel, holesModel) = inputParamsToModel(ballsList, holesList)
    val balls = ListBuffer.empty ++= ballsModel
    val holes = ListBuffer.empty ++= holesModel

    var unassignedBalls: ListBuffer[Ball] = ListBuffer[Ball]()
    val unassignedHoles: ListBuffer[Hole] = ListBuffer[Hole]()
    val holeWithBallList: ListBuffer[HoleWithBall] = ListBuffer[HoleWithBall]()

    for (hole <- holes) {

      val ballsForThisRun: ListBuffer[Ball] = if (unassignedBalls.isEmpty) {
        balls
      } else {
        unassignedBalls
      }

      val currBalls: Map[Boolean, List[Ball]] = ballsForThisRun.toList.groupBy(_.size <= hole.size)

      val maybeCurrBallsAssignable = currBalls.get(true)

      val maybeCurrBallsUnassigned = currBalls.get(false)

      val currTotalUnassigned: ListBuffer[Ball] = ListBuffer[Ball]()

      maybeCurrBallsUnassigned match {
        case Some(currBallsUnassigned) => currBallsUnassigned.foreach(f => currTotalUnassigned.+=(f))
        case _ =>
      }

      maybeCurrBallsAssignable match {
        case Some(currBallsAssignable) =>

          val currBallAssignableSorted = currBallsAssignable.sortBy(- _.size)
          val currBallAssigned = currBallAssignableSorted.head
          val currBallsUnassigned = currBallAssignableSorted.drop(1)

          currBallsUnassigned.foreach(f => currTotalUnassigned.+=:(f))

          unassignedBalls = currTotalUnassigned

          holeWithBallList.+=:(HoleWithBall(hole, currBallAssigned))
        case _ => unassignedHoles.+=:(hole)
      }
    }
    val output = (unassignedBalls.toList, unassignedHoles.toList, holeWithBallList.toList)
    postProcessModelOutput(output)
  }

  def postProcessModelOutput(output: (List[Ball], List[Hole], List[HoleWithBall])): HoleWithBallResult = {
    val balls = output._1
    val holes = output._2
    val holesWithBall = output._3
    HoleWithBallResult(balls.map(_.size), holes.map(_.size), holesWithBall.map(f => (f.hole.size, f.ball.size)))
  }

  def inputParamsToModel(balls: List[Int], holes: List[Int]): (List[Ball], List[Hole]) = {
    val ballsModel = balls.map(f => Ball(f))
    val holesModel = holes.map(f => Hole(f))
    (ballsModel, holesModel)
  }

}
