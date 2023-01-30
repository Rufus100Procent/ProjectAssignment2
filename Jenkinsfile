pipeline {
    
     agent any

      tools {
          maven "MY_MAVEN"
      }

  stages {
        stage('Build') {
            steps {
                sh 'mvn clean install' ./backend
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test' ./backend
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package' ./backend
            }
        }
        stage('Deploy') {
            steps {
                sh 'mvn deploy' ./backend
            }
         }
}
