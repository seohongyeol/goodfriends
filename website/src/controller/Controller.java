package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.NextPage;
import service.UserService;


/**
 * Servlet implementation class Controller
 */
@WebServlet(value="*.do",initParams={@WebInitParam(name="url",value="/util/url.properties")})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private Map<String,UserService> map = new HashMap();
	
    /**
     * Default constructor. 
     */
    public Controller() {
    	
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 호출");
//		System.out.println(config.getInitParameter("url"));
		
		Properties prop = new Properties();
		
		try {
			prop.load(getClass().getResourceAsStream(config.getInitParameter("url")));

			Iterator<Object> iterator = prop.keySet().iterator();
			
			while(iterator.hasNext()){
				String key = (String) iterator.next();
				Class clazz = Class.forName(prop.getProperty(key));
				UserService service = (UserService) clazz.newInstance();
				map.put(key, service);
			}
			
		} catch (IOException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		int cmdIdx = url.lastIndexOf("/") + 1;
		String cmd = url.substring(cmdIdx);

		NextPage nextPage = null;
		nextPage = map.get(cmd).execute(request, response);
		if (nextPage == null) {
			System.out.println("null");
		} else {
			if (nextPage.isRedirect()) {
				System.out.println("리다이렉트방식");
				response.sendRedirect(nextPage.getPageName());
			} else {
				System.out.println("포워드방식");
				request.getRequestDispatcher(nextPage.getPageName()).forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
