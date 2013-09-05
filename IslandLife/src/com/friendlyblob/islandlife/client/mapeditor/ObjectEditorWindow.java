package com.friendlyblob.islandlife.client.mapeditor;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import sun.org.mozilla.javascript.internal.ast.ForInLoop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.XmlReader;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class ObjectEditorWindow extends JFrame implements WindowListener, ActionListener, MouseListener {
	
	public boolean enabled = false;

	public JToggleButton setCollision = null;
	public JToggleButton setCenterPoint = null;
	
	private JButton open = null;
	private JButton save = null;
	private JButton texture = null;
	
	public JTable table = null;
	public ObjectEditorPanel oep = null;
	
    private String[] columnNames = {"id",
            "title",
            "collision",
            "width",
            "height",
            "centerpoint",
            "texture"};
    
	
	public Object[][] data = {};

	private JButton addRow;

	private JButton removeRow;
	
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
		
		texture = new JButton("Set texture");
		texture.setMargin(new Insets(8,8, 8, 8));
		toolBar.add(texture);
		
		oep = new ObjectEditorPanel(this);
		oep.setBounds(0, 50, 400, 500);
		getContentPane().add(oep);
		
		JPanel panel = new JPanel();
		panel.setBounds(400, 50, 400, 500);
		getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		panel.add(toolBar_1);
		
		
		addRow = new JButton(new ImageIcon("textures/gui/add.png"));
		toolBar_1.add(addRow);
		removeRow = new JButton(new ImageIcon("textures/gui/remove.png"));
		toolBar_1.add(removeRow);
		
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.setDataVector(data, columnNames);
		table = new JTable(dtm);
		table.setPreferredScrollableViewportSize(new Dimension(400, 400));
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		
		//Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		table.addMouseListener(this);
		
		// listeners				
		addWindowListener(this);
		setCenterPoint.addActionListener(this);
		setCollision.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);
		texture.addActionListener(this);
		addRow.addActionListener(this);
		removeRow.addActionListener(this);
		
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
					e1.printStackTrace();
				}
				
			  objects = root.getChildByName("objects");
			  
			  XmlReader.Element tmp = null;
			  int childCount = objects.getChildCount();
			  
			  data = new Object[childCount][];
			  for (int i = 0; i < childCount; i++) {
				tmp = objects.getChild(i);
				  
				data[i] = new Object[] { i, tmp.getAttribute("title"), tmp.getAttribute("collision"), tmp.getAttribute("width"), tmp.getAttribute("height"), tmp.getAttribute("centerpoint"), tmp.getAttribute("texture")};
			  }
			  
			  table.setModel(new DefaultTableModel(data, columnNames));
		} else if (source == save) {
			TableModel tm =  table.getModel();
			XmlReader.Element selectedElement = objects.getChild(selectedObjectId);
			for (int i = 0; i < tm.getColumnCount(); i++) {
				selectedElement.setAttribute(columnNames[i], tm.getValueAt(selectedObjectId, i).toString());
			}
			
			PrintWriter out;
			try {
				out = new PrintWriter("data/objects.xml");
				out.write(root.toString());
				out.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (source == setCollision) {
			setCenterPoint.setSelected(false);
		} else if (source == setCenterPoint) {
			setCollision.setSelected(false);
		} else if (source == texture) {
		    JFileChooser chooser = new JFileChooser();
		    chooser.setCurrentDirectory(new File("./textures/objects"));
		    int returnVal = chooser.showOpenDialog(this);
		    if (returnVal == JFileChooser.APPROVE_OPTION) {
		    	String[] parts = chooser.getSelectedFile().getAbsolutePath().split("textures");
		    	
		    	String imgPath = ".\\textures" + parts[1];
		    	table.getModel().setValueAt(imgPath, selectedObjectId, 6);
		    }
		} else if (source == addRow) {
			((DefaultTableModel) table.getModel()).addRow(new Object[] { "", "", "", "", "", ""});
		} else if (source == removeRow) {
			int[] selectedRows = table.getSelectedRows();
			Arrays.sort(selectedRows);
			if (selectedRows.length > 0) {
				for (int i = selectedRows.length - 1; i >= 0 ; i--) {
					((DefaultTableModel) table.getModel()).removeRow(selectedRows[i]);
				}
			}
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
				String id = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
				if (id != "") {
					selectedObjectId = Integer.parseInt(id);
					oep.loadObject(objects.getChild(selectedObjectId));
				}
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
