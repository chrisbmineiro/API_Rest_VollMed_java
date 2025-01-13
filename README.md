# API Rest Vol.Med

## Descrição
Projeto de uma API para gerenciamento de médicos e pacientes utilizando Spring Boot com agendamentos e cancelamento de consultas, seguindo as seguintes regras de negócio no quadro de tarefas do [Trello](https://trello.com/b/pjOSNuQo/api-voll-med).

## Tecnologias Utilizadas
- Java
- Spring Boot
- Maven
- MySQL

## Configuração do Projeto

### Dependências
Adicione a seguinte dependência no `pom.xml` para a documentação da API:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.6.14</version>
</dependency>
```

### Configuração do Banco de Dados
Para configurar o banco de dados, altere as propriedades no arquivo `application.properties`:

```properties
spring.datasource.url=${DATASOURCE_URL}
spring.datasource.username=${DATASOURCE_USERNAME}
spring.datasource.password=${DATASOURCE_PASSWORD}

springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```
### Documentação da API
A documentação da API pode ser acessada após iniciar a aplicação em:

```
http://localhost:8080/swagger-ui.html
```
### Testes
Os testes estão localizados nos seguintes arquivos:
```
src/test/java/med/voll/api/controller/MedicoControllerTest.java
src/test/java/med/voll/api/repository/MedicoRepositoryTest.java
```
**Exemplo de Teste de Controlador**
```java
@Test
@DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
@WithMockUser
void cadastrar_cenario1() throws Exception {
    var response = mvc
            .perform(post("/medicos"))
            .andReturn().getResponse();

    assertThat(response.getStatus())
            .isEqualTo(HttpStatus.BAD_REQUEST.value());
}
```
**Exemplo de Teste de Repositório**
```java
@Test
@DisplayName("Deve escolher um médico aleatório com agenda livre, caso contrario deveria retornar null")
void escolherMedicoAleatorioComAgendaLivreCenario1() {
    // given/arrange
    var proximaSegundaAs10 = LocalDate.now()
            .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
            .atTime(10, 0);

    var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
    var paciente = cadastrarPaciente("Paciente", "paciente@email.com", "00000000000");
    cadastrarConsulta(medico, paciente, proximaSegundaAs10.minusHours(1)); // Ensure the doctor is free at 10:00
    // when/act
    var medicoLivre = medicoRepository.escolherMedicoAleatorioComAgendaLivre(Especialidade.CARDIOLOGIA, proximaSegundaAs10);
    // then/assert
    assertThat(medicoLivre).isNotNull();
}
```
### Executando a Aplicação
Para executar a aplicação, utilize o comando:
```
mvn spring-boot:run
```
### Contribuição
Para contribuir com o projeto, faça um fork do repositório e envie um pull request com suas alterações.