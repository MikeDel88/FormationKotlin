package com.android.formationkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.formationkotlin.commun.name

// Les fonctions.
fun isOldEnought(age: Int): Boolean = age >= 5
fun describePeople(name: String, age: Int, height: Float) {
    println("$name a $age ans, et mesure ${height}m")
}

// Classes
class MyClass(var a: Int, var b: Int) {

    // implémentation d'une logique dans l'initialisation
    init {
        if(a > 0)
            b = 5
    }

    // Constructeurs secondaire
    constructor(a: Int): this(a, 10)
    constructor(): this(1, 13)

    fun affiche() {
        println("$a et $b")
    }
}
// Classe abstraite qui ne peut pas être instancié.
abstract class Vehicule(var nbreRoues: Int) {

    protected var type = "Vehicule" // enfants.

    //Polymorphisme
    open fun afficheNbreRoues() {
        println("Nbre de roues : $nbreRoues")
    }

    abstract fun klaxon()
}
interface Essence {
    var niveau: Double
    fun faireLePlein() {
        println("Remplissage du réservoir")
        niveau = 100.0
    }
    fun voirNiveauEssence() {
        println("Le niveau d'essence : $niveau")
    }
}
interface Figures {
    fun roueArriere()
}
// Classe enfants
class Voiture: Vehicule(4), Essence {

    // Encapsulation (private/public/internal/protected)
    private var couleur = "Bleu" // classe
    public val constructeur = "Ford" // tout le monde
    internal var annee = 2000 // module

    override fun afficheNbreRoues() {
        println("Voiture")
        super.afficheNbreRoues()
    }

    override fun klaxon() {
        println("Pouet !")
    }

    override var niveau: Double
        get() = 50.0
        set(value) {}
}
class Moto: Vehicule(2), Figures, Essence {
    override fun afficheNbreRoues() {
        println("Moto")
        super.afficheNbreRoues()
    }

    override fun klaxon() {
        println("Psssst !")
    }

    override fun roueArriere() {
        println("roue arrière en moto")
    }

    override fun faireLePlein() {
        super.faireLePlein()
        klaxon()
    }

    override var niveau: Double
        get() = 0.0
        set(value) {}
}
class Velo: Vehicule(2), Figures {
    override fun klaxon() {
        println("Couic !!!")
    }

    override fun roueArriere() {
        println("roue arrière en vélo")
    }

}
// Exemple polymorphisme et encapsulation.
// Classe parent
open class Compte {

    protected var solde = 0

    open fun operation(somme: Int) {
        solde += somme
        println("Opération $somme : nouveau solde $solde")
    }
}
// Classe enfant
class ComptePositive: Compte() {

    // Polymorphisme
    override fun operation(somme: Int) {
        if(isOperationValide(somme))
            super.operation(somme)
        else
            println("Opération interdite")
    }

    private fun isOperationValide(somme: Int): Boolean {
        return solde + somme >= 0
    }
}
// Data Class (ne peut être hérité) contient seulement des données.
data class Utilisateur(var nom: String, var age: Int)
// Nested Class
class Bag(var nbreItems: Int) {
    var items = arrayOfNulls<Item>(nbreItems)

    // Indépendant mais étroitement liées car Item ne sert à rien sans Bag.
    class Item(var poids: Int) {
        fun afficheItem() {
            println("Poids de l'item : $poids")
        }
    }

    // Utilise les propriétés de Bag
    inner class Volume {
        fun afficheVolume() {
            println("Le volume du bagage est : ${nbreItems * 10}")
        }
    }
}

// Générics
fun <T> afficheTableauRenverse(tableau: Array<T>) {
    var separateur = ""
    val sb = StringBuilder()

    for(i in tableau.indices.reversed()) {
        sb.append(separateur)
        sb.append(tableau[i])

        separateur = ", "
    }

    println(sb.toString())
}
class Boite<T>(var valeur: T) {
    fun set(valeur: T) {
        this.valeur = valeur
    }
    fun get(): T {
        return valeur
    }
}
// Doit hériter d'un type Number(Int, Float, Double...)
class BoiteInteger<T: Number>(var valeur: T) {
    fun set(valeur: T) {
        this.valeur = valeur
    }
    fun get(): T {
        return valeur
    }
}

// LateInit (pas de type primitif) + Companion Object
class Utilisateur2(var nom: String, var age: Int) {

    companion object Factory {
        fun createBob() = Utilisateur("Bob", 15)
        fun createBobette() = Utilisateur("Bobette", 4)
    }

