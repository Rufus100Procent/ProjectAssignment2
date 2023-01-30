pipeline {
    agent any

    tools {
        maven "MY_MAVEN"
    }

    stages {
        stage ('build') {
            steps {
                sh 'mvn clean install'
            }
            
        }
        stage ('build') {
            steps {
                sh 'mvn test'
            }
            
        }
        stage ('build') {
            steps {
                sh 'mvn package'
            }
            
        }
        stage ('build') {
            steps {
                sh 'mvn deploy'
            }
            
        }
    }
}