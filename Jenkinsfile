pipeline{

    agent any

    tools{
        maven "maven"
    }

    stages{

        stage("SCM Checkout"){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/abhishekkrsrivastava/user-service-ms.git']])
            }

        }
        stage("Build Stage Maven"){
            steps{
                script{
                    bat 'mvn clean install -Dmaven.test.skip=true'
                }
            }
        }
        stage("Build Docker Image"){
            steps{
                script{
                    bat 'docker build -t user-service-ms:1.2 .'
                }
            }
        }
        stage("Tag the Image"){
            steps{
                script{
                    bat 'docker tag user-service-ms:1.2 abhishekvanaras/user-service:1.4'
                }
            }
        }
        stage("Docker Login"){
            steps{
                script{
                   withCredentials([string(credentialsId: '238f4a00-e40f-4dc9-8a11-917560fe2704', variable: 'dockerCredential')]) {
                   bat 'docker login -u abhishekvanaras -p ${dockerCredential}'
                   }
                }
            }
        }
        stage("Docker Push"){
            steps{
                script{
                    bat 'docker push abhishekvanaras/user-service-ms:1.4'
                }
            }
        }

    }

    post{
        always{
           emailext attachLog: true, body: '''<html>
<body>
<p> Build Status: ${BUILD_STATUS}</p>
<p> Build Number: ${BUILD_NUMBER}</p>
<p> Check the <a href="${BUILD_URL}"> Console Output</a></p>
</html>
</body>''', mimeType: 'text/html', replyTo: 'abhishekvanaras@gmail.com', subject: 'PipeLine Status: ${BUILD_NUMBER}', to: 'abhishekvanaras@gmail.com'
        }
    }
}


// SCM CHECKOUT, BUILD , DEPLOY, NOTIFICATION