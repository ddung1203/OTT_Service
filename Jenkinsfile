pipeline {
  agent any
  
  // docker 라이브러리 로드
  options {
    dockerChk()
  }

  environment {
  dockerHubRegistry = 'ddung1203/realmytrip'
  dockerHubRegistryCredential='dockerhub'
  githubCredential='github'
  gitEmail='jeonjungseok1203@gmail.com'
  gitName='Joongseok Jeon'
  }

  stages {
    stage('Checkout Application Git Branch') {
      steps {
        checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: githubCredential, url: 'https://github.com/ddung1203/OTT_Service.git']]])
      }
      // steps 가 끝날 경우 실행한다.
      // steps 가 실패할 경우에는 failure 를 실행하고 성공할 경우에는 success를 실행한다.
      post {
        failure {
          echo 'Repository Clone Failure' 
          // slackSend (color: '#FF0000', message: "FAILED: Repository Clone Failure")
        }
        success {
          echo 'Repository Clone Success' 
          // slackSend (color: '#0AC9FF', message: "SUCCESS: Repository Clone Success")
        }
      }
    }

    stage('Docker Image Build') {
      steps {
        script{
          // 도커 이미지를 빌드하며 빌드한 횟수에 따라 순차적으로 증가하는 젠킨스 자체 변수를 태그로 자동 지정한다.
          def dockerImage = docker.build dockerHubRegistry
      }
       
      }
      // 성공, 실패 시 Slack에 알림 오도록 설정
      post {
        failure {
          echo 'Docker image build failure'
          // slackSend (color: '#FF0000', message: "FAILED: Docker Image Build '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")     
        }
        success {
          echo 'Docker image build success'
          // slackSend (color: '#0AC9FF', message: "SUCCESS: Docker Image Build '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
        }
      }
    }  

    stage('Docker Image Push') {
      steps {
        script{
          // 젠킨스에 등록한 크리덴셜로 도커 허브에 이미지 push
          docker.withRegistry('', credentialsId: dockerHubRegistryCredential) {
          
            app.push("latest")
            // 10초 후에 다음 작업을 이어나가도록 함
            sleep 10
          } 
        }
      }
      

      post {
        failure {
          echo 'Docker Image Push failure'
          // sh "docker rmi ${dockerHubRegistry}:${currentBuild.number}"
          //sh "docker rmi ${dockerHubRegistry}:latest"
          // slackSend (color: '#FF0000', message: "FAILED: Docker Image Push '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
        }
        success {
          echo 'Docker Image Push success'
          // sh "docker rmi ${dockerHubRegistry}:${currentBuild.number}"
          // sh "docker rmi ${dockerHubRegistry}:latest"
          // slackSend (color: '#0AC9FF', message: "SUCCESS: Docker Image Push '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
        }
      }
    }

    stage('Kubernetes Manifest Update') {
      steps {
        // git 계정 로그인, 해당 레포지토리의 main 브랜치에서 클론
        git credentialsId: githubCredential,
            url: 'https://github.com/ddung1203/OTT_Service.git',
            branch: 'main'  

        // 이미지 태그 변경 후 메인 브랜치에 push
        sh "git config --global user.email ${gitEmail}"
        sh "git config --global user.name ${gitName}"
        sh "sed -i 's/realmytrip:.*/realmytrip:${currentBuild.number}/g' argocd/values.yaml"
        sh "git add ."
        sh "git commit -m 'fix:${dockerHubRegistry} ${currentBuild.number} image versioning'"
        sh "git branch -M main"
        sh "git remote remove origin"
        sh "git remote add origin git@github.com:ddung1203/OTT_Service.git"
        sh "git checkout main"
        sh "git push -u origin main"
      }
      post {
        failure {
          echo 'Kubernetes Manifest Update failure'
          slackSend (color: '#FF0000', message: "FAILED: Kubernetes Manifest Update '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
         }
        success {
          echo 'Kubernetes Manifest Update success'
          slackSend (color: '#0AC9FF', message: "SUCCESS: Kubernetes Manifest Update '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
          }
      }
    }
  } 
}