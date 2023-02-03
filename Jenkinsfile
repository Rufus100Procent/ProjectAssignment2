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
            echo 'Pipeline completed'
        }
   success {
            echo 'The pipeline was successful'
        }
  failure {
                echo 'Pipeline failed'
            }
    }
}
