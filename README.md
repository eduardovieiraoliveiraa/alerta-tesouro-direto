Sejam bem-vindos !! 

Minha ideia foi criar um SCHEDULE com o objetivo de me avisar quando Titulos do Tesouro direto atingirem um valor especificado e também para avisar quando a rentabilidade do mesmo estiver sido ultrapassada.

#https://www.tesourodireto.com.br/titulos/precos-e-taxas.htm

O Projeto foi desenvolvido com base o Titulo PRE-FIXADO 2031 e IPCA+ 2029. O Investimento principal é o PRE-FIXADO 2031 que no momento é mais rentavél que o IPCA+ 2029, no entanto se a inflação subir (IPCA) o titulo PRE-FIXADO 2031 pode ser desvantajoso. A ideia é que o 
projeto nos avise quando isso acontecer.

Encontrei muitos problemas para obter uma API que retornasse os titulos publicos do tesouro direto, depois de muito pesquisar a unica forma encontrada foi através do Selenium automatizar esse processo usando a URL:
#https://www.tesourodireto.com.br/json/br/com/b3/tesourodireto/service/api/treasurybondsinfo.json

O Selenium vai abrir o Google executar a URL e retornar para o java um HTML que converto em Json e posteriormente em um Bean. 

Também sofri para obter uma consulta gratuita do IPCA acumulado dos ultimos 12 meses que é a base da nossa inflação. Consegui então uma API aberta do BCB que me retorna a inflação acumulada mes a mes.
#[https://api.bcb.gov.br/dados/serie/bcdata.sgs.433](https://api.bcb.gov.br/dados/serie/bcdata.sgs.433/dados?formato=json&dataInicial=01/09/2023)

Com todos esses dados desenvolvi uma logica para me avisar quando: 

- toda vez que o titulo pre-fixado 2031 for maior que 12% a.a;
- toda vez que o IPCA+ 2029 for mais vantajoso que o 2031 (isso ocorre quando a inflação subir muito);
- Toda vez que o indice sofre uma alterqação diferente da ultima salva no banco H2 em memoria.

  A notificação é enivada no Telegram. Para usar o projeto basta no #application.yml informar o TOKEN do seu telegram e seu chat_id.

  Nesse projeto foram usados:

  spring-boot-starter-data-jpa
  spring-boot-starter-web;
  org.seleniumhq.selenium;
  org.springframework.retry;
  org.jsoup;
  com.google.code.gson;
  org.springframework.cloud.openfeign;

  ----------------------------SCHEDULED--------------------------------------------

  configurado para rodar de segunda a sexta das 9 as 17 horas, uma vez por hora.

  -------------------------------------------------ENVIO DE MENSAGEM TELEGRAM------------

  Você irá precisar no botFather do telegram para obter seu token e um chatId, acesse https://canaltech.com.br/apps/como-criar-um-bot-no-telegram-botfather/ que tem um tutorial.

  acesse as properties do projeto e informe seu token e chatID

--------------------------------------------------DEPLOY WILDFLY ----------------------
Efetue o donwload do https://www.wildfly.org/ 

descompacte o zip em um local. ex: C:\wildfly-33.0.1.Final
acesse: C:\wildfly-33.0.1.Final\bin -> abra o terminal nesse local e execute: .\add-user.bat -> crie um usuario para logar na console de adm do wildfly http://localhost:9990/

feito isso você pode fazer o deploy do projeto. Baste abrir o terminar na pasta do projeto e executar o mvn clean packeage que irá gerar um .war do seu projeto.


