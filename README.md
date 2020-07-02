# Enigma Machine Simulator
![Enigma](https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/EnigmaMachineLabeled.jpg/800px-EnigmaMachineLabeled.jpg)

This is an Enigma machine simulator written in Scala. It consist of three Rotors with 26 characters on them which would
change by input characters, and a Reflector at the end which makes it possible to Code/Decode with the same setting.

## How it works
Similar to an Enigma machine, First you need to generate Rotors' today setting from CLI. This will be saved in a file named 
`todays_rotor_state.enigma`. Then machine will be initialized using this setting.  
For example for a plain text `"aaaa"`, Enigma could return `"xuie"` (based on Rotors' setting). If you give the cipher text `"xuie"` back to Enigma,
 it will decode to `"aaaa"`.  
For more info check the [Wikipedia page](https://en.wikipedia.org/wiki/Enigma_machine).
## How to run
Just run `main.scala` and it gives you the instructions. Preferably using `sbt`.

## Dependencies
- [Pickling](https://github.com/scala/pickling) to Pickle/Unpickle settings to `"todays_rotor_state.enigma"` file.
- Scala 2.11 (other versions not tested) 

## Future work
- Implement Plugboard
- Support different characters, especially space character
- Get setting from the input

