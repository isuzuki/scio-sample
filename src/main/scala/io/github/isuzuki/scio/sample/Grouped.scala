package io.github.isuzuki.scio.sample

import java.time.ZonedDateTime

import com.spotify.scio.ContextAndArgs

object Grouped {
  implicit val zonedDateTimeOrdering: Ordering[ZonedDateTime] = Ordering.by(_.toEpochSecond)

  def main(cmdlineArgs: Array[String]): Unit = {
    val (sc, args) = ContextAndArgs(cmdlineArgs)

    val filtered = sc
      .pubsubSubscription[Item]("projects/project-id/subscriptions/name")
      .debug()
      .groupBy(_.name)
      .debug()
      .mapValues(items => items.map(item => item.name -> items.minBy(_.time)).toMap)
      .debug()
      .saveAsPubsub("projects/project-id/topics/name")

    sc.close()
  }

  case class Item(
    name: String,
    time: ZonedDateTime,
  )
}
