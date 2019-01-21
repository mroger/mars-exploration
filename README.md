# Explorando Marte

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

### Criação das sondas

`POST http://localhost:9890/mars-exploration/probes`

Request
```json
{
  "x": 3,
  "y": 3,
  "direction": "E"
}
```

Response
```json
{
  "id": "b9ab0a9c-91eb-4575-8ab0-227edfe1385a",
  "x": 3,
  "y": 3,
  "direction": "E"
}
```

### Consulta de todas as sondas

`GET http://localhost:9890/mars-exploration/probes`

Response
```json
[
  {
    "id": "47161e8a-301f-4f9c-afeb-e6b7a44bd62e",
    "x": 10,
    "y": 10,
    "direction": "W"
  },
  {
    "id": "b9ab0a9c-91eb-4575-8ab0-227edfe1385a",
    "x": 3,
    "y": 0,
    "direction": "S"
  }
]
```

### Consulta de sonda por id

`GET http://localhost:9890/mars-exploration/probes/{probeId}`

Exemplo:
`GET http://localhost:9890/mars-exploration/probes/b9ab0a9c-91eb-4575-8ab0-227edfe1385a`

Response
```json
{
  "id": "b9ab0a9c-91eb-4575-8ab0-227edfe1385a",
  "x": 3,
  "y": 3,
  "direction": "E"
}
```

### Consulta de sonda por sua posição

`GET http://localhost:9890/mars-exploration/probes/position?x=1&y=2`

Response
```json
{
  "id": "d4d64c3a-457f-425b-82e0-1f44cb909b67",
  "x": 13,
  "y": 2,
  "direction": "S"
}
```

### Sending instructions to control probes

#### Moving the probe

`PUT http://localhost:9890/mars-exploration/probes`

Request
```json
{
  "id": "b9ab0a9c-91eb-4575-8ab0-227edfe1385a",
  "instruction": "M"
}
```

Response
```json
{
  "id": "d4d64c3a-457f-425b-82e0-1f44cb909b67",
  "x": 13,
  "y": 1,
  "direction": "S"
}
```
