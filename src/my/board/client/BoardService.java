package my.board.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("board")
public interface BoardService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	void addBoard(BoardEntity entity);
	List<BoardEntity> listBoards();
}
