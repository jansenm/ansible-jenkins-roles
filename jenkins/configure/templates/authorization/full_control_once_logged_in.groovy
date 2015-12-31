// {{ansible_managed}}
println("** Configuring authorization strategy full_control_once_logged_in")
// Set the authorization strategy to full_control_once_logged_in
import jenkins.model.*
import hudson.security.*

// Get the jenkins instance
def instance = Jenkins.getInstance()

// Create the new strategy and set it
def strategy = new Unsecured()
instance.setAuthorizationStrategy(strategy)
