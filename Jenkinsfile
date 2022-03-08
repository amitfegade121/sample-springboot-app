pipeline {

    agent {
        label "linux-instance"
    }

    tools {
        maven "MAVEN"
    }

    environment {
        REGISTRY = "amitfegade121/sample-springboot-app"
        REGISTRY_CREDETIALS = "DockerHub_Cred"
        dockerImage = ""
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

        stage("Build a docker image") {
             steps {
                 script {
                     dockerImage = docker.build REGISTRY + ":$BUILD_NUMBER"
                 }
             }
        }

        stage("Push docker image") {
            steps {
                script {
                    docker.withRegistry("", REGISTRY_CREDETIALS) {
                         dockerImage.push()
                         dockerImage.push("latest")
                    }
                }                
            }
        }

        stage("Remove unused images") {
            steps {
                sh '''docker rmi $REGISTRY:$BUILD_NUMBER
                      docker rmi $REGISTRY:latest'''
            }
        }

        stage("Deploy to staging and perform code analaysis") {

            parallel {
                 stage("Deploy to staging") {
                      steps {
                          script {
                              def remote = [:]
                              remote.name = 'staging-vm'
                              remote.host = '172.31.18.71'                              
                              remote.allowAnyHosts = true
                              withCredentials([usernamePassword(credentialsId: 'Staging_VM_Cred', passwordVariable: 'password', usernameVariable: 'username')]) {
                                     remote.user =  username
                                     remote.password = password  
                                     sshCommand remote: remote, command: "docker container run -d -p 8080:8080 amitfegade121/sample-springboot-app"
                              }
                          }
                      } 
                      post {
                          success {
                              echo "An application is successfully deployed to staging."
                          }
                          failure {
                              echo "Failed to deploy an application on staging env"
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
                    script {
                       def remote = [:]
                       remote.name = 'prod-vm'
                       remote.host = '172.31.21.76'                              
                       remote.allowAnyHosts = true
                       withCredentials([usernamePassword(credentialsId: 'Prod_VM_Cred', passwordVariable: 'password', usernameVariable: 'username')]) {
                            remote.user =  username
                            remote.password = password  
                           sshCommand remote: remote, command: "docker container run -d -p 8080:8080 amitfegade121/sample-springboot-app"
                    }
                }
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