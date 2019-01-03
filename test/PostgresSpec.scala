import dao.ProductDAO
import org.scalatest.{FlatSpec, Matchers}
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder

import scala.concurrent.duration.DurationInt
import scala.concurrent.Await

class PostgresSpec extends FlatSpec with Matchers with GuiceOneAppPerSuite {

  implicit override lazy val app = new GuiceApplicationBuilder()
    .configure(Map(
        "slick.dbs.default.profile" -> "slick.jdbc.PostgresProfile$",
        "slick.dbs.default.driver" -> "slick.driver.PostgresqlDriver$",
        "slick.dbs.default.db.driver" -> "org.postgresql.Driver",
        "slick.dbs.default.db.url" -> "jdbc:postgresql://172.17.0.2/mytestdb",
        "slick.dbs.default.db.user" -> "postgres",
        "slick.dbs.default.db.password" -> "docker"
    )
    )
    .build()

  def productDao(implicit app: Application) = Application.instanceCache[ProductDAO].apply(app)

  "Table products" should "not bet empty" in {

      val stored = Await.result(productDao.all(), 1 seconds)

      stored.foreach(println)

      stored.size != 0
  }
}
