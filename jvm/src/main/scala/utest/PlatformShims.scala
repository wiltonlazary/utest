package utest

import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}
import concurrent.duration._
import java.io.{PrintWriter, StringWriter}

/**
 * Platform specific stuff that differs between JVM and JS
 */
object PlatformShims {
  def flatten[T](f: Future[Future[T]])(implicit ec: scala.concurrent.ExecutionContext): Future[T] = f.flatMap(x => x)

  def await[T](f: Future[T]): T = Await.result(f, 10.hours)

  def printTrace(e: Throwable): Unit = {
    println(e.getStackTrace.map(_.toString).mkString("\n"))
  }

  class Test
}
