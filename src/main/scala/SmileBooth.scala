import org.bytedeco.javacv.FrameGrabber.ImageMode
import org.bytedeco.javacv.{OpenCVFrameGrabber, CanvasFrame}
import org.bytedeco.javacpp.opencv_core._

object SmileBooth {
  def main(args: Array[String]){

    val canvas = new CanvasFrame("Webcam")

    //Set Canvas frame to close on exit
    canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE)

    //Declare FrameGrabber to import output from webcam
    val grabber = new OpenCVFrameGrabber(0)
    grabber.setImageWidth(640)
    grabber.setImageHeight(480)
    grabber.setBitsPerPixel(CV_8U)
    grabber.setImageMode(ImageMode.COLOR)
    grabber.start()

    while (true) {
      val img = grabber.grab()
      canvas.showImage(img)
    }

  }
}
