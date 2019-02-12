pipeline {
    agent any

    stages {
        stage('Build') {
            node {
                checkout scm
            }
            steps {
                echo 'Building...'
                sh "${tool name: 'sbt', type: 'org.jvnet.hudson.plugins.SbtPluginBuilder$SbtInstallation'}/bin/sbt compile"
            }
        }
        stage('Test') {
            parallel {
                stage('Test on Linux') {
                    agent {
                        label "linux"
                    }
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