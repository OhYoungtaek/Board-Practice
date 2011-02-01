package my.board.server;

import java.util.List;

import javax.jdo.PersistenceManager;

import my.board.client.BoardEntity;
import my.board.client.BoardService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class BoardServiceImpl extends RemoteServiceServlet implements BoardService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
	

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
	
	@Override
	public void addBoard(BoardEntity entity) {
		System.out.println("add func call");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(entity);
		} finally {
			pm.close();
		}
		System.out.println("add ok");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BoardEntity> listBoards() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select from " + BoardEntity.class.getName();
		System.out.println("list entity ok");
		return (List<BoardEntity>) pm.newQuery(query).execute();

	}
}
