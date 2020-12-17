package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class JDBCInitListener
 *
 */
@WebListener
public class JDBCInitListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public JDBCInitListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         ServletContext application = sce.getServletContext();
         
         String url = application.getInitParameter("jdbcUrl");
         String user = application.getInitParameter("jdbcUser");
         String password = application.getInitParameter("jdbcPassword");
         
         ConnectionProvider.setUrl(url);
         ConnectionProvider.setUser(user);
         ConnectionProvider.setPassword(password);
         
         //1.클래스 로딩
         try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
         
         //2.DriverManager에서 connection생성 및 try-with블럭으로 autoClose
         //연결생성하는 클래스를 따로 만들었기 때문에 이 부분은 필요X(해당 url, user, password로 잘 연결되는지 알아보는 용도로만 사용)
         try(
         Connection con = DriverManager.getConnection(url, user, password);
         ) {
        	 System.out.println("연결 잘 됨");
         } catch(Exception e) {
        	 e.printStackTrace();
         }
         
         //context root 경로 
         String contextPath = application.getContextPath();
         application.setAttribute("appRoot", contextPath);
    }
	
}
