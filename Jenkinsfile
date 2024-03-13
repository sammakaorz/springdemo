pipeline {
    
    agent any 
    
    environment {
        IMAGE_TAG = "${BUILD_NUMBER}"
    }
    
    stages {
        
        stage('Checkout'){
           steps {
                git credentialsId: 'github', 
                url: 'https://github.com/sammakaorz/springdemo',
                branch: 'main'
           }
        }

       stage('Build and Push Docker Image') {
      environment {
        DOCKER_IMAGE = "sammakaorz/mavenspringdemo:${BUILD_NUMBER}"
        REGISTRY_CREDENTIALS = credentials('docker')
      }
      steps {
        script {
            sh 'docker build -t ${DOCKER_IMAGE} .'
            def dockerImage = docker.image("${DOCKER_IMAGE}")
            docker.withRegistry("https://registry.hub.docker.com", "docker") {
                dockerImage.push()
            }
        }
      }
    } 
        stage('Checkout K8S manifest SCM'){
            steps {
                git credentialsId: 'github', 
                url: 'https://github.com/sammakaorz/springdemo.git',
                branch: 'main'
            }
        }
        
        stage('Update K8S manifest & push to Repo'){
            steps {
                script{
                    withCredentials([usernamePassword(credentialsId: 'github', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
                        sh '''
                        cat deployment.yaml
			sed -i "s/mavenspringdemo:.*/mavenspringdemo:${BUILD_NUMBER}/g" deployment.yaml
                        cat deployment.yaml
                        git add deployment.yaml
                        git commit -m 'Updated the deployment yaml | Jenkins Pipeline'
                        git remote -v
                        git push https://github.com/sammakaorz/springdemo.git HEAD:main
                        '''                        
                    }
                }
            }
        }
    }
}
