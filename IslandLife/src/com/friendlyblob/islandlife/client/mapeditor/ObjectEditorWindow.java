package com.friendlyblob.islandlife.client.mapeditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JButton;

import sun.org.mozilla.javascript.internal.ast.ForInLoop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;
import com.friendlyblob.islandlife.client.MyGame;
import com.friendlyblob.islandlife.client.gameworld.Map;
import com.friendlyblob.islandlife.client.helpers.Assets;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JSeparator;

public class ObjectEditorWindow extends JFrame implements WindowListener, ActionListener, MouseListener {
	
	public boolean enabled = false;

	public JToggleButton setCollision = null;
	public JToggleButton setCenterPoint = null;
	
	private JButton open = null;
	private JButton save = null;
	
	public JTable table = null;
	public ObjectEditorPanel oep = null;
	
    private String[] columnNames = {"ID",
            "Title",
            "Collision",
            "TextureWidth",
            "TextureHeight",
            "CenterPoint"};
    
	
	public Object[][] data = {};
	
	/**
	 * Create the frame.
	 */
	public ObjectEditorWindow() {
		setResizable(false);

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
		getContentPane().setPreferredSize(new Dimension(800, 550));
		getContentPane().setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 800, 50);
		toolBar.setFloatable(false);
		getContentPane().add(toolBar);
		
		
		
		// init buttons
		open = new JButton(new ImageIcon("textures/gui/open.png"));
		toolBar.add(open);
		save = new JButton(new ImageIcon("textures/gui/save.png"));
		toolBar.add(save);
		
		setCollision = new JToggleButton("Set Collision");
		setCollision.setMargin(new Insets(8,8, 8, 8));
		toolBar.add(setCollision);
		
		setCenterPoint = new JToggleButton("Set Center Point");
		setCenterPoint.setMargin(new Insets(8,8, 8, 8));

		toolBar.add(setCenterPoint);
		
		oep = new ObjectEditorPanel(this);
		oep.setBounds(0, 50, 400, 500);
		getContentPane().add(oep);
		
		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(400, 500));
		table.setFillsViewportHeight(true);
		
		table.addMouseListener(this);
		
		
		//Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(400, 50, 400, 500);
		
		//Add the scroll pane to this panel.
		getContentPane().add(scrollPane);
		
		// listeners				
		addWindowListener(this);
		setCenterPoint.addActionListener(this);
		setCollision.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);
		
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

	private XmlReader.Element objects = null;
	private XmlReader.Element root = null;
	
	public XmlReader.Element getXmlRoot() {
		return root;
	}
	
	/**
	 * Button stuff
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == open) {
		      XmlReader xmlReader = new XmlReader();
		      root = null;
		      
				try {
					root = xmlReader.parse(Gdx.files.internal("data/objects.xml"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			  objects = root.getChildByName("objects");
			  
			  XmlReader.Element tmp = null;
			  int childCount = objects.getChildCount();
			  
			  data = new Object[childCount][];
			  for (int i = 0; i < childCount; i++) {
				tmp = objects.getChild(i);
				  
				data[i] = new Object[] { i, tmp.getAttribute("title"), tmp.getAttribute("collision"), tmp.getAttribute("width"), tmp.getAttribute("height"), tmp.getAttribute("centerpoint")};
			  }
			  
			  table.setModel(new DefaultTableModel(data, columnNames));
		} else if (source == save) {
			oep.save(objects.getChild(selectedObjectId));
		} else if (source == setCollision) {
			setCenterPoint.setSelected(false);
		} else if (source == setCenterPoint) {
			setCollision.setSelected(false);
		}
	}	
	
	/*
	 * Method for turning map editor on and off 
	 */
	public void toggle(){
		enabled = !enabled;
		setVisible(enabled);
	}

	public int selectedObjectId;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			if (table.getSelectedRow() != -1) {
				selectedObjectId = (int) table.getModel().getValueAt(table.getSelectedRow(), 0);
				oep.loadObject(objects.getChild(selectedObjectId));
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
