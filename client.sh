#!/bin/bash

client=tool-rental-client

# Make the following API requests to
# Add the Brands table
curl -X POST http://localhost:8080/brands/addCollection -H 'Content-Type: application/json' -d '[{"name":"Stihl","abbreviation":"S"},{"name":"DeWalt","abbreviation":"D"},{"name":"Werner","abbreviation":"W"},{"name":"Ridgid","abbreviation":"R"}]'

# Add the ToolTypes table
curl -X POST http://localhost:8080/tooltypes/addCollection -H 'Content-Type:application/json' -d '[{"name":"Chainsaw","prefix":"CHN","rate":{"id":"CHN","dailyCharge":1.49,"hasWeekdayCharge":true,"hasWeekendCharge":false,"hasHolidayCharge":true}},{"name":"Ladder","prefix":"LAD","rate":{"id":"LAD","dailyCharge":1.99,"hasWeekdayCharge":true,"hasWeekendCharge":true,"hasHolidayCharge":false}},{"name":"Jackhammer","prefix":"JAK","rate":{"id":"JAK","dailyCharge":2.99,"hasWeekdayCharge":true,"hasWeekendCharge":false,"hasHolidayCharge":false}}]'

# Add the Tools
curl -X POST http://localhost:8080/tools/add -d toolType="JAK" -d toolCode="JAKD" -d brand="DeWalt"
curl -X POST http://localhost:8080/tools/add -d toolType="CHN" -d toolCode="CHNS" -d brand="Stihl"
curl -X POST http://localhost:8080/tools/add -d toolType="LAD" -d toolCode="LADW" -d brand="Werner"
curl -X POST http://localhost:8080/tools/add -d toolType="JAK" -d toolCode="JAKR" -d brand="Ridgid"


# Now run the client app
cd $client && npm install && npm run dev