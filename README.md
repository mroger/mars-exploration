# Explorando Marte

## Decisões de design
Utilizando o conceito de aggregates, foram criadas as classes do módulo mars-exploration-model.
A classe MissionControl foi criada nesse módulo, mas como possui visibilidade pública e pode
representar um repositório de sondas, poderia ser movida para o módulo mars-exploration-api. 

## Construindo executando a aplicação

### Construindo a aplicação
`$ mvn clean install`

### Executando a aplicação
`$ cd mars-exploration-api`

`$ mvn clean spring-boot:run`

### Definindo a área do planalto
O arquivo resources/application.yml define uma área default para o planalto (largura=20 e altura=20).

Para redefinir esta área na execução da aplicação, pode-se fazer, por exemplo:

`$ mvn clean spring-boot:run -Dmissioncontrol.plateau.width=30 -Dmissioncontrol.plateau.height=30`

## API para criação e controle das sondas
A API foi documentada usando-se Swagger e pode-se experimentá-la com a aplicação em execução e acessando-se o endereço

`http://localhost:9890/mars-exploration/swagger-ui.html`

### Operações
As operações disponíveis para as sondas são:
* Criar uma sonda - duas sondas não podem ocupar o mesmo lugar
* Consultar todas as sondas
* Localizar uma sonda pelo id
* Localizar uma sonda por sua posição
* Enviar instruções para movimentar uma sonda identificada pelo Id e rotacioná-la para a esquerda ou para a direita

Obs.: A operação de remover uma sonda não foi implementada porque não seria prático destruí-la uma vez que já estivesse em Marte.
Uma solução seria criar a sonda com o seu tempo de vida configurado e fazer com que ela fosse desativada depois de passado esse tempo.

