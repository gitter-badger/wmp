cd RobotsEditor
sh thriftGen.sh
cd ..
sudo mvn clean
mvn install
cd SharedResources/src/main/webapp
sudo npm install
grunt
cd ../../..
sudo mvn clean
mvn install
cd ../RobotsEditor/src/main/webapp
sudo npm install
grunt
cd ../../../..
echo "Now use tomcat7 to start project"