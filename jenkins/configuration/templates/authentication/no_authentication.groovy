// Set the authentication strategy to SecurityRealm::NO_AUTHENTICATION (default)
import jenkins.model.*
import hudson.security.*

// Get the jenkins instance
def instance = Jenkins.getInstance()

// Deactivate the securityx
def realm = SecurityRealm.NO_AUTHENTICATION
instance.setSecurityRealm(realm)
