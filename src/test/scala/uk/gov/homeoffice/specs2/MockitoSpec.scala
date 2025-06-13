package uk.gov.homeoffice.specs2

import org.mockito.Mockito.*
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class Test() {
  def real() :String = "real"
}

class MockitoSpec extends Specification {

  trait Context extends Scope {
    val mockedTest :Test = mock(classOf[Test])
    when(mockedTest.real()).thenReturn("fake")
  }

  "MockitoSpec" should {
    "work" in new Context {
      mockedTest.real() mustEqual "fake"
    }
  }
}
