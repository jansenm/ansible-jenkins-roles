// {{ansible_managed}}
println("** Configuring authorization strategy no_authorization")
// Set the authorization strategy to unsecured
import jenkins.model.*
import hudson.security.*

// Get the jenkins instance
def instance = Jenkins.getInstance()

// Create the new strategy and set it
def strategy = new AuthorizationStrategy.Unsecured()
instance.setAuthorizationStrategy(strategy)
