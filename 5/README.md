# Stream API and Parallel Stream

Com a evoluço da plataforma Java para a versão 8, houve o surgimento de diversas _features_ relacionadas a manipulação de data, lista, threads, entre outros.
Uma dessas features é a Stream API. Ela permite a iteração e a manipulação de coleções no código fonte, através de uma série de comandos intuitivos, coesos e robustos.
É possível, através do método `stream()` executar operações de agregação como `filter()`, `map()`, etc.

Por padrão, a iteração dos elementos é feita de forma sequencial. Entretanto, é possível navegar entre os elementos de uma lista através do método `parallelStream()`, que permite a execução de forma paralela, otimizando e aumentando o paralelismo na execução da rotina, através da utilização de múltiplos núcleos do processador.

Em uma infraestrutura mais robusta, a utilização do processamento paralelo das coleções pode trazer um ganho de performance significativo. **Entretanto**, deve-se atentar na utilização do parallelStream nos casos em que mais de um item da coleção esteja utilizando um mesmo recurso. Isso pode acarretar em uma sobrecarga muito alta.

Nos cenários em que as coleções não são muito grandes ou nos cenários em que há acessos à um mesmo recurso, a utilização do stream sequencial é mais indicada.
