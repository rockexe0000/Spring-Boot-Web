# Spring-Boot-Web




----------------------------------------------------------------------------

# JAVA 環境設定

1、先下載安裝 JAVA SDK 開發套件。

https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

2、在作業系統變數下新增三個參數： 示範版本為 JDK 1.8.0_201

變數：JAVA_HOME
值：C:\Program Files\Java\jdk1.8.0_201\bin;

變數：PATH
值：%JAVA_HOME%\bin (如果原本就有參數就加;加後面)

變數：CLASSPATH
值：C:\Program Files\Java\jdk1.8.0_201\lib;

PS：其實只要設定PATH就可以了！

3、設定好電腦要重新開機。

4、測試，在命令提示字元下 javac 命令。 (驗證設定是否正確！)

輸入 java -version 後按 ENTER
輸入 javac -version 後按 ENTER
查看輸出訊息是否包含正確版本資訊。

----------------------------------------------------------------

//去掉数据库依赖
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringBootWebApplication {



// 在目錄下執行
mvnw package && java -jar target/Spring-Boot-Web-0.0.1-SNAPSHOT.jar


http://localhost:8080/greeting













