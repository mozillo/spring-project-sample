package anddd7.springboot.controller;

import anddd7.springboot.common.ResponseEnum;
import anddd7.springboot.controller.bean.ResponseListWrapper;
import anddd7.springboot.controller.bean.ResponseWrapper;
import anddd7.springboot.domain.Message;
import anddd7.springboot.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by edliao on 2017/5/3.
 */

@Controller
@RequestMapping("/oraclejet/messages")
public class MessagesController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MessageService messageService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    ResponseListWrapper getList(@RequestParam(name = "offset",required = false) Long startIndex,
                                @RequestParam(name ="limit",required = false) Long pageNum) {
        log.debug("\tEnter getList()\t|\t{}:{},{}:{}", "startIndex", startIndex, "pageNum", pageNum);


        List result = messageService.selectByExample(new Message(), startIndex, pageNum);
        Long resultCount = messageService.selectCountByExample(new Message());

        return new ResponseListWrapper(resultCount, result);
    }

    @RequestMapping(value = "/get/{msgId}", method = RequestMethod.GET)
    @ResponseBody
    ResponseWrapper get(@PathVariable Long msgId) throws Exception {
        Message result = messageService.selectByPrimary(msgId);

        return new ResponseWrapper(ResponseEnum.success, result);
    }

    @RequestMapping(value = "/del/{msgId}", method = RequestMethod.GET)
    @ResponseBody
    ResponseWrapper del(@PathVariable Long msgId) throws Exception {
        messageService.delete(msgId);

        return new ResponseWrapper(ResponseEnum.success);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    ResponseWrapper save(Message message) throws Exception {

        if (message.getMsgId().longValue() > 0) {
            messageService.update(message);
        } else {
            messageService.save(message);
        }

        return new ResponseWrapper(ResponseEnum.success);
    }

}
