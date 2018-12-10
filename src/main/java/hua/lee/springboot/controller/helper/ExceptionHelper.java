package hua.lee.springboot.controller.helper;

import org.slf4j.Logger;
import hua.lee.springboot.exception.TipException;
import hua.lee.springboot.modal.bo.RestResponseBo;

/**
 * @author H & L
 */
public class ExceptionHelper {
    /**
     * 统一处理异常
     *
     * @param logger
     * @param msg
     * @param e
     * @return
     */
    public static RestResponseBo handlerException(Logger logger, String msg, Exception e) {
        if (e instanceof TipException) {
            msg = e.getMessage();
        } else {
            logger.error(msg, e);
        }
        return RestResponseBo.fail(msg);
    }
}
