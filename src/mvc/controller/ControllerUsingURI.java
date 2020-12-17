package mvc.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;

/**
 * Servlet implementation class ControllerUsingURI
 */

public class ControllerUsingURI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String prefix = "/WEB-INF/view/";
    private String suffix = ".jsp";
    private Map<String, CommandHandler> map;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerUsingURI() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	//가장 먼저 시작되는 init메소드안에서 properties에 있는 값들을 읽어오는 과정
    	map = new HashMap<>();
    	
    	ServletContext application = getServletContext();
    	ServletConfig config = getServletConfig();
    	String configFilePath = config.getInitParameter("configFile").trim();
		String filePath = application.getRealPath(configFilePath);
		
		//properties를 통해 요청에 따라 알맞는 Controller(여기선 Handler)를 호출
		//아래 코드는 properties에 저장된 키(사용자가 요청하는 uri), 값(요청한 uri에 따라 실행될 Controller파일)을 Map에 넣는 과정
		try (FileReader fr = new FileReader(filePath);) {
			Properties properties = new Properties();
			properties.load(fr);
			
			Set<Object> keys = properties.keySet();
			for(Object key : keys) {
				Object value = properties.get(key);
				String className = (String) value;
				
				try {
					Class c = Class.forName(className);
					Object o = c.newInstance();
					CommandHandler handler = (CommandHandler) o;
					map.put((String) key, handler);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//아래 코드는 요청한 uri에서 contextPath를 잘라낸 부분을  Map에 저장된 Controller를 호출하기 위한 명령어처럼 사용하기 위함
		String uri = request.getRequestURI();
		String root = request.getContextPath();		
		String command = "";
		if(uri.startsWith(root)) {
			command = uri.substring(root.length());
		}
		
		CommandHandler handler = map.get(command);
		
		if(handler == null) {
			handler = new NullHandler();
		}
		
		//Controller의 처리 결과를 어느 view로 보낼지 결정하는 코드
		String view = null;
		try {
			view = handler.process(request, response); //view 변수에 처리 결과를 담음
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//알맞는 view로 forward하는 코드
		//Controller에서 redirect하는 경우 process메소드가 null을 리턴함으로써 아래의 forward가 일어나지 않고 Controller의 redirect가 이뤄짐
		if(view != null) {
			request.getRequestDispatcher(prefix + view + suffix).forward(request, response);
		}
		
	}

}
