pipeline {
  agent any
  stages {
    stage('env prepare') {
      steps {
        tool(name: 'maven-3.6.3', type: 'maven')
        withCredentials([usernamePassword(credentialsId: 'docker_hub', passwordVariable: 'docker_hubPassword', usernameVariable: 'docker_hubUser')]) {
            sh "docker login -u ${docker_hubUser} -p ${docker_hubPassword}"
        }
      }
    }

    stage('package') {
      steps {
        sh 'mvn clean'
        sh 'mvn compile -Dmaven.test.skip=true'
        sh 'mvn package -Dmaven.test.skip=true'
      }
    }

    stage('archive') {
      steps {
        sh 'version=`mvn help:evaluate -Dexpression=project.version -q -DforceStdout` && cp auth-service/target/auth-service-$version.jar auth-service.jar'
      }
    }

    stage('upload archive') {
      steps {
        sh 'docker build -t whatakitty/auth:latest .'
        sh 'docker push whatakitty/auth:latest'
      }
    }

  }
}
