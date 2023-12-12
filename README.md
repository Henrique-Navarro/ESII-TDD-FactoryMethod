# Projeto Loja de Peixes

## Descrição do Projeto

Este projeto consiste em uma aplicação de loja de peixes, desenvolvida em Java utilizando o framework Spring Boot. A aplicação simula uma loja virtual que oferece diferentes tipos de peixes para compra. Cada tipo de peixe possui características específicas, como peso, expectativa de vida, temperamento, entre outros.

## Padrão de Projeto Factory Method

O padrão de projeto Factory Method foi aplicado para a criação dos diferentes tipos de peixes. A classe `FishFactory` atua como um Factory Method, permitindo a criação dinâmica de instâncias de peixes com base no tipo requisitado.

## Engenharia de Software

### Metodologia

O projeto foi desenvolvido seguindo os princípios da Engenharia de Software, utilizando boas práticas de desenvolvimento, modularização e padrões de projeto. A metodologia ágil foi adotada para iterativamente desenvolver e testar o software.

### Test-Driven Development (TDD)

O desenvolvimento foi guiado por Test-Driven Development (TDD). Foram criados testes unitários para cada funcionalidade antes da implementação, garantindo a qualidade do código e a verificação contínua das funcionalidades.

## Testes

### Testes Unitários

Foram desenvolvidos testes unitários utilizando JUnit e Mockito para testar cada funcionalidade do sistema. Os testes abrangem desde a criação de peixes até a atualização e remoção de informações de peixes e usuários.

### Testes com Spring Boot

A aplicação utiliza o Spring Boot Test Framework para testes de integração, garantindo que todos os componentes estejam funcionando corretamente em conjunto.

## Execução do Projeto

Para executar o projeto localmente, siga as etapas abaixo:

1. Clone o repositório: `git clone https://github.com/Henrique-Navarro/ESII-TDD-FactoryMethod/edit/main/README.md.git`
2. Execute a aplicação no diretório raiz: `mvn spring-boot:run`

O projeto estará rodando na porta 8080.
