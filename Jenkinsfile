pipeline {
    agent any

    tools {
        maven "MY_MAVEN"
    }

    stages {
        stage ('clean') {
            steps {
                dir('./backend') {
                    sh 'mvn clean'
                }
        }
        }
        stage ('test') {
            steps {
                sh 'mvn test -f ./backend' 
                sh 'pwd'
            }
            
        }
         stage ('packagin in to war') {
            steps {
                sh 'mvn war:war -f ./backend' 
                sh 'pwd'
            }
            
        }
        stage ('deploy') {
            steps {
                dir('./backend'){
                    sh 'pwd'
                    sh 'cp ./target/ROOT.war /artifacts' 
                    sh 'pwd'
                }
            }
            
        }
    }
}
