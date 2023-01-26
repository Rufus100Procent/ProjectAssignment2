pipeline {
      agent any

      tools {
          maven "3.8.7"
      }

      stages {
          stage('clean and checkout') {
              steps {
                  sh 'mvn clean'
                  echo 'downloading github project...'
                  checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Rufus100Procent/ProjectAssignment2.git']])

              }
          }

          stage('build') {
              steps {
                  echo 'building...'
                  sh 'mvn test-compile'
                  echo 'finished building'
              }
          }

          stage('test') {
              steps {
                  echo 'starting test.....'
                  sh 'mvn surefire:test'
                  echo 'finished test'
              }
          }

          stage('package') {
              steps {
                  echo 'packaging...'
                  sh 'mvn war:war'
                  echo 'packaged'
              }
          }
      }

      post {
          always {
              echo 'generating test report....'
              junit 'target/*reports/**/*.xml'
              echo 'test report generated'
          }
      }
}