pipeline {
    agent any 
    tools {
        maven 'Maven 3.8.1'
        jdk 'jdk8'
    }
    stages {
        stage ('Build') {
            steps {    
                sh ' mvn clean install -DskipTests'
            }
        }
        /*stage ('Test') {
            steps {    
                sh ' mvn test'
            }
        }*/
        stage ('Build and Push docker image') {
            steps {
                sh ' docker build . -t 177.53.20.16:5001/sms-envio:${BUILD_NUMBER}'
                sh ' docker push 177.53.20.16:5001/sms-envio:${BUILD_NUMBER}'
            }
        }
        stage ('deploy develop') {
            when { expression { env.BRANCH_NAME == 'dev' } }
            steps {
                withCredentials([sshUserPrivateKey(credentialsId: 'dev', keyFileVariable: 'key')]) {
                 script {
                    def remote = [:]
                    remote.host = '177.53.20.17'
                    remote.name = 'desenvolvimento'
                    remote.user = 'desenvolvimento'
                    remote.port = 1544
                    remote.identityFile = key
                    remote.allowAnyHosts = true
                    remote.loglevel = 'SEVERE' 
                    sshCommand remote: remote, command: " echo nexus63987 | sudo -S docker pull 177.53.20.16:5001/sms-envio:${BUILD_NUMBER}"
                    sshCommand remote: remote, command: " echo nexus63987 | sudo -S docker stop sms-envio || true"
                    sshCommand remote: remote, command: " echo nexus63987 | sudo -S docker rm sms-envio || true"
                    sshCommand remote: remote, command: " echo nexus63987 | sudo -S docker container run --network intranet -v /opt/envio-sms:/opt/envio-sms -h sms-envio -d --name sms-envio -p 8083:8083 vonex/sms-envio:${BUILD_NUMBER}"
                 }
              }
            }
        }  
  }
}


