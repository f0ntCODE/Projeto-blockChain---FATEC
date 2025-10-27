
# BlockChain usando Java Spring Boot

Neste projeto venho mostra usando o java o funcionamento de um blockchain igual é a do Bitcoin (claro que bem mais simples). Quero passar o entendimento de como essa tecnologia funciona.


## Autores

- [@Akssasori](https://github.com/Akssasori)

## Documentação da API

#### Adiciona as transações

```http
  POST /transaction
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `sender` | `string` | **Obrigatório**. 0xpessoa17234879 |
| `recipient` | `string` | **Obrigatório**. 0xpessoa2987654321 |
| `amount` | `double` | **Obrigatório**. 5.0 |

## Visualizar transações
As transações podem ser vistas de maneira gráfica ao digitar a seguinte rota:
 ````http
    localhost:7000/blockchain/show
 ````

## Referências

- [Explicação do que é o hash](https://br.cointelegraph.com/learn/how-does-blockchain-work-a-beginners-guide-to-blockchain-technology)
- [Medium](https://medium.com/the-crypto-block/8-concepts-that-will-help-you-understand-blockchain-technology-c51b0941bf19)

### Dos colaboradores com o upgrade
- [Claude](https://claude.ai/)
- [W3 Schools](https://www.w3schools.com/java)


## Vídeo youtube

https://youtu.be/Q4tqKDQurBs