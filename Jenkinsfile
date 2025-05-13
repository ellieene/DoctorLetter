pipeline {
    agent any

    environment {
        IMAGE_NAME = "ellieene/letter"
    }

    stages {
        stage('Clone') {
            steps {
                git branch: 'main', credentialsId: 'Git', url: 'https://github.com/ellieene/DoctorLetter.git'
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    docker.build("${IMAGE_NAME}").push("latest")
                }
            }
        }

        stage('Deploy to Minikube') {
            steps {
                sh 'kubectl apply -f k8s/deployment.yaml'
                sh 'kubectl apply -f k8s/service.yaml'
            }
        }
    }
}