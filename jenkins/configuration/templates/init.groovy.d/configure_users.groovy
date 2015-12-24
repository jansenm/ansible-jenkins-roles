// {{ansible_managed}}
// Make sure a admin user exists (if necessary)

// CONFIGURATION
def admin_user = '{{admin_user | default(jenkins_admin_user)}}'
def admin_password = '{{admin_password | default(jenkins_admin_password)}}'
def admin_fullname = '{{admin_fullname | default(jenkins_admin_fullname)}}'
def admin_email = '{{admin_email | default("")}}'
def admin_description = '{{admin_description | default(jenkins_admin_description)}}'

import hudson.model.*
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
            println("Adding user ${user}")
            user = instance.securityRealm.createAccount(username, password)
        }

        // We make sure those fields ALWAYS have the desired values and are not changed manually. But we NEVER change
        // the password. You are supposed to change it after the first boot.
        user.setFullName(fullname)
        // user.setDescription(description)
        user.addProperty(new Mailer.UserProperty(email));
    }
}


println("** Creating the configured users")
switch (authentication_strategy) {
  case HudsonPrivateSecurityRealm:
    {% for user in users %}
    println("   * creating user {{user.id}}")
    Helper.ensure_user(
        "{{user.id}}",
        "{{user.password}}",
        "{{user.fullname}}",
        "{{user.email}}",
        "{{user.description | default('No description given')}}"
    )
    {% endfor %}
    break
  default:
    println("   * can not create users. authentication strategy not supported.")
    break
}

println("** Giving permissions to configured users.")
switch(authorization_strategy) {
  case GlobalMatrixAuthorizationStrategy:
  case ProjectMatrixAuthorizationStrategy:
    {% for user in users %}
      println("    * configuring permissions for user {{user.id}}")
      {% for permission in user.permissions %}
      permission = Permission.fromId("{{permission}}")
      if (permission)
        {
        authorization_strategy.add(permission, "{{user.id}}")
        }
      else
        {
        println(permission)
        println("      ! Unknown permission {{permission}}")
        }
      {% endfor %}
    {% endfor %}
    break
  default:
    println("   * can not configure permissions. authorization strategy not supported.")
}
