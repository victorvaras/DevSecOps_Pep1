# DevSecOps_Pep1

Como correr SonarQube para SAST por ChatGPT

Paso a paso para integrar SonarQube en Jenkins:
1. Instalar Docker y SonarQube
Asegúrate de tener Docker instalado.

Levanta SonarQube en un contenedor Docker:

bash
Copiar
Editar
docker pull sonarqube:community
docker run -d --name sonarqube -p 9000:9000 sonarqube:community
Esto hará que SonarQube esté accesible en http://localhost:9000.

2. Acceder a SonarQube
Abre http://localhost:9000 en tu navegador.

Login: admin, contraseña: admin (te pedirá cambiarla la primera vez).

3. Generar un Token para Jenkins
En SonarQube, ve a My Account > Security.

En Generate Tokens, pon un nombre (ej: jenkins-token) y presiona Generate.

Copia el token generado (lo necesitarás en Jenkins).

4. Configurar SonarQube en Jenkins
Ve a Jenkins: Manage Jenkins > Configure System.

Busca SonarQube servers y agrega la siguiente configuración:

Nombre: SonarQube

URL: http://localhost:9000

Authentication Token: selecciona el token que creaste.

Guarda la configuración.

5. Actualizar el Jenkinsfile
Abre tu Jenkinsfile y agrega el siguiente stage para analizar el código con SonarQube:

groovy
Copiar
Editar
stage("SonarQube Analysis") {
    steps {
        withSonarQubeEnv('SonarQube') {
            dir("Backend") {
                bat "mvn sonar:sonar -Dsonar.projectKey=backend -Dsonar.login=${SONAR_TOKEN}"
            }
        }
    }
}
En la sección environment de tu Jenkinsfile, agrega:

groovy
Copiar
Editar
environment {
    DOCKER_CREDENTIALS_ID = 'dockerhub_credentials'
    SONAR_TOKEN = credentials('sonarqube_token')
}
6. Verificar el flujo
Después de un commit y ejecutar el pipeline, Jenkins enviará el análisis a SonarQube.

Los resultados se verán en el dashboard de SonarQube en http://localhost:9000.

7. Consideraciones
Si SonarQube no arranca, asegúrate de que Docker tiene al menos 2 GB de RAM asignados.

Si el puerto 9000 está en uso, cambia el puerto externo a otro, por ejemplo: docker run -d --name sonarqube -p 9001:9000 sonarqube:community
