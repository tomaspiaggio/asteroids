package edu.austral.util

import java.awt.Shape
import java.awt.geom.Area
import java.util

trait Collisionable[T <: Collisionable[T]] {
  def getShape: Shape

  def collisionedWith(collisionable: T)
}

class CollisionEngine[T <: Collisionable[T]] {
  def checkCollisions(collisionables: util.List[T]): Unit = {
    if (collisionables.isEmpty) return

    checkCollisions(collisionables.get(0), collisionables.subList(1, collisionables.size()))
  }

  private def testIntersection(shapeA: Shape, shapeB: Shape): Boolean = {
    val areaA = new Area(shapeA)
    areaA.intersect(new Area(shapeB))
    !areaA.isEmpty
  }

  def checkCollisions(current: T, collisionables: util.List[T]): Unit = {
    if (collisionables.isEmpty) return

    collisionables
      .forEach(collisionable => {
        if (testIntersection(current.getShape, collisionable.getShape)) {
          current.collisionedWith(collisionable)
          collisionable.collisionedWith(current)
        }
      })

    checkCollisions(collisionables.get(0), collisionables.subList(1, collisionables.size()))
  }
}
