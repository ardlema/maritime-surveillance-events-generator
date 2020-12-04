package com.github.ardlema.alerts

import java.io.{BufferedWriter, File, FileWriter}

import com.github.tototoshi.csv.CSVWriter

import scala.util.{Failure, Try}
import scala.collection.JavaConverters._

object EventsGenerator {
  
  val directory = "Users/albertorodriguez/Desktop/cameras-images"

  val header: List[String] = List("Serial Number", "Record Type", "First File value", "Second file value")
  val row: List[String] = List("value1", "value2", "value3", "value4")

  def writeCsvFile(
                    fileName: String,
                    header: List[String],
                    row: List[String]
                  ) = {
    val file = new File(fileName)
    val writer = CSVWriter.open(file)
    writer.writeAll(List(header, row))
  }

  def main(args : Array[String]) {
    println(writeCsvFile("./test.csv", header, row))
  }

}
