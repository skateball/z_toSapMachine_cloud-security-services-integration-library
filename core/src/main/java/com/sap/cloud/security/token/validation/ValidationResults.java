package com.sap.cloud.security.token.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;

import java.util.ArrayList;

/**
 * This is a factory class to easily create {@link ValidationResult} objects.
 */
public class ValidationResults {

	private static final Logger logger = LoggerFactory.getLogger(ValidationResults.class);

	/**
	 * Creates an invalid {@link ValidationResult} that contains one
	 * {@link ValidationError} with given description.
	 * 
	 * @param errorDescription
	 *            the description used to create the {@link ValidationError}.
	 * @return an invalid {@link ValidationResult} containing one
	 *         {@link ValidationError} with the given error description.
	 */
	public static ValidationResult createInvalid(String errorDescription) {
		logger.warn(errorDescription);
		ArrayList<ValidationError> validationErrors = new ArrayList<>();
		validationErrors.add(new ValidationErrorImpl(errorDescription));
		ValidationResultImpl validationResult = new ValidationResultImpl(validationErrors);
		return validationResult;
	}

	/**
	 * Works just like {@link #createInvalid(String)} but accepts a template string
	 * with placeholders that are substituted with the given arguments. Works like
	 * described in {@link MessageFormatter}.
	 * 
	 * @param errorMessageTemplate
	 *            the description as template used to create the
	 *            {@link ValidationError}.
	 * @param arguments
	 *            the arguments that are filled inside the description template.
	 * @return an invalid {@link ValidationResult} containing one
	 *         {@link ValidationError} with the given error description.
	 */
	public static ValidationResult createInvalid(String errorMessageTemplate, String... arguments) {
		String format = MessageFormatter.arrayFormat(errorMessageTemplate, arguments).getMessage();
		return createInvalid(format);
	}

	/**
	 * Creates a valid {@link ValidationResult}, which is a {@link ValidationResult}
	 * that contains no errors.
	 *
	 * @return a valid validation result.
	 */
	public static ValidationResult createValid() {
		return new ValidationResultImpl(new ArrayList<>());
	}

}
