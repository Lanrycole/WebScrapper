package view;
 
import entity.Feed;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import java.util.Arrays;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import logic.FeedLogic;
 


/**
 *
 * @author Shariar
 */
@WebServlet(name = "FeedTable", urlPatterns = {"/FeedTable"})
public class FeedTable extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>FeedTable</title>");            
            out.println("</head>");
            out.println("<body>");
            
            FeedLogic feedlogic = new FeedLogic();
            List<Feed> entity = feedlogic.getAll();
            List<String> print = feedlogic.getColumnNames();
            out.println("<table align=\"center\" border=\"1\">");
            out.println("<caption>FEED</caption>");
            //this is an example for your other table use getColumnNames from logic to 
            //create headers in a loop.
               out.println("<tr>");
            for(String col : print){
                out.printf("<th>%s</th>", col);
            }
               for(Feed e: entity){
                out.printf("<tr>"
                        + "<td>%s</td>"
                        + "<td>%s</td>"
                         + "<td>%s</td>"
                         + "<td>%s</td>"
                        + "<td>%s</td>"
                        + "</tr>", e.getId(), e.getPath(), e.getType(), 
                       e.getName(), e.getHostid());
            
            }
                for(String col : print){
                out.printf("<th>%s</th>", col);
            }
            out.println("</tr>");
            out.println("</table>");
            out.printf("<div style=\"text-align: center;\"><pre>%s</pre></div>", toStringMap(request.getParameterMap()));
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private String toStringMap(Map<String, String[]> m) {
        StringBuilder builder = new StringBuilder();
        for(String k: m.keySet()) {
            builder.append("Key=").append(k)
                    .append(", ")
                    .append("Value/s=").append(Arrays.toString(m.get(k)))
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log("GET");
        processRequest(request, response);
 
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log("POST");
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Sample of Feed View Normal";
    }

    private static final boolean DEBUG = true;

    public void log( String msg) {
        if(DEBUG){
            String message = String.format( "[%s] %s", getClass().getSimpleName(), msg);
            getServletContext().log( message);
        }
    }

    public void log( String msg, Throwable t) {
        String message = String.format( "[%s] %s", getClass().getSimpleName(), msg);
        getServletContext().log( message, t);
    }
}
