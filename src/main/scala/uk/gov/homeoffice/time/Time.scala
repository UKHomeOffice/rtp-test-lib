package uk.gov.homeoffice.time

/**
 * Time functionality e.g.
 * timed("Description of what is being timed") { your functionality }
 */
trait Time {
  def timed[R](description: String)(f: => R) = {
    val start = System.currentTimeMillis()
    val result = f
    println(s"$description => ${System.currentTimeMillis() - start} milliseconds")
    result
  }
}