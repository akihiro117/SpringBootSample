## GraphQL のサンプル

### 実装方法
- `Spring Initializr` で `Spring for GraphQL` と `Spring Web` を選択してダウンロードする。
- schema.graphqlsを追加する。  
  - ここに GraphQL で使用するクエリの定義を書く。
- GraphQLを有効にする。
  - `spring.graphql.graphiql.enabled=true` を `application.properties` に追記する。


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

## 感想
- 呼び出す側から取得したいパラメータを取得して欲しい情報だけ取れるのは便利
- スキーマ定義ファイルでエラーが出た時にどこで間違ったかエラー文から特定しづらいのがつらい。
- controllerのアノテーションでidとクラス内のインスタンスをマッピングしてくれるので、同じクラスのインスタンスを別のクエリの結果で使いたいというような時に楽そう。
- 今回書いたサンプルだとN+1問題的なものは起きそうなので工夫が必要そう。
