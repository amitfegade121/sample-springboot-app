pipeline {

    agent {
        label "linux-instance"
    }

    tools {
        maven "MAVEN"
    }

    stages {

        stage("Build and package an application") {

            steps {
                echo "Building an application using maven"
                sh 'mvn clean package'
            }
            post {
                success {
                    echo "Archiving an artifact i.e. .war file"
                    archiveArtifacts artifacts: '**/*.war', followSymlinks: false
                }
                failure {
                    echo "Failed to build an application"
                }
            }
        }

        stage("Deploy to staging and perform code analaysis") {

            parallel {
                 stage("Deploy to staging") {
                     steps {
                         echo "Copying an artifact"
                         copyArtifacts filter: '**/*.war', fingerprintArtifacts: true, projectName: 'spring-boot-app-package', selector: lastSuccessful()
                         echo "Deploying an application to staging tomcat server"
                         deploy adapters: [tomcat8(credentialsId: 'tomcat-staging-cred', path: '', url: 'http://3.15.154.255:8080')], contextPath: null, war: '**/*.war'
                     }
                     post {
                         success {
                             echo "An application is successfully deployed to staging environment."
                         }
                         failure {
                             echo "Failed to deploy an application on staging environment."
                         }
                     }
                 } 
                 stage("Perform code analysis") {

                     environment {
                         SCANNER_HOME = tool "SONARQUBE_SCANNER"
                         ORGANIZATION = "amitf-organization"
                         PROJECT_NAME = "sample-springboot-app"
                     }

                     steps {
                         echo "Performing code analysis using SonarQube"
                         withSonarQubeEnv(installationName: "SONARQUBE_SERVER", credentialsId: 'SONARQUBE_SERVER_CRED') {
                              sh '''$SCANNER_HOME/bin/sonar-scanner -Dsonar.projectKey=$PROJECT_NAME  \
                                    -Dsonar.organization=$ORGANIZATION \
                                    -Dsonar.sources=src \
                                    -Dsonar.java.binaries=target'''
                         }
                     }
                 }
            }
        }

        stage("Deploy to production") {
            steps {
                timeout(time: 1, unit: 'DAYS') {
                     input 'Do you want to deploy an application to production environment?'
                }

                 echo "Copying an artifacat"
                 copyArtifacts filter: '**/*.war', fingerprintArtifacts: true, projectName: 'spring-boot-app-package', selector: lastSuccessful()
                 echo "Deploying an application to production tomcat server"
                 deploy adapters: [tomcat8(credentialsId: 'prod-vm-cred', path: '', url: 'http://18.222.71.152:8080/')], contextPath: null, war: '**/*.war'
            }
            post {
                success {
                    echo "An application is successfully deployed to prod."
                }
                failure {
                    echo "Failed to deploy an application on prod server."
                }
            }
        }
    }
}