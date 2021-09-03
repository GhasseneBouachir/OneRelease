
PRG="$0"
PRGDIR=`dirname "$PRG"`
TOMCAT_HOME=../$PRGDIR

java -classpath $TOMCAT_HOME/lib/hsqldb.jar org.hsqldb.util.DatabaseManagerSwing
