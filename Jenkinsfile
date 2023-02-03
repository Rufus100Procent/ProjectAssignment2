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
                sh 'mvn surefire:test -f ./backend'
                echo 'finished test'
            }
             post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
                failure {
                    // actions to take if the build fails
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
  

}
