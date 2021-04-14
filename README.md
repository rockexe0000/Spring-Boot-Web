# Spring-Boot-Web



http://localhost:8081/demo/greeting


----------------------------------------------------------------------------


## 環境：

* window 10

* Docker version 20.10.2, build 2291f61

* Java 8

* Maven

* Spring Boot 2.4.4

* eclipse-jee-photon-R-win32-x86_64

* MariaDB

----------------------------------------------------------------------------

## JAVA 環境設定

1、先下載安裝 JAVA SDK 開發套件。

<https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html>

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

```
mvnw package && java -jar target/Spring-Boot-Web-0.0.1-SNAPSHOT.jar
```

http://localhost:8081/demo/greeting



在Spring Boot專案根目錄新增檔案命名為Dockerfile（不用副檔名）內容如下。Dockerfile為Docker build image的指令檔。

### Dockerfile

```
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} demo.jar
ENTRYPOINT ["java","-jar","/demo.jar"]

```

下面為Dockerfile每一行指令的簡單說明。

* FROM openjdk:8-jdk-alpine：Spring Boot image使用的base image。
* ARG JAR_FILE=target/*.jar：定義一個變數名稱為JAR_FILE，值為target/*.jar。
* COPY ${JAR_FILE} demo.jar：將build context的JAR_FILE變數的檔案複製到container檔案目錄的demo.jar
* ENTRYPOINT ["java","-jar","/demo.jar"]：在container執行java -jar /demo.jar。







接著開啟終端機(terminal)將目錄移到Spring Boot專案根目錄，也就是Dockerfile的所在目錄。在命令列輸入docker build -t spring-boot-demo .開始建構Spring Boot的docker image。


```
docker build -t spring-boot-demo .
```


docker build 為 Docker建構image的指令。
* -t 後接image的名稱，這邊命名為spring-boot-demo；
* . 意思為以當前目錄為 build context。

輸入docker images檢視建構好的 image

```
docker images
```


輸入$ docker run -p 8080:8081 --name demo spring-boot-demo啟動spring-boot-demo container。

```
docker run -p 8080:8081 --name demo spring-boot-demo
```

* -p 8080:8081 將本機的8080對映到container的8081 port；
* --name demo 將container名稱設定為demo。



列出所有 Docker 容器

```
docker ps -a
```

停止 Docker 容器

```
docker stop DOCKER_ID
```
強制停止 Docker 容器

```
docker kill DOCKER_ID
```
如果 Docker 容器當掉，可以考慮改用 kill

移除 Docker 容器

```
docker rm DOCKER_ID
```


重新啟動 Docker 容器

```
docker restart DOCKER_ID
```


### 上傳到 Docker Hub 


rockexe0000 = YOUR-USER-NAME


使用命令登錄Docker Hub 

```
docker login -u rockexe0000
```


使用docker tag 命令為 spring-boot-demo image重新命名。確保改成 YOUR-USER-NAME 您的 Docker ID。

```
docker tag spring-boot-demo rockexe0000/spring-boot-demo
```


push 到 Docker Hub

```
docker push rockexe0000/spring-boot-demo
```




進入 Image

```
docker run -ti --entrypoint /bin/sh rockexe0000/spring-boot-demo
```


進入正在執行的 container

```
docker exec -ti myapp /bin/sh
```







----------------------------------------------------------------


## MariaDB


https://ithelp.ithome.com.tw/articles/10194334


DbVisualizer Free 12.0.4





找到占用3306的程式PID


```
netstat -ano | findstr 0.0:3306
```

用 PID 找服務名稱

```
tasklist |findstr 4564
```


docker-compose.yml

```
version: '3.2'

services:
  db:
    image: "mariadb:10.5.3"
    restart: always
    ports:
      - "3306:3306"
    volumes:
      - ./db/data:/var/lib/mysql
      - ./db/initdb.d:/docker-entrypoint-initdb.d
    environment:
      MYSQL_ROOT_PASSWORD: "123456"


  adminer:
    image: adminer
    restart: always
    ports:
      - 8080:8080

```





建立 MEMBER 資料庫

```
create database MEMBER;
```


建立一個使用者,登入的密碼為123456

```
DROP USER 'T00001'@'%';

DROP USER 'T00001'@'localhost';
```



賦予T00001 MEMBER資料庫所有權限

```
CREATE USER 'T00001'@'%' IDENTIFIED BY '123456';
CREATE USER 'T00001'@'localhost' IDENTIFIED BY '123456';


GRANT ALL PRIVILEGES ON MEMBER.* TO 'T00001'@'%';
FLUSH PRIVILEGES;

GRANT ALL PRIVILEGES ON MEMBER.* TO 'T00001'@'localhost';
FLUSH PRIVILEGES;

```


建立 member_account 資料表

```
use MEMBER;

CREATE TABLE member_account (
  ID int(11) NOT NULL AUTO_INCREMENT,
  EMAIL varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  PASSWORD varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  ADDRESS varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  CELLPHONE varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  CREATE_DATE datetime DEFAULT NULL,
  PRIMARY KEY (ID)
)
```





























































----------------------------------------------------------------------------

## 參考：



### spring 官方網站

<https://spring.io/guides/topicals/spring-boot-docker>

### 菜鳥工程師 肉豬 Docker build Spring Boot docker image

<https://matthung0807.blogspot.com/2020/11/docker-build-spring-boot-docker-image.html>

### docker 官方網站

<https://docs.docker.com/get-started/>

### Docker-指令小抄

<https://mileslin.github.io/2019/04/Docker-%E6%8C%87%E4%BB%A4%E5%B0%8F%E6%8A%84/>



30天學習Spring MVC 系列

https://ithelp.ithome.com.tw/users/20107812/ironman/1538








