package com.gatling.tests

import io.gatling.core.Predef.*
import io.gatling.http.Predef.*

class ComputerDatabaseSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("http://computer-database.gatling.io")
		.inferHtmlResources(AllowList(), DenyList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""))
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.9")
		.upgradeInsecureRequestsHeader("1")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")

//	val headers_0 = Map("Cache-Control" -> "max-age=0")
//
//	val headers_2 = Map(
//		"Cache-Control" -> "max-age=0",
//		"Origin" -> "http://computer-database.gatling.io")

	val scn = scenario("ComputerDatabaseSimulation")
		.exec(http("ComputerDatabasePage")
			.get("/computers"))
		.pause(1)
		.exec(http("NewComputerPage")
			.get("/computers/new"))
		.pause(1)
		.exec(http("CreateNewComputer")
			.post("/computers")
			.formParam("name", session => session("ComputerTest").as[String])
			.formParam("introduced", session => session("2023-08-01").as[String])
			.formParam("discontinued", session => session("023-08-25").as[String])
			.formParam("company", session => session("27").as[String]))
		.pause(1)
		.exec(http("FilterComputer")
			.get("/computers?f=ComputerTest"))

	// Execute test with 10 users
	setUp(scn.inject(atOnceUsers(10))).protocols(httpProtocol)
}