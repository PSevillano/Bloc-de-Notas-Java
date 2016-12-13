package application.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class methods extends MainApp implements Initializable{

	@FXML
	private TextArea textArea;
	@FXML
	private MenuItem New;
	@FXML
	private MenuItem SaveAs;
	@FXML
	private MenuItem Save;
	@FXML
	private MenuItem Open;
	@FXML
	private MenuItem Select;
	
	public Stage Stage;
    private AnchorPane rootLayout;
    private TextArea textArea2;
    

	@FXML
	public void saveAs(ActionEvent event) throws Exception {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showSaveDialog(null);
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);
		primaryStage.setTitle(file.getAbsolutePath());

		if (file != null) {

			textArea.setWrapText(true);
			String content = textArea.getText();

			FileWriter fileWriter;

			fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
			
		}
		Save.setDisable(false);
		

	}
	
	@FXML
	public void save(ActionEvent event) throws Exception {
		
			
			File file = new File(primaryStage.getTitle());
			if (file != null) {

				textArea.setWrapText(true);
				String content = textArea.getText();

				FileWriter fileWriter;

				fileWriter = new FileWriter(file);
				fileWriter.write(content);
				fileWriter.close();
				
			}
		

	}
	

	@FXML
	public void open(ActionEvent event) {

		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(null);
		BufferedReader in = null;
		Save.setDisable(false);
		
		if(primaryStage.getTitle() == "Editor"){
			
			try {
				primaryStage.setTitle(file.getAbsolutePath());
			    in = new BufferedReader(new FileReader(file));
			    String str;
			    while ((str = in.readLine()) != null) {
			        textArea.appendText(str+"\n");
			    }
			} catch (IOException e) {
			} finally {
			    try { in.close(); } catch (Exception ex) { }
			}
			
		}else{

			try {
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("vista/Interfaz.fxml"));
	            rootLayout = (AnchorPane) loader.load();
	            Stage stage = new Stage();
	            stage.setTitle(file.getAbsolutePath());
	            stage.setScene(new Scene(rootLayout));
	            stage.show();
	            
	            try {
				    in = new BufferedReader(new FileReader(file));
				    String str;
				    while ((str = in.readLine()) != null) {
				        this.textArea.appendText(str+"\n");//TENGO QUE AVERIGUAR COMO METER EL TEXTO QUE ABRO EN EL TEXTAREA 2
				    }
				} catch (IOException e) {
				} finally {
				    try { in.close(); } catch (Exception ex) { }
				}
	            
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
			
		}
	}
	
	@FXML
	public void nuevo(ActionEvent event) throws Exception{
		
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/Interfaz.fxml"));
            rootLayout = (AnchorPane) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Editor");
            stage.setScene(new Scene(rootLayout));
            stage.show();
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	@FXML
	public void select(ActionEvent event){
		textArea.selectAll();
		
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Open.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
		SaveAs.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
		Save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.ALT_DOWN));
		New.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
		Select.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.ALT_DOWN));
		
	}

/*	COSAS PENDIENTES POR HACER:
 * 1.-AL ABRIR UN NUEVO FICHERO QUE SE ESCRIBA EN EL TEXTAREA QUE TOCA
 */

}
