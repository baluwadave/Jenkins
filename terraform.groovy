pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                // Checkout the repository containing the Terraform code
                git branch: 'main', url: 'https://github.com/baluwadave/Terraform_infra.git'
            }
        }

        stage('Terraform Init') {
            steps {
                script {
                    // Initialize Terraform
                    sh 'terraform init'
                }
            }
        }

        stage('Terraform Validate') {
            steps {
                script {
                    // Validate Terraform configuration files
                    sh 'terraform validate'
                }
            }
        }

        stage('Terraform Plan') {
            steps {
                script {
                    // Generate and display the execution plan
                    sh 'terraform plan -out=tfplan -input=false'
                }
            }
        }

        stage('Terraform Apply') {
            steps {
                script {
                    // Apply the Terraform plan
                    sh 'terraform apply -input=false tfplan'
                }
            }
        }
    }

    post {
        always {
            // Clean up the workspace after the build
            cleanWs()
        }
        success {
            echo 'Terraform applied successfully!'
        }
        failure {
            echo 'Terraform apply failed.'
        }
    }
}
