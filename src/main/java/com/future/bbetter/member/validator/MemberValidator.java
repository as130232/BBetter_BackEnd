package com.future.bbetter.member.validator;

import com.future.bbetter.exception.customize.ValidateFailException;
import com.future.bbetter.member.dto.MemberDTO;

public class MemberValidator {
	
	private MemberValidateBehavior validateBehavior;
	
	public void validate(MemberDTO memberDTO) throws ValidateFailException {
		validateBehavior.validate(memberDTO);
	}
	
	public MemberValidator(MemberValidateBehavior validateBehavior) {
		this.validateBehavior = validateBehavior;
	}
}
