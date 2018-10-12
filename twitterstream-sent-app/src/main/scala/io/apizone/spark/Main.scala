
package io.apizone.spark

import java.io.Serializable
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming._
import org.apache.spark.streaming.api.java._
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.twitter.TwitterUtils
import twitter4j.Status
import twitter4j.conf._
import com.github.vspiewak.util.SentimentAnalysisUtils._

object Main extends Serializable {

  //AmazonHelp, CGTNOfficial, ChinaDaily, nytimes (BEST), AJEnglish
	def main(args: Array[String]) : Unit = {
			if (args.length < 1){
				println("Please specify the search string as argment")
				System.exit(-1)
			}
			val searchString = args mkString " "
			    val sc = new StreamingContext("local[*]", "Twitter Streaming", Seconds(5))

					System.setProperty("twitter4j.oauth.consumerKey", "okj4bWgYPuxQUxUrEedXkhfZW")
					System.setProperty("twitter4j.oauth.consumerSecret", "vyNebKVJrZI0rW60xChcOaLM7bInhOUeKPVSbNBcsPaf8WPo2v")
					System.setProperty("twitter4j.oauth.accessToken", "1049345952581148672-uFJzU48DxMiobHVZwwgMqmQNvKxdMJ")
					System.setProperty("twitter4j.oauth.accessTokenSecret", "krVy6vl77tY94m5qMlQr0XwaT00qfmVxNDw8d19DSEJNp")


					val inputDStream : ReceiverInputDStream[Status] = TwitterUtils.createStream(sc, None, Array(searchString))

					inputDStream.foreachRDD(processTweetRDD(_))

					sc.start
					sc.awaitTermination
	}

	def processTweetRDD(rdd: RDD[twitter4j.Status]) : Unit ={
			//do cl
			val results : scala.collection.Map[String, Long] = rdd.filter(_.getLang == "en").map(_.getText).map((txt: String) => {
				detectSentiment(txt).toString
			}).countByValue
					//reduce resultRDD on count by classification

					// //sample data
					// import scala.util.Random
					// val rand = new Random
					// val results = Array(("POSITIVE", rand.nextInt(30)), 
					// 	("NEGATIVE",rand.nextInt(30)), 
					// 	("NEUTRAL", rand.nextInt(20))).map(a => {
					// 		a._1 + ":" + a._2.toString}).mkString("|")

					val message = results.toArray.map((a:(String, Long)) => {
						a._1 + ":" + a._2.toString}).mkString("|")
					//send to message broker
					redis.RedisProducer.publishResults(message)
	}
}