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
                   echo 'packaged'
                }
            }
          stage('Deploy') {
            steps {
                sh 'docker-compose -f docker-compose.yml up -d --build'
                sh 'rm -rf ./webapps'
                sh 'git pull'
                sh 'docker cp ROOT.war ./webapps:/usr/local/tomcat/webapps'
                sh 'docker exec tomcat /usr/local/tomcat/bin/catalina.sh run'            }
            }

}
