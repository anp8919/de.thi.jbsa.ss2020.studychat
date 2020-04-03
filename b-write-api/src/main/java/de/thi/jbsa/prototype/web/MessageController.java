package de.thi.jbsa.prototype.web;

import javax.jms.Queue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.thi.jbsa.prototype.model.cmd.Cmd;
import de.thi.jbsa.prototype.model.cmd.PostMessageCmd;
import de.thi.jbsa.prototype.service.CommandHandlerService;
import lombok.extern.java.Log;

/**
 * @author Christopher Timm <christopher.timm@beskgroup.com> on 2020-02-18
 */
@RestController
@RequestMapping("/api")
@Log
public class MessageController {

  private final CommandHandlerService commandHandlerService;

  public MessageController(CommandHandlerService commandHandlerService) {
    this.commandHandlerService = commandHandlerService;
  }

  @PostMapping(path="message", consumes = "text/plain")
  public ResponseEntity<PostMessageCmd> publish(@RequestBody final PostMessageCmd cmd) {
    log.info("Received command " + cmd);
    commandHandlerService.handleCommand(cmd);
    return new ResponseEntity<>(cmd, HttpStatus.OK);
  }
}
