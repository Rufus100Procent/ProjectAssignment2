pipeline {
    agent any

    tools {
        maven "MY_MAVEN"
    }

    stages {
        stage ('Clone Repository'){
            steps {
                echo 'downloading github project...'
                git branch: 'main', credentialsId: '1234', url: 'https://github.com/Rufus100Procent/ProjectAssignment2.git' 
            }
        }

         stage ('clean'){
            steps {
                dir('./backend') {
                    sh 'pwd'
                    sh 'mvn clean'
                }
            }
        }

         stage ('build'){
            steps {
                echo 'building...'
                sh 'mvn test-compile -f ./backend'
                echo 'finished building'
            }
        }

         stage ('packaging into war'){
            steps {
                sh 'mvn war:war -f ./backend'
                echo 'ROOT.war is ready to be used' 
            }
        }
        stage (){
            steps {
                sh 'pwd'
                sh 'cp ./target/ROOT.war /artifacts' 
                echo 'ROOT.war is now inside tomcat'
            }
        }

        post {
            always {
                echo 'Pipeline completed'
                sh 'ls ./backend/target/surefire-reports/*.xml'
                junit allowEmptyResults: true, testResults: 'backend/target/surefire-reports/*.xml'
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
}
