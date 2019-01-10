import dao.ProductDAO
import models.Product
import org.scalatest.{FlatSpec, Matchers}
import play.api.Application
import play.api.test.WithApplicationLoader
import scala.concurrent.duration.DurationInt
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}

class DatabaseSpec extends FlatSpec with Matchers {

  "Content Table products" should  "be the same as testProducts" in new WithApplicationLoader() {
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

    stored.toSet == testProducts
  }
}
