echo 'Greetings'
echo 'This is Demo CRUD application based on Spring Boot and Apache Wicked'
echo 'If you wish to start manualy, here is example for terminal(located in target folder):' 
echo 'java -jar users-crud-1.0.jar --db.url=jdbc:postgresql://localhost:5432/users_db --db.username=admin --db.password=admin'
echo ''
echo 'Please enter jdbc-url to your PostgreSQL database[like "jdbc:postgresql://localhost:5432/users_db"]:'
read url
echo 'Username:'
read username
read -sp 'Password:' password
cd target
java -jar users-crud-1.0.jar --db.url=$url --db.username=$username --db.password=$password
