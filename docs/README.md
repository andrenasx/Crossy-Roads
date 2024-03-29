# LPOO_50 - Crossy Roads

Bem vindo ao _Crossy Roads_! Neste jogo tens de controlar a galinha para que esta chegue ao topo do ecrã recolhendo todas as moedas.

Cuidado! Não achavas que ia ser assim tão fácil pois não? Se fores contra os veículos, ou atropelado por eles, perdes vida e regressas à posição inicial. Após perder as 3 vidas dadas no início do jogo, este acaba.

Este projeto foi desenvolvido por: Ana Teresa Cruz (up201806460@fe.up.pt), André Nascimento (up201806461@fe.up.pt) e Pedro Coelho (up201806802@fe.up.pt) no âmbito da cadeira LPOO 2019/20.

## Implemented Features 
- **Menu Inicial:** a aplicação inicializa neste menu, onde o jogador pode escolher jogar, aceder a um menu de instruções ou de melhores resultados, ou sair do jogo.

    [![Image from Gyazo](https://i.gyazo.com/64fedd52a04a48c96ab45fe18cca1550.png)](https://gyazo.com/64fedd52a04a48c96ab45fe18cca1550)
    
- **Menu Help:** neste menu o jogador tem uma breve descrição de quais são as regras do jogo e como jogar, tendo uma opção para começar logo uma partida ou voltar ao Menu Inicial.

    [![Image from Gyazo](https://i.gyazo.com/fb485a9ff6a133849a548a5a2665226e.png)](https://gyazo.com/fb485a9ff6a133849a548a5a2665226e)
    
- **Menu Highscores:** neste menu apresentam-se os 7 melhores resultados, sendo demonstrado qual o último nível atingido, o _score_ e a quantidade de movimentos efetuados durante a partida.

    [![Image from Gyazo](https://i.gyazo.com/8fab964708944e9b17fd9588e507870e.png)](https://gyazo.com/8fab964708944e9b17fd9588e507870e)
    
- **Controlo da galinha:** a galinha é controlada através das setas do teclado.
- **Moedas:** quando a galinha passa por cima de uma moeda, esta última desaparece e o _score_ da galinha é incrementado.

    [![Image from Gyazo](https://i.gyazo.com/08ba9edbcf6815657c36a15f39b658f4.gif)](https://gyazo.com/08ba9edbcf6815657c36a15f39b658f4)
    
- **Movimento dos veículos:** os veículos deslocam-se consoante a sua direção (esquerda ou direita) e velocidade, sendo que os carros se deslocam a maior velocidade que os camiões.
- **Colisão entre galinha e veículos:** quando a galinha vai contra um veículo ou é atropelada por um a sua _health_ é reduzida e volta à posição inicial. Após três colisões o jogador perde.

    [![Image from Gyazo](https://i.gyazo.com/2b4efbe03299cc45b212da4ab42e6655.gif)](https://gyazo.com/2b4efbe03299cc45b212da4ab42e6655)
    
- **Música de fundo:** o jogo tem uma música de fundo para proporcionar uma melhor experiência e maior divertimento.
- **Pausa:** ao carregar no ESC o jogador pode pausar o jogo, podendo depois retomá-lo ou voltar ao Menu Inicial.

    [![Image from Gyazo](https://i.gyazo.com/ddfe232c4707db6445c6b465c591ba2f.png)](https://gyazo.com/ddfe232c4707db6445c6b465c591ba2f)
    
- **Níveis:** o jogo consiste em 5 níveis com dificuldade, número de veículos e moedas crescente.

    [![Image from Gyazo](https://i.gyazo.com/05f51c4294a84e159cef5017b4b905e1.gif)](https://gyazo.com/05f51c4294a84e159cef5017b4b905e1)
        
- **Mensagens da _performance_:** no final de uma partida é apresentada uma mensagem no ecrã dizendo se o jogador ganhou ou perdeu e alguns aspetos da sua partida, como por exemplo, o _score_.

    [![Image from Gyazo](https://i.gyazo.com/2120d77c1c2d9f21689fc52c5bfeb34d.png)](https://gyazo.com/2120d77c1c2d9f21689fc52c5bfeb34d)             [![Image from Gyazo](https://i.gyazo.com/2e2f73aa67d34f56eed39d272c88d585.png)](https://gyazo.com/2e2f73aa67d34f56eed39d272c88d585)

### Project Demo

   ![demo](./project_demo/demo.gif)

## Planned Features

- **Efeitos sonoros:** apanhar uma moeda ou ir contra um carro seria sinalizado com um efeito sonoro correspondente.
- **Pausar a música:** quando o jogo está em pausa, a música também pausa e depois quando o jogo é retomado a música também continua.
- **Power-ups:** se fossem acrescentados mais níveis seriam disponibilizados power-ups distribuídos pelo nível, como por exemplo, uma vida.

## Design

### - Os objetos apesar de terem atributos em comum são todos diferentes

#### Problem in Context

Para a realização do jogo precisamos de vários objetos diferentes. Chegou-se à conclusão que estes teriam atributos em comum e, por isso, não fazia sentido repeti-los em todas as classes.

#### Implementation
Sendo assim, foi criada uma classe mãe abstrata ([Element.java](../src/main/java/crossyroads/model/Element.java)) que serve de base para os objetos dos níveis.

O gráfico em UML seguinte demonstra como foi a implementação nas classes.

[![Image from Gyazo](https://i.gyazo.com/1f9f66ce22668b21c9cfe24c14523f2f.png)](https://gyazo.com/1f9f66ce22668b21c9cfe24c14523f2f)

#### Consequences

- Não repetição de métodos comuns;
- Facilidade de acrescentar outros objetos ao jogo;
- As subclasses ficam mais simples e legíveis.

### - Alterações na view

#### Problem in Context

Quando ocorre alguma alteração durante o jogo, a galinha ou veículos se moverem, por exemplo, a view tem de voltar a ser desenhada.

#### The Pattern

Inicialmente foi aplicado o _**Observer**_ _pattern_. Este é usado entre classes com dependências de modo que, quando um objeto é alterado, todos os objetos dependentes deste têm que ser notificados e atualizados. Desta forma, sempre que ocorre algum movimento ou alteração ao jogo, a view é notificada e volta a desenhar.

No entanto, chegamos à conclusão que o uso deste padrão é desnecessário para o jogo. 

#### Implementation
Deste modo, retirou-se a classe Observer e agora é dentro do próprio estado que é desenhada a view correspondente.

O gráfico em UML seguinte demonstra como, inicialmente, foi aplicado o padrão nas classes.

[![Image from Gyazo](https://i.gyazo.com/56c962bfab54de198ce7b5195de49cc7.png)](https://gyazo.com/56c962bfab54de198ce7b5195de49cc7)

O gráfico em UML seguinte demonstra como foi implementada esta mudança.

[![Image from Gyazo](https://i.gyazo.com/75df5f0bfa288a5a24a883cc317b4944.png)](https://gyazo.com/75df5f0bfa288a5a24a883cc317b4944)

*Notar que apenas está representado um State e a sua Gui respetiva

### - Mudanças de estado

#### Problem in Context

Para o funcionamento do jogo é necessário ter controlo sobre o estado atual e lidar com as mudanças de estado, sendo que cada parte do jogo deverá o seu próprio estado correspondente.

#### The Pattern

Recorremos ao _State Pattern_ que permite alterar o comportamento de um objeto quando o seu estado sofre alguma alteração. Foi criada uma interface [State.java](../src/main/java/crossyroads/controller/states/State.java) e depois classes para cada estado nas quais são implementadas os métodos definidos na interface.

#### The Implementation

Implementamos um controlador ([AppController.java](../src/main/java/crossyroads/controller/AppController.java)) que guarda o estado atual e o executa. Dentro de cada estado, dependendo do comando que recebe passa a outro estado.

O gráfico UML seguinte demostra este padrão.

[![Image from Gyazo](https://i.gyazo.com/b361e4eaf83272df0b79c1b3a3392545.png)](https://gyazo.com/b361e4eaf83272df0b79c1b3a3392545)

#### Consequences

- Facilidade em acrescentar mais estados uma vez que, não se tem que alterar os estados já existentes;
- A independência entre cada estado permite que cada um tenha o seu comportamento;
- O comportamento de um objeto é o resultado de uma função do próprio estado, e a alteração ocorre em _runtime_ dependendo do estado;
- Maior número de classes, uma para cada estado.

### - Criação do TerminalScreen

#### Problem in Context

Após a implementação dos estados veio o problema da criação do TerminalScreen, uma vez que o objetivo era que o terminal fosse criado uma única vez, mas permitindo as alterações da view dependendo de cada estado.

#### Implementation

Inicialmente foi implementado o _Singleton Pattern_ que permitia criar apenas uma vez a janela, caso esta não existisse, e devolvê-la, resolvendo assim o nosso problema. No entanto, criou outro problema: a impossibilidade de testar o código sem abrir uma janela do terminal.

Deste modo, foi retirado este padrão. Aproveitando a classe [ScreenFactory.java](../src/main/java/crossyroads/view/ScreenFactory.java) anteriormente implementada, o método de criação do terminal passou a ser chamado uma única vez pela Aplicação principal ([Application.java](../src/main/java/crossyroads/Application.java)) que, por sua vez passa o screen criado à [AppController.java](../src/main/java/crossyroads/controller/AppController.java). Assim, todos os estados passam a receber o screen, permitindo cada uma das suas gui fazer alterações no screen já existente.

O gráfico UML seguinte demonstra esta implementação.

[![Image from Gyazo](https://i.gyazo.com/8599b08a3e90101bbe6a385f44bc7d41.png)](https://gyazo.com/8599b08a3e90101bbe6a385f44bc7d41)

### - Organização de código

#### Problem in Context

Desde o início a organização do código era um ponto fulcral, de maneira a permitir avançar e corrigir erros anteriores sem grandes alterações.

#### The Pattern

Como tal recorremos ao _Architectural Pattern_ _MVC (Model-View-Controller)_, sugerido pelo professor logo na primeira aula prática relacionada com o desenvolvimento do projeto, que é um padrão usado frequentemente em _GUIs_ como o caso do nosso jogo.
Neste padrão, o _Model_ apenas é responsável por armazenar a data, a _View_ mostra a data do _Model_ e envia as ações do utilizador para o _Controller_, e o _Controller_ fornece a data do _Model_ para a _View_ e interpreta as ações do utilizador.

#### Implementation

No nosso caso, o _GameModel_ contém toda a informação referente aos níveis e as várias _Guis_ recebem os _inputs_ do jogador e enviam-os ao estado atual do jogo. A _AppController_ guarda o estado em que o jogo está a correr, e este manipula os dados consoante os _inputs_.

O gráfico em UML seguinte representa este padrão.

[![Image from Gyazo](https://i.gyazo.com/9cd60216aab77a381048b3ff7c60e840.png)](https://gyazo.com/9cd60216aab77a381048b3ff7c60e840)

#### Consequences

- O desenvolvimento da aplicação torna-se mais rápido e modular;
- Facilita a distribuição de tarefas pelos vários elementos;
- Requer regras específicas para a criação de classes e respetivos métodos;
- O uso de camadas ajuda a controlar e encapsular a complexidade de programas grandes, mas adiciona complexidade a aplicações simples.

## Known Code Smells and Refactoring Suggestions

- _Lazy Class_ e _Data Class_

As classes [Truck.java](../src/main/java/crossyroads/model/Truck.java) e [Car.java](../src/main/java/crossyroads/model/Car.java) implementam apenas 3 métodos, a obtenção do comprimento do veículo, a sua cor e velocidade.

Uma das formas de eliminar este code smell seria eliminar as subclasses e os seus atributos e os seus métodos passarem a fazer parte da classe mãe, a [Vehicle.java](../src/main/java/crossyroads/model/Vehicle.java). No entanto, esta solução não se adequa ao nosso caso uma vez que precisamos de uma distinção entre carros e camiões.

Outra classe que também é _Data Class_ é a [Score.java](../src/main/java/crossyroads/model/Score.java), uma vez que é uma classe que apenas contém getters e setters. Esta apenas guarda a informação a ser utilizada pela classe [Highscore.java](../src/main/java/crossyroads/model/Highscore.java).

Para eliminar este code smell podiam ser pensadas outras funcionalidades, ou até mesmo analisar as funcionalidades da Highscore e ver se estas não estariam melhor localizadas na _Data Class_.

- _Duplicate Code_

Todas as classes [Gui](../src/main/java/crossyroads/view) têm o método _drawButtons_ no qual existem algumas linhas de código comuns em todas estas classes.

Uma forma de eliminar este smell seria criar uma classe chamada _DrawButtons_ que desenharia um número de botões e uma lista de intruções passadas por argumento e cada classe chamaria este método. Assim, este método só seria implementado uma vez eliminando as linhas de código duplicadas.


## _Testing Results_

### _Coverage report_

[![Image from Gyazo](https://i.gyazo.com/8066a8656338712940727839a64309e9.png)](https://gyazo.com/8066a8656338712940727839a64309e9)

Mais informações [aqui](./reports/test/index.html).

### _Mutation testing report_

[![Image from Gyazo](https://i.gyazo.com/38b2e4968bff8ff8264582dd44012544.png)](https://gyazo.com/38b2e4968bff8ff8264582dd44012544)

Mais informações [aqui](./reports/pitest/index.html).

## _Self-Evaluation_

Inicialmente o grupo era só de 2 elementos e o trabalho foi distribuído de forma equitativa e justa. Mais tarde, juntou-se um outro elemento e fizemos uma nova distribuição de tarefas. Segue-se a percentagem final discutida entre todos os elementos:

- Ana Teresa Cruz: 40% 
- André Nascimento: 40%
- Pedro Coelho: 20%