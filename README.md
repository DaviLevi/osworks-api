# osworks-api
Api desenvolvida durante o workshop algaworks sobre REST APIs.

# API Domain - Diagrama de classe UML
![Diagrama UML](../main/docs/UML_Osworks_Api.png)

# Endpoints

Cliente :
- Atualizar cliente : PUT - localhost:8080/clientes/:id
- Listar clientes : GET - localhost:8080/clientes
- Buscar cliente pelo Id : GET - localhost:8080/clientes/:id
- Deletar cliente pelo Id : DELETE - localhost:8080/clientes/:id

Ordens Serviço :
- Finalizar ordem : PUT - localhost:8080/ordem-servicos/:id/finalizacao
- Listar comentários : GET - localhost:8080/ordem-servicos/:id/comentarios
- Adicionar comentários : PUT - localhost:8080/ordem-servicos/:id/comentarios
- Buscar ordens pelo Id : GET - localhost:8080/ordem-servicos/:id
- Listar ordens : GET - localhost:8080/ordem-servicos
- Gerar ordem : POST - localhost:8080/ordem-servicos

# Payloads

- Cliente :
```json
{
	"nome" : "Joao",
	"email" : "joao@teste.com",
	"telefone" : "997676581" 
}
```
- Ordem Servico :
```json
{
	"cliente" : {
	    "id" : 35
	},
	"descricao" : "Ordem servico teste",
	"preco" : 300.50
}
```
