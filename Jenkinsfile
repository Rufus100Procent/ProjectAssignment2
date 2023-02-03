pipeline {
    agent any

    tools {
        maven "MY_MAVEN"
    }

    stages {
        stage ('clean') {
            steps {
                dir('./backend') {
                    sh 'pwd'
                    sh 'mvn clean'
                }
        }
        }
         stage('build') {
            steps {
                echo 'building...'
                sh 'mvn test-compile -f ./backend'
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
          post {
                dir ('./backend'){
                echo 'generating test report....'
                sh 'pwd'
                junit './target/surefire-reports/TEST-*xml'
                echo 'test report generated'
                echo 'The pipeline has finished'
        }
          }
         stage ('packagin in to war') {
            steps {
                sh 'mvn war:war -f ./backend' 
            }
            
        }
        stage ('deploy') {
            steps {
                dir('./backend'){
                    sh 'pwd'
                    sh 'cp ./target/ROOT.war /artifacts' 
                }
            }
            
        }

       
        
    }
      success {
            echo 'The pipeline was successful'
        }
        failure {
            sh 'pwd'
            echo 'The pipeline failed'
        }
}
