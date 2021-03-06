# HolidayAPI
Rest API for get information from HolidayAPI
## Getting Started
Follow this instruction to run this project on your local machine
### Prerequisites
1. Maven version 3.5.4<br />
2. Java 8<br />
3. API key from https://holidayapi.com/<br />
### Installing
1.change directory to containing pom.xml path<br />
```
cd HolidayAPI/
```
2.run maven build command<br />
```
mvn clean package
```
3.change directory to target
```
cd target
```
4.run jar file
```
java -Dapi.holiday.key=$YOUR_API_KEY -jar HolidayAPI-1.0.0.jar
```
### Specification
GET Method /holiday/search<br />
Query parameter<br />
    <ul>
        <li>countries<br /> 
            <ul>
                <li>country code separated by "," </li>
                <li>maximum countries = 2</li>
                <li>Check available country at https://holidayapi.com</li>
            </ul>
        </li>
        <li>date<br />
            <ul>
                <li>date for check if any holiday at that day in format yyyy-MM-dd eg. 2016-12-26</li>
            </ul>
        </li>
    </ul>
### Testing
```
curl "http://localhost:8484/holiday/search?countries=US,NO&date=2016-12-26"
```
### Response
``` JSON
{
   "date":"2016-12-26",
   "NO":{
      "status":"SUCCESS",
      "holidays":[
         "1. juledag"
      ]
   },
   "US":{
      "status":"SUCCESS",
      "holidays":[
         "First Day of Kwanzaa",
         "Hanukkah"
      ]
   }
}
```
### My thought

   I change response because I think both countries should have their own status if it is valid data, each country are independent.It's not necessary to be valid at the same time.Also I change holidays to array because of some date like an example has more than one holiday in the same day.


