import org.scalatest.flatspec.AnyFlatSpec
/**
 * @Arpit Bhardwaj
 *
 *
 */
class HelloWorldSpec extends AnyFlatSpec{
  behavior of "Hello World"
  it should "start with 'Hello'" in {
    assert("Hello World".startsWith("Hello"))
  }
}