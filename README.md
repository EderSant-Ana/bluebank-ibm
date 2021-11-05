# Bluebank IBM #
**Contribuidores:** atomita, EderSant-Ana, GuuMoraes, milazangirolame
**Tags:** java, springboot, maven  
**Requisítos mínimos:** Maven 3.2+ , Java 11  
**Versão estável:** 1.0.0  
**Licensa:** GPLv2 ou posterior  
**Url Licensa:** http://www.gnu.org/licenses/gpl-2.0.html  

Projeto Gama Academy + IBM

## Description ##

O [Bluebank](https://app.swaggerhub.com/apis-docs/BlueBank83/BlueBank/1.0.0) é um banco online onde você efetua o cadastro de clientes e recebe pagamentos online por meio de transferências entre contas. 
Também é possível adicionar transferênia do tipo "depósito" no caso de transferências de saldos oriundos de contas correntes de outros bancos. 

### Instalação ###
Usando shell: 
Clone o projeto na sua máquina
Acesse a pasta do projeto e rode o comando 
```
sh mvnw package
```
Com isso o maven irá bluidar o porjeto e se tudo correr bem, um arquivo .jar será criado. Para rodar o projeto basta utilizá-lo:

```
java -jar target/bluebank-1.0.0.jar
```
usando o maven: 
```
mvn package 
```
Após o arquivo .jar criado basta rodar utilizando o java -jar : 
```
java -jar target/bluebank-1.0.0.jar
```

Atenção!
Caso tenha problemas em extrair o arquivo .jar com o maven verifique se as versões no arquivo pom.xml e na sua variável de ambiente JAVA_HOME são as mesmas. 
Caso seja necessário, você poderá apontar a versão correta por exemplo: 

Para o arquivo pom.xml equivalente
```
<properties>
  <java.version>11</java.version>
  <maven.compiler.source>1.8</maven.compiler.source>
  <maven.compiler.target>1.8</maven.compiler.target>
</properties>``

```
sete o valor
```
set JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/
```

### Documentação ###
![Captura de tela de 2021-11-05 09-48-57](https://user-images.githubusercontent.com/32459266/140512750-e21e69ef-a81d-44c3-a307-9f3b4cc82b38.png)

Você pode esclarecer suas dúvidas usando:

* A nossa [Documentação oficial Bluebank](https://app.swaggerhub.com/apis-docs/BlueBank83/BlueBank/1.0.0).

* Para questões técnicas você poderá criar um tópico/issue neste reposítório.



## Changelog ##

### 1.0.0 ###

* Versão incial do app.

