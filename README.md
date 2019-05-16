# labs
Projeto criado para exercitar o uso da api de sentiment do google, a lógica consiste em consultar os tweets de um usuário e analisá-lo com a sentiment api do Google

## Setup

Para execução dos testes será necessário obter as chaves consumer key e secret key do [Tweeter](https://developer.twitter.com/en/docs/basics/authentication/guides/access-tokens.html)

Gerar o Bearer Token conforme instruções descritas [aqui](https://developer.twitter.com/en/docs/basics/authentication/overview/application-only#issuing-application-only-requests)

Também será necessário obter a API key do [Google Natural Language](https://cloud.google.com/docs/authentication/api-keys)

Ambas as chaves devem ser cadastradas no arquivo gradle.properties conforme exemplo abaixo:
```
~/.gradle/gradle.properties
authorizationKey="<BEARER>"
gcpApiKey="<API KEY>"
```
Obs.: Alternativamente as chaves podem ser adicionadas ao arquivo [build.gradle](https://github.com/tramalho/labs/blob/master/app/build.gradle#L29)

## Arquitetura

MVP + Clean Architecture, referência: https://github.com/dmytrodanylyk/android-architecture

O Presenter fica responsável pelas lógicas de apresentação, tornando a view passiva

O UseCase permite mapear quaisquers lógicas de negócio além de coordenar as chamadas aos Repositórios

Por fim o Repositório atua como um datasource sendo o responsável pela obtenção dos dados

A separação acima favorece a testabilidade e a reutilização de código

## Tecnologias Utilizadas

* [Retrofit](https://square.github.io/retrofit/) - Requisições HTTP
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - Programação assincrona
* [Constraint/Design Libraries](https://developer.android.com/topic/libraries/support-library/features) - Criação de Layout 
* [Koin](https://insert-koin.io/) - Injeção de Dependências
* [Mockito](https://site.mockito.org/) - Criação de objetos dublê para testes
* [JUNIT](https://github.com/junit-team/junit4) - Framework de testes
* [Expresso](https://developer.android.com/training/testing/espresso) - Framework de testes de UI
* [Mockwebservice](https://github.com/square/okhttp/tree/master/mockwebserver) - Mock Requisições HTTP para Testes Integrados
