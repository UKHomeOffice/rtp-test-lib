package uk.gov.homeoffice.specs2

import org.specs2.execute.Result
import org.specs2.mutable.SpecificationLike

class SpecificationExpectations {
  this: SpecificationLike =>

  implicit def any2Success[R](r: R): Result = success
}