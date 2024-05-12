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
        stage("Deploy to Container"){
            steps{
                deploy adapters: [tomcat9(credentialsId: 'tomcat2', path: '', url: 'http://localhost:8181/')], contextPath: 'user-service-ms', war: '**/*.war'
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