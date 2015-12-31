// {{ansible_managed}}
println("** Configuring authentication strategy hudson_private")
// Set the authentication strategy to HudsonPrivateSecurityRealm.
import jenkins.model.*
import hudson.security.*

// Get the jenkins instance
def instance = Jenkins.getInstance()

// Create the new security realm and activate it
def realm = new HudsonPrivateSecurityRealm(false)
instance.setSecurityRealm(realm)
