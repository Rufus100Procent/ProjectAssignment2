pipeline {
    agent any

    tools {
        maven "MY_MAVEN"
    }

    stages {
        stage ('clean') {
            steps {
                sh 'mvn clean'
            }
            
        }
        stage ('test') {
            steps {
                sh 'mvn test'
            }
            
        }
        stage ('package') {
            steps {
                sh 'mvn package'
            }
            
        }
        stage ('deploy') {
            steps {
                sh 'mvn deploy'
            }
            
        }
    }
}