package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import modelo.*;

public class gameController implements Initializable {

	private GridPane grid;
	private int casillasParaGanar = 0;
	@FXML
	private BorderPane board = new BorderPane();
	@FXML
	private Button principiante;
	@FXML
	private Button intermedio;
	@FXML
	private Button experto;
	@FXML
	private Button reiniciar;
	@FXML
	private Button m;
	@FXML
	private Button botonReiniciar;
	@FXML
	private Button botonDarPista;

    
	public static final Image tile = new Image("Controller/TileOriginal.png", 20, 20, false, false);
	public static final Image mine = new Image("Controller/Mine.png", 20, 20, false, false);
	
	private Buscaminas busca;
	private boolean t = false;
	
	public void botonPrincipiante(ActionEvent e) {
		busca = new Buscaminas(1);
		grid = new GridPane();
		grid.setGridLinesVisible(true);
		grid.setMaxHeight(30);
		grid.setMaxWidth(30);		
		busca.inicializarPartida();
		Casilla[][] casillas = busca.darCasillas();
		
        for (int row = 0 ; row < Buscaminas.FILAS_PRINCIPIANTE ; row++ ){
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }
        for (int col = 0 ; col < Buscaminas.COLUMNAS_PRINCIPIANTE; col++ ) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }
	
      
		for(int i = 0; i < casillas.length;i++) {
			for(int j = 0; j < casillas[0].length;j++) {
				Button m = new Button("  ");
                final int i_button1 = i;
                final int j_button2 = j;
				m.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				m.setGraphic(new ImageView(tile));
				m.setGraphicTextGap(5);
				m.setDisable(false);
				m.setOnAction( new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent event) {
						
						if(busca.abrirCasilla(i_button1, j_button2)) {
							
							if(busca.darCasillas()[i_button1][j_button2].mostrarValorCasilla() == "*") {
								m.setGraphic(new ImageView(mine));
								m.setDisable(false);
								Alert gameOver = new Alert(AlertType.INFORMATION);
								gameOver.setTitle("Game Over!");
								gameOver.setHeaderText("La bomba exploto!");
								gameOver.setContentText(
										"Perdiste!!! Noooooooo");
								gameOver.showAndWait();
								reiniciar(event);
								botonPrincipiante(event);
							}		
						}
						 
						else{
							m.setText(busca.darCasillas()[i_button1][j_button2].mostrarValorCasilla());
							casillasParaGanar++;
							if(casillasParaGanar == Buscaminas.MINAS_TOTALES_PRINCPIANTE_PARA_GANAR) {
								Alert gameOver = new Alert(AlertType.INFORMATION);
								gameOver.setTitle("¡Ganaste!");
								gameOver.setHeaderText("¡Eres un crack!!!");
								gameOver.setContentText(
										"Sigue jugando B)");
								gameOver.showAndWait();
								reiniciar(e);
								botonPrincipiante(e);
							}
						}
					}
				});
				grid.add(m,i,j);
			}
			
		}
        grid.setHgap(2); 
        grid.setVgap(2);
        
        board.setCenter(grid);
	}
	

	public void botonIntermedio(ActionEvent e) {
		busca = new Buscaminas(2);
		grid = new GridPane();
		grid.setGridLinesVisible(true);
		grid.setMaxHeight(30);
		grid.setMaxWidth(30);		
		busca.inicializarPartida();
		Casilla[][] casillas = busca.darCasillas();
		
        for (int row = 0 ; row < Buscaminas.FILAS_INTERMEDIO ; row++ ){
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }
        for (int col = 0 ; col < Buscaminas.COLUMNAS_INTERMEDIO; col++ ) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }
	
      
		for(int i = 0; i < casillas.length;i++) {
			for(int j = 0; j < casillas[0].length;j++) {
				Button m = new Button("  ");
                final int i_button1 = i;
                final int j_button2 = j;
				m.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				m.setGraphic(new ImageView(tile));
				m.setGraphicTextGap(5);
				m.setDisable(false);
				m.setOnAction( new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent event) {
						
						if(busca.abrirCasilla(i_button1, j_button2)) {
							if(busca.darCasillas()[i_button1][j_button2].mostrarValorCasilla() == "*") {
								m.setGraphic(new ImageView(mine));
								m.setDisable(false);
								Alert gameOver = new Alert(AlertType.INFORMATION);
								gameOver.setTitle("Game Over!");
								gameOver.setHeaderText("La bomba exploto!");
								gameOver.setContentText(
										"Perdiste!!! Noooooooo");
								gameOver.showAndWait();
								reiniciar(event);
								botonIntermedio(event);
							}		
						}
						
						else{
							m.setText(busca.darCasillas()[i_button1][j_button2].mostrarValorCasilla());
							casillasParaGanar++;
							if(casillasParaGanar == Buscaminas.MINAS_TOTALES_INTERMEDIO_PARA_GANAR) {
								Alert gameOver = new Alert(AlertType.INFORMATION);
								gameOver.setTitle("¡Ganaste!");
								gameOver.setHeaderText("¡Eres un crack!!!");
								gameOver.setContentText(
										"Sigue jugando B)");
								gameOver.showAndWait();
								reiniciar(e);
								botonIntermedio(e);
							}
						}
						
						
						
					}
				});
				grid.add(m,i,j);
			}
			
		}
        grid.setHgap(2); 
        grid.setVgap(2);

        board.setCenter(grid);
	}
	
	
	
	public void botonExperto(ActionEvent e) {
		busca = new Buscaminas(3);
		grid = new GridPane();
		grid.setGridLinesVisible(true);
		grid.setMaxHeight(30);
		grid.setMaxWidth(30);		
		busca.inicializarPartida();
		Casilla[][] casillas = busca.darCasillas();
		
        for (int row = 0 ; row < Buscaminas.FILAS_EXPERTO ; row++ ){
            RowConstraints rc = new RowConstraints();
            rc.setValignment(VPos.CENTER);
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }
        for (int col = 0 ; col < Buscaminas.COLUMNAS_EXPERTO; col++ ) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setHalignment(HPos.RIGHT);
            cc.setFillWidth(true); 
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }
	
      
		for(int i = 0; i < casillas.length;i++) {
			for(int j = 0; j < casillas[0].length;j++) {
                final int i_button1 = i;
                final int j_button2 = j;
				Button m = new Button("  ");
				m.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				m.setGraphic(new ImageView(tile));
				m.setGraphicTextGap(5);
				m.setDisable(false);
				m.setOnAction( new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent event) {
						
						if(busca.abrirCasilla(i_button1, j_button2)) {
							if(busca.darCasillas()[i_button1][j_button2].mostrarValorCasilla() == "*") {
								m.setGraphic(new ImageView(mine));
								m.setDisable(false);
								Alert gameOver = new Alert(AlertType.INFORMATION);
								gameOver.setTitle("Game Over!");
								gameOver.setHeaderText("La bomba exploto!");
								gameOver.setContentText(
										"Perdiste!!! Noooooooo");
								gameOver.showAndWait();
								reiniciar(event);
								botonExperto(event);
							}		
						}
						
						else{
							m.setText(busca.darCasillas()[i_button1][j_button2].mostrarValorCasilla());
							casillasParaGanar++;
							if(casillasParaGanar == Buscaminas.MINAS_TOTALES_EXPERTO_PARA_GANAR) {
								Alert gameOver = new Alert(AlertType.INFORMATION);
								gameOver.setTitle("¡Ganaste!");
								gameOver.setHeaderText("¡Eres un crack!!!");
								gameOver.setContentText(
										"Sigue jugando B)");
								gameOver.showAndWait();
								reiniciar(e);
								botonExperto(e);
							}
						}
						
						
						
					}
				});
				grid.add(m,j,i);
			}
			
		}
        grid.setHgap(2); 
        grid.setVgap(2);

        board.setCenter(grid);
	}
	
	public void resolver() {
		try {
		if(!grid.getChildren().isEmpty()) {	
		if(busca.getNivel() == 1) { 
			Casilla[][] casillas = busca.darCasillas();
			grid.getChildren().clear();
			grid = new GridPane();
			grid.setGridLinesVisible(true);
			grid.setMaxHeight(30);
			grid.setMaxWidth(30); 
	        for (int row = 0 ; row < Buscaminas.FILAS_PRINCIPIANTE ; row++ ){
	            RowConstraints rc = new RowConstraints();
	            rc.setFillHeight(true);
	            rc.setVgrow(Priority.ALWAYS);
	            grid.getRowConstraints().add(rc);
	        }
	        for (int col = 0 ; col < Buscaminas.COLUMNAS_PRINCIPIANTE; col++ ) {
	            ColumnConstraints cc = new ColumnConstraints();
	            cc.setFillWidth(true);
	            cc.setHgrow(Priority.ALWAYS);
	            grid.getColumnConstraints().add(cc);
	        }
			
			for(int i = 0; i < casillas.length;i++) {
				
				for(int j = 0; j < casillas[0].length;j++) {
					Button m = new Button("   ");
					m.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
					m.setGraphic(new ImageView(tile));
					m.setGraphicTextGap(5);
					m.setDisable(false);
					Label lab1 = new Label(busca.darCasillas()[i][j].mostrarValorCasilla());
					m.setText(lab1.getText());
					if(busca.darCasillas()[i][j].darTipo() == Casilla.MINA) {
					m.setText("*");
					m.setGraphic(new ImageView(mine));
					m.setGraphicTextGap(5);
					m.setDisable(false);
					}else {
						Label lab2 = new Label(Integer.toString(busca.darCasillas()[i][j].darValor()));
						m.setText(lab2.getText());
					} 
					
					grid.add(m, i, j);
				} 
				 
			}
	        grid.setHgap(2); 
	        grid.setVgap(2);
			board.setCenter(grid);
			
		}else if(busca.getNivel() == 2) {
			Casilla[][] casillas = busca.darCasillas();
			grid.getChildren().clear(); 
			grid = new GridPane();
			grid.setGridLinesVisible(true);
			grid.setMaxHeight(30);
			grid.setMaxWidth(30); 
	        for (int row = 0 ; row < Buscaminas.FILAS_INTERMEDIO ; row++ ){
	            RowConstraints rc = new RowConstraints();
	            rc.setFillHeight(true);
	            rc.setVgrow(Priority.ALWAYS);
	            grid.getRowConstraints().add(rc);
	        }
	        for (int col = 0 ; col < Buscaminas.COLUMNAS_INTERMEDIO; col++ ) {
	            ColumnConstraints cc = new ColumnConstraints();
	            cc.setFillWidth(true);
	            cc.setHgrow(Priority.ALWAYS);
	            grid.getColumnConstraints().add(cc);
	        }
			
			for(int i = 0; i < casillas.length;i++) {
				
				for(int j = 0; j < casillas[0].length;j++) {
					Button m = new Button("   ");
					m.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
					m.setGraphic(new ImageView(tile));
					m.setGraphicTextGap(5);
					m.setDisable(false);
					Label lab1 = new Label(busca.darCasillas()[i][j].mostrarValorCasilla());
					m.setText(lab1.getText());
					if(busca.darCasillas()[i][j].darTipo() == Casilla.MINA) {
					m.setText("*");
					m.setGraphic(new ImageView(mine));
					m.setGraphicTextGap(5);
					m.setDisable(false);
					}else {
						Label lab2 = new Label(Integer.toString(busca.darCasillas()[i][j].darValor()));
						m.setText(lab2.getText());
					} 
					
					grid.add(m, i, j);
				} 
				 
			}
	        grid.setHgap(2); 
	        grid.setVgap(2);
			board.setCenter(grid);
				
			
		}else if(busca.getNivel() == 3) {
			Casilla[][] casillas = busca.darCasillas();
			grid.getChildren().clear();
			grid = new GridPane();
			grid.setGridLinesVisible(true);
			grid.setMaxHeight(30);
			grid.setMaxWidth(30); 
	        for (int row = 0 ; row < Buscaminas.FILAS_EXPERTO ; row++ ){
	            RowConstraints rc = new RowConstraints();
	            rc.setFillHeight(true);
	            rc.setVgrow(Priority.ALWAYS);
	            grid.getRowConstraints().add(rc);
	        }
	        for (int col = 0 ; col < Buscaminas.COLUMNAS_EXPERTO; col++ ) {
	            ColumnConstraints cc = new ColumnConstraints();
	            cc.setFillWidth(true);
	            cc.setHgrow(Priority.ALWAYS);
	            grid.getColumnConstraints().add(cc);
	        }
			
			for(int i = 0; i < casillas.length;i++) {
				
				for(int j = 0; j < casillas[0].length;j++) {
					Button m = new Button("   ");
					m.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
					m.setGraphic(new ImageView(tile));
					m.setGraphicTextGap(5);
					m.setDisable(false);
					Label lab1 = new Label(busca.darCasillas()[i][j].mostrarValorCasilla());
					m.setText(lab1.getText());
					if(busca.darCasillas()[i][j].darTipo() == Casilla.MINA) {
					m.setText("*");
					m.setGraphic(new ImageView(mine));
					m.setGraphicTextGap(5);
					m.setDisable(false);
					}else {
						Label lab2 = new Label(Integer.toString(busca.darCasillas()[i][j].darValor()));
						m.setText(lab2.getText());
					} 
					
					grid.add(m, j, i);
				} 
				 
			}
	        grid.setHgap(2); 
	        grid.setVgap(2);
			board.setCenter(grid);
			
			
		}
		Alert gameOver = new Alert(AlertType.INFORMATION);
		gameOver.setTitle("¡Ganaste Pero haciendo trampas!");
		gameOver.setHeaderText("¡Buuuuuuu!");
		gameOver.setContentText(
				"Vuelva y juegue tramposo");
		gameOver.showAndWait();
		ActionEvent e = null;
		reiniciar(e);
		}else {
			Alert gameOver = new Alert(AlertType.INFORMATION);
			gameOver.setTitle("Alto Ahi!");
			gameOver.setHeaderText("¡No hay tablero!");
			gameOver.setContentText(
					"Por cree el tablero, para poder jugar");
			gameOver.showAndWait();
		}
		}catch(NullPointerException e) {
			Alert gameOver = new Alert(AlertType.INFORMATION);
			gameOver.setTitle("Alto Ahi!");
			gameOver.setHeaderText("¡Excepcion Encontrada!");
			gameOver.setContentText(
					"Por cree el tablero, para poder jugar");
			gameOver.showAndWait();
		}
		
	}
	
	public void darPista(ActionEvent e) {
		try {
			if(!grid.getChildren().isEmpty()) {
			if(busca.getNivel() == 1) {
				Casilla[][] casillas = busca.darCasillas();
				boolean t1 = false;
				grid.setMaxHeight(30);
				grid.setMaxWidth(30); 
				for(int i = 0; i < casillas.length &&!t1 ;i++) {
					
					for(int j = 0; j < casillas[0].length &&!t1;j++) {
						
						if(casillas[i][j].esMina() == false && casillas[i][j].darValor() > 0 && casillas[i][j].darSeleccionada() != true) {
							casillas[i][j].destapar();
							Button m = new Button(casillas[i][j].mostrarValorCasilla());
							m.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
							m.setGraphic(new ImageView(tile));
							m.setGraphicTextGap(5);
							m.setDisable(false);
							grid.add(m, i, j);
						    t1 = true;
						    casillasParaGanar++;
						} 
						
					}
				
				}
				
				
				board.setCenter(grid);
		}else if(busca.getNivel() == 2) {
			Casilla[][] casillas = busca.darCasillas();
			boolean t1 = false;
			grid.setMaxHeight(30);
			grid.setMaxWidth(30); 
			for(int i = 0; i < casillas.length &&!t1 ;i++) {
				
				for(int j = 0; j < casillas[0].length &&!t1;j++) {
					
					if(casillas[i][j].esMina() == false && casillas[i][j].darValor() > 0 && casillas[i][j].darSeleccionada() != true) {
						casillas[i][j].destapar();
						Button m = new Button(casillas[i][j].mostrarValorCasilla());
						m.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
						m.setGraphic(new ImageView(tile));
						m.setGraphicTextGap(5);
						m.setDisable(false);
						grid.add(m, i, j);
					    t1 = true;
					    casillasParaGanar++;
					} 
					
				}
			
			}
			
			
			board.setCenter(grid);
		}else if(busca.getNivel() == 3) {
			Casilla[][] casillas = busca.darCasillas();
			boolean t1 = false;
			grid.setMaxHeight(30);
			grid.setMaxWidth(30); 
			for(int i = 0; i < casillas.length &&!t1 ;i++) {
				
				for(int j = 0; j < casillas[0].length &&!t1;j++) {
					
					if(casillas[i][j].esMina() == false && casillas[i][j].darValor() > 0 && casillas[i][j].darSeleccionada() != true) {
						casillas[i][j].destapar();
						Button m = new Button(casillas[i][j].mostrarValorCasilla());
						m.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
						m.setGraphic(new ImageView(tile));
						m.setGraphicTextGap(5);
						m.setDisable(false);
						grid.add(m, j, i);
					    t1 = true;
					    casillasParaGanar++;
					} 
					
				}
			
			}
			
			
			board.setCenter(grid);
		}
		}else {
			Alert gameOver = new Alert(AlertType.INFORMATION);
			gameOver.setTitle("Alto Ahi!");
			gameOver.setHeaderText("¡No puedes jugar sin un tablero!");
			gameOver.setContentText(
					"Por favor cree el tablero, para poder jugar");
			gameOver.showAndWait();
		}
		}catch(NullPointerException e1) {
			Alert gameOver = new Alert(AlertType.INFORMATION);
			gameOver.setTitle("Alto Ahi!");
			gameOver.setHeaderText("¡Excepcion Encontrada!");
			gameOver.setContentText(
					"Por cree el tablero, para poder jugar");
			gameOver.showAndWait();
		}
	}
	
	public void reiniciar(ActionEvent e) {
		casillasParaGanar = 0;
		try {
		if(busca.getNivel() == 1) {
			grid.getChildren().clear();
		}else if(busca.getNivel() == 2) {
			grid.getChildren().clear();
		}else if(busca.getNivel() == 3) {
			grid.getChildren().clear();
		}
		}catch(NullPointerException e1) {
			Alert gameOver = new Alert(AlertType.INFORMATION);
			gameOver.setTitle("Alto Ahi!");
			gameOver.setHeaderText("¡Excepcion Encontrada!");
			gameOver.setContentText(
					"Por cree el tablero, para poder jugar");
			gameOver.showAndWait();
		}
	}
	
	/**
	 * @return the grid
	 */
	public GridPane getGrid() {
		return grid;
	}


	/**
	 * @param grid the grid to set
	 */
	public void setGrid(GridPane grid) {
		this.grid = grid;
	}


	private Node getNodeFromGridPane(GridPane grid1, final int col,final int row) {
		 ObservableList<Node> childrens = grid1.getChildren();
	    for (Node nodes : childrens) {
	        if (GridPane.getColumnIndex(nodes) == col && GridPane.getRowIndex(nodes) == row) {
	            return nodes;  
	        }
	    }
	    return null;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
}
