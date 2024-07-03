# Teste Luiza Labs

### Descrição do projeto

O objetivo deste projeto realizar a leitura de 2 arquivos onde contem os pedidos e os mesmos serão armazenados em um Banco H2 e posteriormente serão exibidos via api conforme demanda.

### Pré-requisitos
Antes de começar, certifique-se de que você tenha as seguintes dependências instaladas em seu ambiente de desenvolvimento:


| Dependência | Versão  | Link                                                                                                                    | 
|-------------|---------|-------------------------------------------------------------------------------------------------------------------------|
| Java 17     | 17      | [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)                                 |
| Maven       | 3.8.8 + | [Maven](https://maven.apache.org/download.cgi])                                                                         |

## Configuração do Projeto

1. Clone o repositório para o seu ambiente de desenvolvimento:

   ```bash
   git clone https://github.com/lucianobuttarello/testekan.git
   ```

2. Navegue até o diretório raiz do projeto:

   ```bash
   cd testekan
   ```

3. Compile o projeto e baixe as dependências usando o Maven:

   ```bash
   mvn install
   ```

4. Swagger - http://localhost:8080/swagger-ui/index.html#/
   Após subir o projeto obter o token atraves do endpoint login
   passando usuario admin e senha 123, conforme curl
  curl -X 'POST' \
    'http://localhost:8080/api/login' \
    -H 'accept: */*' \
    -H 'Content-Type: application/json' \
    -d '{
    "login": "admin",
    "senha": "123"
  }'

   ![image](https://github.com/lucianobuttarello/testekan/assets/52016220/00ef0e51-4d7a-4e8b-8ed3-3ab9e8bb50f4)

5. Após obter o Token colar no Authorize
6. ![image](https://github.com/lucianobuttarello/testekan/assets/52016220/d11226b9-9405-4d7f-8636-2bf8626f8d46)
![image](https://github.com/lucianobuttarello/testekan/assets/52016220/70f57713-346e-4f04-9b7d-97e129abd930)

7. Cadastrar um novo Beneficfiario:
curl -X 'POST' \
  'http://localhost:8080/api' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiIiLCJzdWIiOiJhZG1pbiIsImlkIjoxLCJleHAiOjE3MjAwMDQ4NzV9.38ciBL9ouvklFyJeUdth5j-7F9oFYRfBYWhQCJuFLR8' \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "Ricardo",
  "telefone": "23234234234",
  "dataNascimento": "2020-07-03",
  "documentos": [
    {
      "descricao": "RG",
      "codigo": "234"
    }
  ]
}'
 ![image](https://github.com/lucianobuttarello/testekan/assets/52016220/df40c6be-6a5c-4efc-89bb-3bfe716fca27)

9. Consultar um Beneficiario por filtro
curl -X 'POST' \
  'http://localhost:8080/api/filtro' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiIiLCJzdWIiOiJhZG1pbiIsImlkIjoxLCJleHAiOjE3MjAwMDY2NTR9.-F4HMEPK1sLsnKpfd2WhT7SNwOIJIiMhtjpm3AWoD9s' \
  -H 'Content-Type: application/json' \
  -d '{
  "dataInicio": "2024-07-03",
  "dataFim": "2024-07-03",
  "nome": "Ricardo",
  "pagina": 0,
  "tamanho": 10
}'

![image](https://github.com/lucianobuttarello/testekan/assets/52016220/b0216f5d-fd17-4687-b62f-7afddbebdb9a)


10. Alterar um Beneficiario
curl -X 'PUT' \
  'http://localhost:8080/api/1' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiIiLCJzdWIiOiJhZG1pbiIsImlkIjoxLCJleHAiOjE3MjAwMDQ4NzV9.38ciBL9ouvklFyJeUdth5j-7F9oFYRfBYWhQCJuFLR8' \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "Ricardo",
  "telefone": "11123123123",
  "dataNascimento": "2000-07-03",
  "documentos": [
    {
      "descricao": "pass",
      "codigo": "34555"
    }
  ]
}'
![image](https://github.com/lucianobuttarello/testekan/assets/52016220/38196aae-3924-4436-862a-f6870d9a4382)

11. Deletar um Beneficiario
    curl -X 'DELETE' \
  'http://localhost:8080/api/1' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiIiLCJzdWIiOiJhZG1pbiIsImlkIjoxLCJleHAiOjE3MjAwMDY2NTR9.-F4HMEPK1sLsnKpfd2WhT7SNwOIJIiMhtjpm3AWoD9s'
![image](https://github.com/lucianobuttarello/testekan/assets/52016220/d35f0200-12d3-4935-9256-d0381463cac9)
   



   
