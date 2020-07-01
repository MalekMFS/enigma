import pickling.Defaults._, pickling.json._
// File handling program
import java.io.File
import java.io.PrintWriter

// Creates a "todays_rotor_state.enigma" file representing rotors' today status
object TodayRotorGenerator {

  def create(alpha: String): Unit = {
    // Create 3 random Rotors
    val alphabet = alpha.toList
    val r1: String = scala.util.Random.shuffle(alphabet).mkString("")
    val r2: String = scala.util.Random.shuffle(alphabet).mkString("")
    val r3: String = scala.util.Random.shuffle(alphabet).mkString("")
    val rotors: Array[String] = Array(r1, r2, r3)
    println("Created Random Rotors")

    // Creating a file
    val file_Object = new File("todays_rotor_state.enigma" )

    // Passing reference of the file to the printwriter
    val pw = new PrintWriter(file_Object)

    // Writing to the file
    pw.write(rotors.pickle.value)
    println("Pickled today Rotors state object to the file")

    // Closing printwriter
    pw.close()
  }
}
