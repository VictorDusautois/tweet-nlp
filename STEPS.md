1. Download & Install Java
	Download & Extract JDK 8 (jdk1.8.0_111)
	Browser ---> https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
	Download http://download.oracle.com/otn-pub/java/jdk/8u181-b13/96a7b8442fe848ef90c96a2fad6ed6d1/jdk-8u181-linux-x64.tar.gz
	tar -zxf jdk-8u181-linux-x64.tar.gz
	

2. Download Spark, sbt, Scala IDE
	Browser ---> https://spark.apache.org/downloads.html
	Select Spark Release = 2.0.2 and Package Type = Pre-built for Apache Hadoop 2.7 and later
	Download spark-2.0.2-bin-hadoop2.7.tgz
	tar -zxf spark-2.0.2-bin-hadoop2.7.tgz

	Browser ---> https://www.scala-sbt.org/
	Download https://piccolo.link/sbt-0.13.17.tgz
	tar -zxf https://piccolo.link/sbt-0.13.17.tgz

	Browser ---> http://scala-ide.org/
	Download http://downloads.typesafe.com/scalaide-pack/4.7.0-vfinal-oxygen-212-20170929/scala-SDK-4.7.0-vfinal-2.12-linux.gtk.x86_64.tar.gz
	tar -zxf scala-SDK-4.7.0-vfinal-2.12-linux.gtk.x86_64.tar.gz




3. Download & Install Redis
	Browser ---> https://redis.io/download
	Download http://download.redis.io/releases/redis-4.0.11.tar.gz
	unzip redis-4.0.11.tar.gz
	cd redis-4.0.11
	make
	src/redis-server


4. Connect to Redis CLI and Subscribe the `tweet_results` Publisher

	src/redis-cli
	SUBSCRIBE tweet_results


5. Download & Setup Maven
	Browser ---> https://maven.apache.org/download.cgi
	Download https://www-us.apache.org/dist/maven/binaries/apache-maven-3.0.5-bin.tar.gz
	tar -xzf apache-maven-3.0.5-bin.tar.gz
	cd apache-maven-3.0.5/
	

6. Set Environment Variables
	vi ~/.bash_profile

	export JAVA_HOME=/home/skhan/jdk1.8.0_111
	export SPARK_HOME=/home/skhan/spark-2.0.2-bin-hadoop2.7
	PATH=$PATH:$HOME/.local/bin:$HOME/bin:/home/skhan/jdk1.8.0_111/bin:$SPARK_HOME/bin:/home/skhan/Downloads/sbt/bin:/home/skhan/Downloads/apache-maven-3.0.5/bin

	source ~/.bash_profile


7. Setup Git
	yum install git
	ssh-keygen
	cat ~/.ssh/id_rsa.pub ---> Copy Public Key
	Login to Git ---> Profile (Top-left) ---> Settings ---> SSH and GPG Keys ---> New SSH Key --> Add the Public Key copied in above step
	

8. Clone Project Source form Git 
	git clone git@github.com:VictorDusautois/tweet-nlp.git
	cd tweet-nlp


9. Import & Build Project `twitterstream-sent-app`
	cd twitterstream-sent-app
	vi ~/.sbt/0.13/plugins/plugins.sbt
	addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.2.4")
	sbt eclipse
	
	Eclipse ---> File ---> Import ---> General ---> Existing Project into Workspace ---> Select the Project Folder


10. Run Project `twitterstream-sent-app` from Eclipse
	Right-click Project ---> Run as ---> Run Configurations ---> Specify Java Project Main Class ---> Enter Argument (Twitter Handle)


11. Run Project `twitter-analysis-dashboard` from Command Line
	cd twitter-analysis-dashboard
	mvn clean install
	java -jar target/twitter-analysis-dashboard-1.0-SNAPSHOT.jar





	


Tweet Handles:
---
AmazonHelp
CGTNOfficial 
ChinaDaily
nytimes (BEST)
AJEnglish

