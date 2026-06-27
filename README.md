# Fluxo Caixa Api

API REST Spring Boot gerada automaticamente por FabricaIA.

## Pré-requisitos

- Java 21+
- Maven 3.9+

## Rodar localmente

```bash
mvn spring-boot:run
```

A API sobe em `http://localhost:8080`.

## Documentação interativa (Swagger UI)

Acesse: `http://localhost:8080/swagger-ui/index.html`

## Endpoints gerados

## Autenticação JWT

| Endpoint | Método | Descrição |
|----------|--------|-----------|
| `/api/auth/registrar` | POST | Cria conta com username/password |
| `/api/auth/login` | POST | Retorna token JWT |

**Admin padrão** (criado automaticamente na primeira execução):
- Username: `admin` (ou env var `ADMIN_USERNAME`)
- Password: `admin123` (ou env var `ADMIN_PASSWORD`)

Para endpoints protegidos, adicione o header: `Authorization: Bearer <token>`

| Endpoint | Métodos | Entidade |
|----------|---------|---------|
| `/api/contabancarias` | GET / POST / PUT / DELETE | CRUD de ContaBancaria |
| `/api/lancamentos` | GET / POST / PUT / DELETE | CRUD de Lancamento |
| `/api/transferencias` | GET / POST / PUT / DELETE | CRUD de Transferencia |
| `/api/previsaofinanceiras` | GET / POST / PUT / DELETE | CRUD de PrevisaoFinanceira |
| `/api/dashboard/resumo` | GET | KPIs e totais |

## Deploy no Render

1. Faça push para o GitHub
2. Conecte o repositório no [Render](https://render.com)
3. O `render.yaml` configura o serviço automaticamente

**Variáveis de ambiente para produção:**

| Variável | Descrição |
|----------|-----------|
| `DATABASE_URL` | URL JDBC do PostgreSQL (ex: `jdbc:postgresql://host:5432/db`) |
| `DATABASE_USERNAME` | Usuário do banco |
| `DATABASE_PASSWORD` | Senha do banco |
| `ADMIN_USERNAME` | Username do admin (padrão: admin) |
| `ADMIN_PASSWORD` | Senha do admin (padrão: admin123) |

## Console H2 (apenas desenvolvimento)

Acesse `http://localhost:8080/h2-console` com JDBC URL `jdbc:h2:mem:testdb`.
