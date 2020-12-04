package com.github.ardlema.alerts

case class CameraIdAndLocation(cameraId: String, locationName: String, latitude: String, longitude: String) {

  def toCSV() = {
    s"""${cameraId},${locationName},${latitude},${longitude}""""
  }
}
