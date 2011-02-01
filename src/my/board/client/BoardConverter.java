package my.board.client;


public class BoardConverter {
	public static BoardModel entityToModel(BoardEntity entity){
		BoardModel model = new BoardModel(entity.getName(), entity.getTitle(), entity.getContent());
		return model;
	}
}
