pipeline {
    agent any

    tools {
        maven "MY_MAVEN"
    }

    stages {
        stage ('build') {
            steps {
                sh 'mvn clean install' ./backend
            }
            
        }
        stage ('build') {
            steps {
                sh 'mvn test' ./backend
            }
            
        }
        stage ('build') {
            steps {
                sh 'mvn package' ./backend
            }
            
        }
        stage ('build') {
            steps {
                sh 'mvn deploy' ./backend
            }
            
        }
    }
}