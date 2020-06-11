package upo.battleship.rossi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MyGridPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel[][] griglia;

	public MyGridPanel(int dim) {
		this.setLayout(new GridLayout(dim, dim));
		griglia = new JLabel[dim][dim];
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		for(int i =0;i<dim;i++) {
			for(int j=0;j<dim;j++) {
				griglia[i][j] = new JLabel("");
				griglia[i][j].setPreferredSize(new Dimension(10, 10));
				//Setto l'orientamento orizzontale e verticale, in modo che la "X" risulti centrata
				griglia[i][j].setHorizontalAlignment(JLabel.CENTER);
				griglia[i][j].setVerticalAlignment(JLabel.CENTER);
				//i JLabel sono naturalmente trasparenti. 
				//Se volete che si veda il loro colore di sfondo, dovete renderli opachi
				griglia[i][j].setOpaque(true);
				//Setto il colore del label a bianco
				griglia[i][j].setBackground(Color.WHITE);
				//Aggiungo un bordo (creato prima del ciclo)
				griglia[i][j].setBorder(border);
				this.add(griglia[i][j]);
			}
		}
		this.setVisible(true);
	}
	
	public void aggiungiElemento(int x, int y) {
		griglia[x][y].setBackground(Color.LIGHT_GRAY);
	}
	
	public void colpisci(int x, int y) {
		griglia[x][y].setText("X");
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new BorderLayout());
		f.setSize(300, 300);
		MyGridPanel gp = new MyGridPanel(5);
		f.add(gp, BorderLayout.CENTER);
		JButton b = new JButton("Colpisci posizione [3,3]");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gp.colpisci(3, 3);
			}
		});
		f.add(b, BorderLayout.SOUTH);
		f.setVisible(true);
		gp.aggiungiElemento(2, 0);
		gp.aggiungiElemento(1, 0);
		gp.aggiungiElemento(0, 0);
		gp.aggiungiElemento(3, 2);
		gp.aggiungiElemento(3, 3);
		gp.aggiungiElemento(3, 4);

	}

}