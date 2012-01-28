package fits.sample;

import java.util.Set;
import javax.validation.Validator;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import org.hibernate.validator.method.MethodValidator;
import org.hibernate.validator.method.MethodConstraintViolation;

@Aspect
public class MethodValidateAspect {

	@Before("execution(@ValidMethod * *.*(..))")
	public void checkMethod(JoinPoint jp) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		// MethodValidor �̎擾
		MethodValidator mvalidator = validator.unwrap(MethodValidator.class);

		//java.lang.reflect.Method ���擾���邽�� MethodSignature �ɃL���X�g
		MethodSignature msig = (MethodSignature)jp.getSignature();

		//���\�b�h�����̃`�F�b�N
		Set<MethodConstraintViolation<Object>> violations = mvalidator.validateAllParameters(jp.getThis(), msig.getMethod(), jp.getArgs());

		//���ʏo��
		for (MethodConstraintViolation<Object> vi : violations) {
			System.out.printf("*** invalid arg : %s, %s\n", vi.getParameterName(), vi.getMessage());
		}
	}

}