/**
 * Control panel
 *
 * @version 1.00 
 * @author Michael Kalinin
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ControlPanel extends JPanel { 
	
	private Universe universe;
	private Timer timer;	
	
	public ControlPanel( Universe universe ) {
	
		this.universe = universe;		
		setLayout( new GridBagLayout() );		
				
		JButton startButton = new JButton( "Start" );
		startButton.addActionListener( new StartButtonAction() );
		GridBagConstraints startButtonConstraints = new GridBagConstraints();
		startButtonConstraints.gridx = 0;
		startButtonConstraints.gridy = 0;
		startButtonConstraints.gridwidth = 1;
		startButtonConstraints.gridheight = 1;
		startButtonConstraints.weightx = 0;
		startButtonConstraints.weighty = 0;		
		startButtonConstraints.anchor = GridBagConstraints.NORTH;
		startButtonConstraints.insets = new Insets( 10, 2, 2, 2 );
		add( startButton, startButtonConstraints );
		
		JButton pauseButton = new JButton( "Pause" );
		pauseButton.addActionListener( new PauseButtonAction() );
		GridBagConstraints pauseButtonConstraints = new GridBagConstraints();
		pauseButtonConstraints.gridx = 1;
		pauseButtonConstraints.gridy = 0;
		pauseButtonConstraints.gridwidth = 1;
		pauseButtonConstraints.gridheight = 1;
		pauseButtonConstraints.weightx = 0;
		pauseButtonConstraints.weighty = 0;		
		pauseButtonConstraints.anchor = GridBagConstraints.NORTH;
		pauseButtonConstraints.insets = new Insets( 10, 2, 2, 2 );
		add( pauseButton, pauseButtonConstraints );
		
		JButton clearButton = new JButton( "Clear" );
		clearButton.addActionListener( new ClearButtonAction() );
		GridBagConstraints clearButtonConstraints = new GridBagConstraints();
		clearButtonConstraints.gridx = 2;
		clearButtonConstraints.gridy = 0;
		clearButtonConstraints.gridwidth = GridBagConstraints.REMAINDER;
		clearButtonConstraints.gridheight = 1;
		clearButtonConstraints.weightx = 0;
		clearButtonConstraints.weighty = 0;		
		clearButtonConstraints.anchor = GridBagConstraints.NORTH;
		clearButtonConstraints.insets = new Insets( 10, 2, 2, 2 );
		add( clearButton, clearButtonConstraints );
		
		int initialDelay = 100;
		JSlider slider = new JSlider ( 0, 20, (int)1000/initialDelay );
		timer = new Timer( initialDelay, new ShowNextGenerationAction() );
		
		slider.setPaintTicks( true );
		slider.setPaintLabels( true );
		slider.setMajorTickSpacing( 5 );
		slider.setMinorTickSpacing( 1 );
		
		GridBagConstraints sliderConstraints = new GridBagConstraints();
		sliderConstraints.gridx = 0;
		sliderConstraints.gridy = 1;
		sliderConstraints.gridwidth = 3;
		sliderConstraints.gridheight = 1;
		sliderConstraints.weightx = 0;
		sliderConstraints.weighty = 100;
		sliderConstraints.anchor = GridBagConstraints.NORTH;
		sliderConstraints.insets = new Insets( 10, 5, 5, 5 );		
		add( slider, sliderConstraints );		
		slider.addChangeListener( new SpeedChangeAction() );
	}
		
	private class ShowNextGenerationAction implements ActionListener {
		@Override
		public void actionPerformed( ActionEvent event ) {
			universe.showNextGeneration();			
		}
	}
	
	private class StartButtonAction implements ActionListener {
		@Override
		public void actionPerformed( ActionEvent event ) {		
			timer.start();		
		}
	}
	
	private class PauseButtonAction implements ActionListener {
		@Override
		public void actionPerformed( ActionEvent event ) {		
			timer.stop();		
		}
	}
	
	private class ClearButtonAction implements ActionListener {
		@Override
		public void actionPerformed( ActionEvent event ) {		
			timer.stop();
			universe.clear();			
		}
	}
	
	private class SpeedChangeAction implements ChangeListener {
		@Override
		public void stateChanged( ChangeEvent event ) {		
			JSlider slider = (JSlider) event.getSource();			
			timer.setDelay( (int)1000/slider.getValue() );  
		}
	}
}

