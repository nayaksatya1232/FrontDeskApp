package com.satya.binding;

import lombok.Data;

@Data
public class UnlockForm {
	private String tmpPwd;
	private String newPwd;
	private String cnfPwd;
}
