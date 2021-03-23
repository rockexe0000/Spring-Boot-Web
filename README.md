# Spring-Boot-Web




## Docker build Spring Boot docker image
https://matthung0807.blogspot.com/2020/11/docker-build-spring-boot-docker-image.html




----------------------------------------------------------------------------

## JAVA 環境設定

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




----------------------------------------------------------------

## Docker build

我們可以在沒有Docker容器的情況下（即在主機OS中）運行應用程序：

在目錄下執行
> mvnw package && java -jar target/Spring-Boot-Web-0.0.1-SNAPSHOT.jar


http://localhost:8080/greeting


接著開啟終端機(terminal)將目錄移到Spring Boot專案根目錄，也就是Dockerfile的所在目錄。在命令列輸入docker build -t spring-boot-demo .開始建構Spring Boot的docker image。


> docker build -t spring-boot-demo .

docker build 為 Docker建構image的指令。
-t 後接image的名稱，這邊命名為spring-boot-demo；
. 意思為以當前目錄為 build context。

### 輸入docker images檢視建構好的 image
> docker images

### 列出所有 Docker 容器
> docker ps

### 停止 Docker 容器
> docker stop DOCKER_ID

### 強制停止 Docker 容器
> docker kill DOCKER_ID

如果 Docker 容器當掉，可以考慮改用 kill


### 重新啟動 Docker 容器
> docker restart DOCKER_ID
















































































































































