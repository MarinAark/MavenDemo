pipeline {
    agent any

    tools {
        maven 'Maven_3.9.9'  // 请确保这个名字和你在 Jenkins 全局工具配置中的 Maven 名称一致
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build & Test') {
            steps {
                sh 'mvn -v'           // 打印 Maven 版本确认配置成功
                sh 'mvn clean test'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
