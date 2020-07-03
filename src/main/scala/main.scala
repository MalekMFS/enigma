import better.files._ //import scala.io.Source
import pickling.Defaults._, pickling.json._
import scala.util.{Try, Success, Failure}

object main extends App {
  //TODO: add Space, uppercase characters support
  val alphabet  = ('a' to 'z').mkString("")

  // main loop
  def cliLoop: Unit = {
    println("\nWelcome to Enigma by MalekMFS!")
    println("Main menu:\n1: Generate Rotors' today status\n2: Show Rotors' today Status\n3: Code/Decode Input\n0: To exit")
    println("Enter your choice:")

    val input = scala.io.StdIn.readInt() //TODO handle non int inputs
    input match {
      case 0 => println("Bye!")
      case 1 => TodayRotorGenerator.create(alphabet); cliLoop
      case 2 => rotorsStatus; cliLoop
      case 3 => enigma; cliLoop
    }
  }

  def rotorsStatus ={
      val fSource = Try(file"todays_rotor_state.enigma".contentAsString)
      fSource match {
        case Success(file) =>
          val rotors = file.mkString.unpickle[Array[String]]
          println("Rotors today status (R1, R2, R3):")
          rotors.foreach(println)
        case Failure(e) =>
          println("Error reading 'todays_rotor_state.enigma'. Make sure it exist and if not, generate it from the menu.\n" + e)
      }
  }

  def enigma = {
    // check if setting file exist
    val fSource = Try(file"todays_rotor_state.enigma".contentAsString)
    fSource match {
      case Success(file) =>
        def getText: String = {
          println("Please enter a text/cipher:")
          val text = scala.io.StdIn.readLine()
          val isValid = text.forall(c => alphabet.contains(c))
          if(isValid) text
          else {
            println("Invalid input! only characters bellow are allowed:\n" + alphabet)
            getText
          }
        }

        val enigma = CodeDecode(alphabet) //Initialize the enigma
        val cipher = enigma.cipher(getText)
        println("Enigma's output:")
        println(cipher)

      case Failure(e) =>
        println("Error reading 'todays_rotor_state.enigma'. Make sure it exist and if not, generate it from the menu.\n" + e)
    }
  }

  // Start the program
  cliLoop
}
