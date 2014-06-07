package gui;
import input_generator.InputGenerator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

import dataStructures.Edge;

import prim.MST_PRIM_adjList;
import prim.MST_PRIM_adjMax;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import kruskal.MST_Kruskal_adjList;
import kruskal.MST_Kruskal_adjMax;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.Label;


public class GUI extends JFrame {

	private JPanel contentPane;
	final JComboBox comboBox = new JComboBox();


	/**
	 * Create the frame.
	 */
	static String filePath="";
	static ArrayList<Edge> currentPath;
	public GUI(String filePath1) {
		filePath=filePath1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMstAssignmnet = new JLabel("MST Assignmnet");
		lblMstAssignmnet.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 27));
		lblMstAssignmnet.setBounds(174, 13, 258, 58);
		contentPane.add(lblMstAssignmnet);
		
		JButton btnKruskalAdjacecyList = new JButton("Kruskal Adjacecy List");
		btnKruskalAdjacecyList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String s=(String) comboBox.getSelectedItem();
					MST_Kruskal_adjList.adjListIp(filePath+s+".txt");
					
						
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MST_Kruskal_adjList.kruskal();
				MST_Kruskal_adjList.adjListOp(filePath);
				currentPath=MST_Kruskal_adjList.getPath();
				
			}	
		});
		btnKruskalAdjacecyList.setBounds(12, 132, 166, 50);
		contentPane.add(btnKruskalAdjacecyList);
		
		JButton btnKruskalAdjacecyMax = new JButton("Kruskal Adjacecy Max");
		btnKruskalAdjacecyMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String s=(String) comboBox.getSelectedItem();
					MST_Kruskal_adjMax.adjMaxIp(filePath+s+".txt");
					
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MST_Kruskal_adjMax.kruskal();
				MST_Kruskal_adjMax.oP(filePath);
				currentPath=MST_Kruskal_adjMax.getPath();
			
			}
		});
		btnKruskalAdjacecyMax.setBounds(12, 270, 166, 50);
		contentPane.add(btnKruskalAdjacecyMax);
		
		JButton btnPrimAdjacecyList = new JButton("Prim Adjacecy List");
		btnPrimAdjacecyList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String s=(String) comboBox.getSelectedItem();
					MST_PRIM_adjList.adjListIp(filePath+s+".txt");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MST_PRIM_adjList.primsList();
				MST_PRIM_adjList.adjListOp(filePath);
				currentPath=MST_PRIM_adjList.getPath();

			}
		});
		btnPrimAdjacecyList.setBounds(399, 132, 166, 50);
		contentPane.add(btnPrimAdjacecyList);
		
		JButton btnPrimAdjacecyMax = new JButton("Prim Adjacecy Max");
		btnPrimAdjacecyMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				try {
					String s=(String) comboBox.getSelectedItem();
					MST_PRIM_adjMax.adjMaxIp(filePath+s+".txt");
						
				
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MST_PRIM_adjMax.primAdjMax();
				MST_PRIM_adjMax.oP(filePath);
				currentPath=MST_PRIM_adjMax.getPath();
				
				
			}
		});
		btnPrimAdjacecyMax.setBounds(399, 270, 166, 50);
		contentPane.add(btnPrimAdjacecyMax);
		
		JButton btnShowOutput = new JButton("Show OutPut Tree");
		btnShowOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								GraphOutPut frame = new GraphOutPut(currentPath);
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				
				
			}
		});
		btnShowOutput.setBounds(206, 361, 166, 50);
		contentPane.add(btnShowOutput);
		
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Dense", "Sparse", "UserDefined"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(103, 82, 153, 22);
		
		contentPane.add(comboBox);
		
		Label label = new Label("Input Type");
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		label.setBounds(10, 80, 87, 24);
		contentPane.add(label);
		
		JButton btnGenerateFile = new JButton("Generate File");
		btnGenerateFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=(String) comboBox.getSelectedItem();
				if (s.equals("Dense")){
					InputGenerator.generateDenseGraph(filePath+s+".txt");
				}else if(s.equals("Sparse")){
					try {
						InputGenerator.generateSparseGraph(filePath+s+".txt");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnGenerateFile.setBounds(286, 76, 109, 35);
		contentPane.add(btnGenerateFile);
	}
}
