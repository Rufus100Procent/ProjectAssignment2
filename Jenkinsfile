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
                    sh 'rm -rf ./target'
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
            }
        }

        stage ('packagin in to war') {
            steps {
                sh 'mvn war:war -f ./backend' 
            }
            
        }
      stage('Publish JUnit Reports') {
            steps {
                junit './backend/target/surefire-reports/*.xml'
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
