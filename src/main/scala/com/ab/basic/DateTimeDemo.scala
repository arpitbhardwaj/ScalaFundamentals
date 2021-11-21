package com.ab.basic

import java.time._

/**
 * @author Arpit Bhardwaj
 *
 * Scala uses java library for date and time stuff
 */

object DateTimeDemo extends App {
  val localDateTime: LocalDateTime = LocalDateTime.of(2021, Month.DECEMBER, 7, 12, 44, 23, 34)
  val localDate: LocalDate = localDateTime.toLocalDate
  val localTime: LocalTime = localDateTime.toLocalTime
}
