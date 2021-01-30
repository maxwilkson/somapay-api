# Somapay API
Projeto de Teste da Somapay

## Tecnologias
    Plataforma Java 11
    Framework Spring Boot 2.4.2
    Banco de dados PostgreSQL 11

## Exemplos   
#### Para obter o token de autenticação  
    curl --location --request POST 'http://localhost:8080/oauth/token' \
    --header 'Authorization: Basic c29tYXBheTpzMG1AcEB5' \
    --form 'grant_type="password"' \
    --form 'username="user"' \
    --form 'password="123"'

#### Para consultar empresas
    curl --location --request GET 'http://localhost:8080/empresas/' \
    --header 'Content-Type: application/json' \
    --header 'Authorization: Bearer 192c15da-6383-418f-a667-2a42758c42f0'
    
#### Para cadastrar empresas  
    curl --location --request POST 'http://localhost:8080/empresas' \
    --header 'Authorization: Bearer 192c15da-6383-418f-a667-2a42758c42f0' \
    --header 'Content-Type: application/json' \
    --data-raw '{
            "nome": "Enrico e Jéssica Ferragens Ltda",
            "cnpj": "39412010000170",
            "inscricaoEstadual": "377136441",
            "dataAbertura": "2016-01-09",
            "site": "www.enricoejessicaferragensltda.com.br",
            "email": "almoxarifado@enricoejessicaferragensltda.com.br",
            "endereco": {
                "logradouro": "Rua 2",
                "numero": "836",
                "bairro": "Cohab II",
                "cidade": "Sobral",
                "estado": "CE",
                "cep": "62050680"
            },
            "contatos": [
                {
                    "tipo": "CELULAR",
                    "numero": "8839632770"
                }
            ],
            "conta": {
                "banco": "Caixa Econômica Federal",
                "agencia": 47123,
                "digitoAgencia": 0,
                "numero": 8632,
                "digitoConta": 3,
                "operacao": 158,
                "saldo": 900200.50
            },
            "funcionarios": []
        }'
        
#### Atualização parcial de campos  
    curl --location --request PATCH 'http://localhost:8080/empresas/1' \
    --header 'Authorization: Bearer 159087ab-c091-4f7d-96af-cdb5c207139e' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "site": "novosite.com.br"
    }'
    
#### Obter o saldo da conta de uma empresa  
    curl --location --request GET 'http://localhost:8080/empresas/1/conta/saldo' \
    --header 'Authorization: Bearer 159087ab-c091-4f7d-96af-cdb5c207139e'
    
#### Listar funcionários de uma empresa
    curl --location --request GET 'http://localhost:8080/empresas/1/funcionarios' \
    --header 'Authorization: Bearer 159087ab-c091-4f7d-96af-cdb5c207139e'
    
#### Cadastrar um funcionário em uma empresa
    curl --location --request POST 'http://localhost:8080/empresas/1/funcionarios' \
    --header 'Authorization: Bearer 159087ab-c091-4f7d-96af-cdb5c207139e' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "nome": "Novo Funcionário",
        "cpf": "06589916390",
        "rg": "119964752",
        "dataNascimento": "1997-11-05",
        "sexo": "MASCULINO",
        "email": "olivercalebdossantos__olivercalebdossantos@imail.com",
        "endereco": {
            "logradouro": "Rua Raquel Magalhães 69",
            "numero": "473",
            "bairro": "Centro",
            "cidade": "Deputado Irapuan Pinheiro",
            "estado": "CE",
            "cep": "63645970"
        },
        "contatos": [
            {
                "tipo": "TELEFONE",
                "numero": "8827411054"
            }
        ],
        "dataAdmissao": "2020-04-15",
        "dataDemissao": null,
        "salario": 3000.00,
        "funcao": "Vendedor",
        "conta": {
            "banco": "Banco Bradesco",
            "agencia": 9087,
            "digitoAgencia": 1,
            "numero": 7704,
            "digitoConta": 0,
            "operacao": null,
            "saldo": 4000.00
        }
    }'
    
 #### Processar a folha de pagamento de uma empresa e receber resumo
     curl --location --request POST 'http://localhost:8080/empresas/1/folha-pagamento/pagar' \
      --header 'Authorization: Bearer 159087ab-c091-4f7d-96af-cdb5c207139e'
