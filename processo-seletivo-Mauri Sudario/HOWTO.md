# Como Executar
Basta ter o programa **Docker** instalado e executar o código abaixo na raiz do projeto:
```
docker-compose up
```

Os scripts de criação e preenchimento do banco de dados serão executados automaticamente.
Caso deseje editar alguma coisa, eles se encontram na pasta **"script"**.
Os containers iniciados são o MySQL para o banco de dados e o Tomcat para o servidor de aplicação.

Depois que os containers subirem, a aplicação estará disponível no link http://localhost:8080/funcionarios/

Os testes estão disponíveis nos arquivos ++test/com/hepta/funcionarios/rest/FuncionarioServiceTest.java++ e ++test/com/hepta/funcionarios/rest/SetorServiceTest.java++.