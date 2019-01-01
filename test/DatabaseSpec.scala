import dao.ProductDAO
import models.Product
import org.scalatest.{FlatSpec, Matchers}
import play.api.Application
import play.api.test.WithApplicationLoader
import scala.concurrent.duration.DurationInt
import scala.concurrent.ExecutionContext.Implicits.global



import scala.concurrent.{Await, Future}

class DatabaseSpec extends FlatSpec with Matchers {

  "Database" should  "work as expected" in new WithApplicationLoader() {
    val app2dao = Application.instanceCache[ProductDAO]
    val dao = app2dao(app)

    dao.init()

    val testProducts = Set(
      Product(1, "product1"),
      Product(2, "product2"),
      Product(3, "product3")
    )

    Await.result(Future.sequence(testProducts.map(dao.insert)), 1 seconds)
    val stored = Await.result(dao.all(), 1 seconds)

    stored.toSet == stored
  }
}

/*
object InMemoryDatabaseFlatSpec {
  val inMemoryDatabaseConfiguration: Map[String, Any] = Map(
    "slick.dbs.default.profile" -> "slick.jdbc.H2Profile$",
    "slick.dbs.default.driver" -> "slick.driver.H2Driver$",
    "slick.dbs.default.db.driver" -> "org.h2.Driver",
    "slick.dbs.default.db.url" -> "jdbc:h2:mem:play;MODE=MYSQL;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=FALSE",
    "slick.dbs.default.db.user" -> "",
    "slick.dbs.default.db.password" -> ""
  )
}

class DatabaseSpec extends FlatSpec with Matchers with GuiceOneAppPerSuite {

  import InMemoryDatabaseFlatSpec._

  override def fakeApplication(): Application = {
    val builder = overrideDependencies(
      new GuiceApplicationBuilder()
        .configure(inMemoryDatabaseConfiguration)
    )
    builder.build()
  }

  def overrideDependencies(application: GuiceApplicationBuilder): GuiceApplicationBuilder = {
    application
  }

  lazy val db = Database.forConfig("slick.dbs.default")
  try {

  } finally db.close()
}
*/
