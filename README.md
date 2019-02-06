# HolidayAPI
Rest API for get information from HolidayAPI
## Getting Started
Follow this instruction to run this project on your local machine
### Prerequisites
1.Maven version 3.5.4<br />
2.Java 8<br />
3.API key from https://holidayapi.com/<br />
### Installing
1.cd to pom.xml path<br />
```
cd HolidayAPI/
```
2.run this command<br />
```
mvn clean package
```
3.cd to target directory
```
cd target
```
4.run jar file
```
java -Dapi.holiday.key=$YOUR_API_KEY -jar HolidayAPI-1.0.0.jar
```
### Testing
```
curl "http://localhost:8484/holiday/search?countries=US,TH&date=2016-12-26"
```
