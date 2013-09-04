package com.friendlyblob.islandlife.client.mapeditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JButton;

import sun.org.mozilla.javascript.internal.ast.ForInLoop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;
import com.friendlyblob.islandlife.client.gameworld.Map;

public class MapEditorWindow extends JFrame implements WindowListener, ActionListener {

	private JButton open;
	private JButton save;
	
	/**
	 * Create the frame.
	 */
	public MapEditorWindow() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		TilePanel tp = new TilePanel();
		
		getContentPane().setLayout(null);
		getContentPane().setPreferredSize(tp.getPreferredSize());
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(0, 0, 500, 50);
		getContentPane().add(toolBar);
		
		open = new JButton(new ImageIcon("textures/gui/open.png"));
		toolBar.add(open);
		
		save = new JButton(new ImageIcon("textures/gui/save.png"));
		toolBar.add(save);
		
		open.addActionListener(this);
		save.addActionListener(this);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(0, 50, 500, 500);
		getContentPane().add(tabbedPane);
		
		tabbedPane.addTab("Tiles", null, tp, null);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Objects", null, panel, null);
		
		this.addWindowListener(this);
		
		setVisible(true);
		pack();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		MapEditor.toggle();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Button stuff
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		int[][] tempMap = null;
		if (source == open) {
		      XmlReader xmlReader = new XmlReader();
		      FileHandle file = Gdx.files.internal("data/untitled.xml");
		      XmlReader.Element root = null;
		      
				try {
					root = xmlReader.parse(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			  XmlReader.Element map = root.getChildByName("map");
			  int width = Integer.parseInt(map.getAttribute("width"));
			  int height = Integer.parseInt(map.getAttribute("height"));
			  int tileWidth = Integer.parseInt(map.getAttribute("tilewidth"));
			  int tileHeight = Integer.parseInt(map.getAttribute("tileheight"));

			  
			  if (width > 0 && height > 0) {
				  tempMap = new int[width][height];
			  }
			
		      XmlReader.Element layer = root.getChildByName("layer");
		      XmlReader.Element data = layer.getChild(0);
		      
		      for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					tempMap[i][j] = Integer.parseInt(data.getChild(i*height+j).getAttribute("gid"));
				}
			  }
		      
		      Map.load(tempMap);
		      
		} else if (source == save) {
			tempMap = Map.getTiles();
			
			System.out.println(tempMap.length);
			System.out.println(tempMap[0].length);
			
			StringWriter sw = new StringWriter();
			 XmlWriter xml = new XmlWriter(sw);
			 try {
				xml.element("xml")
				        .attribute("version", "1.0")
				        .attribute("encoding", "UTF-8")
				        .element("map")
				                .attribute("version", "1.0")
				                .attribute("width", tempMap.length)
				                .attribute("height", tempMap[0].length)
				                .attribute("tilewidth", "96")
				                .attribute("tileheight", "48")
		                .pop()
		                .element("tileset")
		                		.element("image")
		                			.attribute("source", "textures/normal.png")
		                			.attribute("width", "512")
		                			.attribute("height", "512")
	                			.pop()
            			.pop()
            			.element("layer")
            					.element("data");
				
				for (int i = 0; i < tempMap.length; i++) {
					for (int j = 0; j < tempMap[i].length; j++) {
						xml.element("tile")
							.attribute("gid", tempMap[i][j])
							.pop();
					}
				}
				
				xml.pop().pop().pop();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
			 FileOutputStream out = null;
			try {
				out = new FileOutputStream("data/untitled.xml");
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			 try {
				out.write(sw.toString().getBytes());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 try {
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
		}
		
		
		System.out.println("click");
	}	
}
