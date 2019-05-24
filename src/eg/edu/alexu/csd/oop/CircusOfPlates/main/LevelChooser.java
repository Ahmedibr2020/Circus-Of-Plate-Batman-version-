package eg.edu.alexu.csd.oop.CircusOfPlates.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import eg.edu.alexu.csd.oop.CircusOfPlates.world.Circus;
import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;
import javafx.scene.Cursor;

public class LevelChooser extends JFrame implements ActionListener{
	
	private JLabel label;
	private JPanel panel;
	private JMenuBar menu;
	private JMenu levelMenu;
	private Circus circus;
	private ClassFinder c;
	public LevelChooser(){
		
		label = new JLabel("   Batman Circus ©");
		
		
		panel = new JPanel();
		c = ClassFinder.getInstance();
		menu = new JMenuBar();
		levelMenu = new JMenu("Level of the game");
		
		for(int i = 0 ; i < c.find().size() ; i++){
			levelMenu.add(new JMenuItem(c.find().get(i).getSimpleName()));
			levelMenu.getItem(i).addActionListener(this);
		}
		levelMenu.addActionListener(this);
		menu.add(levelMenu);
		panel.add(menu);
		this.add(panel);
		this.add(label);
		this.setLayout(new FlowLayout(FlowLayout.LEFT,180,200));
		this.setLocation(700, 250);
		panel.setBackground(Color.BLACK);
		label.setForeground(Color.BLUE);
	}
	private int i = 0;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		for(i = 0 ; i < levelMenu.getItemCount() ; i++){
			if(e.getSource().equals(levelMenu.getItem(i))){
				dispose();
				final JMenuBar  menuBar = new JMenuBar();;
				JMenu menu = new JMenu("Game Options");
				JMenuItem pauseMenuItem = new JMenuItem("Pause Game");
				JMenuItem resumeMenuItem = new JMenuItem("Resume Game");
				menu.addSeparator();
				menu.add(pauseMenuItem);
				menu.add(resumeMenuItem);
				menu.addSeparator();
				menuBar.add(menu);
				panel.add(menuBar);
				this.circus = new Circus(1355, 720, i);
				final GameController gameController = GameEngine.start("Batman Circus ", this.circus, menuBar, Color.GRAY);
				pauseMenuItem.addActionListener(new ActionListener() {
					@Override 
					public void actionPerformed(ActionEvent e) {
							gameController.pause();
						}
				});
				resumeMenuItem.addActionListener(new ActionListener() {
					@Override 
					public void actionPerformed(ActionEvent e) {
						gameController.resume();
					}
				});
			}
		}
		
	}
	
	public JMenuBar menuBar(){
		return this.menu;
	}
	
	
}