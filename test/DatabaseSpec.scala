import dao.ProductDAO
import models.Product
import org.scalatest.{FlatSpec, Matchers}
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.WithApplicationLoader

import scala.concurrent.duration.DurationInt
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}

class DatabaseSpec extends FlatSpec with Matchers with GuiceOneAppPerSuite {

  implicit override lazy val app = new GuiceApplicationBuilder()
    .configure(
      Map(
        "slick.dbs.default.profile"   -> "slick.jdbc.H2Profile$",
        "slick.dbs.default.db.driver" -> "org.h2.Driver",
        "slick.dbs.default.db.url"    -> "jdbc:h2:mem:play;MODE=MYSQL;DB_CLOSE_DELAY=-1"
      )
    )
    .build()

  def productDao(implicit app: Application) =
    Application.instanceCache[ProductDAO].apply(app)

  "Content of products table" should "be the same as testProducts set" in {

    productDao.init()

    val testProducts = Set(
      Product(1, "product1"),
      Product(2, "product2"),
      Product(3, "product3")
    )

    Await.result(
      Future.sequence(testProducts.map(productDao.insert)),
      1 seconds
    )
    val stored = Await.result(productDao.all(), 1 seconds)

    stored.toSet == testProducts
  }
}
