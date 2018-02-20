package io.github.isuzuki.scio.sample

import java.time.ZonedDateTime

import com.spotify.scio.testing.{PipelineSpec, PubsubIO, TextIO}
import io.github.isuzuki.scio.sample.Grouped.Item

class GroupedTest extends PipelineSpec {
  "Grouped" should "work" in {
    val items = Seq(
      Item("name1", ZonedDateTime.parse("2018-02-23T13:45:30.777Z")),
      Item("name1", ZonedDateTime.parse("2018-02-20T13:45:30.777Z")),
      Item("name2", ZonedDateTime.parse("2018-02-22T13:45:30.777Z"))
    )

    val expected = Seq(
      ("name1", Map("name1" -> Item("name1", ZonedDateTime.parse("2018-02-20T13:45:30.777Z")))),
      ("name2", Map("name2" -> Item("name2", ZonedDateTime.parse("2018-02-22T13:45:30.777Z"))))
    )

    JobTest[Grouped.type]
      .input(PubsubIO("projects/project-id/subscriptions/name"), items)
      .output(PubsubIO[(String, Map[String, Item])]("projects/project-id/topics/name"))(_ should containInAnyOrder (expected))
      .run()
  }
}
