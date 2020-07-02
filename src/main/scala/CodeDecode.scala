import pickling.Defaults._, pickling.json._
// Scala File handling program
import scala.io.Source

case class CodeDecode(alphabet: String) {
  val fSource = Source.fromFile("todays_rotor_state.enigma") // TODO: check if this file exist inside class
  val rotors = fSource.mkString.unpickle[Array[String]]
  var (r1 , r2, r3) = (rotors(0), rotors(1), rotors(2))

  var state = 0   // Rotors' rotation state
  var cipher = ""

  def cipher(plain: String): String = {
    // TODO: control on input. remove unsupported characters.

    // rotor1 => rotor2 => rotor3 => reflector => rotor3_rev => rotor2_rev => rotor1_rev
    for(ch <- plain){
      state += 1
      cipher = cipher :+ enigma_one_char(ch)
      rotate_rotors
    }
    fSource.close()
    return cipher
  }


  // Enigma code for one character
  private def enigma_one_char(ch: Char): Char = {
    val to_r1     = r1(alphabet.indexOf(ch))
    val to_r2     = r2(alphabet.indexOf(to_r1))
    val to_r3     = r3(alphabet.indexOf(to_r2))
    val reflected = alphabet(alphabet.length - alphabet.indexOf(to_r3) -1)
    val from_r3   = alphabet(r3.indexOf(reflected))
    val from_r2   = alphabet(r2.indexOf(from_r3))
    val from_r1   = alphabet(r1.indexOf(from_r2))
    return from_r1
  }

  // Rotate Rotors by the state variable
  private def rotate_rotors {
    r1 = r1.tail :+ r1.head
    if (state % 26 == 0)
      r2 = r2.tail :+ r2.head
    if (state % 26*26 == 0)
      r3 = r3.tail :+ r3.head
  }


  // closing file
  // fSource.close()
}
