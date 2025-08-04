# 図書館アプリ（学習用）

>このプロジェクトは、Vue.js + Spring Boot + MyBatis を使った図書館管理システムの学習用教材です。 
> Vue.jsとSpring Bootの連携、認証、CRUD操作などを一通り体験できます。

## 🔧 搭載している機能
- ✅ ログイン機能（Spring Security）
- 📝 サインアップ（新規ユーザー登録）
- 🔍 本の検索
- ➕ 本の追加
- ✏️ 本の編集

## 🚀 使用技術

| 分類         | 技術スタック                                      |
|--------------|--------------------------------------------------|
| Frontend     | Vue.js / axios / Bootstrap / Font Awesome        |
| Backend      | Spring Boot / MyBatis                            |
| Database     | H2（インメモリ）                                 |
| Build Tool   | Gradle（依存管理・ビルド・起動を一括管理）       |
| Template     | Thymeleaf / Layout Dialect                       |
| Security     | Spring Security / BCrypt                         |


## 🛠️ セットアップ手順

> ⚠️ **このプロジェクトは Gradle を使用しています。Maven では起動できません。**

### 1 リポジトリをクローン

```bash
git clone https://github.com/your-repo/library-app.git
cd library-app
```

### 2 起動方法

##  🚀 ターミナルから起動する場合（Git Bashなど
```bash
./gradlew bootRun
```

##  🧑‍💻 IDE（IntelliJ IDEA / Eclipse）から起動する場合
- プロジェクトを Gradleプロジェクトとしてインポート
- DemoApplication.java を右クリック → 「Run」すればOK！
> このプロジェクトは、Gradleによって依存管理・ビルド・起動が一貫して行われています。  
> npmやMavenなどの追加セットアップは不要で、Spring Boot単体で完結する構成です。  
> フロントエンドのライブラリ（Vue.js / axiosなど）も WebJars を通じて統合されています。

## 🌐 アクセスURL
アプリ起動後、以下のURLにアクセスしてください： 

http://localhost:8080/login
> ログイン画面が表示されます。  
> デフォルトユーザー情報は下記をご参照ください。


### 👤 デフォルトユーザー情報（お試し用)
> ⚠️ **注意：この管理者ユーザー情報はデモ目的で掲載されています。**  
> 本番環境では、セキュリティ上の理由からこうした情報の公開は**絶対に避けてください**。

### 🧑‍ 一般ユーザー

| 項目       | 内容               |
|------------|--------------------|
| Email      | `taro@example.com` |
| Password   | `test123T`         |
| 権限       | 一般ユーザー       |

### 👩‍💼 管理者ユーザー

| 項目       | 内容               |
|------------|--------------------|
| Email      | `hanako@example.com` |
| Password   | `test123T`         |
| 権限       | 管理者             |
