package dao

import models.{Model, Product}

import scala.concurrent.Future

trait ModelDAO[A <: Model] {
  def all(): Future[Seq[A]]

  def insert(a: A): Future[Unit]

  def init(): Unit
}
