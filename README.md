# WashingMachineDemo
Small rest demo application with backend service to control a washing machine. 
Fat jar can be found in zip archive.

No frontend, but can be tested with curl. Examples:

// add washing machine
curl -X POST http://localhost:8080/add -d '{"id":4,"name":"Newest","state":"READY","modes":[{"name":"Cotton","temperature":90},{"name":"Delicate","temperature":30},{"name":"Silk","temperature":45}]}' -H "Content-Type: application/json" 

// delete washing machine
curl -X DELETE http://localhost:8080/delete/1

// run washing machine
curl -X PUT http://localhost:8080/run/1?mode=Cotton

// stop washing machine
curl -X PUT http://localhost:8080/stop/1

// pause washing machine
curl -X PUT http://localhost:8080/pause/1

Also you can get the list of all washing machines
http://localhost:8080/all

and the one by id, e.g.
http://localhost:8080/washingmachines/1


No possibility to add the new mode and update the current washing machine (let's pretend no admin rights there).
