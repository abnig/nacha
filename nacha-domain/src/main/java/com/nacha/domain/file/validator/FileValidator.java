package com.nacha.domain.file.validator;

import com.nacha.domain.file.AbstractACHFile;

public interface FileValidator<T extends AbstractACHFile> {

	void validateFile(T file);

}
