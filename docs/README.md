# LPOO_50 - Crossy Roads

Bem vindo ao _Crossy Roads_! Neste jogo tens que controlar a galinha para que esta chegue ao topo do ecrã recolhendo todas as moedas.

Cuidado! Não achavas que ia ser assim tão fácil pois não? Se fores contra os veículos, ou atropelado por eles, perdes vida e regressas à posição inicial. Após perder as 3 vidas dadas no início do jogo, este acaba.

Este projeto está a ser desenvolvido por: Ana Teresa Cruz (up201806460@fe.up.pt), André Nascimento (up201806461@fe.up.pt) e Pedro Coelho (up201806802@fe.up.pt) no âmbito da cadeira LPOO 2019/20.

## Implemented Features 

- **Controlo da galinha:** a galinha é controlada através das setas do teclado.
- **Moedas:** quando a galinha passa por cima de uma moeda, esta última desaparece e o _score_ da galinha é incrementado dependendo do valor da moeda recolhida.

    [![Coin Collision](https://i.gyazo.com/05353e3bb1c16635ddc19c38356e0810.gif)](https://gyazo.com/05353e3bb1c16635ddc19c38356e0810)

- **Movimento dos veículos:** os veículos deslocam-se consoante a sua direção (esquerda ou direita) a cada 200ms.
- **Colisão entre galinha e veículos:** quando a galinha vai contra um veículo ou é atropleado por um a sua _health_ é reduzida e volta à posição inicial.

    [![Vehicle Collision](https://i.gyazo.com/63df273c94498e994c27186b530ddefa.gif)](https://gyazo.com/63df273c94498e994c27186b530ddefa)

- **Música de fundo:** o jogo tem a música de fundo "O Pintinho Piu" para proporcionar uma melhor experiência e maior divertimento.
- **Níveis:** o jogo consiste em 5 níveis com dificuldade, número de veículos e moedas crescente.    
- **Terminar o jogo:** a janela do jogo fecha e o jogo termina quando a galinha ganha ou perde ou quando o utilizador clica no X da janela do jogo.

## Planned Features

- **Menu inicial:** um menu que permite ao utilizador jogar, sair e ver uma tabela dos _highscores_.
- **Registo dos _highscores_:** contabilizar o tempo de cada partida e guardar, sendo posteriormente apresentados na tabela.
- **Níveis:** para já só existe um nível, mas futuramente existirão mais níveis com número de veículos, moedas e dificuldade crescente.
- **Mensagens:** aparecerá uma mensagem de fim de jogo quando o jogador perdeu.

## Design

### - Os objetos apesar de terem atributos em comum são todos diferentes

#### Problem in Context

Para a realização do jogo precisamos de vários objetos diferentes. Chegou-se à conclusão que estes teriam atributos em comum e, por isso, não fazia sentido repeti-los em todas as classes, o que levou à criação de uma classe mãe.
Por outro lado, não faz sentido que esta classe mãe fosse realmente um objeto representado no jogo, uma vez que só teria os atributos em comum. Fazendo mais sentido representar os objetos criados nas suas sublasses.

#### The Patterns

Aplicamos o _**Factory Method**_ _pattern_. Este permite que as subclasses determinem o objeto a ser criado sem conseguir antecipar o que será esse objeto. Com isto conseguimos resolver o problema referido a cima, a classe mãe, Element, permite as suas subclasses, por exemplo Chicken, determinarem o objeto que querem criar, não interferindo ou tendo qualquer conhecimento do objeto a ser criado.
Também foi aplicado o _**Composite**_ _pattern_. Este permite a hierarquia entre classes, quando se quer representar classes pertencendo a uma parte/todo. Assim, em vez de repetirmos os métodos em todas as classes, agora as subclasses herdam estes métodos em comum da classe mãe.

#### Implementation
O gráfico em UML seguinte demonstra como foram aplicados estes patterns nas classes.

[![Image from Gyazo](https://i.gyazo.com/af6e6d09cc0b7abdb3a8862e4b31a973.png)](https://gyazo.com/af6e6d09cc0b7abdb3a8862e4b31a973)

#### Consequences

- Não repetição de métodos comuns;
- Facilidade de acrescentar outros objetos ao jogo;
- As subclasses ficam mais simples e legíveis.

### - Notificar que houve alterações na view

#### Problem in Context

Quando ocorre alguma alterações durante o jogo, a Chicken ou veículos se moverem por exemplo, a view tem que ser notificada para voltar a desenhar.

#### The Pattern

Aplicamos o _**Observer**_ _pattern_. Este é usado entre classes com dependências de modo que quando um objeto é alterado, todos os objetos dependentes deste têm que ser notificados e atualizados. Desta forma, sempre que ocorre algum movimento ou alteração ao jogo, a view é notificada e volta a desenhar.

#### Implementation
O gráfico em UML seguinte demonstra como foi aplicado este pattern nas classes.

[![Image from Gyazo](https://i.gyazo.com/98fc64d7ec1815f01be4c2108791a78e.png)](https://gyazo.com/98fc64d7ec1815f01be4c2108791a78e)

#### Consequences

- Uma relação abstrata entre a view e observador;
- Permite comunicação entre os objetos;

### - Organização de código

#### Problem in Context

Desde início a organização do código era um ponto fulcral, de maneira a permitir avançar e corrigir erros anteriores sem grandes alterações.

#### The Pattern

Como tal recorremos ao _Architectural Pattern_ _MVC (Model-View-Controller)_, sugerido pelo professor logo na primeira aula prática relacionada com o desenvolvimento do projeto, que é um padrão usado frequentemente em _GUIs_ como o caso do nosso jogo.
Neste padrão, o _Model_ apenas é responsável por armazenar a data, a _View_ mostra a data do _Model_ e envia as ações do utilizador para o _Controller_, e o _Controller_ fornece a data do _Model_ para a _View_ e interpreta as ações do utilizador.

#### Implementation

No nosso caso, o _GameMap_ contém toda a informação referente ao nível que é mostrada pela _Gui_ quando há alguma alteração, a _Gui_ recebe os _inputs_ do jogador e envia-os ao _GameController_, e o _GameController_ manipula os dados consoante os inputs.

[![MVC](https://i.gyazo.com/0efec72c41247b5e6ce85dabb795bb89.png)](https://gyazo.com/0efec72c41247b5e6ce85dabb795bb89)

#### Consequences

- O desenvolvimento da aplicação torna-se mais rápido e modular;
- Facilita a distribuição de tarefas pelos vários elementos;
- Requer regras específicas para a criação de classes e respetivos métodos;
- O uso de camadas ajuda a controlar e encapsular a complexidade de programas grandes, mas adiciona complexidade a aplicações simples;

## Known Code Smells and Refactoring Suggestions

- _Lazy Class_ e _Data Class_

As classes Truck e Car implementam apenas 2 métodos, a obtenção do comprimento do veículo e a sua cor. Não têm grande utilidade.

Uma das formas de eliminar este code smell seria eliminar as subclasses e os seus atributos e métodos passarem a fazer parte da classe mãe.

- _Long Method_

Na classe VehicleController, o método moveVehicles() é demasiado extenso tornando-se a leitura do código difícil.

Uma forma de melhorar o código seria dividir este método em vários, ficando o código mais distribuído e legível.

## Testing

- _Screenshot of coverage report:_

[![Image from Gyazo](https://i.gyazo.com/6ea410024fbc031f7a1962c2f08905b1.png)](https://gyazo.com/6ea410024fbc031f7a1962c2f08905b1)

## Self-Evaluation

Inicialmente o grupo era só de 2 elementos e o trabalho foi distribuído de forma equitativa e justa. Mais tarde, juntou-se um outro elemento e fizemos uma nova distribuição de tarefas. Segue-se a percentagem discutida entre todos os elementos:

- Ana Teresa Cruz: 40% 
- André Nascimento: 40%
- Pedro Coelho: 20%