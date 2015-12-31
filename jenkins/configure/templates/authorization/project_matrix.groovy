// {{ansible_managed}}
println("** Configuring authorization strategy project_matrix")
// Set the authorization strategy to
import jenkins.model.*
import hudson.security.*

// Get the jenkins instance
def instance = Jenkins.getInstance()

// Create the new strategy and set it
def strategy = new ProjectMatrixAuthorizationStrategy()
instance.setAuthorizationStrategy(strategy)
