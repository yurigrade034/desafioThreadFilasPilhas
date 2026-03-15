import kotlin.collections.ArrayDeque
import kotlin.concurrent.thread

fun main() {
    jogoAdivinhacao()
}

fun jogoAdivinhacao() {

    val cartasParaEmbaralhar = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    cartasParaEmbaralhar.shuffle()

    val pilhaDeCartas = ArrayDeque<Int>()
    for (carta in cartasParaEmbaralhar) {
        pilhaDeCartas.addLast(carta)
    }

    thread(name = "Log ADM") {
        while (pilhaDeCartas.isNotEmpty()) {
            val topoAtual = pilhaDeCartas.lastOrNull()
            if (topoAtual != null) {

                println("\n[${Thread.currentThread().name}]: O topo atual é $topoAtual")
            }
            Thread.sleep(5000)
        }
    }

    var pontos = 0
    println("---> Jogo de Adivinhação (Pilhas) <---")
    println("Tente adivinhar a carta do topo, tem ${pilhaDeCartas.size} cartas.")

    while (pilhaDeCartas.isNotEmpty()) {

        val cartaDoTopo = pilhaDeCartas.last()

        print("\nQual número 1 a 10 está no topo da pilha? ")
        val palpite = readLine()?.toIntOrNull()

        if (palpite == cartaDoTopo) {
            println("Acertou baguá, carta era $cartaDoTopo.")
            pontos += 10
        } else {
            println("Errou baguá, carta era $cartaDoTopo.")
        }

        pilhaDeCartas.removeLast()
        println("Cartas restantes: ${pilhaDeCartas.size}")
        Thread.sleep(500)
    }

    println("\n---> FIM DE JOGO <---")
    println("Sua pontuação final: $pontos pontos")
}