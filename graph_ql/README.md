## GraphQL のサンプル

### 実装方法
- schema.graphqlsを追加する。  
  - ここに GraphQL で使用するクエリの定義を書く。
- GraphQLを有効にする。
  - `spring.graphql.graphiql.enabled=true` を `application.properties` に追記する。


### 使い方
GUIから使用する場合
- http://localhost:8080/graphiql にアクセスする。
- 以下のようなクエリを書いて実行ボタンを押す。
```
query bookDetails {
  bookById(id: "book2") {
    id
    name
    pageCount
    author {
      id
      firstName
      lastName
    }
  }
}
```

Curlコマンドでアクセスする場合
- 以下のコマンドで叩く

```
curl -XPOST -H \
'Content-Type: application/json' \
'http://localhost:8080/graphql' \
-d '{"query":"query foo {\n  <schemaで定義されたクエリ>(<パラメータ名>: \"<値>\") {\n<取得したい項目を改行区切りで記述> \n}\n}","operationName":"foo"}'
```

例：
```
curl -XPOST -H \
'Content-Type: application/json' \
'http://localhost:8080/graphql' \
-d '{"query":"query bookDetails {\n  bookById(id: \"book1\") {\n    id\n    name\n    pageCount\n    author {\n      id\n      firstName\n      lastName\n    }\n  }\n}","operationName":"bookDetails"}'
```
