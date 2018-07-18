import com.ibm.model.{Ball, Hole, HoleWithBall, HoleWithBallResult}
import org.scalatest.FunSuite
import com.ibm.service.BallsToHolesAssigner

class BallsToHolesAssignerSpec extends FunSuite {

  test("An empty Set should have size 0") {
    assert(Set.empty.size == 0)
  }

  test("List of Int parameters for the size of Balls and should return the respective models") {
    val balls = List(Ball(1), Ball(2))
    val holes = List(Hole(1))
    val holesWithBall = List(HoleWithBall(Hole(1), Ball(1)))
    val expectedResult = HoleWithBallResult(List(1, 2), List(1), List[(Int, Int)]((1, 1)))
    assert(BallsToHolesAssigner.postProcessModelOutput(balls, holes, holesWithBall) == expectedResult)
  }

  test("The output represented in individual models should result as an object of type HoleWithBallResult") {
    val balls = List(1, 2, 3)
    val holes = List(1, 2, 3)
    val ballsModel = List(Ball(1), Ball(2), Ball(3))
    val holesModel = List(Hole(1), Hole(2), Hole(3))
    assert(BallsToHolesAssigner.inputParamsToModel(balls, holes) == (ballsModel, holesModel))
  }

  test("[Scenario 1] List of sizes for holes and balls should return the assigned/unassigned holes and balls") {
    val ballsList = List(1, 2, 2)
    val holesList = List(1)
    val unassignedBalls: List[Int] = List(2, 2)
    val unassignedHoles: List[Int] = List.empty[Int]
    val holeWithBallList: List[(Int, Int)] = List[(Int, Int)]((1, 1))
    val expectedResult = HoleWithBallResult(unassignedBalls, unassignedHoles, holeWithBallList)
    assert(BallsToHolesAssigner.assignBallsToHoles(ballsList, holesList) == expectedResult)
  }

  test("balls with larger size should get precedence for assignment") {
    val ballsList = List(1, 2, 2, 2, 2, 2)
    val holesList = List(2, 2)
    val unassignedBalls: List[Int] = List(1, 2, 2, 2)
    val unassignedHoles: List[Int] = List.empty[Int]
    val holeWithBallList: List[(Int, Int)] = List[(Int, Int)]((2, 2), (2, 2))
    val expectedResult = HoleWithBallResult(unassignedBalls, unassignedHoles, holeWithBallList)
    assert(BallsToHolesAssigner.assignBallsToHoles(ballsList, holesList) == expectedResult)
  }

}
