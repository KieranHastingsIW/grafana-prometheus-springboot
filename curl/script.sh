#!/bin/sh
curl -i -X GET \
        --url http://kong:8001/
status=$?
echo  ${status}
while [ ${status} -ne 0 ]
do
    sleep 1
    echo ${status}
    curl -i -X GET --url http://kong:8001/
    status=$?
done  


curl -i -X POST \
--url http://kong:8001/services/ \
--data name=GetAllUsers \
--data 'url=http://hello-world:8080/user/users'
curl -i -X POST \
--url http://kong:8001/services/GetAllUsers/routes/ \
    -H "Content-Type: application/json" \
    -d '{"name": "allUsers", "methods":["GET"], "hosts":["localhost"], "paths":["/users"]}'


curl -i -X POST \
--url http://kong:8001/services/ \
--data name=CreateUser \
--data 'url=http://hello-world:8080/user/create'
curl -i -X POST \
--url http://kong:8001/services/CreateUser/routes/ \
    -H "Content-Type: application/json" \
    -d '{"name": "create", "methods":["POST"], "hosts":["localhost"], "paths":["/user"],"headers":{"Content-Type":["application/json"]}}'


curl -i -X POST \
--url http://kong:8001/services/ \
--data name=DeleteUser \
--data 'url=http://hello-world:8080/user/delete'
curl -i -X POST \
--url http://kong:8001/services/DeleteUser/routes/ \
    -H "Content-Type: application/json" \
    -d '{"name": "delete", "methods":["DELETE"], "hosts":["localhost"], "paths":["/user"]}'



curl -i -X POST \
--url http://kong:8001/services/ \
--data name=UpdateUser \
--data 'url=http://hello-world:8080/user/update'
curl -i -X POST \
--url http://kong:8001/services/UpdateUser/routes/ \
    -H "Content-Type: application/json" \
    -d '{"name": "update", "methods":["PUT"], "hosts":["localhost"], "paths":["/user"],"headers":{"Content-Type":["application/json"]}}'
    

curl -i -X POST \
--url http://kong:8001/services/ \
--data name=GetUser \
--data 'url=http://hello-world:8080/user'
curl -i -X POST \
--url http://kong:8001/services/GetUser/routes/ \
    -H "Content-Type: application/json" \
    -d '{"name": "user", "methods":["GET"], "hosts":["localhost"], "paths":["/user"]}'