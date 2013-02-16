@Grab('org.apache.velocity:velocity:1.7')
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.Velocity

/**
 * Velocity �� macro �̃I�[�o�[���C�h���m�F����T���v��
 */

Velocity.init('velocity.properties')

def template = Velocity.getTemplate(args[0])

def context = new VelocityContext()

System.out.withWriter {
	template.merge(context, it)
}
