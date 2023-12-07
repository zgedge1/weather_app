pipeline {
    agent any

    environment {
        PYTHON_EXECUTABLE = 'python3'
        VENV_DIR = 'venv'
    }

    stages {
        stage('SCM') {
            steps {
                script {
                    checkout scm
                }
            }
        }

        stage('Tool Installation') {
            steps {
                script {
                    // Use the configured Python tool
                    def pythonTool = tool 'Python3'
                    env.PATH = "${pythonTool}/bin:${env.PATH}"
                }
            }
        }

        stage('Install Dependencies') {
            steps {
                script {
                    // Create a virtual environment
                    sh "${PYTHON_EXECUTABLE} -m venv ${VENV_DIR}"

                    // Activate the virtual environment
                    env.PATH = "${WORKSPACE}/${VENV_DIR}/bin:${env.PATH}"

                    // Upgrade pip in the virtual environment
                    sh "${PYTHON_EXECUTABLE} -m pip install --upgrade pip"

                    // Install dependencies
                    sh "${PYTHON_EXECUTABLE} -m pip install -r requirements.txt"
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Run your Python script within the virtual environment
                    sh "${PYTHON_EXECUTABLE} weather_app_no_gui.py"
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    def scannerHome = tool 'sonar_jenkins'
                    withSonarQubeEnv() {
                        sh "${scannerHome}/bin/sonar-scanner"
                    }
                }
            }
        }
    }

    post {
        always {
            // Clean up or do any necessary post-build tasks
            echo 'Post-build steps go here'
        }
    }
}
