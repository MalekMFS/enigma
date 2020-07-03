import pickling.Defaults._, pickling.json._
import better.files._, better.files.Dsl.SymbolicOperations

object TodayRotorGenerator {

  // Creates a "todays_rotor_state.enigma" file representing rotors' today status
  def create(alpha: String): Unit = {
    // Create 3 random Rotors
    val alphabet = alpha.toList
    val r1: String = scala.util.Random.shuffle(alphabet).mkString("")
    val r2: String = scala.util.Random.shuffle(alphabet).mkString("")
    val r3: String = scala.util.Random.shuffle(alphabet).mkString("")
    val rotors: Array[String] = Array(r1, r2, r3)
    println("Created Random Rotors")

    // Writing to the file
    val file_Object = file"todays_rotor_state.enigma"
    file_Object < rotors.pickle.value
    println("Pickled today Rotors state object to the file")
  }
}
