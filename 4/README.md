# Deadlock

Trata-se de um problema de concorrência em que processos necessitam utilizar recursos, porém outros processos estão utilizando.
Exemplificando, um Processo A está utilizando um Recurso X mas necessita de um Recurso Y, porém um outro Processo B está utilizando o Recurso Y e, por coincidência, necessita do Recurso X.

![N|Solid](http://2.bp.blogspot.com/-63RZ-BTlAFs/VfGeHMnGdFI/AAAAAAAADuw/gwqtrVliMsM/s1600/Deadlock%2Bof%2BThreads.jpg)

Esse cenário faz com que ambos processos fiquem presos no mesmo estado, uma vez que um precisa do recurso do outro para continuar com sua execução.

## Soluções

Existem diversos algoritmos para evitar deadlock. 
Na maioria dos casos, a utilização de _locks_ e aplicando de segmentos de sincronização permite uma tratamento simples para evitar deadlock.
Em casos mais complexos (com grande quantidade de recursos e processos/threads), é necessário ter mais informações sobre cada processo a fim de determinar a distribuição e priorização dos processos X recursos.
A utilização de tabelas de mapeamento, que armazenam o estado de cada processo e recurso, permitem o gerenciamento destes.
