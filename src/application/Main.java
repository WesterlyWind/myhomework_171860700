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
			primaryStage.setTitle("Java��«��_171860700_v1");
			
			
			root.getChildren().add(worldScene);//�������細��
			worldScene.drawWelcome();//��ʼ��������
			
			HBox hBox = new HBox(15);//����ͨ������ padding ���������ÿؼ��� HBox ��Ե֮��ľ��롣����ͨ������ spacing ���������ø����ؼ�֮��ľ��롣�������� style ���ı䱳����ɫ��
		    hBox.setPadding(new Insets(15, 15, 15, 15));
		    hBox.setStyle("-fx-background-color: #66ccff");

			Button buttonNew =new Button("�¶Ծ�");
			Button buttonHistory =new Button("��ʷ�Ծ�");
			Button buttonStart=new Button("��ʼ��Ϸ");
			hBox.getChildren().addAll(buttonNew,buttonHistory,buttonStart);
			//���ð�ť�¼�
			buttonNew.setOnAction(new EventHandler<ActionEvent>()//�¶Ծ�
			{
				public void handle(ActionEvent arg0)
				{
					SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");//�������ڸ�ʽ
			        System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
			        String fileName=df.format(new Date())+".txt";
		    		mode=1;
		    		worldScene.openFile(fileName);
		    		worldScene.setMode(-1);
					//worldScene.drawBackground2();//��ʼ��������
		    		worldScene.newWar();
				}
			});
			buttonHistory.setOnAction(new EventHandler<ActionEvent>()//��ʷ�Ծ�
			{
				public void handle(ActionEvent arg0)
				{
					mode=0;
					FileChooser f=new FileChooser();
					f.setTitle("���ļ�...");
					File file=f.showOpenDialog(primaryStage);
					worldScene.openFile(file.getAbsolutePath());
					worldScene.setMode(mode);
					System.out.println(file.getAbsolutePath());
				}
			});
			buttonStart.setOnAction(new EventHandler<ActionEvent>()//��ʼ��Ϸ
					{
						public void handle(ActionEvent arg0)
						{
				    		if(!(worldScene.isBattleOver()&&(mode==1))) {
								worldScene.setMode(mode);
								worldScene.flashBegin();

							}
						}
					});
			Scene scene = new Scene(root,1000,560);//���ڴ�С
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
