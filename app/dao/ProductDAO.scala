package dao

import javax.inject.Inject
import models.Product
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.dbio.DBIOAction
import slick.jdbc.JdbcProfile
import slick.jdbc.meta.MTable
import scala.concurrent.duration.DurationInt


import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

class ProductDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  private val products = TableQuery[ProductsTable]

  def all(): Future[Seq[Product]] = db.run(products.result)

  def insert(product: Product): Future[Unit] = db.run(products += product).map{ _ => () }

  def init() = {
    val tables = Await.result(db.run(MTable.getTables), 1 seconds).toList.map(_.name.name)
    if (!tables.contains(products.baseTableRow.tableName))
      Await.result(db.run(products.schema.create), Duration.Inf)

   /*
    val tables = List(products)
    val f = existing.flatMap( v => {
      val names = v.map(mt => mt.name.name)
      val createIfNotExist = tables.filter( table =>
        (!names.contains(table.baseTableRow.tableName))).map(_.schema.create)
      db.run(DBIO.sequence(createIfNotExist))
    })
    Await.result(f, Duration.Inf)
    */
  }

  private class ProductsTable(tag: Tag) extends Table[Product](tag, "PRODUCTS") {
    def id = column[Int]("ID", O.PrimaryKey)
    def name = column[String]("NAME")

    def * = (id, name) <> ((Product.apply _).tupled, Product.unapply)
  }
}
