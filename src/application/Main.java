package application;
	
//import application.MainScene;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
public class Main extends Application {
	int mode;
	@Override
	public void start(Stage primaryStage) {
		try {
			mode=-1;
			BorderPane root = new BorderPane();
			MainScene worldScene=new MainScene();
			primaryStage.setTitle("Java葫芦娃_171860700_v1");
			
			
			root.getChildren().add(worldScene);//加入世界窗口
			worldScene.drawWelcome();//初始化画背景
			
			HBox hBox = new HBox(15);//可以通过设置 padding 属性来设置控件与 HBox 边缘之间的距离。可以通过设置 spacing 属性来设置各个控件之间的距离。可以设置 style 来改变背景颜色。
		    hBox.setPadding(new Insets(15, 15, 15, 15));
		    hBox.setStyle("-fx-background-color: #66ccff");

			Button buttonNew =new Button("新对局");
			Button buttonHistory =new Button("历史对局");
			Button buttonStart=new Button("开始游戏");
			hBox.getChildren().addAll(buttonNew,buttonHistory,buttonStart);
			//设置按钮事件
			buttonNew.setOnAction(new EventHandler<ActionEvent>()//新对局
			{
				public void handle(ActionEvent arg0)
				{
					SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");//设置日期格式
			        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
			        String fileName=df.format(new Date())+".txt";
		    		mode=1;
		    		worldScene.openFile(fileName);
		    		worldScene.setMode(-1);
					//worldScene.drawBackground2();//初始化画背景
		    		worldScene.newWar();
				}
			});
			buttonHistory.setOnAction(new EventHandler<ActionEvent>()//历史对局
			{
				public void handle(ActionEvent arg0)
				{
					mode=0;
					FileChooser f=new FileChooser();
					f.setTitle("打开文件...");
					File file=f.showOpenDialog(primaryStage);
					worldScene.openFile(file.getAbsolutePath());
					worldScene.setMode(mode);
					System.out.println(file.getAbsolutePath());
				}
			});
			buttonStart.setOnAction(new EventHandler<ActionEvent>()//开始游戏
					{
						public void handle(ActionEvent arg0)
						{
				    		if(!(worldScene.isBattleOver()&&(mode==1))) {
								worldScene.setMode(mode);
								worldScene.flashBegin();

							}
						}
					});
			Scene scene = new Scene(root,1000,560);//窗口大小
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			root.setTop(hBox);
			
		    
			
			primaryStage.show();//System.out.println(hBox.getHeight());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
