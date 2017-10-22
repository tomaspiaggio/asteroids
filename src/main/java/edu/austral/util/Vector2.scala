package edu.austral.util

import java.lang.Math.{atan2, cos, sin}

case class Vector2(x: Float, y: Float) {
  def +(other: Vector2): Vector2 = Vector2(x + other.x, y + other.y)

  def -(other: Vector2): Vector2 = Vector2(x - other.x, y - other.y)

  def *(scalar: Float): Vector2 = Vector2(x * scalar, y * scalar)

  def rotate(angle: Float): Vector2 = Vector2(x * cos(angle) - y * sin(angle) toFloat, x * sin(angle) + y * cos(angle) toFloat)

  def rotateDeg(angle: Float): Vector2 = rotate(-Math.PI / (180 / angle) toFloat)

  def setX(x: Float): Vector2 = Vector2(x, y)

  def setY(y: Float): Vector2 = Vector2(x, y)

  lazy val module: Float = Math.pow(Math.pow(x, 2) + Math.pow(y, 2), 0.5).toFloat

  lazy val unitary: Vector2 = Vector2(x / module, y / module)

  lazy val angle: Float = atan2(y, x) - atan2(0, 1) toFloat

  lazy val angleDeg: Float = (angle / Math.PI * 180) toFloat
}

object Vector2 {
  def create(x: Float, y: Float) = Vector2(x: Float, y: Float)
}