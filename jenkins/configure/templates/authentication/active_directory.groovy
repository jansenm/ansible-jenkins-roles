// {{ansible_managed}}
println("** Configuring authentication strategy active_directory")

// Set the authentication strategy to ActiveDirectorySecurityRealm
import jenkins.model.*
import hudson.plugins.active_directory.*

// Get the jenkins instance
def instance = Jenkins.getInstance()

def strategy = GroupLookupStrategy.AUTO
switch ('{{active_directory_groupLookupStrategy}}'.toLowerCase()) {
    case 'recursive':
        strategy = GroupLookupStrategy.RECURSIVE
        break
    case 'chain':
        strategy = GroupLookupStrategy.CHAIN
        break
    default:
        // keep auto
        break
}

// Create the new security realm and activate it
def realm = new ActiveDirectorySecurityRealm(
    '{{active_directory_domain}}',
    '{{active_directory_site}}',
    '{{active_directory_bindName}}',
    '{{active_directory_bindPassword}}',
    null,
    strategy)
instance.setSecurityRealm(realm)
