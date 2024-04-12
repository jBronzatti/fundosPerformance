# Projeto Rentabilidade Mensal

O Projeto Rentabilidade Mensal é uma aplicação Spring Boot desenvolvida para calcular a rentabilidade mensal com base em dados de entrada e gerar um relatório de saída em formato de texto.

## Estrutura do Projeto

Este projeto é construído usando o framework Spring Boot, facilitando a configuração e a execução da aplicação. Os principais componentes incluem:

- **`RentabilidadeMensalApplication.java`**: Classe principal que inicia a aplicação.
- **`RentabilidadeMensalService.java`**: Serviço que contém a lógica para calcular a rentabilidade mensal.
- **`resources/`**: Pasta contendo os arquivos necessários, incluindo o arquivo de entrada `rentabilidades.txt` e onde o arquivo de saída `saida.txt` será criado.

## Configuração do Projeto

Para executar este projeto, você precisará:

- Java JDK 22
- Maven 3.6 ou superior

## Executando o Projeto

Siga estes passos para executar o projeto localmente:

1. Clone o repositório para sua máquina local.
2. Navegue até a pasta do projeto via terminal.
3. Execute o comando `mvn clean install` para construir o projeto. Isso baixará todas as dependências necessárias e compilará o projeto.
4. Após a construção, execute o comando `java -jar target/RentabilidadeMensal-0.0.1-SNAPSHOT.jar` para iniciar a aplicação.

## Arquivos de Entrada e Saída

- **Entrada (`rentabilidades.txt`)**: Este arquivo deve estar localizado na pasta `src/main/resources`. Deve conter dados formatados com datas e valores de rentabilidade, separados por ponto e vírgula (`;`).
- **Saída (`saida.txt`)**: Após a execução do programa, o arquivo de saída será criado na pasta `src/main/resources`. Este arquivo contém a rentabilidade mensal acumulada, ordenada do maior para o menor valor.

## Dependências

- **Spring Boot Starter**: Fornece todas as configurações e dependências básicas necessárias para iniciar uma aplicação Spring Boot.
- **Spring Boot Starter Test**: Oferece suporte para testes com frameworks como JUnit, Hamcrest e Mockito.

## Licença

Este projeto é distribuído sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.
