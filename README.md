Desafio Spring.
---
Instruções:
1. Baixar o repositório.
2. Importar o repositório pelo IntelliJ utilizando o maven.
3. Importar no Postman Spring-Challenge.postman_collection.json
4. (Opcional) Utilizar o endpoint (POST) /products/create para gerar dados de teste com a data relativa (1,2,3 semanas atrás), no caso de dúvidas ver considerações

---

Estado inicial do banco de dados:

8 usuários, 1 - 5 compradores e 6 - 8 vendedores. \
Ninguém segue ninguém

---
Considerações:
* Todos os endpoints seguem o padrão do arquivo de requisitos técnicos funcionais, tanto para requests quanto para responses.
* Foi interpretado que um vendedor, por também ser um usuário, pode seguir e ser seguido, e um comprador só pode seguir.
* O projeto foi feito utilizando arquivos como banco de dados, esses arquivos estão em /resources/jsons
* Existem dois endpoints extras para suporte, em /users/create e /products/create , eles servem para a criação de dados pré definidos para teste. Leia abaixo \

> /users/create gera 8 usuários, do 1 ao 5 são compradores e do 6 ao 8 são vendedores \

*/users/create não é necessário utilizar, o estado inicial do banco de dados já possuí esses registros*

> /products/create gera 4 posts: 2 posts do usuário 6 de 1 semana atrás, 1 post do usuário 7 de 2 semanas atrás, 1 post do usuário 8 de 3 semanas atrás. \

---
Caso seja necessário o reset do banco de dados, basta limpar o conteúdo dos arquivos na pasta /resource/jsons, sem excluír os arquivos em sí. \
E logo após utilizar o endpoint /users/create, visto que a aplicação não possuí outra forma de criar usuários.