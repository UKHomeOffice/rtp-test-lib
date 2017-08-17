package uk.gov.homeoffice.specs2

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class MockitoSpec extends Specification with Mockito {

  trait Context extends Scope{
    val underTest: List[String] = mock[List[String]]
  }

  "MockitoSpec" should {
    "work" in new Context {
      underTest.head returns "Woof"
      underTest.head mustEqual "Woof"
    }
  }
}
