import kotlin.math.roundToInt

/*
Ejercicio 4.2
Crear una clase Persona que tenga nombre, peso (en kg con decimales), altura (en metros con decimales) y su imc.

Crear un constructor primario con todos los atributos, excepto nombre e imc. Este último atributo se calcula en función del peso y la altura. Por tanto no se debe poder modificar, pero sí consultar.

Crear un constructor secundario que también incluya el nombre de la persona cómo parámetro.

Implementa el mét.odo toString, representación del objeto en forma de String: override fun toString() = "". (Pulsa Ctrl+o)

En el main(), crear 3 personas diferentes (la primera sin nombre) utilizando el constructor primario y secundario. Después mostrarlas por consola y a continuación, realizar lo siguiente:

Sobre la persona 1:
Modificar su nombre y para ello debes solicitarlo al usuario por consola. No puede ser nulo o vacío.
Mostrar por consola solo el nombre, peso y altura.
Sobre la persona 3:
Mostrar el peso, altura y imc.
Modificar la altura, por ejemplo a 1.80
Mostrar el peso, altura y imc.
Sobre la persona 2:
Modificar la altura para que tenga el mismo valor que la persona 3.
Mostrar la persona 2 y persona 3.
Comparar si las dos personas son iguales, y mostrar el resultado.
Implementa el mét.odo equals():boolean (Pulsa Ctrl+o).
Ejecutar la comparación.
 */

fun redondearNumero(valor: Double): Double {
    return (valor * 100).roundToInt() / 100.0
}
//No he encontrado un round justo, por lo que para redondear a 2 decimales he implementado una función global

class Persona(var peso: Double, var altura: Double) {
    var nombre: String? = ""
    val imc: Double
        get() = calcularImc()
    //En un getter no se pueden guardar los resultados para usarlos en un futuro
    //En este caso no es necesario poner el "set" privado, ya que cada vez que se consulte se actualizará "imc"

    constructor(nombre: String, peso: Double, altura: Double): this(peso, altura) { //this() llama al constructor primario
        //"imc" No está en el constructor, ya que es una propiedad calculada (se calcula automáticamente cada vez que se accede a ella)
        this.nombre = nombre
    }

    private fun calcularImc() = peso / (altura * altura)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Persona) return false

        return nombre == other.nombre && peso == other.peso && altura == other.altura
    }

    override fun hashCode(): Int {
        var result = nombre.hashCode()
        result += 31 * peso.hashCode()
        result += 31 * altura.hashCode()
        return result
    }

    override fun toString(): String {
        return "Nombre: $nombre, Peso: ${peso}kg, Altura: ${altura}m, Imc: ${redondearNumero(imc)}kg/m2"
    }
}

fun main() {
    val persona = Persona(80.0, 1.86)
    val persona2 = Persona("Cati",50.0, 1.65)

    val persona3 = Persona("Aure", 75.5, 1.77)

    println(persona.toString())
    println(persona2.toString())
    println(persona3.toString())

    do {
        println("Es necesario un nombre: ")
        persona.nombre = readln().trim().lowercase().replaceFirstChar { it.uppercase() }
    } while (persona.nombre.isNullOrEmpty())

    println("Nombre: ${persona.nombre}, Peso: ${persona.peso}kg, Altura: ${persona.altura}m")

    println("Peso: ${persona3.peso}kg, Altura: ${persona3.altura}m, Imc: ${persona3.imc}")
    persona3.altura = 1.80
    println("Peso: ${persona3.peso}kg, Altura: ${persona3.altura}m, Imc: ${persona3.imc}")

    persona2.altura = persona3.altura
    println(persona2)
    println(persona3)

    println("Iguales: ${persona2 == persona3}")
    //Esto reclama al mét.odo sobreescrito "equals"
}