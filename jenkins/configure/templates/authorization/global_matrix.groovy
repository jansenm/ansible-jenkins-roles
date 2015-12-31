// {{ansible_managed}}
println("** Configuring authorization strategy global_matrix")
// Set the authorization strategy to
import jenkins.model.*
import hudson.security.*

// Get the jenkins instance
def instance = Jenkins.getInstance()

// Create the new strategy and set it
def strategy = new GlobalMatrixAuthorizationStrategy()
instance.setAuthorizationStrategy(strategy)