    lateinit var pseudo: String
}



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        describePeople(name = name, age = 18, height = 1.5F)

    }

    /**
     * Formation sur les String et les templates.
     */
    private fun formationString() {

        var age: Int = 5
        val name: String = "Bob"
        val taille: Float = 1.90F

        println("$name a $age ans et mesure $taille m")
        println("${name.uppercase()} a ${age + 5} ans et mesure ${taille}m")

        println("""Ligne :
Nom : $name
Age : $age ans
Taille : $taille mètres
        """)

        age = 10

        println("""Ligne :
            | Nom : $name
            | Age : $age ans
            | Taille : $taille mètres""".trimMargin())

        age *= 2

        println("""Ligne2 :
            > Nom : $name
            > Age : $age ans
            > Taille : $taille mètres""".trimMargin(">"))
    }

    /**
     * Formation sur if/else
     */
    private fun formationFlux() {
        val age = 5
        val name = "Bob"

        if(name === "Bob")
            println("$name est Bob")
        else if(name === "Bobette")
            println("$name est Bobette")
        else
            println("$name n'est ni Bob ni Bobette")
    }

    /**
     * Formation sur les flux When (switch case en plus puissant)
     */
    private fun formationFluxWhen() {
        var age = 5
        val name = "Bob"

        when(age) {
            5 -> println("il a 5 ans")
            10 -> println("il a 10ans")
            else -> println("on ne sait pas son age")
        }

        when(age) {
            in 0..5 -> println("il a entre 0 et 5 ans")
            !in 10..20 -> println("il n'a pas entre 10 et 20 ans")
        }

        when {
            age > 10 -> println("il a plus de 10 ans")
            name === "Bob" -> println("il s'appelle Bob")
        }

        val isAdult = when(age) {
            in 0..18 -> false
            else -> true
        }

    }

    /**
     * Variables mutable ou immutable.
     */
    private fun formationMutableImmutable() {

        // mutable
        var age = 15
        age = 10
        age = 5

        // immutable equivalent final en java
        val nom = "Bob"

        // variable nullable
        var name: String? = "Bobette"

        // null Safe operator
        println(name?.length)

        // assert non null (si null crash).
        println(name!!.length)
    }

    /**
     * Formation sur les tableaux de bases
     */
    private fun formationTableaux() {

        val tableau = Array<Int>(10) { 0 }
        println(tableau.contentToString())
        // ou plus simplement factory function
        val tableauBis = arrayOf(0,1,2,3,4)

    }

    /**
     * Formation sur les flux de contrôle (boucles de base)
     */
    private fun formationBoucle() {

        for(i in 0..4) {
            println(i)
        }

        for(i in 1..5 step 2) {
            println(i)
        }

        for(i in 5 downTo 0) {
            println(i)
        }

        for(i in 5 downTo 0 step 2) {
            println(i)
        }

        for(item in arrayOf(1, 4, 5, 6, 9)) {

            if(item == 4)
                continue

            println(item)
        }

        for((index,item) in arrayOf(1, 4, 5, 6, 9).withIndex()) {

            if(index == 2)
                continue

            println(item)
        }

        var nombre = 10

        while(nombre > 0) {
            println(nombre)
            nombre--
        }

        nombre = 10

        do {
            if(nombre == 4)
                break
            nombre--
        } while(nombre > 0)
    }

    /**
     * Formation de base POO (abstraite, interface, Polymorphisme, Héritage)
     */
    private fun formationPOO() {
        // Type enfants
        var voiture = Voiture()
        var moto = Moto()
        var velo = Velo()

        // Type parent
        var vehicule: Vehicule = Voiture()
        vehicule = Moto()
        vehicule = Velo()

        // Permet de savoir quel véhicule implémente les figures.
        var figureVehicule: Figures = Velo()
        figureVehicule.roueArriere()
        figureVehicule = Moto()
        figureVehicule.roueArriere()

        val compte = ComptePositive()
        compte.operation(50)
        compte.operation(-100)
        compte.operation(-10)
    }

    /**
     * Formation POO sur les Cast et les vérification de types
     */
    private fun formationPOOCast() {
        // Objet de type Essence
        val essenceVehicule: Essence = Voiture()

        // Safe Cast
        val vehiculeEssai: Vehicule? = essenceVehicule as? Voiture
        // Unsafe Cast
        val vehiculeEssai2: Vehicule = Moto() as Moto

        // Unsafe Cast sur une ligne.
        (vehiculeEssai as? Voiture)?.faireLePlein()

        // vérification du type d'objet. Smart Cast
        if(vehiculeEssai2 is Figures) {
            vehiculeEssai2.roueArriere()
        }

        if(vehiculeEssai is Essence) {
            vehiculeEssai.voirNiveauEssence()
            vehiculeEssai.faireLePlein()
        }
    }

    /**
     * FOrmation POO sur les Data Class(copy, equals, hashCode)
     */
    private fun formationPOODataClass() {

        val bob = Utilisateur("Bob", 19)
        println("Infos utilisateur : $bob")

        val bobette = Utilisateur("Bobette", 4)
        println("Infos utilisateur : $bobette")

        val john = bob.copy(nom = "John")
        println("Infos utilisateur : $john")

    }

    /**
     * Formation POO sur les Nested Class qui sont des classes imbriquées.
     */
    private fun formationPOONestedClass() {
        val bag = Bag(2)

        val item1 = Bag.Item(50)
        bag.items[0] = item1
        bag.items[1] = Bag.Item(100)
    }

    /**
     * Formation POO sur les Inner Class qui sont comme des Nested sauf qu'elle ont accès à leur Outer Class.
     */
    private fun formationPOOInnerClass() {
        val bag = Bag(10)

        for(i in 0..10) {
            bag.items[i] = Bag.Item(i * 10)
        }

        val volume = bag.Volume()
        volume.afficheVolume()
    }

    /**
     * Formation POO sur les Génériques, permet de rendre générique avec T une fonction...
     */
    private fun formationPOOGenerics() {
        afficheTableauRenverse(arrayOf(0,1,5,81,15))
        afficheTableauRenverse(arrayOf("Hello", "World", "en", "kotlin"))

        val boiteInt = Boite<Int>(50)
        val v: Int = boiteInt.get()

        val boxString = Boite<String>("Kotlin")
        println(boxString.get())

    }

    /**
     * Formation POO lateInit qui ne s'applique pas sur les types primitifs, permet d'initialiser plus tard une propriété.
     */
    private fun formationPOOLateInit() {
        val bob = Utilisateur2("Bob", 15)
        bob.pseudo = "Bobo"
        // Si on oublie, cela crash au moment de l'utilisation.
    }

    /**
     * Formation POO sur les companion object. Singleton partagée par toutes les autres classes (compense static en Java)
     */
    private fun formationPOOCOmpanionObject() {
        val bob = Utilisateur2.createBob()
        val bobette = Utilisateur2.Factory.createBobette()
    }
}