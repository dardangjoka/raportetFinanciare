pipeline {
    agent any

    environment {
        // Define your Maven and Java environment variables
        JAVA_HOME = "/usr/lib/jvm/java-17-openjdk-amd64"
        M2_HOME = "/usr/share/maven"
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
                sh "nohup mvn spring-boot:run &"
                // Optional: Wait for the application to start

                echo "Application started successfully!"

            }
        }
    }

    post {
        success {
            echo "Build and deployment successful!"
        }
        failure {
            echo "Build or deployment failed!"
        }
    }
}
