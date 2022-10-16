pipeline {
  agent any
  
  environment {
  dockerHubRegistry = 'ddung1203/realmytrip'
  dockerHubRegistryCredential='docker_hub'
  githubCredential='git_hub'
  gitEmail='jeonjungseok1203@gmail.com'
  gitName='Joongseok Jeon'
  }

  stages {
    stage('Checkout Application Git Branch') {
      steps {
        checkout([$class: 'GitSCM', branches: [[name: '*/jeonj']], extensions: [], userRemoteConfigs: [[credentialsId: githubCredential, url: 'https://github.com/ddung1203/OTT_Service.git']]])
      }
      // steps 가 끝날 경우 실행한다.
      // steps 가 실패할 경우에는 failure 를 실행하고 성공할 경우에는 success를 실행한다.
      post {
        failure {
          echo 'Repository clone failure' 
          //slackSend (color: '#FF0000', message: "FAILED: Repository clone failure")
        }
        success {
          echo 'Repository clone success' 
          //slackSend (color: '#0AC9FF', message: "SUCCESS: Repository clone success")
        }
      }
    }

    stage('Docker Image Build') {
      steps {
        // 도커 이미지를 빌드하며 빌드한 횟수에 따라 순차적으로 증가하는 젠킨스 자체 변수를 태그로 자동 지정한다.
        sh "docker build ./realmytrip -t ${dockerHubRegistry}:${currentBuild.number}"
        sh "docker build ./realmytrip -t ${dockerHubRegistry}:latest"
      }
      // 성공, 실패 시 Slack에 알림 오도록 설정
      post {
        failure {
          echo 'Docker image build failure'
          //slackSend (color: '#FF0000', message: "FAILED: Docker Image Build '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")     
        }
        success {
          echo 'Docker image build success'
          //slackSend (color: '#0AC9FF', message: "SUCCESS: Docker Image Build '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
        }
      }
    }  

    stage('Docker Image Push') {
      steps {
        // 젠킨스에 등록한 크리덴셜로 도커 허브에 이미지 push
        withDockerRegistry(credentialsId: dockerHubRegistryCredential, url: '') {
          sh "docker push ${dockerHubRegistry}:${currentBuild.number}"
          sh "docker push ${dockerHubRegistry}:latest"
          // 10초 후에 다음 작업을 이어나가도록 함
          sleep 10
        } 
      }

      post {
        failure {
          echo 'Docker Image Push failure'
          sh "docker rmi ${dockerHubRegistry}:${currentBuild.number}"
          sh "docker rmi ${dockerHubRegistry}:latest"
          //slackSend (color: '#FF0000', message: "FAILED: Docker Image Push '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
        }
        success {
          echo 'Docker Image Push success'
          sh "docker rmi ${dockerHubRegistry}:${currentBuild.number}"
          sh "docker rmi ${dockerHubRegistry}:latest"
          //slackSend (color: '#0AC9FF', message: "SUCCESS: Docker Image Push '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
        }
      }
    }

    stage('K8s Manifest Update') {
      steps {
        // git 계정 로그인, 해당 레포지토리의 main 브랜치에서 클론
        git credentialsId: githubCredential,
            url: 'https://github.com/ddung1203/OTT_Service.git',
            branch: 'jeonj'  

        // 이미지 태그 변경 후 메인 브랜치에 push
        sh "git config --global user.email ${gitEmail}"
        sh "git config --global user.name ${gitName}"
        sh "sed -i 's/realmytrip:.*/realmytrip:${currentBuild.number}/g' argocd/values.yaml"
        sh "git add ."
        sh "git commit -m 'fix:${dockerHubRegistry} ${currentBuild.number} image versioning'"
        sh "git branch -M jeonj"
        sh "git remote remove origin"
        sh "git remote add origin git@github.com:ddung1203/OTT_Service.git"
        sh "git checkout jeonj"
        sh "git push -u origin jeonj"
      }
      post {
        failure {
          echo 'K8s Manifest Update failure'
          //slackSend (color: '#FF0000', message: "FAILED: K8S Manifest Update '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
         }
        success {
          echo 'K8s Manifest Update success'
          //slackSend (color: '#0AC9FF', message: "SUCCESS: K8S Manifest Update '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
          }
      }
    }
  } 
}