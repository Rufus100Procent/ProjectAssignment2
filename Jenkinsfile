pipeline {
    agent any

    tools {
        maven "MY_MAVEN"
    }

    stages {
        stage ('clean') {
            steps {
                sh 'mvn -f ./backend/pom.xml  clean' 
            
        }
        stage ('test') {
            steps {
                sh 'mvn test' .
            }
            
        }
        stage ('package') {
            steps {
                sh 'mvn -f ./backend/pom.xml  package' 
            }
            
        }
        stage ('deploy') {
            steps {
                sh 'mvn  -f ./backend/pom.xml  deploy' 
            }
            
        }
    }
}