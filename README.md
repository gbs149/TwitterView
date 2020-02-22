# TwitterView

Api para pesquisa de hastags no Twitter.

### Disponível em

https://twitter-view.herokuapp.com/api/twittersearch

### Query parameters

- `q`: hashtags a pesquisar

- `resultType`: tipo de resultado da pesquisa `[recent | popular | mixed]`

### Configuração

Configurar credenciais do Twitter em um arquivo twitter4j.properties

```
oauth.consumerKey=***
oauth.consumerSecret=***
oauth.accessToken=***
oauth.accessTokenSecret=***
```

ou como variáveis em ambiente local:

```
$ export twitter4j.oauth.consumerKey=***
$ export twitter4j.oauth.consumerSecret=***
$ export twitter4j.oauth.accessToken=***
$ export twitter4j.oauth.accessTokenSecret=***
```

ou no Heroku: 

```
$ heroku config:add oauth.consumerKey=***
$ heroku config:add oauth.consumerSecret=***
$ heroku config:add oauth.accessToken=***
$ heroku config:add oauth.accessTokenSecret=***
```

### TODOS

- Configurar Swagger
