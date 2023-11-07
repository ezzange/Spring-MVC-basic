package hello.servlet.web.frontcontroller.v1;


import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
                                            //front-controller/v1 를 포함한 하위 모든 요청은 이 서블릿에서 받아들인다.
@WebServlet(name = "frontControllerServletV1", urlPatterns = "front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {
    //요청을 저장한다.
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    // 저장한 요청에 따라 각 각의 객체 인스턴스를 생성한다.
    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");
        //받은 요청을 변수에 저장 /front-controller/v1/members
        String requestURI = request.getRequestURI();
        //호출된 컨트롤러 인스턴스를 반환 MemberListControllerV1()
        ControllerV1 controller = controllerMap.get(requestURI);
        //부모타입의 인스턴스 참조변수로 자식타입의 클래스를 가르킴 -> 다형성

        //없을 경우 404
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //잘 조회가 될 경우 process 호출
        controller.process(request, response);
        //부모의 메서드 호
    }
}

