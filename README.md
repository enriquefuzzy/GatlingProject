# GatlingProject

## Requirements

- Scala 3.3.0
- Maven Plugin 4.5.0
- Gatling 3.9.5

# How to Record requests

The recorder can be used to record requests that are fired when a user navigates through a web page.

## Record Network traffic in Chrome
1. Open Chrome
2. Navigate to Gatling sample page http://computer-database.gatling.io/computers
3. Make sure Network tab is recording network requests and Preserve Log is checked
4. Clear requests in Network tab of dev tools
5. Refresh page to begin recording all requests
6. Go through the flow that you wish to record
7. Download HAR file from Network tab of dev tools

## Create scala file with Recorder

Start the recorder by doing one of the following:
- In IDE right click the Recorder.scala object and select `Run 'Recorder'`
- Use Maven command `mvn gatling:recorder`

Setup Recorder configuration:
- **Recorder mode:** HAR Converter
- **HAR file:** file path to downloaded HAR file (ex: /Users/enriqueaguirre/Downloads/computer-database.gatling.io.har)
- **Package:** taken from Maven project (ex: com.gatling.tests)
- **Class name:** name of the scala file generated (ex: ComputerDatabaseSimulation)
- **Simulations folder:** file path to where the scala file will be inserted
- **DenyList:** No static resources

When the Recorder configuration is set click `Start !` and the simulation file will be created where specified

# How to execute tests

Execute all of the Gatling tests by doing one of the following:
- Right click the Engine object and click `Run 'Engine'`
- Use Maven command `mvn gatling:test`

Execute specific simulation with `mvn gatling:test -Dgatling.simulationClass=com.gatling.tests.ComputerDatabaseSimulation`
