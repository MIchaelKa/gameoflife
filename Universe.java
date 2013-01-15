/**
 * The universe of the Game of Life
 *
 * @version 1.00 
 * @author Michael Kalinin
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Universe extends JComponent {	
	/**
	 * Array of cells
	 */
	private int [][] cells;		
	/**
	 * Logical size
	 */
	private int universeSize = 50;
	/**
	 * Logical coordinate highlight cell
	 */
	private int logicCoordX = -1;
	private int logicCoordY = -1;
	/**
	 * Colors
	 */
	private final static Color gridColor = new Color ( 165, 165, 165 );		
	private final static Color highlightColor = new Color ( 244, 223, 34 );
	
	public Universe () {
		cells = new int [universeSize][universeSize];
		for ( int[] row : cells )
			for ( int c : row )
				c = 0;
		addMouseListener( new MouseHandler() );
		addMouseMotionListener ( new MouseMotionHandler() );
	}
	
	@Override
 	public void paintComponent ( Graphics g ) {
	
		Graphics2D g2D = ( Graphics2D ) g;
		
		g2D.setRenderingHint( RenderingHints.KEY_ANTIALIASING,
							  RenderingHints.VALUE_ANTIALIAS_ON );

		//Paint grid
		g2D.setColor( gridColor );
		int cellSize = ( int ) Math.min ( getWidth(), getHeight() ) / universeSize;
		
		for ( int i = 0 ; i <= universeSize ; i++ ) 
			g2D.drawLine( (int)(i * cellSize), 0, (int)(i * cellSize), (int)(cellSize * universeSize) );
			
		for ( int i = 0 ; i <= universeSize ; i++ )
			g2D.drawLine( 0, (int)(i * cellSize), (int)(cellSize * universeSize), (int)(i * cellSize) );
			
		//Cell highlight		
		if ( logicCoordX >= 0 && logicCoordX < universeSize 
							  && logicCoordY < universeSize ) {
			g2D.setColor( highlightColor );
			g2D.fillRect( (int)(logicCoordX * cellSize) + 1, (int)(logicCoordY * cellSize) + 1, 
														  (int)cellSize - 1, (int)cellSize - 1 );			
		}
		
		//Paint alive cells
		for ( int i = 0 ; i < universeSize ; i++ )
			for ( int j = 0 ; j < universeSize ; j++ ) {
				if ( cells[i][j] == 1 ) {
					g2D.setColor( Color.BLACK );
					g2D.fillRect( (int)(j * cellSize) + 2, (int)(i * cellSize) + 2, 
											  (int)cellSize - 3, (int)cellSize - 3 );
				}
			}		
	}
	
  	private class MouseMotionHandler extends MouseMotionAdapter {
		@Override
		public void mouseMoved ( MouseEvent event ) {
		
			int cellSize = ( int ) Math.min ( getWidth(), getHeight() ) / universeSize;
			logicCoordX = (int)(event.getX() / cellSize);
			logicCoordY = (int)(event.getY() / cellSize);
			repaint();			
		}
	}
	
	private class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed( MouseEvent event ) {
			int cellSize = ( int ) Math.min ( getWidth(), getHeight() ) / universeSize;
			logicCoordX = (int)(event.getX() / cellSize);
			logicCoordY = (int)(event.getY() / cellSize);
			if ( cells[logicCoordY][logicCoordX] == 0 ) cells[logicCoordY][logicCoordX] = 1;
			else cells[logicCoordY][logicCoordX] = 0;
			repaint();            
        }        
    }	

	public void showNextGeneration () {
	
		int [][] nextGeneration = new int [universeSize][universeSize];
		
		for ( int i = 0 ; i < universeSize ; i++ ) {
		
			for ( int j = 0 ; j < universeSize ; j++ ) {
			
				int numLiveNeighbours = cells[checkIndex(i-1)][checkIndex(j-1)] + //NW
										cells[checkIndex(i-1)][checkIndex( j )] + //N
										cells[checkIndex(i-1)][checkIndex(j+1)] + //NE
										cells[checkIndex( i )][checkIndex(j-1)] + //W
										cells[checkIndex( i )][checkIndex(j+1)] + //E
										cells[checkIndex(i+1)][checkIndex(j-1)] + //SW
										cells[checkIndex(i+1)][checkIndex( j )] + //S
										cells[checkIndex(i+1)][checkIndex(j+1)];  //SE
				
				if ( numLiveNeighbours==3 || ( numLiveNeighbours==2 && cells[i][j]==1 ) ) 
				nextGeneration[i][j] = 1;	
				else nextGeneration[i][j] = 0;
							
			}
		}
		cells = nextGeneration;
		repaint();
	}
	
	private int checkIndex ( int index ) {
		if ( index < 0 ) return universeSize-1;
		else if ( index > universeSize-1 ) return 0;
		else return index;
	}
	
	public void clear () {
		for ( int i = 0 ; i < universeSize ; i++ )
			for ( int j = 0 ; j < universeSize ; j++ )
				cells[i][j] = 0;
		repaint();
	}
}	