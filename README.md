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
Agrega el siguiente stage dentro del bloque stages para analizar el código con SonarQube:

<pre> <code> 
stage("SonarQube Analysis") {
    steps {
        withSonarQubeEnv('SonarQube') {
            dir("Backend") {
                bat "mvn sonar:sonar -Dsonar.projectKey=backend -Dsonar.login=${SONAR_TOKEN}"
            }
        }
    }
}
</code> </pre>
Agrega esta parte dentro del bloque environment en tu Jenkinsfile:

<pre> <code> 
environment {
    DOCKER_CREDENTIALS_ID = 'dockerhub_credentials'
    SONAR_TOKEN = credentials('sonarqube_token')
} </code> </pre>

6. Verificar el flujo
Después de un commit y ejecutar el pipeline, Jenkins enviará el análisis a SonarQube.

Los resultados se verán en el dashboard de SonarQube en http://localhost:9000.

7. Consideraciones
Si SonarQube no arranca, asegúrate de que Docker tiene al menos 2 GB de RAM asignados.

Si el puerto 9000 está en uso, cambia el puerto externo a otro, por ejemplo: docker run -d --name sonarqube -p 9001:9000 sonarqube:community

---

### ✅ PASOS PARA CONFIGURAR ZAP DAST ANALIZANDO EL FRONTEND (pasos ordenados por chat gpt y depurados por mi)

#### 📌 1. Nota previa

> ⚠️ El contenedor de ZAP solo se levanta mientras escanea, y luego se elimina automáticamente (`--rm`), tambien ZAP solo se realiza el analisis mediante front ya que simula ser un USER.

---

#### 🧱 2. Comando para traer la imagen mediante docker local

En un cmd:

docker pull ghcr.io/zaproxy/zaproxy:stable

docker run -u zap -p 9090:9090 ghcr.io/zaproxy/zaproxy:stable

---

#### ✅ 3. Agregar stage en tu Jenkinsfile

Agrega esto dentro del bloque stages al final de todo, después de levantar y desplegar:

<pre>
stage('DAST Scan with ZAP') {
    steps {
        script {
            // Ejecutar ZAP para escanear el frontend en localhost:5173
            bat '''
                echo Ejecutando ZAP para escanear el frontend en http://[TU_IP]:5173
                docker run --rm -v %cd%:/zap/wrk/:rw ghcr.io/zaproxy/zaproxy:stable zap-baseline.py -t http://TU_IP:5173 -r zap-report.html
            '''
        }
    }
}

stage('Abrir reporte ZAP (paso opcional)') {
    when {
        expression { isUnix() == false } // Solo en Windows
    }
    steps {
        bat 'start zap-report.html'
    }
}
</pre>

---

#### 📦 4. Bloque `post` al final del `Jenkinsfile` va por fuera de toda la sección de stage

<pre>
post {
    always {
        archiveArtifacts artifacts: 'zap-report.html', fingerprint: true
    }
}
</pre>

---


### 📄 ¿Dónde ver el resultado del escaneo?

1. En Jenkins, entra al **build job** correspondiente.
2. Al final de la página, en **"Artifacts"**, haz clic en `zap-report.html`.
3. Se abrirá o descargará un reporte visual en HTML con:

   * Alertas de seguridad
   * Severidad (Alta, Media, Baja)
   * Tipo (XSS, CSRF, Inyecciones, etc.)
   * URL afectadas

---

