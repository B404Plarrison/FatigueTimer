import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import com.sun.glass.events.MouseEvent;
public class FatiguePanel extends JPanel{
	private JLabel watch, condition,healthlost, attributelost;
	private JTextField input;
	private JRadioButton stop,start;
	private JCheckBox candie, canlose, canout, canweak, cantire;
	boolean die, lose, out, weak, tire;
	public boolean in=true;
	public FatigueTimeTester team;
	public FatiguePanel() {
		String multy=JOptionPane.showInputDialog("Death Fatigue Multiplier?");
		double multi=Double.parseDouble(multy);
		team=new FatigueTimeTester();
		candie= new JCheckBox("Can't Die From Exhaustion");
		candie.addActionListener(new TextListener());
		die=true;
		canlose= new JCheckBox("Can't Lose Health from Fatigue");
		canlose.addActionListener(new TextListener());
		lose=true;
		canout= new JCheckBox("Can't Pass Out");
		canout.addActionListener(new TextListener());
		out=true;
		canweak= new JCheckBox("Can't Weaken due to Tiredness");
		canweak.addActionListener(new TextListener());
		weak=true;
		cantire= new JCheckBox("Can't detect Fatigue");
		cantire.addActionListener(new TextListener());
		tire=true;
		stop=new JRadioButton("Stop");
		start=new JRadioButton("Start");
		watch=new JLabel("0.0");
		condition=new JLabel("fine");
		healthlost=new JLabel("0");
		attributelost=new JLabel("0");
		Font big=new Font("SanSerif", Font.BOLD, 36);
		watch.setFont(big);
		final ScheduledExecutorService executorService=Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleAtFixedRate(new Runnable() {
			public void run(){
				if(in & team.active) {
				watch.setText(Double.toString(team.getTotal()));
				if((team.getTotal()>10*multi)&die) {
					condition.setText("Dead");
					in=false;
				}
				else if ((team.getTotal()>8*multi)&lose) {;
					healthlost.setText("HP-"+ Double.toString(team.getTotal()/team.mult));
					condition.setText("Losing Health");
				}
				else if ((team.getTotal()>5*multi)&out) {
					condition.setText("Passed Out");
				}
				else if ((team.getTotal()>3*multi)&weak) {
					attributelost.setText("Att-"+ Double.toString(.2*(team.getTotal())/team.mult));
					condition.setText("Weakened");
				}
				else if ((team.getTotal()>1*multi)&tire) {
					condition.setText("Tired");
				}
				else {
					condition.setText("Still Fine");
				}
				
				}	
				else {
					team.heel();
				}
			}	
		}, 0, 1, TimeUnit.SECONDS);
		ButtonGroup couple=new ButtonGroup();
		couple.add(start);
		couple.add(stop);
		input=new JTextField(7);
		input.setFont(big);
		input.addActionListener(new TextListener());
		start.addActionListener(new TextListener());
		stop.addActionListener(new TextListener());
		add(watch);
		add(input);
		add(condition);
		add(healthlost);
		add(attributelost);
		add(start);
		add(stop);
		add(candie);
		add(canlose);
		add(canout);
		add(canweak);
		add(cantire);
		String colorize=JOptionPane.showInputDialog("Color?");
		int comma=colorize.indexOf(",");
		String partuno=colorize.substring(0,comma);
		String remuno=colorize.substring(comma+1,colorize.length());
		int red=Integer.parseInt(partuno);
		comma=remuno.indexOf(",");
		String partdos=remuno.substring(0,comma);
		String remdos=remuno.substring(comma+1,remuno.length());
		int green=Integer.parseInt(partdos);
		String parttres=remdos;
		int blue=Integer.parseInt(parttres);
		Color col=new Color(red, green, blue);
		try {
		Color colcom=new Color((2*red/3), (2*green/3), (2*blue/3));
		Color colant=new Color(255-red, 255-green, 255-blue);
		Color colantplus=new Color(155-red, 155-green, 155-blue);
		
		watch.setForeground(colant);
		input.setBackground(col);
		input.setForeground(colantplus);
		condition.setForeground(colant);
		healthlost.setForeground(colant);
		attributelost.setForeground(colant);
		start.setBackground(col);
		start.setForeground(colantplus);
		stop.setBackground(col);
		stop.setForeground(colantplus);
		candie.setForeground(colantplus);
		canlose.setForeground(colantplus);
		canout.setForeground(colantplus);
		canweak.setForeground(colantplus);
		cantire.setForeground(colantplus);
		candie.setBackground(col);
		canlose.setBackground(col);
		canout.setBackground(col);
		canweak.setBackground(col);
		cantire.setBackground(col);
		setBackground(colcom);
		setPreferredSize(new Dimension(1350,90));
		} catch (IllegalArgumentException e) {
			if (red<0) {
				red=0;
			}
			if (red>255) {
				red=255;
			}
			if (blue<0) {
				blue=0;
			}
			if (blue>255) {
				blue=255;
			}
			if (green<0) {
				green=0;
			}
			if (green>255) {
				green=255;
			}
		}
	}
	private class TextListener implements ActionListener{
		public void actionPerformed(ActionEvent field) { 
			if(field.getSource()==input) {
			String value=input.getText();
			double mul=Double.parseDouble(value);
			team.addMult(mul);
			}
			if(field.getSource()==start) {
			team.activate();
			start.setText("Started");
			stop.setText("Stop");
			}
			if(field.getSource()==stop){
			team.deactivate();
			stop.setText("Stopped");
			start.setText("Start");
			}
			if (candie.isSelected()) {
				die=false;
			}
			if (canlose.isSelected()) {
				lose=false;
			}
			if (canout.isSelected()) {
				out=false;
			}
			if (canweak.isSelected()) {
				weak=false;
			}
			if (cantire.isSelected()) {
				tire=false;
			}
	}
}
}
