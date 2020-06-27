package cn.cxd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 11:41 2020/6/26
 * @Modified By:
 */

@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "customException ..")
public class CustomException extends RuntimeException{

}
