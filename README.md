# CEP API - Sistema de Cadastro de Usuários

Esta é uma API REST desenvolvida com Spring Boot para o gerenciamento de usuários e seus respectivos endereços. O sistema integra-se com serviços de busca de CEP e possui validações rigorosas de formato e integridade de dados.

## 🚀 Tecnologias Utilizadas

* **Java 21**: Utilizando as últimas funcionalidades da linguagem.
* **Spring Boot 4.0.5**: Framework base para a construção da API.
* **Spring Data JPA**: Para abstração da camada de persistência.
* **H2 Database**: Banco de dados em memória utilizado para o ambiente de testes.
* **Oracle Database**: Suporte para persistência em ambiente de produção.
* **Maven**: Gerenciador de dependências e build.
* **JUnit 5 & Mockito**: Frameworks utilizados para a suíte de testes unitários.

## 🧪 Suíte de Testes

O projeto foi desenvolvido seguindo boas práticas de testes para garantir a confiabilidade das regras de negócio.

### Testes Unitários de Serviço
Focados no **CadastroService**, validando:
* **Mecanismo de Validação de CEP**: Garante que apenas CEPs com 8 dígitos numéricos sejam aceitos.
* **Isolamento com Mockito**: As dependências externas (Repository e CEPService) são simuladas para testar puramente a lógica de negócio.

### Testes de Repositório (Data JPA)
Utilizando `@DataJpaTest` para validar a integração com o banco de dados H2:
* Validação de métodos customizados de busca (`existsByEmail`, `existsByTelefone`).
* Testes de persistência e integridade dos dados.

## 🛠️ Instruções de Execução

Siga os passos abaixo para rodar o projeto e os testes em sua máquina local.

### Pré-requisitos
* JDK 21 instalado.
* Maven instalado (opcional, pois o projeto inclui o `mvnw`).

### 1. Clonar o repositório
```bash
git clone [https://github.com/seu-usuario/cep-api.git](https://github.com/seu-usuario/cep-api.git)
cd cep-api
