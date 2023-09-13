## SpringBootでdatasourceを動的に切り替えるサンプル

### 実装のポイント
- application.yml にDatasourceの設定値を書く。
- application.ymlで設定したプロパティを保存するオブジェクトとそれを利用したDatasourceのBeanの設定を用意する。
  ([DataSourceConfig.java](routing_datasource/src/main/java/routing_datasource/conf/DataSourceConfig.java))
  ここで指定したBean名がDatasourceを切り替える際に使われる。
- AbstractRoutingDataSourceを継承したクラスを用意する。オーバーライドしたdetermineCurrentLookupKey()で返すBean名がDatasourceとして使われる。
　そのため、determineCurrentLookupKey()で動的にDatasourceを決定するロジックを実装する。
- Datasourceの決定に使用する値は、スレッドセーフである必要があるが、同時にどのオブジェクトからもアクセスできなければならない。
  そのため、LocalThreadのstaticな変数を用意し、そこに保持することにした。
  ([TargetDatasource.java](routing_datasource/src/main/java/routing_datasource/TargetDatasource.java))
- DatasourceのBeanをカスタムで作成した場合は、sqlSessionFactoryの設定も自分で書く必要がある。
  ([SqlSessionFactoryConfig.java](routing_datasource/src/main/java/routing_datasource/conf/SqlSessionFactoryConfig.java))
