/**
 * Conway's Game of Life
 * 
 *
 * @version 1.00 
 * @author Michael Kalinin
 */ 
import java.awt.*;
import javax.swing.*;
 
public class GameOfLifeFrame extends JFrame { 
	
	private Universe universe;
	private ControlPanel controlPanel;
 
	public GameOfLifeFrame() {
	
		setTitle ( "Conway's Game of Life" );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize( 600, 400 );
		setMinimumSize( new Dimension( 600, 400 ) );		
			
		universe = new Universe();
		controlPanel = new ControlPanel( universe );
		
		JPanel mainPanel = new JPanel( new BorderLayout() );
		mainPanel.add( universe, BorderLayout.CENTER );
		mainPanel.add( controlPanel, BorderLayout.EAST );
		
		add( mainPanel ); 		
	}
  
	public static void main( String[] args ) {
		new GameOfLifeFrame().setVisible( true );			  
	}
}