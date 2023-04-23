## GraphQL のサンプル

### 実装方法
- `Spring Initializr` で `Spring for GraphQL` と `Spring Web` を選択してダウンロードする。
- schema.graphqlsを追加する。
  - ここに GraphQL で使用するクエリの定義を書く。
- GraphQLを有効にする。
  - `spring.graphql.graphiql.enabled=true` を `application.properties` に追記する。
- Controllerを実装する。(QueryMappingとSchemaMapping)


### 使い方
GUIから使用する場合
- http://localhost:8080/graphiql にアクセスする。
- 以下のようなクエリを書いて実行ボタンを押す。

例：パラメーターがない場合
```
query foo {
  allRestaurants {
    name,
    address {
      prefecture,
      city
    },
    category {
      id,
      name
    }
  }
}
```
例：パラメーターがある場合
```
query foo {
  restaurantDetail(id: 20) {
    name,
    address {
      prefecture,
      city
    },
    category {
      id,
      name
    }
  }
}
```

Curlコマンドでアクセスする場合
- 以下のコマンドで叩く

例：パラメーターがない場合
```
curl -XPOST -H \
'Content-Type: application/json' \
'http://localhost:8080/graphql' \
-d '{"query":"query foo {\n allRestaurants {\n name\n address \n {prefecture \n city \n}\n category \n {id \n name \n}\n}\n}","operationName":"foo"}'
```

例：パラメーターがある場合
```
curl -XPOST -H \
'Content-Type: application/json' \
'http://localhost:8080/graphql' \
-d '{"query":"query foo {\n restaurantDetail(id: 20) {\n name\n address \n {prefecture \n city \n}\n category \n {id \n name \n}\n}\n}","operationName":"foo"}'
```

## graphQLについて

### 一言でいうと
1つのエンドポイントに対して、クライアントで取得したいデータをクエリによって指定することで、1度のアクセスで必要なデータを必要な分だけ取得できる手法

### メリット
- クライアント側で、どのデータを取得するかを制御でき、必要なデータだけを取得できるため、クライアント側の実装がシンプルになる。また、余分に帯域を使わずに済む。
- RESTful APIでは、取得するデータが足りない場合は、もう1度別のエンドポイントにアクセスして取得しなければならないが、GraphQLでは1度のアクセスで必要なデータを取得できる。
- エンドポイントは1つで、クエリを変えるだけで全データを取得できる。RESTful APIでは取得するデータごとにアクセスするエンドポイントを変える必要がある。

### デメリット
- RESTful APIしか触ったことのないエンジニアだと学習コストがかかる。
- N+1問題が起こりやすい。
- クエリが複雑になる。

### どういう場面で使えるか
- 中・大規模なアプリケーションで、取得するデータの種類やデータを取得するパターンが多いアプリケーション。

### その他感想
- 呼び出す側から取得したいパラメータを取得して欲しい情報だけ取れるのは便利
- スキーマ定義ファイルでエラーが出た時にどこで間違ったかエラー文から特定しづらいのがつらい。
- Controllerのアノテーションでidとそのクラス内のインスタンスをマッピングしてくれるので、1つのクラスを複数のクエリの結果で使いたい時に楽そう。
- 今回書いたサンプルだとN+1問題的なものが起きそうなので工夫が必要。
