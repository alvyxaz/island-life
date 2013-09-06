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
import java.awt.Panel;
import java.awt.List;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class MapEditorWindow extends JFrame implements WindowListener, ActionListener {

	private JButton open;
	private JButton save;
	
	private Object[] zones = {};
	private JButton loadZones;
	private JButton addNewZone;
	private JButton removeZones;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	
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
		
		JPanel zonesPanel = new JPanel();
		tabbedPane.addTab("Zones", null, zonesPanel, null);
		zonesPanel.setLayout(null);
		
				
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_2.setBounds(0, 0, 100, 300);
		zonesPanel.add(panel_2);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		panel_2.add(toolBar_1);
		
		loadZones = new JButton(new ImageIcon("textures/gui/load.png"));
		loadZones.setToolTipText("Load zones");
		toolBar_1.add(loadZones);
		
		addNewZone = new JButton(new ImageIcon("textures/gui/add.png"));
		addNewZone.setToolTipText("Add new zone");
		toolBar_1.add(addNewZone);
		
		removeZones = new JButton(new ImageIcon("textures/gui/remove.png"));
		removeZones.setToolTipText("Remove selected zone(s)");
		toolBar_1.add(removeZones);
		
		JList list = new JList(zones);
		list.setPreferredSize(new Dimension(100, 250));
		panel_2.add(list);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Zone", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setSize(371, 300);
		panel_1.setLocation(100, 0);
		zonesPanel.add(panel_1);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("id");
		panel_1.add(lblNewLabel, "2, 2, right, default");
		
		textField = new JTextField();
		panel_1.add(textField, "4, 2, left, default");
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("title");
		panel_1.add(lblNewLabel_1, "2, 4, right, default");
		
		textField_1 = new JTextField();
		panel_1.add(textField_1, "4, 4, left, default");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("regionsX");
		panel_1.add(lblNewLabel_2, "2, 6, right, default");
		
		textField_2 = new JTextField();
		panel_1.add(textField_2, "4, 6, left, default");
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("regionsY");
		panel_1.add(lblNewLabel_3, "2, 8, right, default");
		
		textField_3 = new JTextField();
		panel_1.add(textField_3, "4, 8, left, default");
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("regionWidth");
		panel_1.add(lblNewLabel_4, "2, 10, right, default");
		
		textField_4 = new JTextField();
		panel_1.add(textField_4, "4, 10, left, default");
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("regionHeight");
		panel_1.add(lblNewLabel_5, "2, 12, right, default");
		
		textField_5 = new JTextField();
		panel_1.add(textField_5, "4, 12, left, default");
		textField_5.setColumns(10);
		
		this.addWindowListener(this);
		loadZones.addActionListener(this);
		addNewZone.addActionListener(this);
		removeZones.addActionListener(this);
		
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
			 
		} else if (source == loadZones) {
			
		} else if (source == addNewZone) {
			
		} else if (source == removeZones) {
			
		}
		
		
		System.out.println("click");
	}	
}
