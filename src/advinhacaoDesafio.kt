import kotlin.collections.ArrayDeque
import kotlin.concurrent.thread

fun main() {
    jogoAdivinhacao()
}

fun jogoAdivinhacao() {
    val cartasParaEmbaralhar = mutableListOf<Any>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14)
    cartasParaEmbaralhar.shuffle()

    val pilhaDeCartas = ArrayDeque<Any>()
    for (carta in cartasParaEmbaralhar) {
        pilhaDeCartas.addLast(carta)
    }

    thread(name = "Log ADM", isDaemon = true) { //processo de segundo plano
        while (pilhaDeCartas.isNotEmpty()) {
            val topoAtual = try { pilhaDeCartas.lastOrNull() } catch (e: Exception) { null }
            if (topoAtual != null) {
                println("\n[${Thread.currentThread().name}]: O topo atual é $topoAtual")
            }
            Thread.sleep(5000)
        } //tenta ler o topo e se nao der nada apenas retorna nulo
    }

    var pontos = 0
    println("---> Jogo de Adivinhação Pilhas <---")
    println("acerte a carta do topo, tem ${pilhaDeCartas.size} cartas")

    while (pilhaDeCartas.isNotEmpty()) {
        val cartaDoTopo = pilhaDeCartas.last()

        print("\nqual número 1 a 14 está no topo da pilha? ")
        val palpite = readLine()?.toIntOrNull()

        if (palpite == cartaDoTopo) {
            println("acertou baguá, carta era $cartaDoTopo.")
            pontos += 10
        } else {
            println("errou baguá, carta era $cartaDoTopo.")
        }

        pilhaDeCartas.removeLast()
        println("Cartas restantes: ${pilhaDeCartas.size}")
        Thread.sleep(500)
    }

    println("\n---> FIM DE JOGO <---")
    println("total de $pontos pontos")
}
