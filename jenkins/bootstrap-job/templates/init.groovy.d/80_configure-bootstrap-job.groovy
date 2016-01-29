println("** Configuring bootstrap job")
final JOB_NAME = 'bootstrap-jenkins-instance'
final JOB_COMMANDS = '''\
job('bootstrap-jenkins') {
    scm {
        git {
            remote {
                name('origin')
                url('{{git_repo}}')
            }
            branch('{{git_branch}}')
        }
    }

    steps {
        dsl {
            removeAction('DISABLE')
            external('src/main/jobs/*.groovy')
        }
    }
}
'''

import hudson.model.Cause
import hudson.model.FreeStyleProject
import jenkins.model.Jenkins
import javaposse.jobdsl.plugin.ExecuteDslScripts

def jenkins = Jenkins.instance

// Get the job to work with
def job = jenkins.getItem(JOB_NAME)
if(!job)
{
    // The job does not yet exist. Create it.
    job = new FreeStyleProject(jenkins, JOB_NAME)
}

// Basis configuration for the job
job.setDisplayName('Jenkins Instance Bootstrap job')
job.setDescription('''\
This job will bootstrap the jenkins instance by downloading job-dsl scripts from a vcs and running them
''')

// Set the log rotator
job.logRotator = new hudson.tasks.LogRotator(-1, 10, -1, 10)

// Clean out all old buildsteps
job.buildersList.clear()

// Put in out new buildstep
step = new ExecuteDslScripts(JOB_COMMANDS)
job.buildersList.add(step)

// Save changes to job and jenkins
job.save()
jenkins.reload()

