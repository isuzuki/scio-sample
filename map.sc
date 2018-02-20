import java.time.{ZoneOffset, ZonedDateTime}
import java.time.format.DateTimeFormatter

case class Item (
  name: String,
  time: ZonedDateTime
)

val items = Seq(
  Item("name1", ZonedDateTime.parse("2018-02-20T13:45:30.777Z", DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC))),
  Item("name2", ZonedDateTime.parse("2018-02-21T13:45:30.777Z", DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC))),
  Item("name1", ZonedDateTime.parse("2018-02-22T13:45:30.777Z", DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC)))
)

implicit val zonedDateTimeOrdering: Ordering[ZonedDateTime] = Ordering.by(_.toEpochSecond)

items.minBy(_.time)

items
  .groupBy(_.name)
//  .mapValues(items => items.map(item => item.name -> items.minBy(_.name)).toMap)
  .mapValues(items => items.map(item => item.name -> items.minBy(_.time)).toMap)
//  .mapValues(_.map(item => item.name -> item.time).toMap)
//  .minBy(_._2.minBy(_.time))