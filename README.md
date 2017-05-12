# runwar 簡易Webアプリケーションサーバ

## Description
dataforms.jarで作成したアプリケーションを、スタンドアローンアプリケーションの様に利用するためのツールです。  
一般的にWebアプリケーションを利用するには、Webアプリケーションサーバやデータベースサーバを用意する必要があります。  
そのため一般ユーザにはハードルが高いものになっています。  
そこでWebアプリケーションサーバとして組み込みTomcat、データベースサーバとして組み込みDerbyを利用して、Webアプリケーションを実行するrunwar.jarファイルを作成しました。  
クライアントPCでrunwar.jarを実行すると、runwar.jsonに登録されたWebアプリケーションを起動し、自動的にデフォルトブラウザでそのアプリケーションをアクセスします。  
runwar.jarを起動したPCは一時的にWebアプリケーションサーバになっているため、同一LAN内の別のPCからも起動されているWebアプリケーションを利用することができます。

## Install
Java8がインストールされたPCで、[リリース](https://github.com/takayanagi2087/runwar/releases)からrunwar_XXXX.zipをダウンロードし、適切なフォルダに展開してください。  
このパッケージにはdataforms.jarのサンプルアプリケーション dfbbsが入っています。  
展開されたフォルダ中のrunwar.jarをダブルクリックするか以下のコマンドの実行で、
簡易アプリケーションサーバを開始しデフォルトブラウザを起動します。  
  
java -jar runwar.jar
  
初回のブラウザ起動時にはdeveloperの登録画面が表示されます。  
developerのパスワードを登録すると、Apache derbyのデータベースが作成され、dfbbsが使えるようになります。  


## Requirement
Java8がインストールされたPC。 

## Licence
[MIT](https://github.com/takayanagi2087/dataforms/blob/master/LICENSE)  



