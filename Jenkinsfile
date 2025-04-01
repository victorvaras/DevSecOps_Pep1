pipeline {
    agent any
    tools {
        maven "maven"
    }
    environment {
            DOCKER_CREDENTIALS_ID = 'dockerhub_credentials' // ID que configuraste en Jenkins
            GITHUB_CREDENTIALS    = 'github_credencial'
        }

    stages {
        stage("Build Backend and Push Docker image") {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/victorvaras/DevSecOps_Pep1.git']])
                dir("Backend") {
                    bat "mvn clean install"
                    script {
                        docker.withRegistry('', DOCKER_CREDENTIALS_ID) {
                        bat "docker build -t victorvaraspro/tingeso-backend:latest ."
                        bat "docker push victorvaraspro/tingeso-backend:latest"
                        }
                    }
                }
            }
        }

        stage("Test") {
            steps {
                dir("Backend") {
                    bat "mvn test"
                }
            }
        }

        stage("Build Frontend and push docker image") {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/victorvaras/DevSecOps_Pep1.git']])
                dir("Frontend") {
                    bat "npm install"
                    bat "npm run build"
                    script {
                        docker.withRegistry('', DOCKER_CREDENTIALS_ID) {
                        bat "docker build -t victorvaraspro/tingeso-frontend:latest ."
                        bat "docker push victorvaraspro/tingeso-frontend:latest"
                        }
                    }
                }
            }
        }

        stage("Deploy Docker Containers") {
            steps {
                script {
                    docker.withRegistry('', DOCKER_CREDENTIALS_ID) {
                        echo "Deploying Backend Container..."

                        // Verificar si el contenedor backend-container ya existe
                        def backendExists = bat(script: "docker ps -a -q -f name=backend-container", returnStdout: true).trim()
                        if (backendExists) {
                            echo "Stopping and removing existing backend-container..."
                            bat "docker stop backend-container || true"
                            bat "docker rm backend-container || true"
                        }

                        // Ejecutar el contenedor del backend con variables de entorno
                        bat "docker run -d --name backend-container -e DB_HOST=192.168.100.10 -p 8090:8090 victorvaraspro/tingeso-backend:latest"
                             
                            
                        

                        echo "Deploying Frontend Container..."

                        // Verificar si el contenedor frontend-container ya existe
                        def frontendExists = bat(script: "docker ps -a -q -f name=frontend-container", returnStdout: true).trim()
                        if (frontendExists) {
                            echo "Stopping and removing existing frontend-container..."
                            bat "docker stop frontend-container || true"
                            bat "docker rm frontend-container || true"
                        }

                        // Ejecutar el contenedor del frontend
                        bat """
                            docker run -d --name frontend-container \
                            -p 5173:80 victorvaraspro/tingeso-frontend:latest
                        """
                    }
                }
            }
        }

    }
}