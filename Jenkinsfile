pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'DEVL',
                    credentialsId: '',
                    url: 'ssh://git@...'

                sh "ls -la"
            }
        }
        stage('Build') {
            steps {
                echo 'Building...'
                sh "${tool name: 'sbt', type: 'org.jvnet.hudson.plugins.SbtPluginBuilder$SbtInstallation'}/bin/sbt compile"
            }
        }
        stage('Test') {
            parallel linux: {
                node('linux') {
                    steps {
                        echo 'Test...'
                        sh "${tool name: 'sbt', type: 'org.jvnet.hudson.plugins.SbtPluginBuilder$SbtInstallation'}/bin/sbt test"
                    }
                }
            }
        }
        stage('Deploy') {
            when {
                expression {
                    currentBuild.result == null || currentBuild.result == 'SUCCESS'
                }
            }
            steps {
                echo 'Deploying'
            }
        }
    }
}