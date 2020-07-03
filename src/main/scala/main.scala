import better.files._ //import scala.io.Source
import pickling.Defaults._, pickling.json._
import scala.util.{Try, Success, Failure}

object main extends App {
  //TODO: add Space, uppercase characters support
  val alphabet  = ('a' to 'z').mkString("")
  var exit = false

  // Main loop
  while(!exit){
    println("\nWelcome to Enigma by MalekMFS!")
    println("Main menu:\n1: Generate Rotors' today status\n2: Show Rotors' today Status\n3: Code/Decode Input\n0: To exit")
    println("Enter your choice:")

    val input = scala.io.StdIn.readInt() //TODO handle non int inputs
    input match {
      case 0 => exit = true; println("Bye!")
      case 1 => TodayRotorGenerator.create(alphabet)
      case 2 => rotorsStatus
      case 3 => enigma
    }

  }

  def rotorsStatus ={
//    try {
//      val fSource = Source.fromFile("todays_rotor_state.enigma")
//      val rotors = fSource.mkString.unpickle[Array[String]]
//      println("Rotors today status (R1, R2, R3):")
//      rotors.map(println)
//    }
//    catch {
//      case e => println("Error reading 'todays_rotor_state.enigma'. Make sure it exist or generate it from the menu.\n"
//                        + e)
//    }
      // check if setting file exist
      val fSource = Try(file"todays_rotor_state.enigma".contentAsString)
      fSource match {
        case Success(file) =>
          val rotors = file.mkString.unpickle[Array[String]]
          println("Rotors today status (R1, R2, R3):")
          rotors.map(println)
        case Failure(e) =>
          println("Error reading 'todays_rotor_state.enigma'. Make sure it exist and if not, generate it from the menu.\n" + e)
      }
  }

  def enigma = {
    // check if setting file exist
    val fSource = Try(file"todays_rotor_state.enigma".contentAsString)
    fSource match {
      case Success(file) =>
        var isValid = false
        var text: String = null
        while (!isValid) {
          println("Please enter a text/cipher:")
          text = scala.io.StdIn.readLine()
          isValid = text.forall(c => alphabet.contains(c))
          if (!isValid) println("Invalid input! only characters bellow are allowed:\n" + alphabet)
        }

        val enigma = CodeDecode(alphabet) //Initialize the enigma
        val cipher = enigma.cipher(text)
        println("Enigma's output:")
        println(cipher)

      case Failure(e) =>
        println("Error reading 'todays_rotor_state.enigma'. Make sure it exist and if not, generate it from the menu.\n" + e)
    }
  }

}