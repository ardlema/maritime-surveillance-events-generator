package com.github.ardlema.alerts

import java.io.{BufferedWriter, File, FileWriter}

import com.github.tototoshi.csv.CSVWriter

import scala.util.{Failure, Random, Try}
import scala.collection.JavaConverters._

object EventsGenerator {

  val outputDirectoryWithGeneratedFiles = "/Users/albertorodriguez/Desktop/cameras-images/"
  val inputDirectoryWithSampleImages = "/Users/albertorodriguez/Desktop/images"
  val numberOfCharsOfGeneratedFilesName = 10

  val header: List[String] = List("camera_id", "location", "latitude", "longitude", "timestamp", "source_file")
  val camerasIdsAndLocations = List(
    CameraIdAndLocation("id12345", "Zahara de los Atunes - Playa de los Alemanes", "36.811696464945555" , "-6.401511098075663"),
    CameraIdAndLocation("id373483", "Tarifa - Playa de los lances", "36.04292962922407" , "-5.638702603172488"),
    CameraIdAndLocation("id283734", "San Lucar de Barrameda - Rincon de la Victoria", "36.811696464945555", "-6.401511098075663")
  )

  def writeCsvFile(
                    fileName: String,
                    header: List[String],
                    row: List[String]
                  ) = {
    val file = new File(fileName)
    val writer = CSVWriter.open(file)
    writer.writeAll(List(header, row))
  }

  def main(args: Array[String]) {

    val files = getListOfFiles(inputDirectoryWithSampleImages)

    files match {
      case Some(listOfFiles) => { generateCSVFiles(listOfFiles)}
      case None => println("Please provide a valid directory")
      }
  }

  def getListOfFiles(dir: String): Option[List[File]] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      Option(d.listFiles.filter(_.isFile).toList)
    } else {
      None
    }
  }

  def generateCSVFiles(files: List[File]) = {
    val random = new Random
    //while (true) {
      val generateRandomFileName = Random.alphanumeric.take(numberOfCharsOfGeneratedFilesName).mkString("")
      val fileName = s"""${outputDirectoryWithGeneratedFiles}$generateRandomFileName.csv"""
    println("FILE NAME -> "+fileName)
      val randomCameraIdAndLocation = camerasIdsAndLocations(random.nextInt(camerasIdsAndLocations.size))
      val randomImageFile = files(random.nextInt(files.size))
      val row = List(randomCameraIdAndLocation.cameraId,
        randomCameraIdAndLocation.locationName,
        randomCameraIdAndLocation.latitude,
        randomCameraIdAndLocation.longitude,
        "2015-01-01T12:10:30Z",
        randomImageFile.getAbsolutePath)
      writeCsvFile(fileName, header, row)
    //}
  }
}
