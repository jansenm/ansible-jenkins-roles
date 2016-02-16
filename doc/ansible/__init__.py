import docutils
import docutils.nodes
import docutils.parsers.rst
import docutils.parsers.rst.directives
import sphinx.addnodes
import sphinx.application
import sphinx.directives
import sphinx.domains
import sphinx.environment
import sphinx.locale
import sphinx.roles
import sphinx.util.compat
import sphinx.util.docfields
import sphinx.util.nodes


class AnsibleRoleRole(sphinx.roles.XRefRole):
    def process_link(self, env, refnode, has_explicit_title, title, target):
        return title, target


class AnsibleRoleDirective(sphinx.directives.ObjectDescription):
    # :BUG: Something is wrong (just a test)

    required_arguments = 1

    doc_field_types = [
        sphinx.util.docfields.GroupedField(
            'default',
            label=sphinx.locale.l_('Defaults'),
            names=('default', 'default')
        ),
        sphinx.util.docfields.Field(
            'dependency',
            label=sphinx.locale.l_('Dependencies'),
            names=('dependency', 'depend'),
            rolename='role',
            bodyrolename='role'
        ),
        sphinx.util.docfields.TypedField(
            'parameter',
            label=sphinx.locale.l_('Parameters'),
            names=('param', 'parameter', 'arg', 'argument'),
            typerolename='role',
            typenames=('type',)
        ),
        sphinx.util.docfields.Field(
            'become',
            label=sphinx.locale.l_('Uses become'),
            names=('become')
        )
    ]

    option_spec = {
        'noindex': docutils.parsers.rst.directives.flag
    }

    has_content = True

    def handle_signature(self, sig: str, signode: sphinx.addnodes.desc_signature):
        (ns, _, rolename) = sig.rpartition('/')
        signode += sphinx.addnodes.desc_annotation('role', 'Role ')
        signode += sphinx.addnodes.desc_addname(ns, "{ns} ".format(ns=ns))
        signode += sphinx.addnodes.desc_name(rolename, rolename)
        return 'role-' + sig

    def add_target_and_index(self, name, sig, signode):
        targetname = name
        signode['ids'].append(targetname)
        self.env.domaindata['ansible']['roles'][name] = (self.env.docname, name)
        self.state.document.note_explicit_target(signode)


class AnsibleDomain(sphinx.domains.Domain):
    """Ansible domain"""

    name = "ansible"

    label = "Ansible"

    object_types = {
        'role': sphinx.domains.ObjType(sphinx.locale.l_('role'), 'role')
    }

    directives = {
        'role': AnsibleRoleDirective
    }

    roles = {
        'role': AnsibleRoleRole()
    }

    initial_data = {
        "roles": {}
    }

    def clear_doc(self, doc):
        for name in self.data['roles']:
            if doc == self.data['roles'][name][1]:
                del self.data['roles'][name]

    def get_objects(self):
        for docname, name in self.data['roles'].values():
            yield name, name, 'role', docname, 'role-' + name, 1

    def resolve_xref(self, env, fromdocname, builder,
                     type, target, node, contnode):

        print(target)
        if (type == "role"):
            for (docname, name) in self.data['roles'].values():
                if name == target:
                    print("Yes")
                    return sphinx.util.nodes.make_refnode(
                        builder,
                        fromdocname,
                        docname,
                        name,
                        contnode
                    )
        else:
            # print("here")
            # print(node)
            # print(contnode)
            # print(type)
            # print(target)
            # print(builder)
            # print(fromdocname)
            # print(env)
            return

    def resolve_any_xref(self, env, fromdocname, builder,
                         type, target, node, contnode):

        print("resolve_xref")
        print(type)
        print(target)


def setup(app: sphinx.application.Sphinx):
    """Initialize the sphinx extension for ansible.
    """
    app.add_domain(AnsibleDomain)
