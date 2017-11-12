## Dúvida

Segundo o exemplo do exercício, a primeira chamada do `getNext()` retorna o caractere **e** (aAbBABacaf**e**) porém, segundo o algoritmo, o correto é retornar o caractere **a** (aAbBABac**a**fe).

1° Iteração:
trecho verificado: aAbBAB**aca**fe

`hasNext()` -> true

`getNext()` -> a

2° Iteração:
trecho verificado: aAbBAB**afe**

`hasNext()` -> true

`getNext()` -> e

3° Iteração:
fim da palavra

`hasNext()` -> false

