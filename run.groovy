pipeline {
    agent any

    environment {
        // Define your Maven and Java environment variables
        JAVA_HOME = "/usr/lib/jvm/java-17-openjdk-amd64"
        MAVEN_HOME = "/usr/share/maven"
    }

    stages {

        stage('Build') {
            steps {
                // Maven clean and install
                sh "${M2_HOME}/bin/mvn clean install"
            }
        }

        stage('Start Application') {
            steps {
                // Start the Spring Boot application
                sh "nohup ${M2_HOME}/bin/mvn spring-boot:run > app.log 2>&1 &"
                // Optional: Wait for the application to start
                script {
                    def appUrl = 'http://localhost:8081'
                    timeout(time: 3, unit: 'MINUTES') {
                        waitUntil {
                            def response = sh(script: "curl -sL -w %{http_code} ${appUrl} -o /dev/null", returnStdout: true).trim()
                            echo "Waiting for app to start..."
                            return response == '200'
                        }
                    }
                    echo "Application started successfully!"
                }
            }
        }
    }

    post {
        always {
            // Cleanup steps, stop the application or any other cleanup tasks
            sh "${M2_HOME}/bin/mvn spring-boot:stop"
        }
        success {
            echo "Build and deployment successful!"
        }
        failure {
            echo "Build or deployment failed!"
        }
    }
}
