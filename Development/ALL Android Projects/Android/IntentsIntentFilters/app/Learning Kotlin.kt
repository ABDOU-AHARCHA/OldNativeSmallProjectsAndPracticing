


class Person(firstName: String, lastName: String) {
    // Properties initialized in the primary constructor
    val fullName: String

    // Initializer block of the primary constructor
    init {
        fullName = "$firstName $lastName"
//        println("Person instance created with name: $fullName")
    }

    // Other members of the class
}


fun main() {
    val person = Person("John", "Doe")


}

