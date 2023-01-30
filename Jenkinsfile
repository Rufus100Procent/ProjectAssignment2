pipeline {
    
     agent any

      tools {
          maven "MY_MAVEN"
      }

      stages {
          stage('clean and checkout') {
              steps {
                  sh 'mvn clean' ./backend
              }
          }

          stage('build') {
              steps {
                  echo 'building...'
                  sh 'mvn test-compile' ./backend
                  echo 'finished building'
              }
          }

          stage('test') {
              steps {
                  echo 'starting test.....'
                  sh 'mvn surefire:test' ./backend
                  echo 'finished test'
              }
          }

          stage('package') {
              steps {
                  echo 'packaging...'
                  sh 'mvn war:war' ./backend
                  
                     }
                   }
         }
}
