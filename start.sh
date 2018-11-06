mvn clean
mvn package
echo '********************************************************************'
echo '***                   USERS CRUD APPLICATION                     ***'
echo '***                           (DEMO)                             ***'
echo '***                                                              ***'
echo '***             if you wish to launch in one action              ***'
echo '***           plese edit and use manual_start.sh file            ***'
echo '********************************************************************'
read -p 'jdbc:postgresql://[host]:[port]/[database] -> host:' host
read -p 'jdbc:postgresql://'$host':[port]/[database] -> port:' port
read -p 'jdbc:postgresql://'$host':'$port'/[database] -> database:' database
read -p 'Username:' username
read -sp 'Password:' password
cd target
java -jar users-crud-1.0.jar --db.url='jdbc:postgresql://'$host':'$port'/'$database --db.username=$username --db.password=$password
