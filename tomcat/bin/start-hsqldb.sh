
if [ -z "$2" ]; then
  echo "You have to specify the database name and the port to use, in this way:"
  echo "	$ $0 mydatabase-db 1777"
  exit 1
fi

PRG="$0"
PRGDIR=`dirname "$PRG"`
TOMCAT_HOME=../$PRGDIR

java -classpath $TOMCAT_HOME/lib/hsqldb.jar org.hsqldb.Server -database $TOMCAT_HOME/data/$1 -port $2 -silent true -trace false
