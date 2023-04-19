node {
    stage('Checkout') {
        git url: 'git@github.com:projet-edecision/Edecision.git', branch: 'main', credentialsId: 'githubSSH'
        sh 'git submodule update --init --recursive'
    }

    stage('Update Submodules') {
        sh 'git submodule update --remote --merge --recursive'
        sh 'git add .'
        sh 'git commit -m "Update submodules" || true'  // Permet d'éviter une erreur si aucune modification n'est détectée
        sh 'git push --set-upstream origin main'
    }
}
