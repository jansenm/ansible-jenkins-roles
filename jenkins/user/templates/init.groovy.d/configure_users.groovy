// Make sure a admin user exists (if necessary)

// CONFIGURATION
def admin_user = '{{admin_user | default(jenkins_admin_user)}}'
def admin_password = '{{admin_password | default(jenkins_admin_password)}}'
def admin_fullname = '{{admin_fullname | default(jenkins_admin_fullname)}}'
def admin_email = '{{admin_email | default(jenkins_admin_email)}}'
def admin_description = '{{admin_description | default(jenkins_admin_description)}}'

import jenkins.model.*
import hudson.security.*
import hudson.tasks.Mailer

// Get the jenkins instance.
def instance = Jenkins.getInstance()

// Get the authentication and authorization strategies
authentication_strategy = instance.getSecurityRealm()
authorization_strategy = instance.getAuthorizationStrategy()

// With
// void ensure_user(username, password, fullname, email, description=none) {
// i got
// Caused by: java.lang.ClassFormatError: Illegal class name "30_configure_users$ensure_user" in class file 30_configure_users$ensure_user
//         at java.lang.ClassLoader.defineClass1(Native Method)
class Helper {
    static void ensure_user(username, password, fullname, email, description=none) {
        // Check if the user already exists
        def instance = Jenkins.getInstance()
        def user = instance.securityRealm.allUsers.find {it.id == username}

        if (user == null) {
            println "Adding user ${user}"
            user = instance.securityRealm.createAccount(username, password)
        }

        // We make sure those fields ALWAYS have the desired values and are not changed manually
        user.setFullName(fullname)
        // user.setDescription(description)
        user.addProperty(new Mailer.UserProperty(email));
    }
}


// First make sure the admin user exists (and create it if possible)
switch (authentication_strategy) {
  case HudsonPrivateSecurityRealm:
    // In this real we need to create the user.
    Helper.ensure_user(admin_user, admin_password, admin_fullname, admin_email, admin_description)
    break
  default:
    println("Authentication Strategy not handled")
    break
}

// Then make sure he is admin (if possible)
switch(authorization_strategy) {
  case GlobalMatrixAuthorizationStrategy:
  case ProjectMatrixAuthorizationStrategy:
    println("Matrix Strategy")
    authorization_strategy.add(Jenkins.ADMINISTER, "admin")
    break
  default:
    println("Authorization Strategy not handled")
    println(authorization_strategy)
}
