package my.board.client;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Board implements EntryPoint {
	final BoardServiceAsync boardService = (BoardServiceAsync)GWT.create(BoardService.class);
	List<BoardModel> modelList;
	public void onModuleLoad() {
		
		Viewport viewport = new Viewport();
		final BorderLayout borderLayout = new BorderLayout();
		viewport.setLayout(borderLayout);
		RootPanel rootPanel = RootPanel.get();
		rootPanel.add(viewport);
		BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH,20);
		northData.setCollapsible(false);
		northData.setSplit(false);

		HTML headerHtml = new HTML();
		headerHtml.setHTML("<h1>RSS Reader</h1>");
		viewport.add(headerHtml, northData);

		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
		centerData.setCollapsible(false);
		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 200, 150, 300);
		westData.setCollapsible(true);
		westData.setSplit(true);

		ContentPanel mainPanel = new ContentPanel();
		ContentPanel navPanel = new ContentPanel();
		mainPanel.setLayout(new FillLayout(Orientation.VERTICAL));
		
		ContentPanel contentPanel = new ContentPanel();
		contentPanel.setHeading("New ContentPanel");
		contentPanel.setCollapsible(true);
		
		AdvancedFormsExample afe = new AdvancedFormsExample();
		contentPanel.setBottomComponent(afe);
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		
		ColumnConfig columnConfig = new ColumnConfig("id", "New Column", 150);
		configs.add(columnConfig);
		
		ColumnConfig columnConfig_1 = new ColumnConfig("id", "New Column", 150);
		configs.add(columnConfig_1);
		Grid grid = new Grid(new ListStore(), new ColumnModel(configs));
		contentPanel.add(grid);
		grid.setBorders(true);
		afe.setBorders(true);
		mainPanel.add(contentPanel);
		viewport.add(mainPanel, centerData);
		viewport.add(navPanel, westData);
	}
	
	class AdvancedFormsExample extends LayoutContainer {

		  private VerticalPanel vp;
		  
		  @Override
		  protected void onRender(Element parent, int index) {
		    super.onRender(parent, index);
		    vp = new VerticalPanel();
		    vp.setSpacing(10);
		    createColumnForm();
		    add(vp);
		  }

		  private void createColumnForm() {
			modelList = new ArrayList();
		    FormData formData = new FormData("100%");
		    FormPanel panel = new FormPanel();
		    panel.setFrame(true);
		    //panel.setIcon(Resources.ICONS.form());
		    panel.setHeading("FormPanel");
		    panel.setSize(600, -1);
		    panel.setLabelAlign(LabelAlign.TOP);
		    panel.setButtonAlign(HorizontalAlignment.CENTER);

		    LayoutContainer main = new LayoutContainer();
		    main.setLayout(new ColumnLayout());

		    LayoutContainer left = new LayoutContainer();
		    left.setStyleAttribute("paddingRight", "10px");
		    FormLayout layout = new FormLayout();
		    layout.setLabelAlign(LabelAlign.TOP);
		    left.setLayout(layout);

		    final TextField<String> name = new TextField<String>();
		    name.setFieldLabel("Name");
		    left.add(name, formData);

		    final TextField<String> title = new TextField<String>();
		    title.setFieldLabel("Title");
		    left.add(title, formData);
		   
		    main.add(left, new ColumnData(.5));

		    panel.add(main, new FormData("100%"));
		    
		    final TextArea content = new TextArea();
		    content.setFieldLabel("Content");
		    content.setHeight(200);
		    
		    panel.add(content, new FormData("100%"));

		    panel.addButton(new Button("Cancel", new SelectionListener<ButtonEvent>() {

				@Override
				public void componentSelected(ButtonEvent ce) {
					// TODO Auto-generated method stub
//					AsyncCallback<List<BoardEntity>> callbackList = new AsyncCallback<List<BoardEntity>>() {
//
//						@Override
//						public void onFailure(Throwable caught) {
//							// TODO Auto-generated method stub
//							MessageBox.alert("fail","",null);
//							System.out.println("fail");
//						}
//
//						@Override
//						public void onSuccess(List<BoardEntity> result) {
//							// TODO Auto-generated method stub
//							System.out.println("success");
//							MessageBox.alert("success","" ,null);
//						
////							if(!result.isEmpty()){
////								for (BoardEntity b : result){
////									modelList.add(BoardConverter.entityToModel(b));
////								}
////							}
////							System.out.println("success save Model");
//						}
//					};
					System.out.println("call listboards");
					boardService.listBoards(new AsyncCallback<List<BoardEntity>>(){

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							MessageBox.alert("fail","",null);
							System.out.println("fail");
						}

						@Override
						public void onSuccess(List<BoardEntity> result) {
							// TODO Auto-generated method stub
							System.out.println("success");
							MessageBox.alert("success","" ,null);
						
							if(!result.isEmpty()){
								for (BoardEntity b : result){
									modelList.add(BoardConverter.entityToModel(b));
								}
							}
							System.out.println("success save Model");
						}
						
					});
				}
			}));
		    panel.addButton(new Button("Submit", new SelectionListener<ButtonEvent>(){

				@Override
				public void componentSelected(ButtonEvent ce) {
					// TODO Auto-generated method stub
					AsyncCallback<Void> callback = new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(Void result) {
							// TODO Auto-generated method stub
							System.out.println("success");
							
						}
					};
					MessageBox.alert("Data", name.getValue() + " ; " + content.getValue(),null);
					boardService.addBoard(new BoardEntity(name.getValue(),title.getValue(),content.getValue()),callback);
					
					
//					BoardService.addBoard(new Board(name.getValue(), title.getValue(), content.getValue()));
//					List<Board> boardList = boardService.listBoards();
//				//	boardDB.addBoard(new Board(name.getValue(), title.getValue(), content.getValue()));
//					if(!boardList.isEmpty()){
//						for (Board b : boardList){
//							boardModelList.add(BoardConverter.entityToModel(b));
//						}
//					}
//					
					
					//System.out.println(name.getValue() + title.getValue() + content.getValue());
				}
		    	
		    }));

		    vp.add(panel);
		  }
	}
}
