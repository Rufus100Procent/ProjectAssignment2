    pipeline {

 agent any

    tools {
        maven "MY_MAVEN"
    }

    stages {

            stage("Clone Repository") {
                 steps {
                     echo 'downloading github project...'
                     git branch: 'main', credentialsId: '1234', url: 'https://github.com/Rufus100Procent/ProjectAssignment2.git' 
                    
              }
        }

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
        stage('test'){
            steps {
                echo 'starting test.....'
                sh 'mvn surefire:test -f ./backend'
                echo 'finished test'
                sh 'pwd'
            }
             post {
                always {
                    sh 'pwd'
                    sh 'sleep 5'
                    sh './backend/target/surefire-reports/*'
                    sh 'pwd'
                }
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
 post {
        always {
        echo 'generating test report....'
        junit './backend/target/*reports/**/*.xml'
        echo 'test report generated'
        echo 'Pipeline completed'
        sh 'rm -rf ./backend/target'
        }
   success {
            echo 'The pipeline was successful'
        }
  failure {
                echo 'Pipeline failed'
            }
    }
}
