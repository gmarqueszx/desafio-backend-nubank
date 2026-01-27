<h1 align="center" style="font-weight: bold;">Cadastro de Usuários e Gestão de Contatos - Desafio Backend Nubank💻</h1>

<p align="center">
 <a href="#tech">Stacks</a> • 
  <a href="#routes">API Endpoints</a> • 
  <a href="#routes">Diagrama Relacional (DER)</a>
</p>

<p align="center">
    <b>API para cadastro de usuários e gestão de contatos. Resolução de um desafio técnico de backend da Nubank.</b>
</p>

<h2 id="technologies">💻 Stacks</h2>

- Java 21
- PostgreSQL 17.5 
- Spring Boot 4.0.1
- MapStruct
- Flyway
- Hibernate Validator


<h2 id="routes">📍 API Endpoints</h2>

Endpoints que compõe a API:
​
| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /api/v1/clientes</kbd>     | Listar todos os clientes cadastrados
| <kbd>GET /api/v1/clientes/{clienteId}/contatos</kbd>     | Buscar contatos cadastrados de um cliente
| <kbd>POST /api/v1/clientes</kbd>     | Cadastrar um cliente com seus contatos
| <kbd>PUT /api/v1/clientes/{clienteId}</kbd>     | Atualizar dados de um cliente
| <kbd>DELETE /api/v1/clientes/{clienteId}}</kbd>     | Deletar um cliente
| <kbd>POST /api/v1/contatos</kbd>     | Atribuir contatos a um cliente existente

<h2 id="der">📍 Diagrama Relacional (DER)</h2>

<img width="547" height="455" alt="image" src="https://github.com/user-attachments/assets/cc844caa-95b2-4b8c-80a9-a0215a56166b" />

