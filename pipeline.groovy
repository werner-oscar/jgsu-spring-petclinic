pipeline {
    agent any

    stages {
        stage('Chekout') {
            steps {
                // Get some code from a GitHub repository
                git url: 'https://github.com/werner-oscar/jgsu-spring-petclinic.git', branch: 'main'
            }
        }
        stage('Build') {
            steps {
                sh "./mvnw clean package"
            }

            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
