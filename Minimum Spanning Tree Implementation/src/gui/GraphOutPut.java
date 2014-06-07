package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

import dataStructures.Edge;


public class GraphOutPut extends JFrame {

	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
	private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

	private JGraphModelAdapter m_jgAdapter;

	
	/**
	 * Create the frame.
	 */
	//for Printing
	
	public GraphOutPut(ArrayList<Edge> pathOut) {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setVisible(true);
		

		// create a JGraphT graph
				ListenableGraph g = new ListenableDirectedGraph(DefaultEdge.class);

				// create a visualization using JGraph, via an adapter
				m_jgAdapter = new JGraphModelAdapter(g);

				JGraph jgraph = new JGraph(m_jgAdapter);


				getContentPane().add(jgraph);
				contentPane.setSize(1200, 1200);
				int x=10;
				int y=10;
				boolean flag=false;
				for (int i = 1; i <= pathOut.size()+1; i++) {
					g.addVertex(i);
					positionVertexAt(i, x, y);
					x+=100;
					if (!flag){
						flag=true;
						y+=200;
					}else{
						flag=false;
						y-=200;
					}
						
					if (x==800){
						x=10;
						y+=1000;
					}
				}
				
				
				for (int i = 0; i < pathOut.size(); i++) {
					int from=pathOut.get(i).from;
					int to=pathOut.get(i).to;
					double w=pathOut.get(i).w;
					
					g.addEdge(from, to,w);
				}

		
		
	}
	
	
	
	private void positionVertexAt(Object vertex, int x, int y) {
		DefaultGraphCell cell = m_jgAdapter.getVertexCell(vertex);
		Map attr = cell.getAttributes();
		Rectangle2D b = GraphConstants.getBounds(attr);

		GraphConstants.setBounds(attr, new Rectangle(x, y, (int) b.getWidth(),
				(int) b.getHeight()));

		Map cellAttr = new HashMap();
		cellAttr.put(cell, attr);
		m_jgAdapter.edit(cellAttr, null, null, null);
	}
	
	
	

}
