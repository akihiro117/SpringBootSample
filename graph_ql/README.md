## GraphQL のサンプル

### 実装方法
- schema.graphqlsを追加する。  
  - ここに GraphQL で使用するクエリの定義を書く。
- GraphQLを有効にする。
  - `spring.graphql.graphiql.enabled=true` を `application.properties` に追記する。


### 使い方
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