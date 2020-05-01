#LPOO_50 - Crossy Roads

Bem vindo ao _Crossy Roads_! Neste jogo tens que controlar a galinha para que esta chegue ao topo do ecrã coletando todas as moedas.

Cuidado! Não achavas que ia ser assim tão fácil pois não? Se fores contra os veículos, ou atropelado por eles, perdes vida. Após perder as 3 vidas dadas no início do jogo, este acaba.

Este projeto está a ser desenvolvido por: Ana Teresa Cruz (up201806460@fe.up.pt), André Nascimento (up201806461@fe.up.pt) e Pedro Coelho (up201806802@fe.up.pt) no âmbito da cadeira LPOO 2019/20.

##Implemented Features 

- **Controlo da galinha:** a galinha é controlada através das setas do teclado.
- **Moedas:** quando a galinha passa por cima de uma moeda, esta última desaparece e o _score_ da galinha é incrementado dependendo do valor da moeda recolhida.
- **Movimento dos veículos:** os veículos deslocam-se consoante a sua direção (esquerda ou direita) a cada 200ms.
- **Colisão entre galinha e veículos:** quando a galinha vai contra um veículo ou é atropleado por um a sua vida é reduzida e volta à posição inicial.
(screenshots do score e health)

##Planned Features

- **Menu inicial:** um menu que permite ao utilizador jogar, sair e ver uma tabela dos _highscores_.
- **Registo dos _highscores_:** contabilizar o tempo de cada partida e guardar, sendo posteriormente apresentados na tabela.
- **Níveis:** para já só existe um nível, mas futuramente existirão mais níveis com número de veículos, moedas e dificuldade crescente.
- **Música de fundo:** uma música agradável para propor uma boa experiência.
- **Mensagens:** aparecerá uma mensagem de fim de jogo quando o jogador perdeu.

##Design
(aprofundar muito mais!)
- Factory Method: element, as subclasses especificam o objeto a criar
- Composite: element e vehicle, representam uma parte todo , permitindo nos decompor em objetos mais complexos;
- Observer:  gameObserver, quando um veiculo ou a galinha se mexem pode-se notificar a view para desenhar~.
- MVC: o modelo representa a data, a view mostra os dados do modelo e manda as ações para o controlador; o controlador manda os dados do modelo para a view e interpreta as ações do utilizador.

##Known Code Smells and Refactoring Suggestions

- _Lazy Class_ e _Data Class_

As classes Truck e Car implementam apenas 2 métodos, um para obter o comprimento do veículo e a sua cor. Não têm grande utilidade.

Uma das formas de eliminar este code smell seria eliminar as subclasses e os seus atributos e métodos passarem a fazer parte da classe mãe.

- Long Method

Na classe VehicleController o método moveVehicles() é demasiado extenso.

Uma forma de melhorar o código seria dividir este método em vários, ficando o código mais distribuído e legível.

##Testing

- _Screenshot of coverage report:_

- _Link to mutation testing report:_