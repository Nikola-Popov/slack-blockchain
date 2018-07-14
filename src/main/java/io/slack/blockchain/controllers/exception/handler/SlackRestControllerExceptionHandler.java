package io.slack.blockchain.controllers.exception.handler;

import static io.slack.blockchain.domain.attachments.Status.DANGER;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import io.slack.blockchain.commons.factories.AttachmentResponseFactory;
import io.slack.blockchain.domain.attachments.Attachment;
import io.slack.blockchain.domain.attachments.AttachmentResponse;
import io.slack.blockchain.interactive.components.dialogs.exceptions.DialogOpenException;

@ControllerAdvice
public class SlackRestControllerExceptionHandler {
	private static final String DIALOG_OPEN_ERROR_MESSAGE = "An error occured while trying to open the transaction dialog window";

	@Autowired
	private AttachmentResponseFactory attachmentResponseFactory;

	@ResponseBody
	@ExceptionHandler(DialogOpenException.class)
	public ResponseEntity<AttachmentResponse> handleDialogOpenException() {
		return status(INTERNAL_SERVER_ERROR).body(attachmentResponseFactory
				.createAttachmentResponse(Attachment.builder().text(DIALOG_OPEN_ERROR_MESSAGE).status(DANGER).build()));
	}

}
