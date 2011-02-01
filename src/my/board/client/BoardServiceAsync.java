package my.board.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface BoardServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
	void addBoard(BoardEntity entity, AsyncCallback<Void> callback);
	void listBoards(AsyncCallback<List<BoardEntity>> callback);
}
 