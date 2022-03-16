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
        stage ('Imagem docker') {
            steps {
                sh 'docker build . -t vonex/sms_envio:${BUILD_NUMBER}'
            }
        }
        stage ('Run docker') {
            steps {
                //sh ' docker stop sms-envio' 
                //sh ' docker rm sms-envio'
                sh ' docker container run --network intranet -v /opt/envio-sms:/opt/envio-sms -h sms-envio -d --name sms-envio -p 8083:8083 vonex/sms_envio:${BUILD_NUMBER}'
            }
        }        
    }
}


