package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {
    /**
     *
     * @param paramMap
     * @param model
     * @return
     */
    String proscess(Map<String, String> paramMap, Map<String, Object> model);
}

